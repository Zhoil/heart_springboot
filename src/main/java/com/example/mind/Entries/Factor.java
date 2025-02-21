package com.example.mind.Entries;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Factors")
public class Factor {
    @Id
    @Column(name = "factor_id", length = 30)
    private String factorId;

    @Column(name = "factor_name", nullable = false, length = 100)
    private String factorName;

    @ManyToMany(mappedBy = "factors",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Question> questions;

    // Getters and Setters


    public String getFactorId() {
        return factorId;
    }

    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
