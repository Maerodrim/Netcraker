package com.example.DataRoadsAndWeather.Model;

import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Enum.CardStatus;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@Table(name = "Card")
public class Card {
    public Card() {
    }

    public Card(String nameCard, Integer dataBegSession, Integer allDevelopment,
                Integer allAnalysis, Integer allTesting, Double money, ColorCard colorCard, Integer priority, Integer subs) {
        this.nameCard = nameCard;
        this.dataBegSession = dataBegSession;
        this.allDevelopment = allDevelopment;
        this.allAnalysis = allAnalysis;
        this.allTesting = allTesting;
        this.money = money;
        this.colorCard = colorCard;
        this.priority = priority;
        this.subs = subs;
    }

    public Card(String nameCard, Integer dataBegSession, Integer dataEndSession,
                Integer development, Integer allDevelopment, Integer analysis,
                Integer allAnalysis, Integer testing, Integer allTesting,
                Double money, Integer subs, ColorCard colorCard,
                CardStatus status, Integer priority) {
        this.nameCard = nameCard;
        this.dataBegSession = dataBegSession;
        this.dataEndSession = dataEndSession;
        this.development = development;
        this.allDevelopment = allDevelopment;
        this.analysis = analysis;
        this.allAnalysis = allAnalysis;
        this.testing = testing;
        this.allTesting = allTesting;
        this.money = money;
        this.subs = subs;
        this.colorCard = colorCard;
        this.status = status;
        this.priority = priority;
    }

    public Card(String nameCard, Integer dataBegSession, Integer dataEndSession,
                Integer development, Integer allDevelopment, Integer analysis,
                Integer allAnalysis, Integer testing, Integer allTesting,
                Double money, Integer subs, ColorCard colorCard,
                CardStatus status, Integer priority, String email) {
        this.nameCard = nameCard;
        this.dataBegSession = dataBegSession;
        this.dataEndSession = dataEndSession;
        this.development = development;
        this.allDevelopment = allDevelopment;
        this.analysis = analysis;
        this.allAnalysis = allAnalysis;
        this.testing = testing;
        this.allTesting = allTesting;
        this.money = money;
        this.subs = subs;
        this.colorCard = colorCard;
        this.status = status;
        this.priority = priority;
        this.email = email;
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
    @Column(name = "email", unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setColorCard(ColorCard colorCard) {
        this.colorCard = colorCard;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public void addAnalysis(Integer analysis) {
        this.analysis += analysis;
        if (analysis >= allAnalysis) {
            status = CardStatus.AnalDone;
        }
    }

    public void addDevelopment(Integer development) {
        this.development += development;
        if (development >= allDevelopment) {
            status = CardStatus.DevDone;
        }
    }

    public void addTesting(Integer testing) {
        this.testing += testing;
        if (testing >= allTesting) {
            status = CardStatus.ReadyDeploy;
        }
    }

    public void updateCard(Card card){
        this.nameCard = card.nameCard;
        this.dataBegSession = card.dataBegSession;
        this.dataEndSession = card.dataEndSession;
        this.development = card.development;
        this.allDevelopment = card.allDevelopment;
        this.analysis = card.analysis;
        this.allAnalysis = card.allAnalysis;
        this.testing = card.testing;
        this.allTesting = card.allTesting;
        this.money = card.money;
        this.subs = card.subs;
        this.colorCard = card.colorCard;
        this.status = card.status;
        this.priority = card.priority;
        this.email = card.email;}

    public void setDataBegSession(Integer dataBegSession) {
        this.dataBegSession = dataBegSession;
    }

    public void setDataEndSession(Integer dataEndSession) {
        this.dataEndSession = dataEndSession;
    }

    public void nextCardStatus() {
        this.status = CardStatus.values()[status.ordinal() + 1];
    }

    public void backCardStatus() {
        this.status = CardStatus.values()[status.ordinal() - 1];
    }

    public ColorCard getColorCard() {
        return colorCard;
    }

    public Double getMoney() {
        return this.money;
    }

    public String getNameCard() {
        return nameCard;
    }

    public Integer getDataSession() {
        return dataBegSession;
    }

    public Integer getDevelopment() {
        return development;
    }

    public Integer getAnalysis() {
        return analysis;
    }

    public Integer getTesting() {
        return testing;
    }

    public Integer getDataBegSession() {
        return dataBegSession;
    }

    public Integer getDataEndSession() {
        return dataEndSession;
    }

    public Integer getAllDevelopment() {
        return allDevelopment;
    }

    public Integer getAllAnalysis() {
        return allAnalysis;
    }

    public Integer getAllTesting() {
        return allTesting;
    }

    public CardStatus getStatus() {
        return status;
    }

    public Integer getResult() {
        return dataEndSession - dataBegSession;
    }

    public Integer getPriority() {
        return priority;
    }

    public Integer getIdCard() {
        return idCard;
    }

    public Integer getSubs() {
        return subs;
    }

    public void setSubs(Integer subs) {
        this.subs = subs;
    }

}
