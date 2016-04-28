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
public class Route {
    private Airport departure;
    private Airport destination;
    
    public Route(Airport departure, Airport destination){
        this.departure=departure;
        this.destination=destination;
    }

    public Airport getDeparture(){
        return departure;
    }

    public Airport getDestination(){
        return destination;
    }
    
    public String toString(){
        return "Departure: "+departure.toString()+"Destination: "+destination.toString()+"\n";
    }
    
    
    
}
