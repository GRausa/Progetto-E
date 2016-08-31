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
import objects.Seat;
import objects.Ticket;

/**
 *
 * @author Giovanni
 */
public interface AdapterDB {

    Reservation addTickets(Reservation reservation) throws SQLException;

    Ticket editSeatTicket(Ticket tp) throws SQLException;

    Ticket editTicket(Ticket tp) throws SQLException;

    ArrayList<HoldLuggage> getAllHoldLuggages() throws SQLException;

    ArrayList<Insurance> getAllInsurances() throws SQLException;

    ArrayList<Meal> getAllMeals() throws SQLException;

    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;

    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;

    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;

    Reservation getReservation(int codeReservation) throws SQLException;

    ArrayList<Seat> getSeatsFlight(String codeFlight) throws SQLException;

    Ticket getTicket(String codeTicket) throws SQLException;

    void insertSupplement(String code, String codeTicket) throws SQLException;

    void insertSupplementHoldLuggage(String code, String codeTicket) throws SQLException;

    void insertSupplementInsurance(String code, String codeTicket) throws SQLException;

    void insertSupplementMeal(String code, String codeTicket) throws SQLException;

    //verifico se è stato fatto il checkIn
    boolean isCheckIn(String codeTicket) throws SQLException;
    
    Reservation makeReservation(Reservation reservation) throws SQLException;

    int numberSeatFirstClassFlight(String cod) throws SQLException;

    int numberSeatFlight(String codeFlight) throws SQLException;

    int numberSeatFreeFlight(String codeFlight) throws SQLException;

    ArrayList<String> searchAllCitys() throws SQLException;

    Flight searchFlight(String codeFlight) throws SQLException;

    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;

    ArrayList<Flight> searchFlights(Route route) throws SQLException;

    ArrayList<Route> searchRoutes() throws SQLException;

    boolean seatIsFree(String codeFlight, int nseat) throws SQLException;

    void setAllSeatFlight(Flight volo) throws SQLException;

    /* USATO NELL'INIZIALIZZAZIONE DEI POSTI*/
    ArrayList<Flight> setAllSeatFlights() throws SQLException;

    void setCheckIn(String codeTicket) throws SQLException;

    void setSeat(String volo, int num, int classe) throws SQLException;

    void setSeatBoolean(String codeFlight, int nSeat, String ticketPassenger, boolean b) throws SQLException;

    Flight setSeatsFlight(Flight flight) throws SQLException;
    
}