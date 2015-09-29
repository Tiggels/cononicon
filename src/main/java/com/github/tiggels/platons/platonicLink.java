package com.github.tiggels.platons;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;
import org.hypergraphdb.HGPlainLink;
import org.hypergraphdb.HGValueLink;

import java.util.List;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicLink extends HGPlainLink {

    private String value;

    public PlatonicLink() {
    }

    public PlatonicLink(String value, HGHandle... targets) {
        super(targets);
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public String toString() {
        StringBuilder b = new StringBuilder(this.value == null?"null":this.value);
        b.append('[');

        for(int i = 0; i < this.getArity(); ++i) {
            b.append(this.outgoingSet[i]);
            if(i < this.getArity() - 1) {
                b.append(",");
            }
        }

        b.append(']');
        return b.toString();
    }
}
