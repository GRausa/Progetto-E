/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Airport offre metodi per la gestione di un aeroporto.
 * 
 * @author cl418377
 */
public class Airport implements Comparable{
    private String name;
    private City city;
    
    /**
     * Inizializza un nuovo aeroporto.
     * 
     * @param name nome dell'aeroporto
     * @param city  città in cui è presente l'aeroporto
     */
    public Airport(String name, City city){
        this.name=name;
        this.city=city;
    }
    /**
     * 
     * @return la città dell'aeroporto
     */
    public City getCity(){
        return city;
    }
     /**
      * 
      * @return il nome dell'aeroporto
      */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return rappresentazione scritta dell'aeroporto
     */
    public String toString(){
        return name+" "+city.toString();
    }
    
    public int compareTo(Object o){
        Airport a = (Airport) o;
        return(name.compareTo(a.getName()));
    }
    /**
     * Confronta due aeroporti.
     * 
     * @param airport riferimento all'aeroporto che vogliamo comparare
     * @return  <code>true</code> se l'aereoporto su cui è invocato il metodo è uguale 
     *         a quello passato come argomento, altrimenti ritorna <code>false</code>
     */
    public boolean equals(Airport airport){
        return this.name.equals(airport.getName());
    }
    /**
     * 
     * @param name nome dell'aeroporto
     * @return <code>true</code> se l'aereoporto su cui è invocato il metodo ha il nome
     *         uguale alla stringa passata come parametro, altrimenti ritorna <code>false</code>
     */
    public boolean equalsName(String name){
        return this.name.equals(name);
    }
}
