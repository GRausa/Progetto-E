/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Giovanni
 */
public class Flight {
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double prezzo;

    public Flight(Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo) {
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.prezzo = prezzo;
    }

    public Route getR() {
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
        return r.toString()+"\n"+sdf.format(this.dateDeparture.getTime())+" - "+sdf.format(this.dateDestination.getTime())+"\n"+prezzo+"€";
    }
    
    
}
