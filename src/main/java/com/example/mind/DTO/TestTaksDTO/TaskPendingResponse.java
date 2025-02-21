package com.example.mind.DTO.TestTaksDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TaskPendingResponse {
    @JsonProperty("tasks")
    private List<TaskPendingDTO> tasks;

    // 构造函数和 Getter/Setter
    public TaskPendingResponse(List<TaskPendingDTO> tasks) {
        this.tasks = tasks;
    }

    public TaskPendingResponse () {

    }
    public List<TaskPendingDTO> getTasks() {
        return tasks;
    }
    public void setTasks(List<TaskPendingDTO> tasks) {
        this.tasks = tasks;
    }
}
