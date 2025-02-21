package com.example.mind.DTO.SclaeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;

public class ScaleQuesOptionsDTO {
    /**
     * 选项描述
     */
    @JsonProperty("description")
    private String description;
    /**
     * ID 编号
     */
    @JsonProperty("id")
    private String id;
    /**
     * 所属题目id
     */
    @JsonProperty("quetsionId")
    private String quetsionId;
    /**
     * 分值
     */
    @JsonProperty("score")
    private Integer score;

    public ScaleQuesOptionsDTO(String id, String quetsionId,Integer score, String description) {
        this.id = id;
        this.quetsionId = quetsionId;
        this.description = description;
        this.score = score;
    }
    public ScaleQuesOptionsDTO() {
    }

    // 手动实现 Builder
    public static ScaleQuesOptionsDTOBuilder builder() {
        return new ScaleQuesOptionsDTOBuilder();
    }

    public static class ScaleQuesOptionsDTOBuilder {
        private String description;
        private String id;
        private String quetsionId;
        private Integer score;

        public ScaleQuesOptionsDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ScaleQuesOptionsDTOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ScaleQuesOptionsDTOBuilder quetsionId(String quetsionId) {
            this.quetsionId = quetsionId;
            return this;
        }

        public ScaleQuesOptionsDTOBuilder score(Integer score) {
            this.score = score;
            return this;
        }

        public ScaleQuesOptionsDTO build() {
            ScaleQuesOptionsDTO scaleQuesOptionsDTO = new ScaleQuesOptionsDTO();
            scaleQuesOptionsDTO.setDescription(this.description);
            scaleQuesOptionsDTO.setid(this.id);
            scaleQuesOptionsDTO.setQuetsionId(this.quetsionId);
            scaleQuesOptionsDTO.setScore(this.score);
            return scaleQuesOptionsDTO;
        }

    }

    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public String getQuetsionId() { return quetsionId; }
    public void setQuetsionId(String value) { this.quetsionId = value; }

    public Integer getScore() { return score; }
    public void setScore(Integer value) { this.score = value; }
}
