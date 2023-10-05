package com.urban.p2pchatapp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Message(String text) {
        this.text = text;
        timestamp = String.valueOf(LocalDateTime.now());
    }


    public Message() {
    }

    public Message(Long id, String text, String timestamp, User user) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
