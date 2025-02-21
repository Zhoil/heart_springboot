//package com.example.mind.Service;
//
//import com.example.mind.DTO.TestTaksDTO.AnswerHistory.AnswerDTO;
//import com.example.mind.DTO.TestTaksDTO.AnswerHistory.AnswerHistoryResponse;
//import com.example.mind.DTO.TestTaksDTO.AnswerHistory.QuestionAnswerDTO;
//import com.example.mind.DTO.TestTaksDTO.AnswerHistory.ScaleAnswerDTO;
//import com.example.mind.Entries.Scale;
//import com.example.mind.Entries.TestAnswers;
//import com.example.mind.Entries.TestTasks;
//import com.example.mind.repository.TestTasksRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class AnswerHistoryService {
//
//    @Autowired
//    private TestTasksRepository testTasksRepository;
//
//    // 获取用户答题历史
//    public AnswerHistoryResponse getAnswerHistory(String testedUserId) {
//        List<TestTasks> tasks = testTasksRepository.findAllByTestedUserIdWithDetails(testedUserId);
//
//        List<AnswerDTO> answerDTOs = tasks.stream().map(task -> {
//            AnswerDTO answerDTO = new AnswerDTO();
//            answerDTO.setTask_id(task.getTaskId());
//            answerDTO.setTitle(task.getTitle());
//            answerDTO.setTimestamp(task.getCreatedAt());
//
//            // 计算总分
//            Integer totalScore = task.getAnswers().stream()
//                    .mapToInt(TestAnswers::getScore)
//                    .sum();
//            answerDTO.setScore(totalScore);
//
//            // 构建量表结构
//            answerDTO.setScales(buildScaleDTOs(task));
//            return answerDTO;
//        }).collect(Collectors.toList());
//
//        return new AnswerHistoryResponse(answerDTOs);
//    }
//
//    // 构建量表结构
//    private List<ScaleAnswerDTO> buildScaleDTOs(TestTasks task) {
//        return task.getScales().stream().map(scale -> {
//            ScaleAnswerDTO scaleDTO = new ScaleAnswerDTO();
//            scaleDTO.setTitle(scale.getTitle());
//            scaleDTO.setDescription(scale.getDescription());
//
//            // 构建问题结构
//            scaleDTO.setQuestions(buildQuestionDTOs(task, scale));
//            return scaleDTO;
//        }).collect(Collectors.toList());
//    }
//
//    // 构建问题结构
//    private List<QuestionAnswerDTO> buildQuestionDTOs(TestTasks task, Scale scale) {
//        return scale.getQuestions().stream().map(question -> {
//            QuestionAnswerDTO questionDTO = new QuestionAnswerDTO();
//            questionDTO.setDescription(question.getQuestionText());
//
//            // 查找用户答案
//            Optional<TestAnswers> answer = task.getAnswers().stream()
//                    .filter(a -> a.getQuestion().getQuestionId().equals(question.getQuestionId()))
//                    .findFirst();
//
//            answer.ifPresent(a ->
//                    questionDTO.setUserAnswer(a.getSelectedOption().getOptionText())
//            );
//            return questionDTO;
//        }).collect(Collectors.toList());
//    }
//
//}
