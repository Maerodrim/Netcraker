package com.example.GameNetcreckerData.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Chat")
public class Chat {

    public Chat(String name, Set<Users> idUsers, Set<Message> messages) {
        this.name = name;
        this.idUsers = idUsers;
    }

    public Chat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idChat", unique = true, nullable = true)
    private Integer idChat;
    @Column(name = "name", unique = false, nullable = true)
    private String name;
    @OneToMany
    @Column(name = "idUsers", unique = false, nullable = true)
    private Set<Users> idUsers;
    @OneToMany
    @Column(name = "messages", unique = false, nullable = true)
    private Set<Message> messages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Users> getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Set<Users> idUsers) {
        this.idUsers = idUsers;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public void addIdMessage(Message idSession) {
        this.messages.add(idSession);
    }

    public void removeIdMessage(Message idSession) {
        this.messages.remove(idSession);
    }

    public boolean testIdMessage(Message idSession) {
        return this.messages.contains(idSession);
    }
}
