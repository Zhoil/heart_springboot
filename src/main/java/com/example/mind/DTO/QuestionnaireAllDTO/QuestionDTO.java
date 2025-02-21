package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("scaleId")
    private String scaleId;
    @JsonProperty("description")
    private String description;
    @JsonProperty("options")
    private List<OptionDTO> options;
    @JsonProperty("optionAnswers")
    private List<OptionAnswerDTO> optionAnswers;

    // 全参构造函数、Getters & Setters

    public QuestionDTO() { };

    public QuestionDTO(String questionId, String scaleId, String questionText, List<OptionDTO> optionResponses) {
        this.id = questionId;
        this.scaleId = scaleId;
        this.description = questionText;
        this.options = optionResponses;
    }

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

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public List<OptionAnswerDTO> getOptionAnswers() {
        return optionAnswers;
    }

    public void setOptionAnswers(List<OptionAnswerDTO> optionAnswers) {
        this.optionAnswers = optionAnswers;
    }

}
