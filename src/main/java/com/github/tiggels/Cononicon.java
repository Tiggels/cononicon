package com.github.tiggels;

import com.github.tiggels.nlp.Parse;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.server.WebEntry;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Slf4jLog;
import org.eclipse.jetty.util.log.StdErrLog;
import org.glassfish.jersey.servlet.ServletContainer;
import org.hypergraphdb.HGEnvironment;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery;
import org.hypergraphdb.HyperGraph;

/**
 * Created by Miles on 9/16/15.
 */

public class Cononicon {

    private static final String PLATO_SPACE = "./server/data/graphs/ps";
    private static final String QUERY_SPACE = "./server/data/graphs/qs";
    private static final String META_SPACE = "./server/data/graphs/ms";
    private static final String TEMP_SPACE = "./server/data/graphs/ts";

    private static HyperGraph PS;
    private static HyperGraph QS;
    private static HyperGraph MS;
    private static HyperGraph TS;

    public static float devolvePower;

    private static LexicalizedParser parser;

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/cononicon");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                WebEntry.class.getCanonicalName());

        try {

            parser = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");

            PS = HGEnvironment.get(PLATO_SPACE);
            QS = HGEnvironment.get(QUERY_SPACE);
            MS = HGEnvironment.get(META_SPACE);
            TS = HGEnvironment.get(TEMP_SPACE);

            jettyServer.start();
            jettyServer.join();

        } finally {
            jettyServer.destroy();
            System.out.println("Closing Spaces");
            PS.close();
            QS.close();
            MS.close();
            TS.close();
            System.out.println("Closed");
        }
    }

    public static HyperGraph getPlatoSpace() {
        return PS;
    }

    public static HyperGraph getQuerySpace() {
        return QS;
    }

    public static HyperGraph getMetaSpace() {
        return MS;
    }

    public static HyperGraph getTempSpace() {
        return TS;
    }

    public static LexicalizedParser getParser() {
        return parser;
    }

    public static void clear(HyperGraph db) {
        System.out.println("Purging DB at " + db.getLocation());
        for (HGHandle handel : db.findAll(HGQuery.hg.or(HGQuery.hg.type(PlatonicAtom.class), HGQuery.hg.type(PlatonicLink.class)))) {
            System.out.println("REMOVED : " + db.get(handel).toString());
            db.remove(handel, true);
        }
    }
}
