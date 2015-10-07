package com.github.tiggels.platons;

import com.github.tiggels.Cononicon;
import org.assertj.core.util.Lists;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGPlainLink;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miles on 9/19/15.
 */
public class PlatonicLink extends HGPlainLink {

    public String value;

    public PlatonicLink() {
    }

    public PlatonicLink(HGHandle... targets) {
        super(targets);
        this.value = "";
    }

    public PlatonicLink(String value, HGHandle... targets) {
        super(targets);
        this.value = value;
    }

    public PlatonicLink(String value, List<HGHandle> targets) {
        super(targets.toArray(new HGHandle[targets.size()]));
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        String str = "({" + value + "}# ";
        for (int i = 0; i < this.getArity(); i++) {
            str += Cononicon.getPlatoSpace().get(this.getTargetAt(i)).toString();
            if (i < this.getArity() - 1) {
                str += " <<-+->> ";
            }
        }
        str += " )";
        return str;
    }
}
