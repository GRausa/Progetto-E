/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * La classe Reservation indica una prenotazione di uno o più Ticket.
 * 
 * @author Giovanni
 */
public class Reservation {
    private ArrayList<Ticket> tickets;
    private int code;
    private String email, number, codeFlight;

    /**
     * Crea una nuova prenotazione su un volo della compagnia.
     * 
     * @param codeFlight codice del volo del quale si vuole effettuare una prenotazione
     * @param email email del cliente che effettua la prenotazione
     * @param number recapito telefonico del cliente che effettua la prenotazione
     */
    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    /**
     * Crea una nuova prenotazione su un volo della compagnia
     * 
     * @param codeFlight codice del volo del quale si vuole effettuare una prenotazione
     * @param number recapito telefonico del cliente che effettua la prenotazione
     * @param email email del cliente che effettua la prenotazione
     * @param p lista dei biglietti della prenotazione
     */
    public Reservation(String codeFlight, String number, String email, ArrayList<Ticket> p) {
        
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
        this.tickets=p;
    }
    /**
     * Crea una nuova prenotazione su un volo della compagnia a partire dal suo codice
     * 
     * @param code codice del volo del quale si vuole effettuare una prenotazione
     */
    public Reservation(int code){
        this.code=code;
    }

    public ArrayList<Ticket> getPassengers() {
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
    
    public double getTotalPrice(){
        double tot=0;
        for(Ticket t:tickets)
            tot=tot+t.getTotalPrice();
        return tot;    
    }
            


    public String getCodeFlight() {
        return codeFlight;
    }
    /**
     * 
     * @return rappresentazione scritta della prenotazione 
     */
    public String printReservation(String tab){
        String s="";
        s+="Prenotazione: "+code+" ("+email+" "+number+")"+tab;
        s+="Volo: "+codeFlight+tab;
        s+="Numero passeggeri: "+tickets.size();
        return s;
    }   
 
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 
     * @return lista dei passeggeri della prenotazione
     */
    public String printTickets(String tab) {
        String s="";
        for(Ticket tp:tickets){
            s+=tp.printTicketPassenger(tab)+tab;
        }
        return s;
    }
    
    
    
}
