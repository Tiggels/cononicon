package com.github.tiggels.trans;

import com.github.tiggels.Cononicon;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.platons.PrintLink;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;
import org.hypergraphdb.HGQuery;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.algorithms.HGBreadthFirstTraversal;
import org.hypergraphdb.algorithms.HGDepthFirstTraversal;
import org.hypergraphdb.algorithms.SimpleALGenerator;
import org.hypergraphdb.util.Pair;

import java.awt.print.Book;

/**
 * Created by Miles on 10/1/15.
 */
public class ITran {

    public static void Translate() {

        HyperGraph temp = Cononicon.getTempSpace();
        HyperGraph plato = Cononicon.getPlatoSpace();

        for (HGHandle handle : temp.findAll(HGQuery.hg.and(HGQuery.hg.type(PlatonicAtom.class), HGQuery.hg.eq("word", "ROOT")))) {
            HGBreadthFirstTraversal trans = new HGBreadthFirstTraversal(handle, new SimpleALGenerator(temp));
            while (trans.hasNext()) {
                Pair<HGHandle, HGHandle> current = trans.next();
                System.out.print("Visited \"" + temp.get(current.getSecond()) + "\" from neighbor link \"");
                PrintLink.printFancy(temp, (HGLink) temp.get(current.getFirst()));
                System.out.print("\"\n");
            }
        }

        // End by clearing the TempSpace
        clear(Cononicon.getTempSpace());
    }

    public static void clear(HyperGraph db) {
        System.out.println("Purging DB at " + db.getLocation());
        for (HGHandle handel : db.findAll(HGQuery.hg.and(HGQuery.hg.type(PlatonicAtom.class)))) {
            System.out.println("REMOVED : " + db.get(handel).toString());
            db.remove(handel, true);
        }
    }
}
