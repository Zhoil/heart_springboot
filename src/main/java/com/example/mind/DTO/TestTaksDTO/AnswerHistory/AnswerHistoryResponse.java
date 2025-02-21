package com.example.mind.DTO.TestTaksDTO.AnswerHistory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswerHistoryResponse {
    @JsonProperty("answers")
    private List<AnswerDTO> answers;
    // 构造函数和Getter/Setter
    public AnswerHistoryResponse() {

    }

    public AnswerHistoryResponse(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
