/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * Represents a reservation of one or more tickets. The customer is identified
 * with his forwarding address.
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
     * @param codeFlight Code of the flight of which the customer wants a
     * reservation.
     * @param email Email of the customer.
     * @param number Phone address of the customer.
     */
    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }

    /**
     * Constructs a reservation with one or more tickets.
     *
     * @param codeFlight Code of the flight of which the customer wants a
     * reservation.
     * @param number Phone address of the customer.
     * @param email Email of the customer.
     * @param p List of tickets.
     *
     *
     */
    public Reservation(String codeFlight, String number, String email, ArrayList<Ticket> p) {

        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
        this.tickets = p;
    }

    /**
     * Constructs a new reservation with only the resrvation's code.
     *
     * @param code codice del volo del quale si vuole effettuare una
     * prenotazione
     */
    public Reservation(int code) {
        this.code = code;
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

    public double getTotalPrice() {
        double tot = 0;
        for (Ticket t : tickets) {
            tot = tot + t.getTotalPrice();
        }
        return tot;
    }

    public String getCodeFlight() {
        return codeFlight;
    }

    /**
     *
     * @return Written description of the reservation.
     */
    public String printReservation(String tab) {
        String s = "";
        s += "Prenotazione: " + code + " (" + email + " " + number + ")" + tab;
        s += "Volo: " + codeFlight + tab;
        s += "Numero passeggeri: " + tickets.size();
        return s;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return Written description of the reservation.
     */
    public String printTickets(String tab) {
        String s = "";
        for (Ticket tp : tickets) {
            s += tp.printTicketPassenger(tab) + tab;
        }
        return s;
    }

}
