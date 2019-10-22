package com.example.DataRoadsAndWeather.Model;

import com.example.DataRoadsAndWeather.Dto.View.View;
import com.example.DataRoadsAndWeather.Model.Enum.CardStatus;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;


@Entity
@JsonView(Card.class)
@Table(name = "Card")
public class Card {
    public Card() {
    }

    public Card(String nameCard, Integer idSession, Integer dataBegSession, Integer allDevelopment,
                Integer allAnalysis, Integer allTesting, Double money, ColorCard colorCard, Integer priority,Integer subs) {
       // this.session=session;
        this.nameCard = nameCard;
        this.dataBegSession = dataBegSession;
        this.allDevelopment = allDevelopment;
        this.allAnalysis = allAnalysis;
        this.allTesting = allTesting;
        this.money = money;
        this.colorCard = colorCard;
        this.priority = priority;
        this.subs=subs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCard", unique = true, nullable = true)
    @JsonView(View.UI.class)
    private Integer idCard;
    @Column(name = "nameCard", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private String nameCard;
    @Column(name = "dataBegSession", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer dataBegSession;
    @Column(name = "dataEndSession", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer dataEndSession;
    @Column(name = "Dev", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer development = 0;
    @Column(name = "AllDev", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer allDevelopment;
    @Column(name = "Anal", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer analysis = 0;
    @Column(name = "AllAnal", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer allAnalysis;
    @Column(name = "Test", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer testing = 0;
    @Column(name = "AllTest", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer allTesting;
    @Column(name = "Money", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Double money;
    @Column(name = "Subs", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer subs;
    @Column(name = "Color", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private ColorCard colorCard;
    @Column(name = "CardStatus", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private CardStatus status = CardStatus.Selected;
    @Column(name = "Priority", unique = false, nullable = true)
    @JsonView(View.UI.class)
    private Integer priority;


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

    public void setDataBegSession(Integer dataBegSession) {
        this.dataBegSession = dataBegSession;
    }

    public void setDataEndSession(Integer dataEndSession) {
        this.dataEndSession = dataEndSession;
    }

    public void nextCardStatus() {
        this.status = CardStatus.values()[status.ordinal() + 1];
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
