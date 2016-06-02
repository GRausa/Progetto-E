/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Giovanni
 */
public class Flight {
    private String code;
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double prezzo;
    private ArrayList<Seat> seats;

    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.prezzo = prezzo;
    }
    
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo, ArrayList<Seat> seats) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.prezzo = prezzo;
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

    public double getPrezzo() {
        return prezzo;
    }
    
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return code+" "+r.toString()+"\n"+sdf.format(this.dateDeparture.getTime())+" - "+sdf.format(this.dateDestination.getTime())+"\n"+prezzo+"€";
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    
    
    
}
