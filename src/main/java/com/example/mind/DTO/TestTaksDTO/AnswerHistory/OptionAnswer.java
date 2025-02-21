package com.example.mind.DTO.TestTaksDTO.AnswerHistory;

public class OptionAnswer {
    private String optionId;
    private String description; // 只返回选项的描述

    public OptionAnswer(String optionId, String description) {
        this.optionId = optionId;
        this.description = description;
    }

    public OptionAnswer() {
    }

    public OptionAnswer(String optionId, String optionText, Object o) {
        this.optionId = optionId;
        this.description = optionText;
    }

    // getters and setters

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
