package com.example.mind.DTO.TestTaksDTO.AnswerHistory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScaleAnswerDTO {
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("questions")
    private List<QuestionAnswerDTO> questions;
    // 全参构造函数、Getter/Setter

    public ScaleAnswerDTO() {

    }

    public ScaleAnswerDTO(String title, String description, List<QuestionAnswerDTO> questions) {
        this.title = title;
        this.description = description;
        this.questions = questions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionAnswerDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionAnswerDTO> questions) {
        this.questions = questions;
    }
}
