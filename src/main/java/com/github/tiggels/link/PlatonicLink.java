package com.github.tiggels.link;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

/**
 * Created by Miles on 9/18/15.
 */
public class PlatonicLink extends HGPlainLink {

    float attenuation;

    public PlatonicLink() {
    }

    public PlatonicLink(HGHandle... targets) {
        super(targets);
    }
}