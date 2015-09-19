package com.github.tiggels;


import com.github.tiggels.logic.PlatonicLogic;
import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 9/16/15.
 */

public class Main {

    static HyperGraph graph;
    static PlatonicLogic logic;

    public static void main(String[] args) {
        try {
            System.out.println("LOADING PLATOSPACE-HYPERGRAPH");
            graph = new HyperGraph(System.getProperty("user.dir") + "/conicServer");

            System.out.println("LOADING PLATONIC NLP");
            logic = new PlatonicLogic(graph);

            System.out.println("LOADING COMPLETE");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            graph.close();
        }

        System.out.println("--- NLP CALL ---");
        logic.analise("Plato is a tall man. Plato waits by the bus-stop. The bus pulls up the the bus-stop. He gets on the bus.");
    }
}
