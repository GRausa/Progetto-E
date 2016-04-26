/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author cl418377
 */
public class Flight {
    private String code;
    private Date data;
    private ArrayList<Boolean> seats;
    private Double price; //prezzo dipene dal volo
    
    public Flight(String code, Date data, Double price){
        this.code=code;
        this.data=data;
        this.price=price;
        this.seats=new ArrayList<>();
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
    
    public Double getPrice(){
        return price;
    }
}
