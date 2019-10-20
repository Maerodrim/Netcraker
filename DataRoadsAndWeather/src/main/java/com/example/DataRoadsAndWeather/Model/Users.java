package com.example.DataRoadsAndWeather.Model;

import javax.persistence.*;

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
    private Integer idUsers;
    @Column(name = "name", unique = false, nullable = true)
    private String name;
    @Column(name = "email", unique = false, nullable = true)
    private String email;
    @Column(name = "Password", unique = false, nullable = true)
    private String password;
    @Column(name = "position", unique = false, nullable = true)
    private String role;
  /*  @OneToMany
    @Column(name = "chat", unique = false, nullable = true)
    private Set<Chat> chat;
    @OneToMany
    @Column(name = "idSession", unique = false, nullable = true)
    private Set<Integer> idSession;*/

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
/*
    public Set<Integer> getIdSession() {
        return idSession;
    }

    public void addIdSession(Integer idSession) {
        this.idSession.add(idSession);
    }

    public void removeIdSession(Integer idSession) {
        this.idSession.remove(idSession);
    }

    public boolean testIdSession(Integer idSession) {
        return this.idSession.contains(idSession);
    }

    public void addChat(Chat chat) {
        this.chat.add(chat);
    }

    public void removeChat(Chat chat) {
        this.chat.remove(chat);
    }

    public boolean testChat(Chat chat) {
        return this.chat.contains(chat);
    }*/
}
