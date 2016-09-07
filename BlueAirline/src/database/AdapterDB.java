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
 * Provides methods for the Database managment. Provides also methods for the administrators. 
 * 
 * @author Giovanni
 */
public interface AdapterDB {
    /**
     * Modifies the seat number of a ticket.
     * 
     * @param tp Ticket to modify.
     * @return Ticket modified.
     * @throws SQLException If there is Database access error or other errors.
     */
    Ticket editSeatTicket(Ticket tp) throws SQLException;
    /**
     * Modifies a ticket.
     * 
     * @param tp Ticket to modify.
     * @return Ticket modified.
     * @throws SQLException If there is Database access error or other errors.
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
     * @throws SQLException If there is Database access error or other errors.
     */
    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the insurances associated to the ticket.
     * @throws SQLException If there is Database access error or other errors.
     */
    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the meals associated to the ticket.
     * @throws SQLException If there is Database access error or other errors.
     */
    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeReservation Code of the reservation.
     * @return Full reservation.
     * @throws SQLException If there is Database access error or other errors.
     */
    Reservation getReservation(int codeReservation) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return Full ticket.
     * @throws SQLException If there is Database access error or other errors.
     */
    Ticket getTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return <code>true</code> if the check in is already done;
     *         <code>false</code> otherwise.
     * @throws SQLException If there is Database access error or other errors.
     */
    boolean isCheckIn(String codeTicket) throws SQLException;
    /**
     * Inserts a reservation in the database.
     * 
     * @param reservation Reservation to do.
     * @return Reservation done.
     * @throws SQLException If there is Database access error or other errors.
     */
    Reservation makeReservation(Reservation reservation) throws SQLException;
    /**
     * 
     * @param codeFlight Code of the flight.
     * @return Number of the seats available.
     * @throws SQLException If there is Database access error or other errors.
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
     * @throws SQLException If there is Database access error or other errors.
     */
    Flight searchFlight(String codeFlight) throws SQLException;
    /**
     * Search flights corrispondences.
     * 
     * @param departure Departure city.
     * @param destination Destination city.
     * @param date Date of the flight.
     * @return Corrispondences found.
     * @throws SQLException If there is Database access error or other errors.
     */
    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;
    /**
     * 
     * @param route Route.
     * @return Flights that travel over this route.
     * @throws SQLException If there is Database access error or other errors.
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
     * @throws SQLException If there is Database access error or other errors.
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
     * @throws SQLException If there is Database access error or other errors.
     */
    void setCheckIn(String codeTicket) throws SQLException;
    /**
     * Sets all the seats of a flight.
     * 
     * @param flight Flight.
     * @return Flight with all seats set.
     * @throws SQLException If there is Database access error or other errors.
     */
    Flight setSeatsFlight(Flight flight) throws SQLException;
    /**
     * Inserts a flight in the Database.
     * 
     * @param flight Flight. 
     * @return Flight inserted.
     * @throws SQLException If there is Database access error or other errors.
     */
    Flight insertFlight(Flight flight) throws SQLException ;
    /**
     * 
     * @param airportDeparture Departure's airport.
     * @param airportDestination Destinarion's airport.
     * @return Code of the route.
     * @throws SQLException If there is Database access error or other errors.
     */
    String getCodeRoute(String airportDeparture, String airportDestination) throws SQLException;
    /**
     * 
     * @param userpass Login information.
     * @return <code>true</code> if informations are correct.
 *   *          <code>false</code> otherwise.
     * @throws SQLException If there is Database access error or other errors.
     */
    Boolean checkLogin(String userpass) throws SQLException;
    /**
     * Modifies a flight in the Database.
     * 
     * @param flight Flight to modify.
     * @return Flight modified.
     * @throws SQLException If there is Database access error or other errors.
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
