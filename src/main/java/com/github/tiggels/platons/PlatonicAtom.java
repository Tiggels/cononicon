package com.github.tiggels.platons;

import com.github.tiggels.Cononicon;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicAtom implements java.io.Serializable {

    private float power;
    private String word;
    private String pos;

    public PlatonicAtom() {
    }

    public PlatonicAtom(String word, String pos) {
        this.word = word;
        this.pos = pos;
    }

    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isLive() {
        if (power > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void devolve() {
        power *= Cononicon.devolvePower;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return word + "@" + power;
    }
}
