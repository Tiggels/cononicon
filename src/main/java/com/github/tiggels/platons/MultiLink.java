package com.github.tiggels.platons;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGLink;

/**
 * Created by Miles on 9/19/15.
 */
public class MultiLink implements HGLink{

    public int getArity() {
        return 0;
    }

    public HGHandle getTargetAt(int i) {
        return null;
    }

    public void notifyTargetHandleUpdate(int i, HGHandle hgHandle) {

    }

    public void notifyTargetRemoved(int i) {

    }
}
