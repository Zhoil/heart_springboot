package com.example.mind.Controller;

import com.example.mind.DTO.CreateTaskDTO.CreateTestTaskRequest;
import com.example.mind.Service.TestTasksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/add")
public class TestTaskController {

    @Autowired
    private TestTasksService testTasksService;

    @ApiOperation(value = "创建测试任务")
    @RequestMapping(value = "/testTask",method = RequestMethod.POST)
    public ResponseEntity<Map<String, Boolean>> createTestTask(@RequestBody CreateTestTaskRequest request) {
        System.out.println(request.getId());
        testTasksService.createTestTask(request);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

}
