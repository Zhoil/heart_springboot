package com.example.mind.DTO.QuestionnaireAllDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionnaireDTO {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("analyseDescription")
    private String analyseDescription;
    @JsonProperty("testerid")
    private String testerid;
    @JsonProperty("scales")
    private List<QuesScaleDTO> scales;

    // 全参构造函数、Getters & Setters
    public QuestionnaireDTO(String id, String name, String analyseDescription, String testerid, List<QuesScaleDTO> scales) {
        this.id = id;
        this.name = name;
        this.analyseDescription = analyseDescription;
        this.testerid = testerid;
        this.scales = scales;
    }

    public QuestionnaireDTO() { };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnalyseDescription() {
        return analyseDescription;
    }

    public void setAnalyseDescription(String analyseDescription) {
        this.analyseDescription = analyseDescription;
    }

    public String getTesterid() {
        return testerid;
    }

    public void setTesterid(String testerid) {
        this.testerid = testerid;
    }

    public List<QuesScaleDTO> getScales() {
        return scales;
    }

    public void setScales(List<QuesScaleDTO> scales) {
        this.scales = scales;
    }
}
