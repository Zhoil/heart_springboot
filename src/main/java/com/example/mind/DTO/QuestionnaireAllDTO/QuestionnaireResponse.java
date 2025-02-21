package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuestionnaireResponse {
    @JsonProperty("questionnaire")
    private QuestionnaireDTO questionnaire;

    public QuestionnaireResponse(QuestionnaireDTO questionnaire) {
        this.questionnaire = questionnaire;
    }

    // Getter & Setter

    public QuestionnaireDTO getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(QuestionnaireDTO questionnaire) {
        this.questionnaire = questionnaire;
    }
}
