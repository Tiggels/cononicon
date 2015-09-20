package com.github.tiggels.platon.parts;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicLink implements HGLink{

    protected HGHandle subject, predicate;

    protected PlatonicModi modi;

    private String word;

    public PlatonicLink() {
    }

    public PlatonicLink(HGHandle subject) {
        this.subject = subject;
    }

    public PlatonicLink(HGHandle subject, HGHandle predicate) {
        this.subject = subject;
        this.predicate = predicate;
    }

    public PlatonicLink(HGHandle subject, HGHandle predicate, PlatonicModi modi) {
        this.subject = subject;
        this.predicate = predicate;
        this.modi = modi;
    }

    public HGHandle getSubject() {
        return subject;
    }

    public void setSubject(HGHandle subject) {
        this.subject = subject;
    }

    public HGHandle getPredicate() {
        return predicate;
    }

    public void setPredicate(HGHandle predicate) {
        this.predicate = predicate;
    }

    public PlatonicModi getModi() {
        return modi;
    }

    public void setModi(PlatonicModi mod) {
        this.modi = mod;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getArity() {
        return 2;
    }

    public HGHandle getTargetAt(int i) {
        if (i == 0) {
            return subject;
        } else if (i == 1) {
            return predicate;
        } else {
            return null;
        }
    }

    public void notifyTargetHandleUpdate(int i, HGHandle hgHandle) {
        if (i == 0) {
            subject = hgHandle;
        } else if (i == 1) {
            predicate = hgHandle;
        }
    }

    public void notifyTargetRemoved(int i) {
        if (i == 0) {
            subject = null;
        } else if (i == 1) {
            predicate = null;
        }
    }

    public String toString() {
        return subject + "=>" + predicate;
    }
}
