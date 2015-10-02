package com.github.tiggels.platons;

import org.hypergraphdb.HGLink;
import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 10/1/15.
 */
public class PrintLink {

    public static void printFancy(HyperGraph graph, HGLink link) {
        for (int i = 0; i < link.getArity(); i++) {
            System.out.print(graph.get(link.getTargetAt(i)).toString());
            if (i != link.getArity() - 1) {
                System.out.print(" <-> ");
            }
        }
    }
}
