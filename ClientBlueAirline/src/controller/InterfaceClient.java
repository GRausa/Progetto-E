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
import static tui.administrator.MethodsControlAdministrator.checkLogin;

/**
 *
 * @author Giovanni
 */
public interface InterfaceClient{

    boolean hello();
    
    boolean connect(String ipServer);
    
    boolean sendMail(String destination,String object,String text) throws IOException;
    
    Flight[] calendar(Route rotta) throws IOException;

    Ticket checkIn(Ticket tp) throws IOException;

    Ticket editSeatTicket(Ticket tp) throws IOException;

    Ticket editTicket(Ticket ticketPassenger) throws IOException;

    String[] getAllCitys() throws IOException;

    HoldLuggage[] getAllHoldLuggages() throws IOException;

    Insurance[] getAllInsurances() throws IOException;

    Meal[] getAllMeals() throws IOException;

    Reservation getReservation(Reservation res) throws IOException;

    Ticket getTicket(Ticket tp) throws IOException;

    Flight insertFlight(Flight flight) throws IOException;

    boolean isCheckIn(Ticket tp) throws IOException;

    Reservation makeReservation(Reservation res) throws IOException;

    Flight searchFlight(Flight flight) throws IOException;

    Flight[] searchFlights(Flight flight) throws IOException;

    Route[] searchRoutes(Route rotta) throws IOException;
    
    boolean checkLogin(String userpass) throws IOException;
    
}
