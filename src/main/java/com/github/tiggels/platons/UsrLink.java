package com.github.tiggels.platons;

import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

import java.util.List;

/**
 * Created by Miles on 10/16/15.
 */
public class UsrLink extends HGPlainLink{
    
    String usr;

    public UsrLink() {
    }

    public UsrLink(HGHandle... targets) {
        super(targets);
        this.usr = "";
    }

    public UsrLink(String usr, HGHandle... targets) {
        super(targets);
        this.usr = usr;
    }

    public UsrLink(String usr, List<HGHandle> targets) {
        super(targets.toArray(new HGHandle[targets.size()]));
        this.usr = usr;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }
}
