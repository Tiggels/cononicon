package com.github.tiggels;

import org.hypergraphdb.HGConfiguration;
import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.app.wordnet.HGWordNetLoader;
import org.hypergraphdb.app.wordnet.SemTools;
import org.hypergraphdb.app.wordnet.WNGraph;
import org.hypergraphdb.app.wordnet.WNStat;
import org.hypergraphdb.app.wordnet.data.SynsetLink;
import org.hypergraphdb.app.wordnet.data.VerbSynsetLink;
import org.hypergraphdb.app.wordnet.data.Word;

import java.util.List;

/**
 * Created by Miles on 9/16/15.
 */

public class Server {

    private static final String PLATO_SPACE = "./server/data/graphs/ps";
    private static final String QUERY_SPACE = "./server/data/graphs/qs";
    private static final String META_SPACE = "./server/data/graphs/ms";
    private static final String WORD_NET = "./server/data/graphs/wn";

    public static float devolvePower;

    public static void main(String[] args) {
        // Test to see if a wordnet database is loaded and if not creates one.
        // Loading a new wordnet can take a while, so its best to keep the file around.
        if (!HGEnvironment.exists(WORD_NET)) {

            System.err.println("No WordNet File Found, creating one...");

            // Set Transactions to false for speed reasons
            // TODO: make sure this is a good idea.
            HGConfiguration config = new HGConfiguration();
            config.setTransactional(false);

            // Create a new graph
            HyperGraph wnGraph = HGEnvironment.get(WORD_NET, config);

            long startTime = System.currentTimeMillis();

            // Instantiate HGWordNet Provided Loader and load EJWL (Extended Java WordNet Library)
            HGWordNetLoader loader = new HGWordNetLoader();
            loader.setDictionaryLocation("/Users/Miles/IdeaProjects/cononicon/src/main/resources/dict"); // TODO: CHANGE FROM ABS PATH TO LOC PATH
            loader.loadWordNet(wnGraph);

            wnGraph.close(); // Close the graph because you MUST ALLAYS CLOSE THE GRAPH

            long endTime = System.currentTimeMillis();
            System.out.println("Completed in " + (endTime - startTime) / 1000 + " seconds.");
        }

        // Quick test of the database and start sequence,
        // IMPORTANT: everything else in this class is nonessential
        WNGraph wnGraph = getWordNetUtil(); // Get special WnGraph

        long startTime = System.currentTimeMillis();

        HGHandle starting = wnGraph.findWord("start"); // Find links with "start"
        List<HGHandle> senses = wnGraph.getNounSenses(starting);

        System.err.println("Searched in in " + (System.currentTimeMillis() - startTime) + " milliseconds.");

        System.out.print("\n" + "--------------------------------------------------------\n" + "\n" + "\n"); // Formatting

        for (HGHandle HGword : senses) {
            SynsetLink link = wnGraph.getGraph().get(HGword); // Read though the links and print the info
            System.out.println(link.getGloss());
        }

        // It looks broken but it works in terminal
        System.out.println("\n" +
                "\n" +
                "--------------------------------------------------------\n" +
                "                                                        \n" +
                " .M\"\"\"bgd MMP\"\"MM\"\"YMM   db      `7MM\"\"\"Mq. MMP\"\"MM\"\"YMM\n" +
                ",MI    \"Y P'   MM   `7  ;MM:       MM   `MM.P'   MM   `7\n" +
                "`MMb.          MM      ,V^MM.      MM   ,M9      MM     \n" +
                "  `YMMNq.      MM     ,M  `MM      MMmmdM9       MM     \n" +
                ".     `MM      MM     AbmmmqMA     MM  YM.       MM     \n" +
                "Mb     dM      MM    A'     VML    MM   `Mb.     MM     \n" +
                "P\"Ybmmd\"     .JMML..AMA.   .AMMA..JMML. .JMM.  .JMML.   \n" +
                "                                                        \n" +
                "--------------------------------------------------------\n" +
                "                                                        ");

        System.out.println("CONONICON LOADED");
    }

    public static HyperGraph getPlatoSpace() {
        return HGEnvironment.get(PLATO_SPACE);
    }

    public static HyperGraph getQuerySpace() {
        return HGEnvironment.get(QUERY_SPACE);
    }

    public static HyperGraph getMetaSpace() {
        return HGEnvironment.get(META_SPACE);
    }

    public static HyperGraph getWordNet() {
        return HGEnvironment.get(WORD_NET);
    }

    public static  WNGraph getWordNetUtil() {
        return new WNGraph(getWordNet());
    }

    public static SemTools getSemTools() {
        return new SemTools(getWordNet());
    }
}
