package com.example.mind.Entries;


import jakarta.persistence.*;

@Entity
@Table(name = "Reports")
public class Report {
    @Id
    @Column(name = "report_id", length = 30)
    private String reportId;

    @OneToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TestTasks task;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

    // Getters and Setters

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public TestTasks getTask() {
        return task;
    }

    public void setTask(TestTasks task) {
        this.task = task;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}
