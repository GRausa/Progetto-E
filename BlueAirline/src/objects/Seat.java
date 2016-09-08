/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * Represents a booked in seat associated to a ticket.
 * 
 * @author Giovanni
 */
public class Seat {
    private int number;
    private int classe;
    private String ticket;
    /**
     * Construct a new seat.
     * 
     * @param numero Number of the seat.
     * @param classe Class of the seat.
     * @param ticket Ticket associated to the seat.
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
