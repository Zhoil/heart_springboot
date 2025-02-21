package com.example.mind.DTO.TestTaksDTO.AnswerHistory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public class AnswerDTO {
    @JsonProperty("task_id")
    private String task_id;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("timestamp")
    private Timestamp timestamp;
    @JsonProperty("title")
    private String title;
    @JsonProperty("scales")
    private List<ScaleAnswerDTO> scales;
    // 全参构造函数、Getter/Setter

    public AnswerDTO(String task_id, Integer score, Timestamp timestamp, String title, List<ScaleAnswerDTO> scales) {
        this.task_id = task_id;
        this.score = score;
        this.timestamp = timestamp;
        this.title = title;
        this.scales = scales;

    }

    public AnswerDTO() {
    }

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ScaleAnswerDTO> getScales() {
        return scales;
    }

    public void setScales(List<ScaleAnswerDTO> scales) {
        this.scales = scales;
    }
}
