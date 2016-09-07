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
 * Provides methodsin order to communicate with the server.
 * 
 * @author Giovanni
 */
public interface InterfaceClient{
    /**
     * Checks if the server is on.
     * 
     * @return <code>true</code> if the server is on;
     *         <code>false</code> otherwise.
     */
    boolean hello();
    /**
     * 
     * Connects to the server.
     * 
     * @param ipServer Ip address of the server.
     * 
     * @return <code>true</code> if the connection is established;
     *         <code>false</code> otherwise.
     */
    boolean connect(String ipServer);
    /**
     * Send a confirmation email.
     * 
     * @param destination Address of the customer.
     * @param object Object of the email
     * @param text Text of the email.
     * @return <code>true</code> if the email is sent;
     *         <code>false</code> otherwise.
     * @throws IOException if occurs an I/O exception
     */
    boolean sendMail(String destination,String object,String text) throws IOException;
    /**
     * 
     * @param rotta Route.
     * @return List of flight that cover the route.
     * @throws IOException if occurs an I/O exception.
     */
    Flight[] calendar(Route rotta) throws IOException;
    /**
     * Makes the checkin of a ticket.
     * 
     * @param tp Ticket.
     * @return ticket with checkin.
     * @throws IOException if occurs an I/O exception
     */
    Ticket checkIn(Ticket tp) throws IOException;
    /**
     * Edits a seat number of a ticket.
     * 
     * @param tp Ticket.
     * @return Modified ticket.
     * @throws IOException if occurs an I/O exception
     */
    Ticket editSeatTicket(Ticket tp) throws IOException;
    /**
     * Edits a ticket.
     * 
     * @param ticketPassenger Ticket.
     * @return Ticket modified.
     * @throws IOException if occurs an I/O exception
     */
    Ticket editTicket(Ticket ticketPassenger) throws IOException;
    /**
     * 
     * @return List of all cities in the database.
     * 
     * @throws IOException if occurs an I/O exception
     */
    String[] getAllCitys() throws IOException;
    /**
     * 
     * @return List of all possible hold luggages in the database.
     * @throws IOException if occurs an I/O exception
     */
    HoldLuggage[] getAllHoldLuggages() throws IOException;
    /**
     * 
     * @return List of all possible hinsurances in the database.
     * @throws IOException if occurs an I/O exception
     */
    Insurance[] getAllInsurances() throws IOException;
    /**
     * 
     * @return List of all possible meals in the database.
     * @throws IOException if occurs an I/O exception
     */
    Meal[] getAllMeals() throws IOException;
    
    Reservation getReservation(Reservation res) throws IOException;

    Ticket getTicket(Ticket tp) throws IOException;
    /**
     * Insert a flight in the database.
     * 
     * @param flight Flight.
     * @return flight inserted. 
     * @throws IOException if occurs an I/O exception.
     */
    Flight insertFlight(Flight flight) throws IOException;
    /**
     * Verifies if the check in is already done.
     * 
     * @param tp Ticket.
     * @return <code>true</code> if the passenger already did the check in;
     *         <code>false</code> otherwise.
     * @throws IOException if occurs an I/O exception
     */
    boolean isCheckIn(Ticket tp) throws IOException;
    
    Reservation makeReservation(Reservation res) throws IOException;
    
    //Flight searchFlight(Flight flight) throws IOException;
    
    Flight[] searchFlights(Flight flight);
    
    Route[] searchRoutes(Route rotta) throws IOException;
    /**
     * 
     * @param userpass username and password of the administrator.
     * @return<code>true</code> if the user/pass are correct;
     *         <code>false</code> otherwise.
     * @throws IOException if occurs an I/O exception
     */
    boolean checkLogin(String userpass) throws IOException;
    /**
     * Modifies a flight.
     * 
     * @param flight Flight to modify;
     * @return Flight modified.
     * @throws IOException if occurs an I/O exception
     */
    Flight editFlight(Flight flight) throws IOException;
    
}
