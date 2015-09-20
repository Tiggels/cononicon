package com.github.tiggels;


import com.github.tiggels.logic.PlatonicLogic;
import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 9/16/15.
 */

public class CononiconServer {

    static HyperGraph graph;
    static PlatonicLogic logic;
    public static float devolvePower;

    public static void main(String[] args) {
        try {
            System.out.println("LOADING PLATOSPACE-HYPERGRAPH");
            graph = new HyperGraph(System.getProperty("user.dir") + "/conicServer");

            System.out.println("LOADING PLATONIC NLP");
            logic = new PlatonicLogic(graph);

            if (args[0] != null && Float.valueOf(args[0]) > 0 && Float.valueOf(args[0]) < 1) {
                devolvePower = Float.valueOf(args[0]);
            } else if (args[0] != null) {
                System.out.println("DEVOLVE POWER MUST BE GRATER THAN 0 AND LESS THAN 1");
                devolvePower = .5f;
            }
            System.out.println("DEVOLVE POWER: " + devolvePower);

            System.out.println("LOADING COMPLETE");
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            graph.close();
        }

        System.out.println("--- NLP CALL ---");
        logic.analise("Plato is a tall man. Plato waits by the bus-stop. The bus pulls up to the bus-stop. He gets on the bus.");
    }
}
