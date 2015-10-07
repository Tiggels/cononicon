package com.github.tiggels.trans;

import com.github.tiggels.Cononicon;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.platons.PlatonicLink;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery;
import org.hypergraphdb.HyperGraph;
import org.hypergraphdb.algorithms.DefaultALGenerator;
import org.hypergraphdb.algorithms.HGBreadthFirstTraversal;
import org.hypergraphdb.algorithms.SimpleALGenerator;
import org.hypergraphdb.util.Pair;

/**
 * Created by Miles on 10/1/15.
 */
public class GraphTran {

    public static void Translate() {

        HyperGraph temp = Cononicon.getTempSpace();

        for (HGHandle handle : temp.findAll(HGQuery.hg.and(HGQuery.hg.type(PlatonicLink.class), HGQuery.hg.disconnected()))) {
            System.out.println("Starting at :" + temp.get(handle).toString());

            HGBreadthFirstTraversal trans =
                    new HGBreadthFirstTraversal(handle, new DefaultALGenerator(
                            temp,
                            HGQuery.hg.type(PlatonicLink.class),
                            HGQuery.hg.type(PlatonicLink.class)
                    ));

            while (trans.hasNext()) {
                Pair<HGHandle, HGHandle> current = trans.next();
                System.out.println("Visited \"" + temp.get(current.getSecond()) + "\" from neighbor link \"" + temp.get(current.getFirst()).toString() + "\"");
            }
        }

        // End by clearing the TempSpace
        clear(Cononicon.getTempSpace());
    }

    public static void clear(HyperGraph db) {
        System.out.println("Purging DB at " + db.getLocation());
        for (HGHandle handel : db.findAll(HGQuery.hg.or(HGQuery.hg.type(PlatonicAtom.class), HGQuery.hg.type(PlatonicLink.class)))) {
            System.out.println("REMOVED : " + db.get(handel).toString());
            db.remove(handel, true);
        }
    }

    public static void forceClear(HyperGraph db) {
        System.out.println("!!!!FORCE!!!! Purging DB at " + db.getLocation());
        for (HGHandle handel : db.findAll(HGQuery.hg.or(HGQuery.hg.type(PlatonicAtom.class), HGQuery.hg.type(PlatonicLink.class)))) {
            db.remove(handel, true);
        }
    }
}
