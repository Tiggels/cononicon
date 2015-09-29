package com.github.tiggels.nlp;

import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.platons.PlatonicAtom;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

import com.github.tiggels.Server;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miles on 9/19/15.
 */
public class ParseEN {

    HyperGraph plato;

    private final static String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

    public ParseEN() {
        plato = Server.getPlatoSpace();
    }

    public void analise(String text) {
        //Parse
        List<CoreLabel> tokens = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
        Tree tree = parser.apply(tokens);

        tree.pennPrint();

        addPlatons(tree);
    }

    private List<HGHandle> addPlatons(Tree tree) {

        List<HGHandle> list = new ArrayList<HGHandle>();

        for (Tree subtree : tree.getChildrenAsList().remove(0)) {

            if (subtree.getChildrenAsList().size() == 0) {
                PlatonicAtom atom = new PlatonicAtom(tree.label().value(), subtree.label().value());
                System.err.println("Added new atom: " + subtree.label().value() + " with " + tree.label().value());
                list.add(plato.add(atom));
            } else {
                List<HGHandle> targets  = addPlatons(subtree);
                HGHandle[] handles = new HGHandle[targets.size()];
                targets.toArray(handles);

                PlatonicLink link = new PlatonicLink(subtree.label().value(), handles);
                System.err.println("Added new link: " + subtree.label().value() + " for: " + handles.toString());
                list.add(plato.add(link));
            }
        }

        return list;
    }
}
