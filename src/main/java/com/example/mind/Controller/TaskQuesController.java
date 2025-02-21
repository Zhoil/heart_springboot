package com.example.mind.Controller;


import com.example.mind.DTO.QuestionnaireAllDTO.*;
import com.example.mind.DTO.TestTaksDTO.TaskPendingDTO;
import com.example.mind.DTO.TestTaksDTO.TaskPendingResponse;
import com.example.mind.Service.QuestionnaireAnswerService;
import com.example.mind.Service.QuestionnaireService;
import com.example.mind.Service.TestTasksService;
import com.example.mind.annotation.LogAnnotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/query")
public class TaskQuesController {

    private static final Logger log = LoggerFactory.getLogger(TaskQuesController.class);
    @Autowired
    private TestTasksService testTasksService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionnaireAnswerService questionnaireAnswerService;

    @LogAnnotation("查询该用户所有待测任务")
    @ApiOperation(value = "查询该用户所有待测任务 | 被测试人查询待测任务")
    @RequestMapping(value = "/task_to_do",method = RequestMethod.GET)
    public TaskPendingResponse task_to_do(@RequestParam("id") String id) {
        System.out.println("id: " + id);
        TaskPendingDTO taskPendingDTO = new TaskPendingDTO("t2_q1740045283046", "2", "3", "m1_s1740042672919", "Pending", "测试任务 t2_q1740045283046", " 测试 ");
//        TaskPendingDTO taskPendingDTO2 = new TaskPendingDTO("t2_q1740045283046", "2", "3", "m1_s1740042672910", "Pending", "测试任务 t2_q1740045283046", " 测试 ");
        TaskPendingResponse taskPendingResponse = new TaskPendingResponse();
        List<TaskPendingDTO> taskPendingDTOList = new ArrayList<>();
        taskPendingDTOList.add(taskPendingDTO);
//        taskPendingDTOList.add(taskPendingDTO2);
        taskPendingResponse.setTasks(taskPendingDTOList);
        return taskPendingResponse;
//        return testTasksService.getTasksByTestedUserId(id);
    }

    @LogAnnotation("查询问卷")
    @ApiOperation(value = "查询问卷")
    @RequestMapping(value = "/questionnaire",method = RequestMethod.GET)
    public QuestionnaireResponse questionnaire(@RequestParam("id") String id) {

        // 创建选项列表
        OptionDTO option1 = new OptionDTO();
        option1.setOption_id("oqm1_s1740042672919_0_0");
        option1.setQuestion_id("qm1_s1740042672919_0");
        option1.setOption_text("选1");
        option1.setScore(1);

        OptionDTO option2 = new OptionDTO();
        option2.setOption_id("oqm1_s1740042672919_0_1");
        option2.setQuestion_id("qm1_s1740042672919_0");
        option2.setOption_text("选2");
        option2.setScore(2);

        // 创建第一个问题列表
        QuestionDTO question1 = new QuestionDTO();
        question1.setId("qm1_s1740042672919_0");
        question1.setScaleId("m1_s1740042672919");
        question1.setDescription("题目1");
        question1.setOptions(List.of(option1, option2));

        // 创建量表列表
        QuesScaleDTO scale1 = new QuesScaleDTO();
        scale1.setId("m1_s1740042672919");
        scale1.setName("标题2");
        scale1.setManagerId("1");
        scale1.setCategory("身体");
        scale1.setQuestions(List.of(question1));

        // 创建问卷
        QuestionnaireDTO questionnaire = new QuestionnaireDTO();
        questionnaire.setId("t2_q1740045283046");
        questionnaire.setName("测试任务 t2_q1740045283046");
        questionnaire.setAnalyseDescription(" ");
        questionnaire.setTesterid("2");
        questionnaire.setScales(List.of(scale1));
        System.out.println(questionnaire);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String questionnaireJson = objectMapper.writeValueAsString(questionnaire);

            // 打印到控制台
            System.out.println("Questionnaire details: " + questionnaireJson);
        } catch (Exception e) {
            System.out.println("Error formatting questionnaire to JSON: " + e.getMessage());
        }

        return new QuestionnaireResponse(questionnaire);

//        return questionnaireService.getQuestionnaireDetails(id);
    }

    @ApiOperation(value = "查询问卷详情 | 测试人查询测试任务详情")
    @RequestMapping(value = "/test_task_result_detail",method = RequestMethod.GET)
    public QuestionnaireDTO test_task_result_detail(@RequestParam("id") String id) {
        System.out.println("id: " + id);
        return questionnaireAnswerService.getQuestionnaireDetails(id);
    }

}
