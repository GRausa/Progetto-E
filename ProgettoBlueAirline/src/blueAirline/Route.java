/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *La classe Route ci dà informazioni sulla rotta che seguirà l'aereo in un determinato flight.
 * @author cl418377
 */
public class Route {
    /**
     * Aeroporto di partenza
     */
    private Airport departure;
    /**
     * Aeroporto di arrivo
     */
    private Airport destination;
    /**
     * Crea una nuova rotta.
     * @param departure aeroporto di partenza
     * @param destination aeroporto di arrivo 
     */
    public Route(Airport departure, Airport destination){
        this.departure=departure;
        this.destination=destination;
    }
    /**
     * 
     * @return aeroporto di partenza 
     */
    public Airport getDeparture(){
        return departure;
    }
    /**
     * 
     * @return aeroporto di arrivo 
     */
    public Airport getDestination(){
        return destination;
    }
    /**
     * Confronta due rotte.
     * 
     * @param route rotta dell'aereo
     * @return <code>true</code> se la rotta su cui è invocato il metodo è uguale 
     *         a quello passata come argomento, altrimenti ritorna <code>false</code>
     */
    public boolean equals(Route route){
        return (this.departure.getName().equals(route.departure.getName()) & this.destination.getName().equals(route.destination.getName()));
    }
    /**
     * 
     * @return rappresentazione scritta di una rotta
     */
    public String toString(){
        return "Departure: "+departure.toString()+"Destination: "+destination.toString();
    }
    
    
    
}
