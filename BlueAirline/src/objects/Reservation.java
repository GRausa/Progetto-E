/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * Represents a reservation of one or more tickets. The customer is identified with his forwarding address.
 * 
 * @author Giovanni
 */
public class Reservation { 
    private ArrayList<Ticket> tickets;
    private int code;
    private String email, number, codeFlight;

    /**
     * Constructs an empty reservation (no tickets).
     * 
     * @param codeFlight Code of the flight of which the customer wants a reservation.
     * @param email Email of the customer.
     * @param number Phone address of the customer.
     */
    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    /**
     * Constructs a reservation without any ticket. 
     * 
     * @param code Code of the reservation.
     * @param email Email of the customer.
     * @param number Phone address of the customer.
     * @param codeFlight Code of the flight of which the customer wants a reservation.
     */
    public Reservation(int code, String email, String number, String codeFlight) {
        this.code = code;
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> passengers) {
        this.tickets = passengers;
    }
    
    public int getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getCodeFlight() {
        return codeFlight;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    /**
     * 
     * @return Written description of the reservation.
     */
    public String printReservation(){
        String s="";
        s+="Prenotazione: "+code+" ("+email+" "+number+")\n";
        s+="Volo: "+codeFlight+"\n";
        s+="Numero passeggeri: "+tickets.size();
        return s;
    }   
    /**
     * 
     * @return Written description of the tickets of the reservation. 
     */
    public String printTickets() {
        String s="";
        for(Ticket tp:tickets){
            s+=tp.printTicketPassenger()+"\n";
        }
        return s;
    }
    
/*
    @Override
    public String toString() {
        return "Reservation{" + "passengers=" + tickets.get(0) + ", code=" + code + ", email=" + email + ", number=" + number + ", codeFlight=" + codeFlight + '}';
    }
 */  
}
