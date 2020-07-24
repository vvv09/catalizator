package com.valunskii.catalizator.domain;

import org.springframework.data.annotation.Id;


public class Message {
    @Id
    private Long id;
    private String data;


    public Message(String data) {
        this.data = data;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
