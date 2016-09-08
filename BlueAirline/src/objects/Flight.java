/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a flight that the company performs.
 * 
 * @author Giovanni
 */
public class Flight {
    /**
     * Supplement of the first class.
     */
    public static double COSTOPRIMACLASSE=30;
    private String code=null;
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double price;
    private ArrayList<Seat> seats;
    private String codeAirplane;
    
    /**
     * Constructs a new flight. The seats are not added.
     * 
     * @param code Code of the flight.
     * @param r Route of the flight.
     * @param dateDeparture Departure's date.
     * @param dateDestination Arrival's date.
     * @param prezzo Price of a ticket in the second class of the flight.
     */
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.price = prezzo;
    }
    /**
     * Constructs a new flight.
     * 
     * @param code Code of the flight.
     * @param r Route of the flight.
     * @param dateDeparture Departure's date.
     * @param dateDestination Arrival's date.  
     * @param price Price of a ticket in the second class of the flight.
     * @param seats List of the seat.
     */
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double price, ArrayList<Seat> seats) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.price = price;
        this.seats = seats;
    }

    public String getCode() {
        return code;
    }

    public Route getRoute() {
        return r;
    }

    public Calendar getDateDeparture() {
        return dateDeparture;
    }

    public Calendar getDateDestination() {
        return dateDestination;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }    
    
    public String getCodeAirplane(){
        return this.codeAirplane;
    }
    
}
