package com.github.tiggels.server;

import com.github.tiggels.Cononicon;
import com.github.tiggels.nlp.Parse;
import com.github.tiggels.platons.PlatonicAtom;
import com.github.tiggels.platons.PlatonicLink;
import com.github.tiggels.server.json.tell.Message;
import com.github.tiggels.server.json.tell.Parsable;
import org.assertj.core.util.Strings;
import org.hypergraphdb.HGHandle;
import org.hypergraphdb.HGQuery;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Miles on 10/13/15.
 */

@Path("/api")
public class WebEntry {

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String status() {
        return  "  ______   .__   __.  __       __  .__   __.  _______ \n" +
                " /  __  \\  |  \\ |  | |  |     |  | |  \\ |  | |   ____|\n" +
                "|  |  |  | |   \\|  | |  |     |  | |   \\|  | |  |__   \n" +
                "|  |  |  | |  . `  | |  |     |  | |  . `  | |   __|  \n" +
                "|  `--'  | |  |\\   | |  `----.|  | |  |\\   | |  |____ \n" +
                " \\______/  |__| \\__| |_______||__| |__| \\__| |_______|\n" +
                "                                                      \n";
    }

    @POST
    @Path("tell~{usr}")
    @Consumes(MediaType.TEXT_PLAIN)
    public void tellUSR(String str, @PathParam("usr") String usr) {

        Parse.analise(str, usr);
    }

    @GET
    @Path("atom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atomGet() {

        List<PlatonicAtom> atoms = Cononicon.getPlatoSpace().getAll(
                HGQuery.hg.and(
                        HGQuery.hg.type(PlatonicAtom.class)
                )
        );

        PlatonicAtom[] arrayAtom = new PlatonicAtom[atoms.size()];
        arrayAtom = atoms.toArray(arrayAtom);

        return Response.status(200).entity(arrayAtom).build();
    }

    @DELETE
    @Path("atom")
    @Produces(MediaType.TEXT_PLAIN)
    public String atomDelete() { // TODO: MAKE DELETE NOT DESTROY

        Cononicon.clear(Cononicon.getPlatoSpace());

        return "  ________________  __  ________   _____  ________________ \n" +
                " /_  __/ ____/ __ \\/  |/  /  _/ | / /   |/_  __/ ____/ __ \\\n" +
                "  / / / __/ / /_/ / /|_/ // //  |/ / /| | / / / __/ / / / /\n" +
                " / / / /___/ _, _/ /  / // // /|  / ___ |/ / / /___/ /_/ / \n" +
                "/_/ /_____/_/ |_/_/  /_/___/_/ |_/_/  |_/_/ /_____/_____/  \n" +
                "                                                           \n";
    }

    @GET
    @Path("link")
    @Produces(MediaType.APPLICATION_JSON)
    public Response linkGet() {

        List<PlatonicLink> links = Cononicon.getPlatoSpace().getAll(
                HGQuery.hg.and(
                        HGQuery.hg.type(PlatonicLink.class)
                )
        );

        PlatonicLink[] arrayAtom = new PlatonicLink[links.size()];
        arrayAtom = links.toArray(arrayAtom);

        return Response.status(200).entity(arrayAtom).build();
    }

    @DELETE
    @Path("link")
    @Produces(MediaType.TEXT_PLAIN)
    public String linkDelete() { // TODO: MAKE DELETE NOT DESTROY

        Cononicon.clear(Cononicon.getPlatoSpace());

        return "  ________________  __  ________   _____  ________________ \n" +
                " /_  __/ ____/ __ \\/  |/  /  _/ | / /   |/_  __/ ____/ __ \\\n" +
                "  / / / __/ / /_/ / /|_/ // //  |/ / /| | / / / __/ / / / /\n" +
                " / / / /___/ _, _/ /  / // // /|  / ___ |/ / / /___/ /_/ / \n" +
                "/_/ /_____/_/ |_/_/  /_/___/_/ |_/_/  |_/_/ /_____/_____/  \n" +
                "                                                           \n";
    }
}
