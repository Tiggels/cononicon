package com.github.tiggels.platons;

import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 9/22/15.
 */
public class HyperAtom {

    HyperGraph graph;

    public HyperAtom() {
    }

    public HyperAtom(HyperGraph graph) {
        this.graph = graph;
    }

    public HyperGraph getGraph() {
        return graph;
    }

    public void setGraph(HyperGraph graph) {
        this.graph = graph;
    }
}
