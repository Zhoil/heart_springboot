package com.example.mind.DTO.SclaeDTO.Stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScaleStatsDTO {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("managerId")
    private String managerId;
    @JsonProperty("questions")
    private List<QuestionStatsDTO> questions;

    public ScaleStatsDTO(String scaleId, String title, String managerId, List<QuestionStatsDTO> questionDTOs) {
        this.Id = scaleId;
        this.name = title;
        this.managerId = managerId;
        this.questions = questionDTOs;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<QuestionStatsDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionStatsDTO> questions) {
        this.questions = questions;
    }
}
