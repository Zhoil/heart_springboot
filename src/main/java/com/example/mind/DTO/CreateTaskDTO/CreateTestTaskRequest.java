package com.example.mind.DTO.CreateTaskDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateTestTaskRequest {
    // 此处 id 表示创建者的 ID
    @JsonProperty("id")
    private String id;
    @JsonProperty("tid")
    private String tid;
    // 被测试人 ID 数组
    @JsonProperty("userIds")
    private List<String> userIds;
    // 量表 ID 数组
    @JsonProperty("scaleIds")
    private List<String> scaleIds;

    // getters & setters

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public List<String> getUserIds() {
        return userIds;
    }
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
    public List<String> getScaleIds() {
        return scaleIds;
    }
    public void setScaleIds(List<String> scaleIds) {
        this.scaleIds = scaleIds;
    }
}
