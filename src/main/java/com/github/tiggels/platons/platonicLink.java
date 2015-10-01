package com.github.tiggels.platons;

import com.github.tiggels.Server;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;
import org.hypergraphdb.HGPlainLink;
import org.hypergraphdb.HGValueLink;

import java.util.List;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicLink extends HGPlainLink {

    protected String value;

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
}
