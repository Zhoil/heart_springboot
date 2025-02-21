package com.example.mind.DTO.SclaeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScaleQuestionsDTO {
    /**
     * 题干
     */
    @JsonProperty("description")
    private String description;
    /**
     * 因子id
     */
//    @JsonProperty("factorId")
//    private String factorId;
    @JsonProperty("factorIds")
    private List<String> factorIds;
    /**
     * 题目编号
     */
    @JsonProperty("id")
    private String id;
    /**
     * 一个题目可能包含多个选项
     */
    @JsonProperty("options")
    private List<ScaleQuesOptionsDTO> options;
    /**
     * 所属量表id
     */
    @JsonProperty("scaleId")
    private String scaleId;

    public ScaleQuestionsDTO(String id, String scaleId, String description, List<String> factorIds, List<ScaleQuesOptionsDTO> options) {
        this.id = id;
        this.scaleId = scaleId;
        this.description = description;
        this.factorIds = factorIds;
        this.options = options;
    }

    public ScaleQuestionsDTO() {
    }

    // 手动实现 Builder
    public static ScaleQuestionsDTOBuilder builder() {
        return new ScaleQuestionsDTOBuilder();
    }

    public static class ScaleQuestionsDTOBuilder {
        private String description;
//        private String factorId;
        private List<String> factorIds;
        private String id;
        private List<ScaleQuesOptionsDTO> options;
        private String scaleId;

        public ScaleQuestionsDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

//        public ScaleQuestionsDTOBuilder factorId(String factorId) {
//            this.factorId = factorId;
//            return this;
//        }

        public ScaleQuestionsDTOBuilder factorIds(List<String> factorIds) {
            this.factorIds = factorIds;
            return this;
        }

        public ScaleQuestionsDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ScaleQuestionsDTOBuilder options(List<ScaleQuesOptionsDTO> options) {
            this.options = options;
            return this;
        }

        public ScaleQuestionsDTOBuilder scaleId(String scaleId) {
            this.scaleId = scaleId;
            return this;
        }

        public ScaleQuestionsDTO build() {
            ScaleQuestionsDTO scaleQuestionsDTO = new ScaleQuestionsDTO();
            scaleQuestionsDTO.setDescription(this.description);
//            scaleQuestionsDTO.setFactorId(this.factorId);
            scaleQuestionsDTO.setFactorIds(this.factorIds);
            scaleQuestionsDTO.setid(this.id);
            scaleQuestionsDTO.setOptions(this.options);
            scaleQuestionsDTO.setScaleId(this.scaleId);
            return scaleQuestionsDTO;
        }

    }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

//    public String getFactorId() { return factorId; }
//    public void setFactorId(String value) { this.factorId = value; }
    public List<String> getFactorIds() { return factorIds; }
    public void setFactorIds(List<String> value) { this.factorIds = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public List<ScaleQuesOptionsDTO> getOptions() { return options; }
    public void setOptions(List<ScaleQuesOptionsDTO> value) { this.options = value; }

    public String getScaleId() { return scaleId; }
    public void setScaleId(String value) { this.scaleId = value; }
}
