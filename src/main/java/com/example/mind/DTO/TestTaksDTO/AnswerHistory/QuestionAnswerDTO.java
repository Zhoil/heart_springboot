package com.example.mind.DTO.TestTaksDTO.AnswerHistory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionAnswerDTO {
    @JsonProperty("description")
    private String description;
    @JsonProperty("userAnswer")
    private List<OptionAnswer> userAnswer;
    // 全参构造函数、Getter/Setter

    public QuestionAnswerDTO() {

    }

    public QuestionAnswerDTO(String description, List<OptionAnswer> userAnswer) {
        this.description = description;
        this.userAnswer = userAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OptionAnswer> getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(List<OptionAnswer> userAnswer) {
        this.userAnswer = userAnswer;
    }
}
