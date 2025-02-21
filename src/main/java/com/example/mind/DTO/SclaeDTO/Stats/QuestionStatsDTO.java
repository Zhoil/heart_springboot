package com.example.mind.DTO.SclaeDTO.Stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionStatsDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("scaleId")
    private String scaleId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("factorId")
    private List<String> factorIds;
    @JsonProperty("options")
    private List<OptionStatsDTO> options;

    public QuestionStatsDTO(String questionId, String scaleId, String questionText, List<String> factorIds, List<OptionStatsDTO> optionDTOs) {
        this.id = questionId;
        this.scaleId = scaleId;
        this.description = questionText;
        this.factorIds = factorIds;
        this.options = optionDTOs;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScaleId() {
        return scaleId;
    }

    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFactorIds() {
        return factorIds;
    }

    public void setFactorId(List<String> factorIds) {
        this.factorIds = factorIds;
    }

    public List<OptionStatsDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionStatsDTO> options) {
        this.options = options;
    }
}
