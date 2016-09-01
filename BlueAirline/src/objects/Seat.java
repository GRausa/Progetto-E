/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * La classe Seat indica un posto a sedere su un aereo.
 * 
 * @author Giovanni
 */
public class Seat {
    private int number;
    private int classe;
    private String ticket;
    /**
     * Istanzia un nuovo posto a sedere.
     * 
     * @param numero numero del posto a sedere
     * @param classe classe del posto
     * @param ticket ticket associato al posto
     */
    public Seat(int numero,int classe, String ticket){
        this.ticket=ticket;
        this.classe=classe;
        this.number=numero;
    }

    public int getNumber() {
        return number;
    }

    public int getClasse() {
        return classe;
    }

    public String getTicket() {
        return ticket;
    }
    
    
}
