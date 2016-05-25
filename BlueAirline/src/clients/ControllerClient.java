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

    public ArrayList<Route> searchRoutes() throws SQLException {
        return adapter.searchRoutes();
    }
    
    public ArrayList<Flight> riempi() throws SQLException{
        return adapter.riempivoli();
    }

    public ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException {
        return adapter.searchFlights(departure, destination, date);
    }
    
    public Reservation searchReservation(int code) throws SQLException{
        return adapter.searchReservation(code);
    }
    
    public Reservation makeReservation(Reservation reservation, ArrayList<TicketPassenger> passengers) throws SQLException{
        return adapter.makeReservation(reservation, passengers);
    }
    
    public ArrayList<String> searchAllCitys() throws SQLException{
        return adapter.searchAllCitys();
    }
    

}
