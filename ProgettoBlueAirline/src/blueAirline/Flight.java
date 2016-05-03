/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author cl418377
 */
public class Flight {
    private String code;
    private Airplane airplane;
    private int flightTime;
    private Date departureDate;
    private Route route;
    private HashMap<Seat,Boolean> seats; //SERVE A MAPPARE OGNI POSTO PER VEDERE SE È DISPONIBILE
    private int SeatFree; //CONTATORE POSTI LIBERI(SI DECREMENTA AD OGNI PRENOTAZIONE
    //private Seat[] seats;//DA MODIFICARE
    private double price; //prezzo dipene dal volo
    
    public Flight(String code, Airplane airplane, Route route, Date departureDate,int flightTime, double price){
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
        this.SeatFree=airplane.getNumSeat();
    }
    
    public String getCode(){
        return code;
    }
    
    
    public double getPrice(){
        return price;
    }
    
    public String toString(){
        return code+"\n"+route.toString()+"Departure Date: "+departureDate.getDate()+"/"+departureDate.getMonth()+"/"+departureDate.getYear()+" "+departureDate.getHours()+":"+departureDate.getMinutes()+"\nTempo di volo in minuti: "+flightTime+"\nPrice: "+Double.toString(price)+" €\nSeats Occcupied:"+"/"+airplane.getNumSeat()+"\n\n";
        
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Route getRoute() {
        return route;
    }
    
    /*public int seatsOccupeted(){
        int n = 0;
        for(int i=0;i<seats.length;i++){
            if(seats[i].isReserved()){
                    n++;
            }
        }
        return n;
    }
    */
    
}
