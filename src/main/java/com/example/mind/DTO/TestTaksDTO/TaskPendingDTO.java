package com.example.mind.DTO.TestTaksDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskPendingDTO {
    @JsonProperty("task_id")
    private String task_id;
    @JsonProperty("tester_id")
    private String tester_id;
    @JsonProperty("testeduser_id")
    private String testeduser_id;
    @JsonProperty("scale_id")
    private String scale_id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;

    // 全参构造函数、Getters & Setters
    public TaskPendingDTO(String taskId, String testerId, String testeduserId,
                   String scaleId, String status, String title, String desc) {
        this.task_id = taskId;
        this.tester_id = testerId;
        this.testeduser_id = testeduserId;
        this.scale_id = scaleId;
        this.status = status;
        this.title = title;
        this.description = desc;
    }
}
