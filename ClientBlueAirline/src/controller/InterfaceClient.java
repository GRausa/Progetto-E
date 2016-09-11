/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Ticket;

/**
 * Provides methods in order to communicate with the server.
 *
 * @author Giovanni
 */
public interface InterfaceClient {

    /**
     * Checks if the server is on.
     *
     * @return <code>true</code> if the server is on; <code>false</code>
     * otherwise.
     */
    boolean hello();

    /**
     *
     * Connects to the server.
     *
     * @param ipServer Ip address of the server.
     *
     * @return <code>true</code> if the connection is established;
     * <code>false</code> otherwise.
     */
    boolean connect(String ipServer);

    /**
     * Send a confirmation email.
     *
     * @param destination Address of the customer.
     * @param object Object of the email
     * @param text Text of the email.
     * @return <code>true</code> if the email is sent; <code>false</code>
     * otherwise.
     */
    boolean sendMail(String destination, String object, String text);

    /**
     *
     * @param rotta Route.
     * @return List of flight that cover the route.
     */
    Flight[] calendar(Route rotta);

    /**
     * Makes the checkin of a ticket.
     *
     * @param tp Ticket.
     * @return ticket with checkin.
     */
    Ticket checkIn(Ticket tp);

    /**
     * Edits a seat number of a ticket.
     *
     * @param tp Ticket.
     * @return Modified ticket.
     */
    Ticket editSeatTicket(Ticket tp);

    /**
     * Edits a ticket.
     *
     * @param ticketPassenger Ticket.
     * @return Ticket modified.
     */
    Ticket editTicket(Ticket ticketPassenger);

    /**
     *
     * @return List of all cities in the database.
     *
     */
    String[] getAllCitys();

    /**
     *
     * @return List of all possible hold luggages in the database.
     */
    HoldLuggage[] getAllHoldLuggages();

    /**
     *
     * @return List of all possible hinsurances in the database.
     */
    Insurance[] getAllInsurances();

    /**
     *
     * @return List of all possible meals in the database.
     */
    Meal[] getAllMeals();
    /**
     * 
     * 
     * @param res Empty reservation.
     * @return Reservation.
     */
    Reservation getReservation(Reservation res);
    /**
     * 
     * @param tp Empty ticket.
     * @return Ticket.
     */
    Ticket getTicket(Ticket tp);

    /**
     * Insert a flight in the database.
     *
     * @param flight Flight.
     * @return flight inserted.
     */
    Flight insertFlight(Flight flight);

    /**
     * Verifies if the check in is already done.
     *
     * @param tp Ticket.
     * @return <code>true</code> if the passenger already did the check in;
     * <code>false</code> otherwise.
     */
    boolean isCheckIn(Ticket tp);
    /**
     * Makes a reservation.
     * 
     * @param res Reservation.
     * @return reservation.
     */
    Reservation makeReservation(Reservation res);

    /**
     * 
     * @param flight Empty flight;
     * @return List of flights.
     */
    Flight[] searchFlights(Flight flight);
    /**
     * 
     * @param rotta Empty route.
     * @return List of routes.
     */
    Route[] searchRoutes(Route rotta);

    /**
     *
     * @param userpass username and password of the administrator.
     * @return<code>true</code> if the user/pass are correct; <code>false</code>
     * otherwise.
     */
    boolean checkLogin(String userpass);

    /**
     * Modifies a flight.
     *
     * @param flight Flight to modify;
     * @return Flight modified.
     */
    Flight editFlight(Flight flight);

}
