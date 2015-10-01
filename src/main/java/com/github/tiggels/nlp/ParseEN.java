package com.github.tiggels.nlp;

import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.trans.ATran;
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
        Tree parse = parser.apply(Sentence.toWordList(text.split(" ")));
        GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
        Collection<TypedDependency> tdl = gs.typedDependenciesCCprocessed();

        tree.pennPrint(); // Print the pennTree tree

        System.out.println("\nAdding Root Atom\nAdded new atom: ROOT");

        PlatonicAtom root = new PlatonicAtom("ROOT","ROOT");
        tempAtoms.put("ROOT",tempSP.add(root));

        System.out.println("\nAdding Leaf Atoms");

        for (Tree leaf : tree.getLeaves()) {
            // Get the thing at the base of the tree, and add that to the graph
            // Along with parent POS
            PlatonicAtom atom = new PlatonicAtom(leaf.label().value(), leaf.parent(tree).label().value());
            HGHandle handel = tempSP.add(atom);
            System.out.println("Added new atom: \"" + leaf.label().value() + "\" with type: \"" + leaf.parent(tree).label().value() + "\" @ " + handel.toString());
            // Add the new atoms to a hashmap my name.
            tempAtoms.put(leaf.label().value(), handel);
        }

        System.out.println("\nFinished Creating Atoms\nAdding Links");

        for (TypedDependency dep : tdl) {

            // Remove Punctuation
            String depV = dep.dep().value().replaceAll("[?!.$\"]*", "");
            String govV = dep.gov().value().replaceAll("[?!.$\"]*", "");

            // Create a link from hashmap info
            PlatonicLink link = new PlatonicLink(
                    dep.reln().getLongName(),
                    tempAtoms.get(depV),
                    tempAtoms.get(govV)
            );

            HGHandle handel = Cononicon.getTempSpace().add(link);
            System.out.println("Added new link: \"" + depV + "\" <-" + dep.reln().getLongName() + "(" + dep.reln().getShortName() + ")" + "-> \"" + govV + "\" @ " + handel);
        }

        System.out.println("\nFinished Adding Links");
        System.out.println("Transitioning information from TempSpace to PlatoSpace");
    }
}
