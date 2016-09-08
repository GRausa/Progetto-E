/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * Represent a route exercised by the company. A route is a connection between two airports.
 * 
 * @author Giovanni
 */
public class Route {

    private String deparutreAirport, destinationAirport, departureCity, destinationCity;

    /**
     * Constructs a new Route.
     * 
     * @param deparutreAirport Departure airport.
     * @param destinationAirport Destination airport.
     * @param departureCity Departure city. 
     * @param destinationCity Destination city.
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
    
}
