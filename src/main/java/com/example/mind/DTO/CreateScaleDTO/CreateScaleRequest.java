package com.example.mind.DTO.CreateScaleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateScaleRequest {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("category_name")
    private String category_name;
    @JsonProperty("managerId")
    private String managerId;
    @JsonProperty("questions")
    private List<QuestionRequest> questions;

    // Getters & Setters

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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }
}
