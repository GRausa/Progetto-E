/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import objects.Flight;
import objects.Route;
import objects.Seat;

/**
 *
 * @author Giovanni
 */
public class ParserSQL {

    public static ArrayList<Route> parseRoutes(ResultSet resultQuery) throws SQLException{
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
    
    public static ArrayList<Flight> parseFlights(ResultSet resultQuery) throws SQLException{
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
    /*
    public static Reservation parseReservation(ResultSet resultQuery) throws SQLException{        
        int code = resultQuery.getInt("COD_PRENOTAZIONE");
        String codeFlight = resultQuery.getString("VOLO");
        String email = resultQuery.getString("EMAIL");
        String number = resultQuery.getString("NUMERO");
        Reservation reservation = new Reservation(code,codeFlight,email,number);
        return reservation;
    }
    */
    public static double parseFunctionSQL(ResultSet resultQuery, String value) throws SQLException{ //SINGOLI MAX,MIN,COUNT...
        resultQuery.next();
        return resultQuery.getDouble(value);
    }
    
    public static ArrayList<String> parseCitis(ResultSet resultQuery) throws SQLException{
        ArrayList<String> citys = new ArrayList<>();
        while (resultQuery.next()) {
            citys.add(resultQuery.getString("NOME"));
        }
        return citys;
    }
    
    static ArrayList<Seat> parseSeats(ResultSet resultQuery) throws SQLException {
        ArrayList<Seat> seats = new ArrayList<>();
        while (resultQuery.next()) {
            int num = Integer.parseInt(resultQuery.getString("NUMERO"));
            int classe = Integer.parseInt(resultQuery.getString("Classe"));
            String passenger = resultQuery.getString("PASSEGGERO");
            seats.add(new Seat(num, classe, passenger));
        }
        return seats;    
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
    
    public static String stringDate(GregorianCalendar date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String d = sdf.format(date.getTime());
        String[] vet = d.split("/");
        return vet[2]+"-"+vet[1]+"-"+vet[0];
    }

    
}
