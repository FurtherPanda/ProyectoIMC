package com.imc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bmi_records")
public class BMIRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double weight;

    private double bmi;

    private LocalDateTime dateRecorded;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BMIRecord() {}

    public Long getId() {
        return id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public LocalDateTime getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDateTime dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}