/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class Reservation {
    private ArrayList<Passenger> passengers;
    private String prenotationCode;
    private Customer customer;
    private Boolean checkIn;
    private Flight flight;

    public Reservation(Flight flight,ArrayList<Passenger> passengers, String prenotationCode, Customer customer) {
        this.flight=flight;
        this.passengers = passengers;
        this.prenotationCode = "PR"+flight.getProgressiveReservation();
        flight.addProgressiveReservation();
        this.customer=customer;
        for(Passenger p : passengers){
            p.setTicket(new Ticket("COD"+flight.getProgressiveTicket(), flight.getPrice(),flight.getProgressiveTicket()));
        }
        this.customer=customer;
        this.checkIn=false;
    }
    
    
}
