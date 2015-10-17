package com.github.tiggels;

import com.github.tiggels.nlp.ParseEN;
import com.github.tiggels.trans.GraphTran;
import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HyperGraph;

import java.util.Scanner;

/**
 * Created by Miles on 9/16/15.
 */

public class Cononicon {

    private static final String PLATO_SPACE = "./server/data/graphs/ps";
    private static final String QUERY_SPACE = "./server/data/graphs/qs";
    private static final String META_SPACE = "./server/data/graphs/ms";
    private static final String TEMP_SPACE = "./server/data/graphs/ts";

    private static HyperGraph PS;
    private static HyperGraph QS;
    private static HyperGraph MS;
    private static HyperGraph TS;


    public static float devolvePower;

    public static void main(String[] args) {

        try {
            PS = HGEnvironment.get(PLATO_SPACE);
            QS = HGEnvironment.get(QUERY_SPACE);
            MS = HGEnvironment.get(META_SPACE);
            TS = HGEnvironment.get(TEMP_SPACE);

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

            ParseEN parse = new ParseEN();

            System.out.println("CONONICON LOADED");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String next = scanner.nextLine();
                if (next.toLowerCase().equals("/exit")) {
                    System.out.println("Exiting");
                    break;
                } else if (next.toLowerCase().equals("/clear")) {
                    GraphTran.clear(PS);
                    GraphTran.clear(QS);
                    GraphTran.clear(MS);
                    GraphTran.clear(TS);
                } else {
                    parse.analise(next);
                }
            }
        } finally {
            System.out.println("Closing Spaces");
            PS.close();
            QS.close();
            MS.close();
            TS.close();
            System.out.println("Closed");
        }
    }

    public static HyperGraph getPlatoSpace() {
        return PS;
    }

    public static HyperGraph getQuerySpace() {
        return QS;
    }

    public static HyperGraph getMetaSpace() {
        return MS;
    }

    public static HyperGraph getTempSpace() {
        return TS;
    }
}
