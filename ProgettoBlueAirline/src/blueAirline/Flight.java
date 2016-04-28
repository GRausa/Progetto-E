/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author cl418377
 */
public class Flight {
    private DateFormat df = new SimpleDateFormat("dd/MM/yy");
    DateFormat df2 = new SimpleDateFormat("HH:mm");
    private String code;
    private Airplane airplane;
    private GregorianCalendar dataDeparture;
    private GregorianCalendar timeDeparture;
    private GregorianCalendar timeDestination;
    private Route route;
    private ArrayList<Boolean> seats;
    private double price; //prezzo dipene dal volo
    
    public Flight(String code, Airplane airplane, Route route, Date dataDeparture,Date timeDeparture, Date timeDestination, double price){
        this.code=code;
        this.airplane=airplane;
        this.route=route;
        this.dataDeparture = new GregorianCalendar();
        this.timeDeparture = new GregorianCalendar();
        this.timeDestination = new GregorianCalendar();
        this.dataDeparture.setTime(dataDeparture);
        this.timeDeparture.setTime(timeDeparture);
        this.timeDestination.setTime(timeDestination);
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
        return code+"\n"+route.toString()+"Price: "+Double.toString(price)+"\nSeats Occcupied:"+seats.size()+"/"+airplane.getNumSeat()+"\n\n";
    }
}
