package com.example.mind.DTO.SclaeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ScaleGetResponse {
    /**
     * id
     */
    @JsonProperty("id")
    private String id;
    /**
     * 创建的管理员id
     */
    @JsonProperty("managerId")
    private String managerId;
    /**
     * 量表名称
     */
    @JsonProperty("name")
    private String name;
    /**
     * 该量表包含的问题
     */
    @JsonProperty("questions")
    private List<ScaleQuestionsDTO> questions;

    public ScaleGetResponse() {
    }

    public ScaleGetResponse(String id, String name, String managerId, List<ScaleQuestionsDTO> questions) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.questions = questions;
    }

    // 手动实现 Builder
    public static ScaleGetResponseBuilder builder() {
        return new ScaleGetResponseBuilder();
    }

    public static class ScaleGetResponseBuilder {
        private String id;
        private String name;
        private String managerId;
        private List<ScaleQuestionsDTO> questions;

        public ScaleGetResponseBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ScaleGetResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ScaleGetResponseBuilder managerId(String managerId) {
            this.managerId = managerId;
            return this;
        }

        public ScaleGetResponseBuilder questions(List<ScaleQuestionsDTO> questions) {
            this.questions = questions;
            return this;
        }

        public ScaleGetResponse build() {
            ScaleGetResponse response = new ScaleGetResponse();
            response.setid(this.id);
            response.setName(this.name);
            response.setManagerId(this.managerId);
            response.setQuestions(this.questions);
            return response;
        }
    }


    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public String getManagerId() { return managerId; }
    public void setManagerId(String value) { this.managerId = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public List<ScaleQuestionsDTO> getQuestions() { return questions; }

    public void setQuestions(List<ScaleQuestionsDTO> questions) { this.questions = questions; }
}
