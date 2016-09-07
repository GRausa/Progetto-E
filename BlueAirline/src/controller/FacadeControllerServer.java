/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AdapterDB;
import database.ConcreteAdapterDB;
import java.sql.SQLException;
import java.util.ArrayList;
import mail.Email;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Ticket;

/**
 * Provides a simplified interface to the methods the customers need.
 * 
 * @author Giovanni
 */
public class FacadeControllerServer implements InterfaceServer {

    private AdapterDB adapter;
    
    static FacadeControllerServer istance;
    
    private FacadeControllerServer(){   
        adapter = new ConcreteAdapterDB();
    }
    
    public static FacadeControllerServer getIstance(){
        if(istance==null)
            istance = new FacadeControllerServer();
        return istance;
    }
    
    //ricerca tutte le rotte
    @Override
    public ArrayList<Route> searchRoutes() {
        return adapter.searchRoutes();
    }    
   
    //ricerca voli da città partenza e città arrivo e data
    @Override
    public ArrayList<Flight> searchFlights(String departure, String destination, String date)  {       
        return adapter.searchFlights(departure, destination, date);
    }
    
    //ricerca volo in base alla rotta
    @Override
    public ArrayList<Flight> searchFlights(Route route)  {
        return adapter.searchFlights(route);
    }
    
    //ricerca volo in base al codice -> restituisce tutto il volo con i posti
    @Override
    public Flight searchFlight(String codeFlight) {
        return adapter.searchFlight(codeFlight);
    }
    
    //imposta array posti di un volo 
    /*
    private Flight setSeatsFlight(Flight flight) throws SQLException{
        return adapter.setSeatsFlight(flight);
    }*/
    
    //effettua prenotazione
    @Override
    public Reservation makeReservation(Reservation reservation) {
        return adapter.makeReservation(reservation);
    }
    /*
    //numero posti liberi in un volo
    private int numberSeatFreeFlight(String codeFlight) throws SQLException{
        return adapter.numberSeatFreeFlight(codeFlight);
    }*/
    
    //modifica una ticket
    @Override
    public Ticket editSeatTicket(Ticket tp) {
        return adapter.editSeatTicket(tp);
    }
    
    //ricerca di tutte le città
    @Override
    public ArrayList<String> searchAllCitys() {
        return adapter.searchAllCitys();
    }
    /*
    //dato volo e posto vedere se è libero
    private boolean seatIsFree(String codeFlight, int nseat) throws SQLException{
        return adapter.seatIsFree(codeFlight, nseat);
    }*/
    
    //ritorna tutti i meal
    @Override
    public ArrayList<Meal> getAllMeals(){
        return adapter.getAllMeals();
    }
    
    //ritorna tutti i bagagli
    @Override
    public ArrayList<HoldLuggage> getAllHoldLuggages(){
        return adapter.getAllHoldLuggages();
    }
    
    //ritorna tutte le assicurazioni
    @Override
    public ArrayList<Insurance> getAllInsurances() {
        return adapter.getAllInsurances();
    }
    
    //effettua checkin
    @Override
    public void setCheckIn(String codeTicket) {
        adapter.setCheckIn(codeTicket);
    }
    
    //controlla se è stato fatto il checkin
    @Override
    public boolean isCheckIn(String codeTicket) {
        return adapter.isCheckIn(codeTicket);
    }
    
    //ritorna ticketPassenger
    @Override
    public Ticket getTicket(String codeTicket) {
        Ticket tp = adapter.getTicket(codeTicket);
        if(tp!=null){
            tp.setMeals(this.getMealsTicket(codeTicket));
            tp.setHoldLuggages(this.getHoldLuggagesTicket(codeTicket));
            tp.setInsurances(this.getInsurancesTicket(codeTicket)); 
        }
        return tp;        
    }
    
    //ritorna i pasti di un ticket
    private ArrayList<Meal> getMealsTicket(String codeTicket) {
        return adapter.getMealsTicket(codeTicket);
    }
    
    //ritorna le assicurazioni di un ticket
    private ArrayList<Insurance> getInsurancesTicket(String codeTicket) {
        return adapter.getInsurancesTicket(codeTicket);
    }
    
    //ritorna i bagagli di un ticket
    private ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) {
        return adapter.getHoldLuggagesTicket(codeTicket);
    }
    
    //ritorna tutta la prenotazione
    @Override
    public Reservation getReservtion(int codeReservation) {
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
    @Override
    public Ticket editTicket(Ticket tp)  {
        return adapter.editTicket(tp);
    }

    @Override
    public void sendMail(String sender, String object, String text) {
        Email.sendMail(new Email(sender,object,text));
    }
    
    //inserisci un volo
    @Override
    public Flight insertFlight(Flight flight) {
        return adapter.insertFlight(flight);
    }
    
    //inserisci un volo
    @Override
    public Flight editFlight(Flight flight) {
        return adapter.editFlight(flight);
    }
    
    //login
    @Override
    public Boolean checkLogin(String username) {
        return adapter.checkLogin(username);
    }
    
    
}
