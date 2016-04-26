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
    private Date data;
    private Route route;
    private ArrayList<Boolean> seats;
    private double price; //prezzo dipene dal volo
    
    public Flight(String code, Date data, double price, Airplane airplane, Route route){
        this.code=code;
        this.data=data;
        this.price=price;
        this.seats=new ArrayList<>();
        this.airplane=airplane;
        this.route=route;
    }
    
    public String getCode(){
        return code;
    }
    
    public Date getData(){
        return data;
    }
    
    public ArrayList<Boolean> getSeats(){
        return seats;
    }
    
    public double getPrice(){
        return price;
    }
}
