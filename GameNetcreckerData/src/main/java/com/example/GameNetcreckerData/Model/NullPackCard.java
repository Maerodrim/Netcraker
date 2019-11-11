package com.example.GameNetcreckerData.Model;

import com.example.GameNetcreckerData.Dto.View.View;
import com.example.GameNetcreckerData.Model.Enum.CardStatus;
import com.example.GameNetcreckerData.Model.Enum.ColorCard;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@Table(name = "NullPackCard")
public class NullPackCard {
    public NullPackCard() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.CARD.class)
    private Integer idCard;
    @Column(name = "nameCard", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private String nameCard;
    @Column(name = "dataBegSession", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer dataBegSession;
    @Column(name = "dataEndSession", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer dataEndSession;
    @Column(name = "development", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer development;
    @Column(name = "allDevelopment", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer allDevelopment;
    @Column(name = "analysis", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer analysis;
    @Column(name = "allAnalysis", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer allAnalysis;
    @Column(name = "testing", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer testing;
    @Column(name = "allTesting", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer allTesting;
    @Column(name = "money", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Double money;
    @Column(name = "subs", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer subs;
    @Column(name = "colorCard", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private ColorCard colorCard;
    @Column(name = "CardStatus", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private CardStatus status = CardStatus.Selected;
    @Column(name = "priority", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Integer priority;

    public String getNameCard() {
        return nameCard;
    }

    public Integer getDataBegSession() {
        return dataBegSession;
    }

    public Integer getDataEndSession() {
        return dataEndSession;
    }

    public Integer getDevelopment() {
        return development;
    }

    public Integer getAllDevelopment() {
        return allDevelopment;
    }

    public Integer getAnalysis() {
        return analysis;
    }

    public Integer getAllAnalysis() {
        return allAnalysis;
    }

    public Integer getTesting() {
        return testing;
    }

    public Integer getAllTesting() {
        return allTesting;
    }

    public Double getMoney() {
        return money;
    }

    public Integer getSubs() {
        return subs;
    }

    public ColorCard getColorCard() {
        return colorCard;
    }

    public CardStatus getStatus() {
        return status;
    }

    public Integer getPriority() {
        return priority;
    }
}

