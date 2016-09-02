/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Ticket;

/**
 *
 * @author Giovanni
 */
public interface AdapterDB {

    Ticket editSeatTicket(Ticket tp) throws SQLException;

    Ticket editTicket(Ticket tp) throws SQLException;

    ArrayList<HoldLuggage> getAllHoldLuggages();

    ArrayList<Insurance> getAllInsurances();

    ArrayList<Meal> getAllMeals();

    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;

    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;

    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;

    Reservation getReservation(int codeReservation) throws SQLException;

    Ticket getTicket(String codeTicket) throws SQLException;

    boolean isCheckIn(String codeTicket) throws SQLException;

    Reservation makeReservation(Reservation reservation) throws SQLException;

    int numberSeatFreeFlight(String codeFlight) throws SQLException;

    ArrayList<String> searchAllCitys();

    Flight searchFlight(String codeFlight) throws SQLException;

    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;

    ArrayList<Flight> searchFlights(Route route) throws SQLException;

    ArrayList<Route> searchRoutes();

    boolean seatIsFree(String codeFlight, int nseat) throws SQLException;

    ArrayList<Flight> setAllSeatFlights();

    void setCheckIn(String codeTicket) throws SQLException;

    Flight setSeatsFlight(Flight flight) throws SQLException;
    
    Flight insertFlight(Flight flight) throws SQLException ;
    
    String getCodeRoute(String airportDeparture, String airportDestination) throws SQLException;
    
    Boolean checkLogin(String userpass) throws SQLException;;
    
    //void setSeat(String volo, int num, int classe) throws SQLException;
    
    //int numberSeatFirstClassFlight(String cod) throws SQLException;
    
    //void insertSupplement(String code, String codeTicket) throws SQLException;

    //void insertSupplementHoldLuggage(String code, String codeTicket) throws SQLException;
    
    //void insertSupplementInsurance(String code, String codeTicket) throws SQLException;
    
    //void insertSupplementMeal(String code, String codeTicket) throws SQLException;
    
    //ArrayList<Seat> getSeatsFlight(String codeFlight) throws SQLException;
    
    //verifico se è stato fatto il checkIn
    
    //int numberSeatFlight(String codeFlight) throws SQLException;
    
    //void setSeatBoolean(String codeFlight, int nSeat, String ticketPassenger, boolean b) throws SQLException;
    
    //void setAllSeatFlight(Flight volo) throws SQLException;
    
    //Reservation addTickets(Reservation reservation) throws SQLException;
}
