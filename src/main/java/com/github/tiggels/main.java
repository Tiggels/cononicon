package com.github.tiggels;


import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 9/16/15.
 */

public class main {

    public static void main(String[] args) {
        HyperGraph graph = new HyperGraph(System.getProperty("user.dir").toString());
        String hello = graph.get(graph.add("Hello World"));
        System.out.println(hello.toLowerCase());
        graph.close();
    }
}
