package com.example.mind.DTO.SclaeDTO.Stats;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionStatsDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("score")
    private int score;
    @JsonProperty("description")
    private String description;
    @JsonProperty("personCount")
    private Integer personCount;

    public OptionStatsDTO(String optionId, String questionId, Integer score, String optionText, int personCount) {
        this.id = optionId;
        this.questionId = questionId;
        this.score = score;
        this.description = optionText;
        this.personCount = personCount;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }
}
