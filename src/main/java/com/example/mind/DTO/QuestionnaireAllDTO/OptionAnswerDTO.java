package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionAnswerDTO {
    @JsonProperty("option_id")
    private String option_id;
    @JsonProperty("question_id")
    private String question_id;
    @JsonProperty("option_text")
    private String option_text;
    @JsonProperty("score")
    private int score;
    @JsonProperty("personCount")
    private Integer personCount;

    // 全参构造函数、Getters & Setters

    public OptionAnswerDTO() { };

    public String getOption_id() {
        return option_id;
    }

    public void setOption_id(String option_id) {
        this.option_id = option_id;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public String getOption_text() {
        return option_text;
    }

    public void setOption_text(String option_text) {
        this.option_text = option_text;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }
}
