package com.github.tiggels.platons;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicLink extends HGPlainLink {

    protected String value;

    public PlatonicLink() {
    }

    public PlatonicLink(HGHandle... targets) {
        super(targets);
        this.value = "";
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
}
