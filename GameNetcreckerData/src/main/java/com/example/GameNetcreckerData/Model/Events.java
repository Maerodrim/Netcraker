package com.example.GameNetcreckerData.Model;


import com.example.GameNetcreckerData.Dto.View.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "Events")
public class Events {
    public Events() {
    }

    public Events(Integer day, String text) {
        this.day = day;
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEvents;

    @Column(name = "text", unique = false, nullable = true)
    @JsonView(View.Events.class)
    private String text;

    @Column(name = "day", unique = false, nullable = true)
    @JsonView(View.Events.class)
    private Integer day;

    public void setText(String text) {
        this.text = text;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getText() {
        return text;
    }

    public Integer getDay() {
        return day;
    }
}
