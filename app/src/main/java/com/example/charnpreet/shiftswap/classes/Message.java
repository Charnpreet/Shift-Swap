package com.example.charnpreet.shiftswap.classes;

public class Message {
    private String message, type, from;
    private long time;

    public Message() {

    }
    public Message(String message, String type, long time,String from){
        this.message = message;
        this.time = time;
        this.type = type;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}