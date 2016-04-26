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

    public Reservation(ArrayList<Passenger> passengers, String prenotationCode, Customer customer) {
        this.passengers = passengers;
        this.prenotationCode = prenotationCode;
        this.customer=customer;
    }
    
    
}
