package com.example.mind.Service;

import com.example.mind.DTO.QuestionnaireAllDTO.*;
import com.example.mind.Entries.Option;
import com.example.mind.Entries.Question;
import com.example.mind.Entries.Scale;
import com.example.mind.Entries.TestTasks;
import com.example.mind.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuestionnaireAnswerService {

    @Autowired
    private TestAnswersRepository testAnswersRepository;

    @Autowired
    private TestTasksRepository testTasksRepository;

    @Autowired
    private ScaleRepository scaleRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private OptionRepository optionRepository;

    // 通过任务id获取问卷详情 优化
    public QuestionnaireDTO getQuestionnaireDetails(String taskId) {
        // 1. 查询问卷信息
        TestTasks questionnaire = testTasksRepository.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("问卷未找到"));

        // 2. 查询问卷下的所有量表
        List<Scale> scales = scaleRepository.findByTaskId(questionnaire.getTaskId());
        List<String> scaleIds = scales.stream().map(Scale::getScaleId).collect(Collectors.toList());

        // 3. 查询所有量表下的问题
        List<Question> questions = questionRepository.findByScaleIds(scaleIds);
        Map<String, List<Question>> scaleQuestionsMap = questions.stream()
                .collect(Collectors.groupingBy(q -> q.getScale().getScaleId()));

        // 4. 查询所有问题的选项
        List<String> questionIds = questions.stream().map(Question::getQuestionId).collect(Collectors.toList());
        List<Option> options = optionRepository.findOptionsByQuestionIds(questionIds);
        Map<String, List<Option>> questionOptionsMap = options.stream()
                .collect(Collectors.groupingBy(o -> o.getQuestion().getQuestionId()));

        // 5. 统计每个选项的选择人数
        List<String> optionIds = options.stream().map(Option::getOptionId).collect(Collectors.toList());
        Map<String, Integer> optionCountMap = optionRepository.countOptionSelections(optionIds)
                .stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> ((Number) obj[1]).intValue()
                ));

        // 6. 组装数据
        List<QuesScaleDTO> scaleResponses = scales.stream().map(scale -> {
            List<Question> scaleQuestions = scaleQuestionsMap.getOrDefault(scale.getScaleId(), new ArrayList<>());

            List<QuestionDTO> questionResponses = scaleQuestions.stream().map(question -> {
                List<Option> questionOptions = questionOptionsMap.getOrDefault(question.getQuestionId(), new ArrayList<>());

                List<OptionDTO> optionResponses = questionOptions.stream().map(option ->
                        new OptionDTO(
                                option.getOptionId(),
                                option.getQuestion().getQuestionId(),
                                option.getOptionText(),
                                option.getScore(),
                                optionCountMap.getOrDefault(option.getOptionId(), 0)
                        )
                ).collect(Collectors.toList());

                return new QuestionDTO(question.getQuestionId(), question.getScale().getScaleId(), question.getQuestionText(), optionResponses);
            }).collect(Collectors.toList());

            return new QuesScaleDTO(scale.getScaleId(), scale.getDescription(), scale.getManagerId(), scale.getCategory().getCategoryName(), questionResponses);
        }).collect(Collectors.toList());

        return new QuestionnaireDTO(questionnaire.getTaskId(), questionnaire.getDescription(), questionnaire.getDescription(), questionnaire.getTesterId().getUserId(), scaleResponses);
    }

}
