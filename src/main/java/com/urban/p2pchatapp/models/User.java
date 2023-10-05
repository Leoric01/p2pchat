package com.urban.p2pchatapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nick;
    private boolean isLogged;

    @OneToMany(mappedBy = "user")
    private List<Message> messages;
    public User(String nick) {
        this.nick = nick;
        this.isLogged = false;
    }

    public User() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
