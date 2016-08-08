/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import database.AdapterSQL;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Flight;
import objects.Reservation;
import objects.Route;
import objects.TicketPassenger;

/**
 *
 * @author Giovanni
 */
public class ControllerClient {

    private AdapterSQL adapter;

    public ControllerClient() {
        adapter = new AdapterSQL();
    }
    
    //ricerca tutte le rotte
    public ArrayList<Route> searchRoutes() throws SQLException {
        return adapter.searchRoutes();
    }
    
    //riempie tutti i posti NULL
    public ArrayList<Flight> setAllSeatFlights() throws SQLException{
        return adapter.setAllSeatFlights();
    }
    
    //ricerca voli da città partenza e città arrivo e data
    public ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException {       
        return adapter.searchFlights(departure, destination, date);
    }
    
    //ricerca volo in base alla rotta
    public ArrayList<Flight> searchFlights(Route route) throws SQLException {
        return adapter.searchFlights(route);
    }
    
    //ricerca volo in base al codice -> restituisce tutto il volo con i posti
    public Flight searchFlight(String codeFlight) throws SQLException{
        return adapter.searchFlight(codeFlight);
    }
    
    //ricerca il volo con i posti 
    public Flight getFlightWithSeats(Flight flight) throws SQLException{
        return adapter.setSeatsFlight(flight);
    }
    
    
    /* da implementare
    public Reservation searchReservation(int code) throws SQLException{
        return adapter.searchReservation(code);
    }
    */
    
    //effettua prenotazione
    public Reservation makeReservation(Reservation reservation) throws SQLException{
        return adapter.makeReservation(reservation);
    }
    
    //numero posti liberi in un volo
    public int numberSeatFree(String codeFlight) throws SQLException{
        return adapter.numberSeatFree(codeFlight);
    }
    
    public TicketPassenger editSeatTicketPassenger(TicketPassenger tp) throws SQLException{
        return adapter.editSeatTicketPassenger(tp);
    }
    
    //ricerca di tutte le città
    public ArrayList<String> searchAllCitys() throws SQLException{
        return adapter.searchAllCitys();
    }
    
    //dato volo e posto vedere se è libero
    public boolean seatIsFree(String codeFlight, int nseat) throws SQLException{
        return adapter.seatIsFree(codeFlight, nseat);
    }
    

}
