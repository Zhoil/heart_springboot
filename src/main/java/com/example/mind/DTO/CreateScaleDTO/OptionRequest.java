package com.example.mind.DTO.CreateScaleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionRequest {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("score")
    private int score;
    @JsonProperty("description")
    private String description;

    // Getters & Setters

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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
}
