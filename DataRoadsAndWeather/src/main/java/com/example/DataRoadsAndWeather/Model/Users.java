package com.example.DataRoadsAndWeather.Model;

import com.example.DataRoadsAndWeather.Dto.View.View;
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
    @Column(name = "position", unique = false, nullable = true)
    @JsonView(View.USERS.class)
    private String role;
    @ManyToMany
    @JsonView(View.SESSION.class)
    @Column(name = "Session", unique = false, nullable = true)
    private Set<Session> Session;

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

    public Set<Session> getSession() {
        return Session;
    }

    public void addSession(Session session) {
        this.Session.add(session);
    }

    public void removeSession(Session session) {
        this.Session.remove(session);
    }

    public boolean testSession(Session session) {
        return this.Session.contains(session);
    }

}
