/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 *La classe City offre metodi per gestire gli aeroporti presenti in una determinata città,
 * come la visualizzazione e l'aggiunta di essi.
 * 
 * @author cl418377
 */

public class City implements Comparable{
    private String name;
    private ArrayList<Airport> airports;
    
    /**
     * Inizializza una nuova città con una lista vuota di aeroporti.
     * @param name  nome della città
     */
    public City(String name){
        this.name=name;
        airports = new ArrayList<>();
    }
    /**
     * 
     * @return ArrayList degli aeroporti di una città
     */
    public ArrayList<Airport> getArrayAirports(){
        return airports;
    }   
    /**
     * Aggiunge un aeroporto alla lista già presente.
     * 
     * @param airport aeroporto da aggiungere
     */
    public void insertAirport(Airport airport){
        airports.add(airport);
    }
    /**
     * 
     * @return nome della città
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return nome della città 
     */
    public String toString(){
        return name+"\n";
    }
    
    public int compareTo(Object o){
        City a = (City) o;
        return(name.compareTo(a.getName()));
    }
    /**
     * Confronta due città.
     * 
     * @param city riferimento alla città che vogliamo comparare
     * @return <code>true</code> se la città su cui è invocato il metodo ha il nome
     *         uguale alla città passata come parametro, altrimenti ritorna <code>false</code>
     */
    public boolean equals(City city){
        return this.name.equals(city.getName());
    }
    
}
