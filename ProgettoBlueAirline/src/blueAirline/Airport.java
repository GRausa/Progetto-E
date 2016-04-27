/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *
 * @author cl418377
 */
public class Airport implements Comparable{
    private String name;
    private City city;
    
    public Airport(String nome, City city){
        this.name=nome;
        this.city=city;
    }
    
    public String getName(){
        return name;
    }
    
    public City getCity(){
        return city;
    }
    
    public String toString(){
        return name+" "+city.toString();
    }
    
    public int compareTo(Object o){
        Airport a = (Airport) o;
        return(name.compareTo(a.getName()));
    }
    
    public boolean equals(Airport airport){
        return this.name.equals(airport.getName());
    }
}
