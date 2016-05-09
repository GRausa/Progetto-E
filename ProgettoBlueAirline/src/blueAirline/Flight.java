/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author cl418377
 */
public class Flight {
    private String code;
    private Airplane airplane;
    private int flightTime;
    private Calendar departureDate;
    private Route route;
    private HashMap<Seat,Boolean> seats; //SERVE A MAPPARE OGNI POSTO PER VEDERE SE È DISPONIBILE
    private int seatFree; //CONTATORE POSTI LIBERI(SI DECREMENTA AD OGNI PRENOTAZIONE
    private double price; //prezzo dipene dal volo
    private int progressiveReservation;
    private int progressiveTicket;
    
    public Flight(String code, Airplane airplane, Route route, GregorianCalendar departureDate,int flightTime, double price){
        this.code=code;
        this.airplane=airplane;
        this.route=route;
        this.departureDate = departureDate;
        this.flightTime= flightTime;
        this.price=price;
        this.seats = new HashMap<>();
        for(int i=0;i<airplane.getNumSeat();i++){
            seats.put(airplane.getSeats()[i], Boolean.FALSE);
        }
        this.seatFree=airplane.getNumSeat();
        this.progressiveReservation=0;
        this.progressiveTicket=0;
    }
    
    public String getCode(){
        return code;
    }
    
    
    public double getPrice(){
        return price;
    }
    
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return code+"\n"+route.toString()+"Departure Date: "+ sdf.format(this.departureDate.getTime())+"\nTempo di volo in minuti: "+flightTime+"\nPrice: "+Double.toString(price)+" €\nSeats Occcupied: "+(airplane.getNumSeat()-seatFree)+"/"+airplane.getNumSeat()+"\n";
        
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public Route getRoute() {
        return route;
    }

    public int getSeatFree() {
        return seatFree;
    }
    
    public void addProgressiveReservation(){
        this.progressiveReservation++;
    }
    
    public void addProgressiveTicket(){
        this.progressiveTicket++;
    }
    
    public int getProgressiveReservation(){
        return this.progressiveReservation;
    }
    
    public int getProgressiveTicket(){
        seatFree--;
        return this.progressiveTicket;
        
    }
    
    public int getFlightTime(){
        return flightTime;
    }
    
    public void insertSeat(int n){
        for (HashMap.Entry<Seat, Boolean> val : seats.entrySet()) {
            if(val.getKey().getNumber()==n){
               val.setValue(Boolean.TRUE);
            }
        }
    }
    
    public boolean seatIsOccuped(int n){
        for (HashMap.Entry<Seat, Boolean> val : seats.entrySet()) {
            if(val.getKey().getNumber()==n & val.getValue()==true){
               return true;
            } 
        }
        return false;
    }  
    
    public int automaticSeatOccuped(){
        int i=0;
        for (HashMap.Entry<Seat, Boolean> val : seats.entrySet()) {
            if(val.getValue()==false)
                return i;
            i++;            
        }
        return 0;
    }
    
    public Airplane getAirplane(){
        return airplane;
    }
       
}
    
    
