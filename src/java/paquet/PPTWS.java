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
    
    static ArrayList<Partida> llistaPartides =  new ArrayList<Partida>();
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PPTWS
     */
    public PPTWS() {
        
    }
    
    /*
    CONSUMIDOR:
    http://localhost:8888/REST_PPT/webresources/PPT/iniciarJoc?c=22&j=Jugador1          POST
    http://localhost:8888/REST_PPT/webresources/PPT/iniciarJoc?c=22&j=Jugador2          POST
    
    http://localhost:8888/REST_PPT/webresources/PPT/moureJugador?c=22&j=Jugador1&t=1    PUT
    http://localhost:8888/REST_PPT/webresources/PPT/moureJugador?c=22&j=Jugador2&t=2    PUT
    http://localhost:8888/REST_PPT/webresources/PPT/consultarEstatPartida?c=22          GET
    
    http://localhost:8888/REST_PPT/webresources/PPT/moureJugador?c=22&j=Jugador1&t=1    PUT
    http://localhost:8888/REST_PPT/webresources/PPT/moureJugador?c=22&j=Jugador2&t=2    PUT
    http://localhost:8888/REST_PPT/webresources/PPT/consultarEstatPartida?c=22          GET
    
    http://localhost:8888/REST_PPT/webresources/PPT/acabarJoc?c=22                      DELETE
    */

    /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/iniciarJoc/")
    @Produces("text/plain")
    public String iniciarJoc(@QueryParam("c") int codiPartida, @QueryParam("j") String jug1) {
         System.out.println("CP: "+codiPartida);
         codiPartida = (int)codiPartida;
        boolean flag = false;
        for(int i = 0; i < llistaPartides.size(); ++i){
            System.out.println(llistaPartides.get(i).getID()+" : "+codiPartida);
            if(llistaPartides.get(i).getID() == codiPartida){
                llistaPartides.get(i).setJUG2(jug1);
                flag = true;
                System.out.println("El jugador 2: "+jug1+" s'ha unit a la partida: "+codiPartida);
                System.out.println(llistaPartides.size());
                return "false";
            }
        }
        if(!flag){
            Partida p = new Partida(codiPartida, jug1);
            llistaPartides.add(p);
            System.out.println("Partida Creada: "+codiPartida+" amb Jugador 1: "+jug1);
            System.out.println(llistaPartides.size());
            return "true";
        }
        

        return null;
    }
    
    /**
     * Retrieves representation of an instance of paquet.PPTWS
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/consultarEstatPartida/")
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
                    System.out.println("Estat de la Partida: "+p.getEstat());
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
    @Path("/moureJugador/")
    @Consumes("text/plain")
    public String moureJugador(@QueryParam("c") int codiPartida,@QueryParam("j") String jug,@QueryParam("t") int tipus) {
        
                for(int i = 0; i < llistaPartides.size(); ++i){
            if(llistaPartides.get(i).getID() == codiPartida){
                if(llistaPartides.get(i).getJUG1().getNick() == null ? jug == null : llistaPartides.get(i).getJUG1().getNick().equals(jug)){
                    llistaPartides.get(i).getJUG1().setMoviment(tipus);
                    System.out.println("El Jugador: "+jug+" ha tirat "+tipus);
                } else if (llistaPartides.get(i).getJUG2().getNick() == null ? jug == null : llistaPartides.get(i).getJUG2().getNick().equals(jug)){
                    llistaPartides.get(i).getJUG2().setMoviment(tipus);
                    System.out.println("El Jugador: "+jug+" ha tirat "+tipus);
                } else {
                    System.out.println("ErrorMoureJug");
                }
                return "false";
            }
        }
        
        return "true";
        
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
                System.out.println("La partida "+codiPartida+" ha estat eliminada: ");
                return "true";
            }
        }
        return "false";
    }
    
}
