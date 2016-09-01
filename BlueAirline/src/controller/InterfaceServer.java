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
 *
 * @author Giovanni
 */
public interface InterfaceServer {

    //modifica una ticket
    Ticket editSeatTicket(Ticket tp) throws SQLException;

    //modifica il biglietto
    Ticket editTicket(Ticket tp) throws SQLException;

    //ritorna tutti i bagagli
    ArrayList<HoldLuggage> getAllHoldLuggages();

    //ritorna tutte le assicurazioni
    ArrayList<Insurance> getAllInsurances();

    //ritorna tutti i meal
    ArrayList<Meal> getAllMeals() ;

    //ritorna i bagagli di un ticket
    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;

    //ritorna le assicurazioni di un ticket
    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;

    //ritorna i pasti di un ticket
    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;

    //ritorna tutta la prenotazione
    Reservation getReservtion(int codeReservation) throws SQLException;

    //ritorna ticketPassenger
    Ticket getTicket(String codeTicket) throws SQLException;

    //controlla se è stato fatto il checkin
    boolean isCheckIn(String codeTicket) throws SQLException;

    //effettua prenotazione
    Reservation makeReservation(Reservation reservation) throws SQLException;

    //numero posti liberi in un volo
    int numberSeatFreeFlight(String codeFlight) throws SQLException;

    //ricerca di tutte le città
    ArrayList<String> searchAllCitys() ;

    //ricerca volo in base al codice -> restituisce tutto il volo con i posti
    Flight searchFlight(String codeFlight) throws SQLException;

    //ricerca voli da città partenza e città arrivo e data
    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;

    //ricerca volo in base alla rotta
    ArrayList<Flight> searchFlights(Route route) throws SQLException;

    //ricerca tutte le rotte
    ArrayList<Route> searchRoutes();

    //dato volo e posto vedere se è libero
    boolean seatIsFree(String codeFlight, int nseat) throws SQLException;

    //effettua checkin
    void setCheckIn(String codeTicket) throws SQLException;

    //imposta array posti di un volo
    Flight setSeatsFlight(Flight flight) throws SQLException;
    
}
