package com.example.mind.Entries;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Scales")
public class Scale {
    @Id
    @Column(name = "scale_id", length = 30)
    private String scaleId;

    @Column(name = "title",nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id", nullable = false)
    private ScaleCategory category;

    @Column(name = "description",length = 200)
    private String description;

    @Column(name = "manager_id",length = 30)
    private String managerId;


    @OneToMany(mappedBy = "scale",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TaskScale",
            joinColumns = @JoinColumn(name = "scale_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<TestTasks> taskss;

    // Getters and Setters

    public String getScaleId() {
        return scaleId;
    }

    public void setScaleId(String scaleId) {
        this.scaleId = scaleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ScaleCategory getCategory() {
        return category;
    }

    public void setCategory(ScaleCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<TestTasks> getTaskss() {
        return taskss;
    }

    public void setTaskss(List<TestTasks> taskss) {
        this.taskss = taskss;
    }
}
