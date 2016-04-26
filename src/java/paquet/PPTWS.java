/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquet;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author ivan
 */
@Path("PPT")
public class PPTWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PPTWS
     */
    public PPTWS() {
    }

    /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @POST
    @Path("iniciarJoc/")
    @Produces("text/plain")
    public String iniciarJoc(String codiPartida) {
        //TODO return proper representation object
        return codiPartida;
    }
    
    /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @GET
    @Path("consultarEstatPartida/")
    @Produces("text/plain")
    public String consultarEstatPartida(@QueryParam("c") String codiPartida) {
        //TODO return proper representation object
        return codiPartida;
    }

    /**
     * PUT method for updating or creating an instance of PPTWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("moureJugador/")
    @Consumes("text/plain")
    public void moureJugador(String content) {
    }
    
        /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @DELETE
    @Path("acabarJoc/")
    @Produces("text/plain")
    public String acabarJoc(String codiPartida) {
        //TODO return proper representation object
        
        return "hola rest";
    }
    
}
