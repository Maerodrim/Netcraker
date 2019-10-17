package com.example.DataRoadsAndWeather.Model;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idMessage;
    @Column(name = "text", unique = false, nullable = true)
    private String text;
    @Column(name = "tag", unique = false, nullable = true)
    private String tag;

    public Message() {
    }

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return idMessage;
    }

    public void setId(Integer id) {
        this.idMessage = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
