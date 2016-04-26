/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

/**
 *
 * @author cl418377
 */
public class Airport {
    private String name;
    private City city;
    
    public Airport(String nome, City city){
        this.name=nome;
        this.city=city;
    }
    
    public String getNome(){
        return name;
    }
    
    public City getCity(){
        return city;
    }
}
