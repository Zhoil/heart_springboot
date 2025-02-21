package com.example.mind.Service;

import com.example.mind.DTO.CreateTaskDTO.CreateTestTaskRequest;
import com.example.mind.DTO.TestTaksDTO.AnswerHistory.*;
import com.example.mind.DTO.TestTaksDTO.TaskPendingDTO;
import com.example.mind.DTO.TestTaksDTO.TaskPendingResponse;
import com.example.mind.DTO.TestTaksDTO.TestTaskHistory;
import com.example.mind.DTO.TestTaksDTO.TestTaskHistoryResponse;
import com.example.mind.Entries.*;
import com.example.mind.Excption.ResourceNotFoundException;
import com.example.mind.enumtion.TaskStatus;
import com.example.mind.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestTasksService {

    @Autowired
    private TestTasksRepository testTasksRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScaleRepository scaleRepository;

    @Autowired
    private TestedUserRepository testedUserRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private TestAnswersRepository testAnswersRepository;

    // 通过测试者id获取任务历史
    public TestTaskHistoryResponse getTaskHistoryByTesterId(String testerId) {
        List<Object[]> results = testTasksRepository.findTaskHistoryByTesterId(testerId);
        List<TestTaskHistory> historyList = results.stream()
                .map(arr -> {
                    TestTaskHistory dto = new TestTaskHistory();
                    dto.setTaskid((String) arr[0]);
                    dto.setTitle((String) arr[1]);
                    dto.setDescription((String) arr[2]);
                    dto.setPersonCount(((Long) arr[3]).intValue());
                    return dto;
                })
                .collect(Collectors.toList());

        return new TestTaskHistoryResponse(historyList); // 包裹到外层对象
    }

    // 通过被测试用户id获取待测任务
    public TaskPendingResponse getTasksByTestedUserId(String testedUserId) {
        List<Object[]> rawResults = testTasksRepository.findTasksByTestedUserId(testedUserId);
        System.out.println(rawResults.stream().toList());

        List<TaskPendingDTO> tasks = rawResults.stream()
                .map(arr -> new TaskPendingDTO(
                        (String) arr[0],   // task_id
                        (String) arr[1],   // tester_id
                        (String) arr[2],   // testedUser_id
                        (String) arr[3],   // scale_id
                        (String) arr[4],   // status
                        (String) arr[5],   // title
                        (String) arr[6]    // description
                ))
                .collect(Collectors.toList());
        System.out.println(tasks.stream().toList());

        return new TaskPendingResponse(tasks);
    }

    // 通过被测人id获取答题历史(answer_history)
    public AnswerHistoryResponse getTestHistory(String testedUserId) {

        // 1. 查询被测人所有测试任务
        List<TestTasks> testTasks = testTasksRepository.findByTestedUserId(testedUserId);
        if (testTasks.isEmpty()) {
            throw new ResourceNotFoundException("testTasks not found for id: " + testedUserId);
        }

        // 2. 构建返回的 DTO
        List<AnswerDTO> testHistoryDTOList = new ArrayList<>();
        for (TestTasks task : testTasks) {
            // 3. 查询答题记录
            List<TestAnswers> testAnswers = testAnswersRepository.findByTaskId(task.getTaskId());

            // 4. 获取该任务的所有量表
            List<String> scaleIds = task.getScales().stream()
                    .map(Scale::getScaleId)
                    .collect(Collectors.toList());
            List<Scale> scales = scaleRepository.findScalesWithQuestions(scaleIds);

            // 5. 构建问题和选项
            List<ScaleAnswerDTO> scaleDTOs = new ArrayList<>();
            for (Scale scale : scales) {
                List<QuestionAnswerDTO> questionDTOs = new ArrayList<>();
                for (Question question : scale.getQuestions()) {
                    // 获取每个问题的选项
                    List<Option> options = optionRepository.findByQuestionId(question.getQuestionId());
                    List<OptionAnswer> optionDTOs = options.stream()
                            .map(option -> new OptionAnswer(option.getOptionId(), option.getOptionText(),
                                    testAnswers.stream()
                                            .filter(ta -> ta.getSelectedOption().equals(option))
                                            .map(ta -> ta.getSelectedOption().getOptionText())
                                            .findFirst()
                                            .orElse("")))
                            .collect(Collectors.toList());

                    questionDTOs.add(new QuestionAnswerDTO(question.getQuestionText(), optionDTOs));
                }
                scaleDTOs.add(new ScaleAnswerDTO(scale.getTitle(), scale.getDescription(), questionDTOs));
            }

            // 6. 创建任务历史记录 DTO
            AnswerDTO testHistoryDTO = new AnswerDTO(
                    task.getTaskId(),
                    calculateTotalScore(task.getTaskId()),
                    task.getCreatedAt(),
                    task.getTitle(),
                    scaleDTOs
            );
            testHistoryDTOList.add(testHistoryDTO);
        }

        return new AnswerHistoryResponse(testHistoryDTOList);
    }

    // 计算测试任务的总分(answer_history)
    public int calculateTotalScore(String taskId) {
        // 1. 获取所有与任务相关的答案
        List<TestAnswers> answers = testAnswersRepository.findByTaskId(taskId);

        // 2. 计算所有答案的分数总和
        int totalScore = answers.stream()
                .mapToInt(TestAnswers::getScore) // 获取每个答案的分数
                .sum(); // 累加

        return totalScore;
    }

    @Transactional
    public void createTestTask(CreateTestTaskRequest request) {
        TestTasks testTask = new TestTasks();

        testTask.setTaskId(request.getTid());

        // 根据传入的创建者ID（request.getId()）获取用户实体
        User creator = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("创建者不存在: " + request.getId()));
        testTask.setTesterId(creator);

        // 设置默认状态和标题（标题可根据实际业务调整）
        testTask.setStatus(TaskStatus.Pending);
        testTask.setTitle("测试任务 " + testTask.getTaskId());
        testTask.setDescription("");

        // 处理被测试人集合
        Set<TestedUser> testedUsers = new HashSet<>();
        for (String testedUserId : request.getUserIds()) {
            TestedUser testedUser = testedUserRepository.findById(testedUserId)
                    .orElseThrow(() -> new RuntimeException("被测试人不存在: " + testedUserId));
            testedUsers.add(testedUser);
        }
        testTask.setTestedUsers(testedUsers.stream().toList());

        // 处理量表集合
        Set<Scale> scales = new HashSet<>();
        for (String scaleId : request.getScaleIds()) {
            Scale scale = scaleRepository.findById(scaleId)
                    .orElseThrow(() -> new RuntimeException("量表不存在: " + scaleId));
            scales.add(scale);
        }
        testTask.setScales(scales.stream().toList());

        // 保存测试任务，同时级联保存关联关系
        testTasksRepository.save(testTask);
    }

    @Transactional
    public void updateTaskStatus(String taskId, TaskStatus status) {
        TestTasks testTask = testTasksRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("任务不存在: " + taskId));
        testTask.setStatus(status);
        testTasksRepository.save(testTask);  // 保存更新后的任务
    }

    @Transactional
    public void completeTask(String taskId) {
        updateTaskStatus(taskId, TaskStatus.Completed);
    }

}
