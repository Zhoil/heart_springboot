package com.example.mind.Entries;


import jakarta.persistence.*;

@Entity
@Table(name = "TestAnswers")
public class TestAnswers {
    @Id
    @Column(name = "answer_id", length = 30)
    private String answerId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "task_id", nullable = false)
    private TestTasks task;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "selected_option_id", nullable = false)
    private Option selectedOption;

    @Column(name = "score",nullable = false)
    private Integer score;

    // Getters and Setters

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public TestTasks getTask() {
        return task;
    }

    public void setTask(TestTasks task) {
        this.task = task;
    }


    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Option getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Option selectedOption) {
        this.selectedOption = selectedOption;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
