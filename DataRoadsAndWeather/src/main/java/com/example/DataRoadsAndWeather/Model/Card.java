package com.example.DataRoadsAndWeather.Model;

import com.example.DataRoadsAndWeather.Model.Enum.CardStatus;
import com.example.DataRoadsAndWeather.Model.Enum.ColorCard;

import javax.persistence.*;

@Entity
@Table(name = "Card")
public class Card {
    public Card() {
    }

    public Card(Session session, String nameCard, Integer idSession, Integer dataBegSession, Integer allDevelopment,
                Integer allAnalysis, Integer allTesting, Double money, ColorCard colorCard, Integer priority) {
        this.session=session;
        this.nameCard = nameCard;
        this.dataBegSession = dataBegSession;
        this.allDevelopment = allDevelopment;
        this.allAnalysis = allAnalysis;
        this.allTesting = allTesting;
        this.money = money;
        this.colorCard = colorCard;
        this.priority = priority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "idCard", unique = true, nullable = true)
    private Integer idCard;
    @ManyToOne
    @JoinColumn(name = "idCard", unique = true, nullable = true)
    private Session session;
    @JoinColumn(name = "nameCard", unique = false, nullable = true)
    private String nameCard;
    @JoinColumn(name = "dataBegSession", unique = false, nullable = true)
    private Integer dataBegSession;
    @JoinColumn(name = "dataEndSession", unique = false, nullable = true)
    private Integer dataEndSession;
    @JoinColumn(name = "Dev", unique = false, nullable = true)
    private Integer development = 0;
    @JoinColumn(name = "AllDev", unique = false, nullable = true)
    private Integer allDevelopment;
    @JoinColumn(name = "Anal", unique = false, nullable = true)
    private Integer analysis = 0;
    @JoinColumn(name = "AllAnal", unique = false, nullable = true)
    private Integer allAnalysis;
    @JoinColumn(name = "Test", unique = false, nullable = true)
    private Integer testing = 0;
    @JoinColumn(name = "AllTest", unique = false, nullable = true)
    private Integer allTesting;
    @JoinColumn(name = "Money", unique = false, nullable = true)
    private Double money;
    @Column(name = "Color", unique = false, nullable = true)
    private ColorCard colorCard;
    @JoinColumn(name = "CardStatus", unique = false, nullable = true)
    private CardStatus status = CardStatus.Selected;
    @JoinColumn(name = "Priority", unique = false, nullable = true)
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
}
