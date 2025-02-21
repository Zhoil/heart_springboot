package com.example.mind.Service;

import com.example.mind.DTO.SubmitAnswerRequest;
import com.example.mind.Entries.Option;
import com.example.mind.Entries.Question;
import com.example.mind.Entries.TestAnswers;
import com.example.mind.Entries.TestTasks;
import com.example.mind.repository.OptionRepository;
import com.example.mind.repository.QuestionRepository;
import com.example.mind.repository.TestAnswersRepository;
import com.example.mind.repository.TestTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private TestAnswersRepository testAnswersRepository;

    @Autowired
    private TestTasksRepository testTasksRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Transactional
    public void submitAnswers(SubmitAnswerRequest request) {
        // 查询测试任务（TestTasks 表）
        TestTasks testTask = testTasksRepository.findById(request.getPid())
                .orElseThrow(() -> new RuntimeException("测试任务不存在: " + request.getPid()));

        // 遍历每个答案字符串，格式："量表id-问题id-选项id"
        for (String ansStr : request.getAnswer()) {
            String[] parts = ansStr.split("-");
            if (parts.length != 3) {
                throw new RuntimeException("答案格式错误: " + ansStr);
            }
            // parts[0] 为量表ID（当前示例中未使用，可扩展校验），
            // parts[1] 为问题ID，
            // parts[2] 为选项ID
            String questionId = parts[1];
            String optionId = parts[2];

            // 查询题目实体（Questions 表）
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("题目不存在: " + questionId));

            // 查询选项实体（Options 表）并获取分值
            Option option = optionRepository.findById(optionId)
                    .orElseThrow(() -> new RuntimeException("选项不存在: " + optionId));
            int score = option.getScore();

            // 构造 TestAnswer 实体，设置关联关系
            TestAnswers testAnswer = new TestAnswers();
            testAnswer.setAnswerId(UUID.randomUUID().toString());
            testAnswer.setTask(testTask);
            testAnswer.setQuestion(question);
            testAnswer.setSelectedOption(option);
            testAnswer.setScore(score);

            // 保存答题记录
            testAnswersRepository.save(testAnswer);
        }
    }




}
