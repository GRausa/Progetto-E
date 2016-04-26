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
public class City {
    private String nome;
    private ArrayList<Airport> airports;
    
    public City(String nome){
        this.nome=nome;
        airports = new ArrayList<>();
    }
    
    public String getNome(){
        return nome;
    }
    
    
}
