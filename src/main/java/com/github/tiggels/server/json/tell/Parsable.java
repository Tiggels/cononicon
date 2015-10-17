package com.github.tiggels.server.json.tell;

/**
 * Created by Miles on 10/15/15.
 */
public class Parsable {

    private String parse;

    private String uid;

    public Parsable() {
        parse = "";
        uid = "";
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean test() {
        return true;
    }
}
