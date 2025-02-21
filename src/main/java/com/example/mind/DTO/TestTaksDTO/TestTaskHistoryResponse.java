package com.example.mind.DTO.TestTaksDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TestTaskHistoryResponse {
    @JsonProperty("testTaskHistory")
    private List<TestTaskHistory> testTaskHistory;

    public TestTaskHistoryResponse(List<TestTaskHistory> testTaskHistory) {
        this.testTaskHistory = testTaskHistory;
    }

    // Getter 和 Setter
}
