/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.teriaca.chat_gui_server;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mattiateriaca
 */
public class Message {

    String body;
    String receiver;
    String tag;
    String sender;
    String ora;

    public Message() {
    }

    public Message(String body, String receiver, String tag, String sender) {
        this.body = body;
        this.receiver = receiver;
        this.tag = tag;
        this.sender = sender;
    }

    public String getOra() {
        return ora;
    }

    public Message(String body, String username) {
        this.body = body;
        this.sender = username;
        this.tag = "!";
        this.ora = null;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
