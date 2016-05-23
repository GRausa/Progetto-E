/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import objects.Flight;
import objects.Reservation;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class ParserSQL {

    public ArrayList<Route> parseRoutes(ResultSet resultQuery) throws SQLException{
        ArrayList<Route> routes = new ArrayList<>();
        while (resultQuery.next()) {
            String departureAirport = resultQuery.getString("AEROPORTOPARTENZA");
            String destinationAirport = resultQuery.getString("AEROPORTOARRIVO");
            String departureCity = resultQuery.getString("CITTAPARTENZA");
            String destinationCity = resultQuery.getString("CITTAARRIVO");
            routes.add(new Route(departureAirport, destinationAirport, departureCity, destinationCity));
        }
        return routes;        
    }
    
    public ArrayList<Flight> parseFlights(ResultSet resultQuery) throws SQLException{
        ArrayList<Flight> flights = new ArrayList<>();
        while (resultQuery.next()) {
            String code = resultQuery.getString("COD_VOLO");
            String departureCity = resultQuery.getString("CITTAPARTENZA");
            String departureAirport = resultQuery.getString("AEROPORTOPARTENZA");
            String destinationCity = resultQuery.getString("CITTAARRIVO");
            String destinationAirport = resultQuery.getString("AEROPORTOARRIVO");
            String departureDate  = resultQuery.getString("DATAPARTENZA"); 
            String departureTime = resultQuery.getString("ORAPARTENZA");
            String destinationDate  = resultQuery.getString("DATAARRIVO"); 
            String destinationTime = resultQuery.getString("ORAARRIVO");
            double price = resultQuery.getDouble("PREZZO");
            
            Route r = new Route(departureAirport, destinationAirport, departureCity, destinationCity);
            Calendar departureCalendar = ParserSQL.returnCalendar(departureDate, departureTime);
            Calendar destinationCalendar = ParserSQL.returnCalendar(destinationDate, destinationTime);
            
            flights.add(new Flight(code,r,departureCalendar,destinationCalendar,price));
        }
        return flights;        
    }
    
    public Reservation parseReservation(ResultSet resultQuery) throws SQLException{        
        int code = resultQuery.getInt("COD_PRENOTAZIONE");
        String codeFlight = resultQuery.getString("VOLO");
        String email = resultQuery.getString("EMAIL");
        String number = resultQuery.getString("NUMERO");
        Reservation reservation = new Reservation(code,codeFlight,email,number);
        return reservation;
    }
    
    //METODI GENERICI
    
    public static Calendar returnCalendar(String stringDate, String stringTime){
        String[] vetDate = stringDate.split("-");
        int year = Integer.parseInt(vetDate[0]);
        int month = Integer.parseInt(vetDate[1]);
        int day = Integer.parseInt(vetDate[2]);
        String[] vetTime = stringTime.split(":");
        int hour = Integer.parseInt(vetTime[0]);
        int minute = Integer.parseInt(vetTime[1]);
        GregorianCalendar date = new GregorianCalendar(year,month,day,hour,minute);
        return date;
    }
}
