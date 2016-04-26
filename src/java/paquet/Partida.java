/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquet;

import java.util.Random;

/**
 *
 * @author ivan
 */
public class Partida {
    private int ID;
    private Jugador JUG1;
    private Jugador JUG2;
    private int estat;
        /*
            -2 -1 Guanya J1
             1  2 Guanya J2
             0    Empat
        */
    

    public Partida(int codiPartida, String nick) {
        JUG1 = new Jugador(nick);
        this.ID = codiPartida;
    }

    
    public Jugador getJUG1() {
        return JUG1;
    }

    public Jugador getJUG2() {
        return JUG2;
    }

    public void setJUG2(String JUG2) {
        this.JUG2 = new Jugador(JUG2);
    }

    public int getID() {
        return ID;
    }

    public int getEstat() {
        return estat;
    }

    public void setEstat(int estat) {
        
        
        this.estat = estat;
    }
    
    
    
}
