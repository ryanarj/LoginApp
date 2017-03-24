package com.example.kr.firstapp;

/*Constructor for the chatmessager*/
public class ChatMessage {

    String name;
    String message;

    public ChatMessage(){

    }

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage(){
        return message;
    }
}
