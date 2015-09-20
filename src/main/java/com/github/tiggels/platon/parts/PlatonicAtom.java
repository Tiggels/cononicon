package com.github.tiggels.platon.parts;

import com.github.tiggels.CononiconServer;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicAtom implements java.io.Serializable {

    private float power;

    private String word;

    public PlatonicAtom() {
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
        power *= CononiconServer.devolvePower;
    }
}
