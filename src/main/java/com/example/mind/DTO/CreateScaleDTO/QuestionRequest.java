package com.example.mind.DTO.CreateScaleDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionRequest {
    @JsonProperty("Id")
    private String Id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("factorId")
    private List<String> factorId;
    @JsonProperty("options")
    private List<OptionRequest> options;

    // Getters & Setters

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFactorId() {
        return factorId;
    }

    public void setFactorId(List<String> factorId) {
        this.factorId = factorId;
    }

    public List<OptionRequest> getOptions() {
        return options;
    }

    public void setOptions(List<OptionRequest> options) {
        this.options = options;
    }

    public QuestionRequest getQuestionRequest() {
        return this;
    }
}
