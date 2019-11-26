package com.example.GameNetcreckerData.Model;

import com.example.GameNetcreckerData.Dto.View.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GraphGame {
    public GraphGame(Integer day, Integer analysis, Integer development, Integer testing, String email) {
        this.day = day;
        this.analysis = analysis;
        this.development = development;
        this.testing = testing;
        this.email = email;
        this.cost=0;
    }

    public GraphGame(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.GraphGame.class)
    private Integer idGraphGame;
    @Column(name = "day", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private Integer day;
    @Column(name = "analysis", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private Integer analysis;
    @Column(name = "development", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private Integer development;
    @Column(name = "testing", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private Integer testing;
    @Column(name = "cost", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private Integer cost;
    @Column(name = "email", unique = false, nullable = true)
    @JsonView(View.GraphGame.class)
    private String email;

    public Integer getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Integer analysis) {
        this.analysis = analysis;
    }

    public Integer getDevelopment() {
        return development;
    }

    public void setDevelopment(Integer development) {
        this.development = development;
    }

    public Integer getTesting() {
        return testing;
    }

    public void setTesting(Integer testing) {
        this.testing = testing;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
