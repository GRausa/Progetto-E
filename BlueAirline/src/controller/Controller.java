/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AdapterSQL;
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
public class Controller {

    private AdapterSQL adapter;

    public Controller() {
        adapter = new AdapterSQL();
    }
    
    //ricerca tutte le rotte
    public ArrayList<Route> searchRoutes() throws SQLException {
        return adapter.searchRoutes();
    }    
   
    //ricerca voli da città partenza e città arrivo e data
    public ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException {       
        return adapter.searchFlights(departure, destination, date);
    }
    
    //ricerca volo in base alla rotta
    public ArrayList<Flight> searchFlights(Route route) throws SQLException {
        return adapter.searchFlights(route);
    }
    
    //ricerca volo in base al codice -> restituisce tutto il volo con i posti
    public Flight searchFlight(String codeFlight) throws SQLException{
        return adapter.searchFlight(codeFlight);
    }
    
    //ricerca il volo con i posti 
    public Flight setFlightWithSeats(Flight flight) throws SQLException{
        return adapter.setSeatsFlight(flight);
    }
    
    //effettua prenotazione
    public Reservation makeReservation(Reservation reservation) throws SQLException{
        return adapter.makeReservation(reservation);
    }
    
    //numero posti liberi in un volo
    public int numberSeatFree(String codeFlight) throws SQLException{
        return adapter.numberSeatFree(codeFlight);
    }
    
    //modifica una ticket
    public Ticket editSeatTicketPassenger(Ticket tp) throws SQLException{
        return adapter.editSeatTicketPassenger(tp);
    }
    
    //ricerca di tutte le città
    public ArrayList<String> searchAllCitys() throws SQLException{
        return adapter.searchAllCitys();
    }
    
    //dato volo e posto vedere se è libero
    public boolean seatIsFree(String codeFlight, int nseat) throws SQLException{
        return adapter.seatIsFree(codeFlight, nseat);
    }
    
    //ritorna tutti i meal
    public ArrayList<Meal> getAllMeals() throws SQLException{
        return adapter.getAllMeals();
    }
    
    //ritorna tutti i bagagli
    public ArrayList<HoldLuggage> getAllHoldLuggages() throws SQLException{
        return adapter.getAllHoldLuggages();
    }
    
    //ritorna tutte le assicurazioni
    public ArrayList<Insurance> getAllInsurances() throws SQLException{
        return adapter.getAllInsurances();
    }
    
    //effettua checkin
    public void setCheckIn(String codeTicket) throws SQLException{
        adapter.setCheckIn(codeTicket);
    }
    
    //controlla se è stato fatto il checkin
    public boolean isCheckIn(String codeTicket) throws SQLException{
        return adapter.isCheckIn(codeTicket);
    }
    
    //ritorna ticketPassenger
    public Ticket getTicketPassenger(String codeTicket) throws SQLException{
        Ticket tp = adapter.getTicketPassenger(codeTicket);
        if(tp!=null){
            tp.setMeals(this.getMealsTicketPassenger(codeTicket));
            tp.setHoldLuggages(this.getHoldLuggagesTicketPassenger(codeTicket));
            tp.setInsurances(this.getInsurancesTicketPassenger(codeTicket)); 
        }
        return tp;        
    }
    
    //ritorna i pasti di un ticket
    public ArrayList<Meal> getMealsTicketPassenger(String codeTicket) throws SQLException{
        return adapter.getMealsTicketPassenger(codeTicket);
    }
    
    //ritorna le assicurazioni di un ticket
    public ArrayList<Insurance> getInsurancesTicketPassenger(String codeTicket) throws SQLException{
        return adapter.getInsurancesTicketPassenger(codeTicket);
    }
    
    //ritorna i bagagli di un ticket
    public ArrayList<HoldLuggage> getHoldLuggagesTicketPassenger(String codeTicket) throws SQLException{
        return adapter.getHoldLuggagesTicketPassenger(codeTicket);
    }
    
    //ritorna tutta la prenotazione
    public Reservation getReservtion(int codeReservation) throws SQLException{
        Reservation res = adapter.getReservation(codeReservation);
        if(res!=null){
            ArrayList<Ticket> ticketPassengers = new ArrayList<>();
            for(Ticket tp : res.getPassengers()){
                ticketPassengers.add(this.getTicketPassenger(tp.getCode()));
            }
            res.setPassengers(ticketPassengers);;
        }
        return res;        
    }
    
    //modifica il biglietto 
    public Ticket editTicketPassenger(Ticket tp) throws SQLException {
        return adapter.editTicketPassenger(tp);
    }
    
    
}
