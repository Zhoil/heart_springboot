package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuesScaleDTO {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("managerId")
    private String managerId;
    @JsonProperty("category")
    private String category;
    @JsonProperty("questions")
    private List<QuestionDTO> questions;

    // 全参构造函数、Getters & Setters
    public QuesScaleDTO(String id, String name, String managerId, String category, List<QuestionDTO> questions) {
        Id = id;
        this.name = name;
        this.managerId = managerId;
        this.category = category;
        this.questions = questions;
    }

    public QuesScaleDTO() { };

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<QuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDTO> questions) {
        this.questions = questions;
    }
}
