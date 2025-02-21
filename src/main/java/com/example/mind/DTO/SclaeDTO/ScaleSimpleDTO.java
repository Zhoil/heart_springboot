package com.example.mind.DTO.SclaeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.JoinColumn;

public class ScaleSimpleDTO {
    @JsonProperty("scale_id")
    private String scale_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("category")
    private String category;
    @JsonProperty("manager_id")
    private String manager_id;

    // 全参构造函数（用于 JPQL 投影）
    public ScaleSimpleDTO(String scaleId, String title, String category, String managerId) {
        this.scale_id = scaleId;
        this.title = title;
        this.category = category;
        this.manager_id = managerId;
    }

    // Getters & Setters


    public String getScale_id() {
        return scale_id;
    }

    public void setScale_id(String scale_id) {
        this.scale_id = scale_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
