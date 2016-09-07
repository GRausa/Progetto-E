/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
public interface InterfaceServer {

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
     * @param codeReservation Code of the reservation.
     * @return Full reservation.
     */
    Reservation getReservtion(int codeReservation);

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
     * <code>false</code> otherwise.
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
     * Makes the check in of a ticket.
     *
     * @param codeTicket Code of the ticket.
     */
    void setCheckIn(String codeTicket);

    /**
     * Inserts a flight in the Database.
     *
     * @param flight Flight.
     * @return Flight inserted.
     */
    Flight insertFlight(Flight flight);

    /**
     *
     * @param userpass Login information.
     * @return <code>true</code> if informations are correct. *
     *          <code>false</code> otherwise.
     */
    Boolean checkLogin(String username);

    /**
     * Send an email to the addressee.
     *
     * @param sender Addressee of the email.
     * @param object Object of the email.
     * @param text Text of the email.
     */
    void sendMail(String sender, String object, String text);

    /**
     * Modifies a flight in the Database.
     *
     * @param flight Flight to modify.
     * @return Flight modified.
     */
    Flight editFlight(Flight flight);

    //numero posti liberi in un volo
    //int numberSeatFreeFlight(String codeFlight) throws SQLException;
    //dato volo e posto vedere se è libero
    //boolean seatIsFree(String codeFlight, int nseat) throws SQLException;
    //imposta array posti di un volo
    //Flight setSeatsFlight(Flight flight) throws SQLException;    
    //ritorna i bagagli di un ticket
    //ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;
    //ritorna le assicurazioni di un ticket
    //ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;
    //ritorna i pasti di un ticket
    //ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;
}
