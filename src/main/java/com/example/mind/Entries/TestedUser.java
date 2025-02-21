package com.example.mind.Entries;


import com.example.mind.enumtion.Gender;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "testeduser")
public class TestedUser {
    @Id
    @Column(name = "testeduser_id", length = 30)
    private String testedUserId;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "id_card", unique = true, length = 18)
    private String idCard;

    @Column(name = "phone",length = 20)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "TaskTesteduser",
            joinColumns = @JoinColumn(name = "testeduser_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<TestTasks> tasks;

    public String getTestedUserId() {
        return testedUserId;
    }

    public void setTestedUserId(String testedUserId) {
        this.testedUserId = testedUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
