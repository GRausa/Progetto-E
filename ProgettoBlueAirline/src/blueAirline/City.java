/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class City implements Comparable{
    private String name;
    private ArrayList<Airport> airports;
    
    public City(String name){
        this.name=name;
        airports = new ArrayList<>();
    }
    
    public ArrayList<Airport> getArrayAirports(){
        return airports;
    }   
    
    public void insertAirport(Airport airport){
        airports.add(airport);
    }
    
    public String getName(){
        return name;
    }
    
    public String toString(){
        return name+"\n";
    }
    
    public int compareTo(Object o){
        City a = (City) o;
        return(name.compareTo(a.getName()));
    }
    
    public boolean equals(City city){
        return this.name.equals(city.getName());
    }
    
}
