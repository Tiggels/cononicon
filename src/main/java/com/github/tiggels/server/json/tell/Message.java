package com.github.tiggels.server.json.tell;

/**
 * Created by Miles on 10/15/15.
 */
public class Message {

    String message;

    String uid;

    public Message() {
    }

    public Message(String message, String uid) {
        this.message = message;
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
