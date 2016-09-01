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
     * Crea una nuova prenotazione su un volo della compagnia.
     * 
     * @param code recapito telefonico del cliente che effettua la prenotazione
     * @param email email del cliente che effettua la prenotazione
     * @param number numero della prenotazione
     * @param codeFlight codice del volo del quale si vuole effettuare una prenotazione
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
    
/*
    @Override
    public String toString() {
        return "Reservation{" + "passengers=" + tickets.get(0) + ", code=" + code + ", email=" + email + ", number=" + number + ", codeFlight=" + codeFlight + '}';
    }
 */  
}
