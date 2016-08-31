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
    public Ticket editSeatTicket(Ticket tp) throws SQLException{
        return adapter.editSeatTicket(tp);
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
    public Ticket getTicket(String codeTicket) throws SQLException{
        Ticket tp = adapter.getTicket(codeTicket);
        if(tp!=null){
            tp.setMeals(this.getMealsTicket(codeTicket));
            tp.setHoldLuggages(this.getHoldLuggagesTicket(codeTicket));
            tp.setInsurances(this.getInsurancesTicket(codeTicket)); 
        }
        return tp;        
    }
    
    //ritorna i pasti di un ticket
    public ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException{
        return adapter.getMealsTicket(codeTicket);
    }
    
    //ritorna le assicurazioni di un ticket
    public ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException{
        return adapter.getInsurancesTicket(codeTicket);
    }
    
    //ritorna i bagagli di un ticket
    public ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException{
        return adapter.getHoldLuggagesTicket(codeTicket);
    }
    
    //ritorna tutta la prenotazione
    public Reservation getReservtion(int codeReservation) throws SQLException{
        Reservation res = adapter.getReservation(codeReservation);
        if(res!=null){
            ArrayList<Ticket> tickets=  new ArrayList<>();
            for(Ticket tp : res.getTickets()){
                tickets.add(this.getTicket(tp.getCode()));
            }
            res.setTickets(tickets);;
        }
        return res;        
    }
    
    //modifica il biglietto 
    public Ticket editTicket(Ticket tp) throws SQLException {
        return adapter.editTicket(tp);
    }
    
    
}
