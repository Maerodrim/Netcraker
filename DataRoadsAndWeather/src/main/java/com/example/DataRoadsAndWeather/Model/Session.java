package com.example.DataRoadsAndWeather.Model;


import com.example.DataRoadsAndWeather.Dto.View.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Session")
public class Session {
    public Session(){}

    public Session(String nameSession, Set<Users> users) {
        this.nameSession = nameSession;
        this.users = users;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSession", unique = true, nullable = true)
    @JsonView(View.REST.class)
    private Integer idSession;
    @Column(name = "nameSession", unique = true, nullable = true)
    @JsonView(View.REST.class)
    private String nameSession;
    @Column(name = "dataSession", unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Integer dataSession;
    @Column(name = "Dev", unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Integer Development;
    @Column(name = "Anal", unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Integer Analysis;
    @Column(name = "Test", unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Integer Testing;
    @OneToMany
    @Column(name = "Users", unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Set<Users> users;
    @OneToMany
    @Column(name = "Card",unique = false, nullable = true)
    @JsonView(View.REST.class)
    private Set<Card> card;

    public Integer getIdSession() {
        return idSession;
    }

    public String getNameSession() {
        return nameSession;
    }

    public void setNameSession(String nameSession) {
        this.nameSession = nameSession;
    }

    public Integer getDataSession() {
        return dataSession;
    }

    public void setDataSession(Integer dataSession) {
        this.dataSession = dataSession;
    }

    public void addCard(Card card) {
        this.card.add(card);
    }

    public void removeCard(Card card) {
        this.card.remove(card);
    }

    public boolean testCard(Card card) {
        return this.card.contains(card);
    }

    public void addUsers(Users users) {
        this.users.add(users);
    }

    public void removeUsers(Users users) {
        this.users.remove(users);
    }

    public boolean testUsers(Users users) {
        return this.users.contains(users);
    }

    public Integer getDevelopment() {
        return Development;
    }

    public void setDevelopment(Integer development) {
        Development = development;
    }

    public Integer getAnalysis() {
        return Analysis;
    }

    public void setAnalysis(Integer analysis) {
        Analysis = analysis;
    }

    public Integer getTesting() {
        return Testing;
    }

    public void setTesting(Integer testing) {
        Testing = testing;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public Set<Card> getCard() {
        return card;
    }

    public void setCard(Set<Card> card) {
        this.card = card;
    }
}
