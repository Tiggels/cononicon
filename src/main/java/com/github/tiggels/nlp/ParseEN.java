package com.github.tiggels.nlp;

import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.trans.GraphTran;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.*;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HyperGraph;

import com.github.tiggels.Cononicon;

import java.io.StringReader;
import java.util.*;

/**
 * Created by Miles on 9/19/15.
 */
public class ParseEN {

    HyperGraph tempSP;

    private final String PCG_MODEL = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

    private final TreebankLanguagePack tlp = new PennTreebankLanguagePack();

    private final GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();

    private final HashMap<String, HGHandle> tempAtoms = new HashMap<String, HGHandle>();

    public ParseEN() {
        tempSP = Cononicon.getTempSpace();
    }

    public void analise(String text) {

        //Create some complicated stanfordNLP objects that I don't understand that well.
        List<CoreLabel> tokens = tokenizerFactory.getTokenizer(new StringReader(text)).tokenize();
        Tree tree = parser.apply(tokens);

        tree.pennPrint(); // Print the pennTree tree

        System.out.println("\nAdding Leaf Atoms");

        for (Tree leaf : tree.getLeaves()) {
            // Get the thing at the base of the tree, and add that to the graph
            // Along with parent POS
            PlatonicAtom atom = new PlatonicAtom(leaf.label().value(), leaf.parent(tree).label().value());
            HGHandle handel = tempSP.add(atom);
            System.out.println("Added new atom: \"" + leaf.label().value() + "\" with type: \"" + leaf.parent(tree).label().value() + "\" @ " + handel.toString());
            // Add the new atoms to a hashmap my name.
            tempAtoms.put(leaf.label().value().toLowerCase(), handel);
        }

        System.out.println("\nFinished Creating Atoms\nAdding Links");

        sap(tree);

        System.out.println("\nFinished Adding Links\nTransitioning information from TempSpace to PlatoSpace");

        GraphTran.Translate();
    }

    private HGHandle sap(Tree tree) {
        if (tree.label().value().equals("ROOT")) { // If the current node is called "ROOT", call this for all its children. This basally excludes the top node.
            for (Tree twig : tree.getChildrenAsList()) {
                sap(twig); // A recursive call, This calls its own function on all its children.
            }
            return tempAtoms.get(tree.label().value().toLowerCase()); // This returns the value of the root node because ... It has to return something ...
        } else if (tree.numChildren() < 1) { // If the tree has no children then it returns its value. Only words have no children, so this gets the value of the words.
            return tempAtoms.get(tree.label().value().toLowerCase());
        } else if (tree.numChildren() == 1) { // if the tree has one child that it calls sap() on that child.
            return sap(tree.firstChild());
        } else { // ELSE if the link is not the ROOT and has more than two children, call sap on each of them, link the result and return the link.
            List<HGHandle> childAtoms = new ArrayList<HGHandle>();
            for (Tree twig : tree.getChildrenAsList()) {
                childAtoms.add(sap(twig));
            }
            System.out.print("\n");
            PlatonicLink link = new PlatonicLink(tree.label().value(), childAtoms);
            System.out.println(link.toString());
            return Cononicon.getTempSpace().add(link);
        }
    }
}
