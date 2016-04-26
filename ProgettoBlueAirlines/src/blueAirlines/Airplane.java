/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class Airplane implements Comparable{
    private String name;
    private String code;
    private Seat [] seats;
    
    public Airplane(String code, String name, int nSeats){
        this.name=name;
        this.code=code;
        this.seats=new Seat[nSeats];
    }
    
    public int getNumSeat(){
        return seats.length;
    }
    
    public String getName(){
        return name;
    }
    
    public String getCode(){
        return code;
    }
    
    @Override
    public String toString(){
        return code+" "+name+" "+seats.length+"\n";
    }
    
    public int compareTo(Object o){
        Airplane a = (Airplane) o;
        return(name.compareTo(a.getCode()));
    }

    
            
}
