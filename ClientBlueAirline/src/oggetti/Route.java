/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

/**
 *
 * @author riccardo
 */
public class Route {

    private String deparutreAirport, destinationAirport, departureCity, destinationCity;

    public Route(String deparutreAirport, String destinationAirport, String departureCity, String destinationCity) {
        this.deparutreAirport = deparutreAirport;
        this.destinationAirport = destinationAirport;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
    }
    public Route(String departureCity,String destinationCity){
        this.deparutreAirport = null;
        this.destinationAirport = null;
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

    public String toString() {
        return departureCity + "(" + deparutreAirport + ")" + " - " + destinationCity + "(" + destinationAirport + ")";
    }

}
