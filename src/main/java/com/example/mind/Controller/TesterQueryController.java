package com.example.mind.Controller;


import com.example.mind.DTO.TestTaksDTO.TestTaskHistoryResponse;
import com.example.mind.Service.TestTasksService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/query")
public class TesterQueryController {

    @Autowired
    private TestTasksService testTasksService;

    @ApiOperation(value = "测试人查询测试任务历史")
    @RequestMapping(value = "/test_task_result_history",method = RequestMethod.GET)
    public TestTaskHistoryResponse test_task_result_history(@RequestParam("id") String id) {
        return testTasksService.getTaskHistoryByTesterId(id);
    }




}
