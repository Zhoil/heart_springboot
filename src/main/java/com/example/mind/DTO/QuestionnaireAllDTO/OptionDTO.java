package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionDTO {
    @JsonProperty("option_id")
    private String option_id;
    @JsonProperty("question_id")
    private String question_id;
    @JsonProperty("option_text")
    private String option_text;
    @JsonProperty("score")
    private int score;
    @JsonProperty("personCount")
    private int personCount;

    // 全参构造函数、Getters & Setters
    public OptionDTO(String option_id, String question_id, String option_text, int score, int personCount) {
        this.option_id = option_id;
        this.question_id = question_id;
        this.option_text = option_text;
        this.score = score;
        this.personCount = personCount;
    }

    public OptionDTO(String option_id, String question_id, String option_text, int score) {
        this.option_id = option_id;
        this.question_id = question_id;
        this.option_text = option_text;
        this.score = score;
    }

    public OptionDTO() { };

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

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }
}
