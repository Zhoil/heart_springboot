package com.example.mind.Entries;


import com.example.mind.enumtion.TaskStatus;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "testtasks")
public class TestTasks {
    @Id
    @Column(name = "task_id", length = 30)
    private String taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tester_id", nullable = false)
    private User testerId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Pending', 'Completed') DEFAULT 'Pending'")
//    private TaskStatus status = TaskStatus.Pending;
    private TaskStatus status ;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 200)
    private String description;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TestAnswers> answers;

    @ManyToMany(mappedBy = "tasks",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<TestedUser> testedUsers;

    @ManyToMany(mappedBy = "taskss",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Scale> scales = new ArrayList<>();

    // Getters and Setters

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public User getTesterId() {
        return testerId;
    }

    public void setTesterId(User testerId) {
        this.testerId = testerId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<TestAnswers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<TestAnswers> answers) {
        this.answers = answers;
    }

    public List<TestedUser> getTestedUsers() {
        return testedUsers;
    }

    public void setTestedUsers(List<TestedUser> testedUsers) {
        this.testedUsers = testedUsers;
    }

    public List<Scale> getScales() {
        return scales;
    }

    public void setScales(List<Scale> scales) {
        this.scales = scales;
    }
}
