/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * La classe Route indica una rotta su cui la compagnia effettua dei voli.
 * 
 * @author Giovanni
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
/*
    public String toString() {
        return departureCity + "(" + deparutreAirport + ")" + " - " + destinationCity + "(" + destinationAirport + ")";
    }
*/
}
