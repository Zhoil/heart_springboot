package com.example.mind.DTO.TestTaksDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestTaskHistory {
    /**
     * 描述
     */
    @JsonProperty("description")
    private String description;
    /**
     * 作答人数
     */
    @JsonProperty("personCount")
    private Integer personCount;
    /**
     * 任务id，主键
     */
    @JsonProperty("task_id")
    private String task_id;
    /**
     * 标题
     */
    @JsonProperty("title")
    private String title;


    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public double getPersonCount() { return personCount; }
    public void setPersonCount(Integer value) { this.personCount = value; }

    public String getTaskid() { return task_id; }
    public void setTaskid(String value) { this.task_id = value; }

    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
}
