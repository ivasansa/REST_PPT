/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquet;

import java.util.ArrayList;
import javax.jws.WebParam;
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
    ArrayList<Partida> llistaPartides = new ArrayList<Partida>();
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
    public String iniciarJoc(@QueryParam("c") int codiPartida, @QueryParam("j") String jug1) {
         
        boolean flag = false;
        
        for(int i = 0; i < llistaPartides.size(); ++i){
            if(llistaPartides.get(i).getID() == codiPartida){
                llistaPartides.get(i).setJUG2(jug1);
                flag = true;
                return "false";
            }
        }
        if(!flag){
            Partida p = new Partida(codiPartida, jug1);
            llistaPartides.add(p);
            return "true";
        }
        

        return null;
    }
    
    /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @GET
    @Path("consultarEstatPartida/")
    @Produces("text/plain")
    public String consultarEstatPartida(@QueryParam("c") int codiPartida) {
        
        for(int i = 0; i < llistaPartides.size(); ++i){
            if(llistaPartides.get(i).getID() == codiPartida){
                
               
                /*
                0 No tirat
                1 Pedra
                2 Paper
                3 Tissora
                */
                Partida p = llistaPartides.get(i);
                Jugador j1 = p.getJUG1();
                Jugador j2 = p.getJUG2();

                
                
                if((j1.getMoviment() != 0)&&(j2.getMoviment() != 0)){
                    switch (j1.getMoviment()) {
                        case 1:
                            switch (j2.getMoviment()) {
                                case 1:
                                    //J1 Pedra
                                    //J2 Pedra
                                    //Empat
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 2:
                                    //J1 Pedra
                                    //J2 Paper
                                    //J2 guanya
                                    
                                    p.setEstat(p.getEstat() + 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 3:
                                    //J1 Pedra
                                    //J2 Tisora
                                    //J1 guanya
                                    
                                    p.setEstat(p.getEstat() - 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            break;
                            
                        case 2:
                            switch (j2.getMoviment()) {
                                case 1:
                                    //J1 Paper
                                    //J2 Pedra
                                    //J1 Guanya
                                    p.setEstat(p.getEstat() - 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 2:
                                    //J1 Paper
                                    //J2 Paper
                                    //Empat
                                    
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 3:
                                    //J1 Paper
                                    //J2 Tisora
                                    //J2 guanya
                                    
                                    p.setEstat(p.getEstat() + 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            break;
                        case 3:
                            switch (j2.getMoviment()) {
                                case 1:
                                    //J1 Tisora
                                    //J2 Pedra
                                    //J2 Guanya
                                    p.setEstat(p.getEstat() + 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 2:
                                    //J1 Tisora
                                    //J2 Paper
                                    //J1 Guanya
                                    p.setEstat(p.getEstat() - 1);
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                case 3:
                                    //J1 Tisora
                                    //J2 Tisora
                                    //empat
                                    
                                    j1.setMoviment(0);
                                    j2.setMoviment(0);
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            break; 
                         
                            
                        default:
                            throw new AssertionError();
                    }
                    return String.valueOf(p.getEstat());
                }
            }
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of PPTWS
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Path("moureJugador/")
    @Consumes("text/plain")
    public void moureJugador(@QueryParam("c") int codiPartida,@QueryParam("j") String jug,@QueryParam("t") int tipus) {
        
                for(int i = 0; i < llistaPartides.size(); ++i){
            if(llistaPartides.get(i).getID() == codiPartida){
                if(llistaPartides.get(i).getJUG1().getNick() == jug){
                    llistaPartides.get(i).getJUG1().setMoviment(tipus);
                } else if (llistaPartides.get(i).getJUG2().getNick() == jug){
                    llistaPartides.get(i).getJUG2().setMoviment(tipus);
                } else {
                    System.out.println("ErrorMoureJug");
                }
                //return "false";
            }
        }
        
        //return "true";
        
    }
    
        /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @DELETE
    @Path("acabarJoc/")
    @Produces("text/plain")
    public String acabarJoc(@QueryParam("c") int codiPartida) {
        for(int i = 0; i < llistaPartides.size(); ++i){
            if(llistaPartides.get(i).getID() == codiPartida){
                llistaPartides.remove(i);
                return "true";
            }
        }
        return "false";
    }
    
}
