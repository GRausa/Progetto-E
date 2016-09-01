/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * La classe Route indica una rotta su cui la compagnia effettua dei voli.
 * 
 * @author riccardo
 */
public class Route {

    private String deparutreAirport, destinationAirport, departureCity, destinationCity;

    /**
     * Crea una nuova rotta sulla quali verranno effettuati dei voli.
     * 
     * @param deparutreAirport aeroporto di partenza
     * @param destinationAirport aeroporto di arrivo
     * @param departureCity città di partenza 
     * @param destinationCity città di arrivo
     */
    public Route(String deparutreAirport, String destinationAirport, String departureCity, String destinationCity) {
        this.deparutreAirport = deparutreAirport;
        this.destinationAirport = destinationAirport;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
    }
    /**
     * Crea una nuova rotta fra città sulla quali verranno effettuati dei voli.
     * 
     * @param departureCity città di partenza 
     * @param destinationCity città di arrivo
     */
    public Route(String departureCity,String destinationCity){
        this.deparutreAirport = null;
        this.destinationAirport = null;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
    }
    
    /**
     * Crea una nuova rotta vuota.
     * 
     */
    public Route(){
        this.deparutreAirport = null;
        this.destinationAirport = null;
        this.departureCity = null;
        this.destinationCity = null;
    }

    public void setDeparutreAirport(String deparutreAirport) {
        this.deparutreAirport = deparutreAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
    
    public String getDeparutreAirport() {
        return deparutreAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String printRoute() {
        return departureCity + "(" + deparutreAirport + ")" + " - " + destinationCity + "(" + destinationAirport + ")";
    }

}
