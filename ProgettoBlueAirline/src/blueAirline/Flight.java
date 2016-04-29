/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cl418377
 */
public class Flight {
    private String code;
    private Airplane airplane;
    private Date destinationDate;
    private Date departureDate;
    private Route route;
    private ArrayList<Boolean> seats;
    private double price; //prezzo dipene dal volo
    
    public Flight(String code, Airplane airplane, Route route, Date departureDate,Date destinationDate, double price){
        this.code=code;
        this.airplane=airplane;
        this.route=route;
        this.departureDate = departureDate;
        this.destinationDate = destinationDate;
        this.price=price;
        this.seats=new ArrayList<>();
    }
    
    public String getCode(){
        return code;
    }
    
    public ArrayList<Boolean> getSeats(){
        return seats;
    }
    
    public double getPrice(){
        return price;
    }
    
    public String toString(){
        return code+"\n"+route.toString()+"Departure Date: "+departureDate.getDate()+"/"+departureDate.getMonth()+"/"+departureDate.getYear()+" "+departureDate.getHours()+":"+departureDate.getMinutes()+"\nDestination Date: "+destinationDate.getDate()+"/"+destinationDate.getMonth()+"/"+destinationDate.getYear()+" "+destinationDate.getHours()+":"+destinationDate.getMinutes()+"\nPrice: "+Double.toString(price)+" €\nSeats Occcupied:"+seats.size()+"/"+airplane.getNumSeat()+"\n\n";
        //return code+"\n"+route.toString()+"Data partenza: "+departur+"\nData arrivo: "+destinationDate.toLocaleString()+"\nPrice: "+Double.toString(price)+"\nSeats Occcupied:"+seats.size()+"/"+airplane.getNumSeat()+"\n\n";
    
    }
}