/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
     */
    Ticket editSeatTicket(Ticket tp);
    /**
     * Modifies a ticket.
     * 
     * @param tp Ticket to modify.
     * @return Ticket modified.
     */
    Ticket editTicket(Ticket tp);
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
     */
    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket);
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the insurances associated to the ticket.
     */
    ArrayList<Insurance> getInsurancesTicket(String codeTicket);
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return List of the meals associated to the ticket.
     */
    ArrayList<Meal> getMealsTicket(String codeTicket);
    /**
     * 
     * @param codeReservation Code of the reservation.
     * @return Full reservation.
     */
    Reservation getReservation(int codeReservation);
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return Full ticket.
     */
    Ticket getTicket(String codeTicket);
    /**
     * 
     * @param codeTicket Code of the ticket.
     * @return <code>true</code> if the check in is already done;
     *         <code>false</code> otherwise.
     */
    boolean isCheckIn(String codeTicket);
    /**
     * Inserts a reservation in the database.
     * 
     * @param reservation Reservation to do.
     * @return Reservation done.
     */
    Reservation makeReservation(Reservation reservation);
    /**
     * 
     * @param codeFlight Code of the flight.
     * @return Number of the seats available.
     */
    int numberSeatFreeFlight(String codeFlight);
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
     */
    Flight searchFlight(String codeFlight);
    /**
     * Search flights corrispondences.
     * 
     * @param departure Departure city.
     * @param destination Destination city.
     * @param date Date of the flight.
     * @return Corrispondences found.
     */
    ArrayList<Flight> searchFlights(String departure, String destination, String date);
    /**
     * 
     * @param route Route.
     * @return Flights that travel over this route.
     */
    ArrayList<Flight> searchFlights(Route route);
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
     */
    boolean seatIsFree(String codeFlight, int nseat);
    
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
     */
    void setCheckIn(String codeTicket);
    /**
     * Sets all the seats of a flight.
     * 
     * @param flight Flight.
     * @return Flight with all seats set.
     */
    Flight setSeatsFlight(Flight flight);
    /**
     * Inserts a flight in the Database.
     * 
     * @param flight Flight. 
     * @return Flight inserted.
     */
    Flight insertFlight(Flight flight);
    /**
     * 
     * @param airportDeparture Departure's airport.
     * @param airportDestination Destinarion's airport.
     * @return Code of the route.
     */
    String getCodeRoute(String airportDeparture, String airportDestination);
    /**
     * 
     * @param userpass Login information.
     * @return <code>true</code> if informations are correct.
 *   *          <code>false</code> otherwise.
     */
    Boolean checkLogin(String userpass);
    /**
     * Modifies a flight in the Database.
     * 
     * @param flight Flight to modify.
     * @return Flight modified.
     */
    Flight editFlight(Flight flight);
    
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
