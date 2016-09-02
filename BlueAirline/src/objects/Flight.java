/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * La classe Flight indica un volo che effettua la compagnia.
 * 
 * @author Giovanni
 */
public class Flight {
    public static double COSTOPRIMACLASSE=30;
    private String code;
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double price;
    private ArrayList<Seat> seats;
    private String codeAirplane;
    
    /**
     * Istanzia un nuovo volo
     * 
     * @param code codice del volo
     * @param r rotta del volo
     * @param dateDeparture data di partenza 
     * @param dateDestination data di arrivo
     * @param prezzo prezzo del volo
     */
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.price = prezzo;
    }
    /**
     * Istanzia un nuovo volo
     * 
     * @param code codice del volo
     * @param r rotta del volo
     * @param dateDeparture data di partenza
     * @param dateDestination data di arrivo   
     * @param price prezzo del volo
     * @param seats lista dei posti a sedere
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
    /*
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return code+" "+r.toString()+"\n"+sdf.format(this.dateDeparture.getTime())+" - "+sdf.format(this.dateDestination.getTime())+"\n"+prezzo+"€";
    }
    */

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
