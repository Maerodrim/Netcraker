package com.example.GameNetcreckerData.Model;

import com.example.GameNetcreckerData.Dto.View.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class Users {
    public Users() {
    }

    public Users(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Day=8;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.USERS.class)
    private Integer idUsers;

    @Column(name = "name", unique = false, nullable = true)
    @JsonView(View.USERS.class)
    private String name;

    @Column(name = "email", unique = true, nullable = true)
    @JsonView(View.USERS.class)
    private String email;

    @Column(name = "Password", unique = false, nullable = true)
    private String password;

    @Column(name = "position", unique = false, nullable = false)
    @JsonView(View.USERS.class)
    private String role;

    @OneToMany
    @Column(name = "Card",unique = false, nullable = true)
    @JsonView(View.CARD.class)
    private Set<Card> card;

    @Column(name = "Day", unique = false, nullable = true)
    @JsonView(View.USERS.class)
    private Integer Day;

    @Column(name = "idGameTable ", unique = false, nullable = true)
    @JsonView(View.GAMETABLE.class)
    private Integer idGameTable;

    public void setGameTable(Integer gameTable) {
        this.idGameTable = gameTable;
    }

    public Integer getGameTable() {
        return idGameTable;
    }

    public Integer getDay() {
        return Day;
    }

    public void setDay(Integer day) {
        Day = day;
    }

    public void newDay() {
       this.Day += 1;
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

    public Integer getIdUsers() {
        return idUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Card> getCard() {
        return card;
    }

    public void setCard(Set<Card> card) {
        this.card = card;
    }

}
