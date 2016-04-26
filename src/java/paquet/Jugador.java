/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquet;

/**
 *
 * @author ivan
 */
public class Jugador {
    private String nick;
    
    private int moviment;
        /*
        0 No tirat
        1 Pedra
        2 Paper
        3 Tissora
        */

    public Jugador(String nick) {
        this.nick = nick;
    }

    
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getMoviment() {
        return moviment;
    }

    public void setMoviment(int moviment) {
        this.moviment = moviment;
    }
    
    
    
}
