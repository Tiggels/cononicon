package com.github.tiggels;

import com.github.tiggels.nlp.ParseEN;
import com.github.tiggels.trans.ITran;
import net.sf.extjwnl.data.*;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.data.list.PointerTargetTree;
import net.sf.extjwnl.data.relationship.AsymmetricRelationship;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;
import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HyperGraph;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Miles on 9/16/15.
 */

public class Cononicon {

    private static final String PLATO_SPACE = "./server/data/graphs/ps";
    private static final String QUERY_SPACE = "./server/data/graphs/qs";
    private static final String META_SPACE = "./server/data/graphs/ms";
    private static final String TEMP_SPACE = "./server/data/graphs/ts";

    public static float devolvePower;

    public static void main(String[] args) {

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

        ITran.clear(getTempSpace());

        System.out.println("CONONICON LOADED");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            parse.analise(scanner.nextLine());
        }
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

    public static HyperGraph getTempSpace() {
        return HGEnvironment.get(TEMP_SPACE);
    }
}
