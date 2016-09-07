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
 * Provides methods for the Database managment.
 * 
 * @author Giovanni
 */
public interface AdapterDB {
    /**
     * Modifies the seat number of a ticket.
     * 
     * @param tp Ticket to modify.
     * @return Ticket modified.
     * @throws SQLException 
     */
    Ticket editSeatTicket(Ticket tp) throws SQLException;
    /**
     * Modifies a ticket.
     * 
     * @param tp Ticket to modify.
     * @return Ticket modified.
     * @throws SQLException 
     */
    Ticket editTicket(Ticket tp) throws SQLException;
    /**
     * 
     * @return List of all hold luggages.
     */
    ArrayList<HoldLuggage> getAllHoldLuggages();
    /**
     * 
     * @return List of all insurances.
     */
    ArrayList<Insurance> getAllInsurances();
    /**
     * 
     * @return List of all meals.
     */
    ArrayList<Meal> getAllMeals();
    /**
     * 
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the hold luggages associated to the ticket.
     * @throws SQLException 
     */
    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the insurances associated to the ticket.
     * @throws SQLException 
     */
    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the meals associated to the ticket.
     * @throws SQLException 
     */
    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeReservation Code of the reservation.
     * @return Full reservation.
     * @throws SQLException 
     */
    Reservation getReservation(int codeReservation) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return Full ticket.
     * @throws SQLException 
     */
    Ticket getTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return <code>true</code> if the check in is already done;
     *         <code>false</code> otherwise.
     * @throws SQLException 
     */
    boolean isCheckIn(String codeTicket) throws SQLException;
    /**
     * Inserts a reservation in the database.
     * 
     * @param reservation Reservation to do.
     * @return Reservation done.
     * @throws SQLException 
     */
    Reservation makeReservation(Reservation reservation) throws SQLException;
    /**
     * 
     * @param codeFlight Code of the flight.
     * @return Number of the seats available.
     * @throws SQLException 
     */
    int numberSeatFreeFlight(String codeFlight) throws SQLException;
    /**
     * 
     * @return List of all cities.
     */
    ArrayList<String> searchAllCitys();
    /**
     * Search a flight in the database.
     * 
     * @param codeFlight Code of the flight.
     * @return Flight found.
     * @throws SQLException 
     */
    Flight searchFlight(String codeFlight) throws SQLException;
    /**
     * Search flights corrispondences.
     * 
     * @param departure Departure city.
     * @param destination Destination city.
     * @param date Date of the flight.
     * @return Corrispondences found.
     * @throws SQLException 
     */
    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;
    /**
     * 
     * @param route Route.
     * @return Flights that travel over this route.
     * @throws SQLException 
     */
    ArrayList<Flight> searchFlights(Route route) throws SQLException;
    /**
     * 
     * @return List of routes.
     */
    ArrayList<Route> searchRoutes();
    /**
     * 
     * @param codeFlight Code of the flight.
     * @param nseat Number of the seat.
     * @return <code>true</code> if the seat is available;
     *         <code>false</code> otherwise.
     * @throws SQLException 
     */
    boolean seatIsFree(String codeFlight, int nseat) throws SQLException;
    
    /**
     * Sets all the seats of all flights.
     * 
     * @return List of all flights.
     */
    ArrayList<Flight> setAllSeatFlights();
    /**
     * Makes the check in of a ticket.
     * 
     * @param codeTicket Code of the ticket.
     * @throws SQLException 
     */
    void setCheckIn(String codeTicket) throws SQLException;
    /**
     * Sets all the seats of a flight.
     * 
     * @param flight Flight.
     * @return Flight with all seats set.
     * @throws SQLException 
     */
    Flight setSeatsFlight(Flight flight) throws SQLException;
    /**
     * Inserts a flight in the Database.
     * 
     * @param flight Flight. 
     * @return Flight inserted.
     * @throws SQLException 
     */
    Flight insertFlight(Flight flight) throws SQLException ;
    /**
     * 
     * @param airportDeparture Departure's airport.
     * @param airportDestination Destinarion's airport.
     * @return Code of the route.
     * @throws SQLException 
     */
    String getCodeRoute(String airportDeparture, String airportDestination) throws SQLException;
    /**
     * 
     * @param userpass Login information.
     * @return <code>true</code> if informations are correct.
 *   *          <code>false</code> otherwise.
     * @throws SQLException
     */
    Boolean checkLogin(String userpass) throws SQLException;
    /**
     * Modifies a flight in the Database.
     * 
     * @param flight Flight to modify.
     * @return Flight modified.
     * @throws SQLException 
     */
    Flight editFlight(Flight flight) throws SQLException;
    
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
