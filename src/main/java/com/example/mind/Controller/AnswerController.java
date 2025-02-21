package com.example.mind.Controller;


import com.example.mind.DTO.SubmitAnswerRequest;
import com.example.mind.DTO.TestTaksDTO.AnswerHistory.AnswerHistoryResponse;
import com.example.mind.Service.AnswerService;
import com.example.mind.Service.TestTasksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private TestTasksService testTasksService;

    @ApiOperation(value = "查询用户答题历史")
    @RequestMapping(value = "/query/answer_history",method = RequestMethod.POST)
    public AnswerHistoryResponse answer_history(@RequestParam("userid") String userid) {
        return testTasksService.getTestHistory(userid);
    }

    @ApiOperation(value = "提交答案")
    @RequestMapping(value = "/add/answer",method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> submitAnswers(@RequestBody SubmitAnswerRequest request) {
        answerService.submitAnswers(request);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

}
