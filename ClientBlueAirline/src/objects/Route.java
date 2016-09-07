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
    /**
     * Constructs a new Route with only departure's and destination's cities. Airports set to null.
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
     * Constructs an empy route.
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
