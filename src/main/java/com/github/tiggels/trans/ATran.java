package com.github.tiggels.trans;

import com.github.tiggels.Cononicon;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery;
import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 10/1/15.
 */
public class ATran {

    public static void Translate() {

        // End by clearing the TempSpace
        clear(Cononicon.getTempSpace());
    }

    public static void clear(HyperGraph db) {
        System.out.println("Purging DB at " + db.getLocation());
        for (HGHandle handel : db.findAll(HGQuery.hg.all())) {
            db.remove(handel, false);
        }
    }
}
