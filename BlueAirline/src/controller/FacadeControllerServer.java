/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AdapterDB;
import database.ConcreteAdapterDB;
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
    
    @Override
    public ArrayList<Route> searchRoutes() {
        return adapter.searchRoutes();
    }    
   
    @Override
    public ArrayList<Flight> searchFlights(String departure, String destination, String date)  {       
        return adapter.searchFlights(departure, destination, date);
    }
    
    @Override
    public ArrayList<Flight> searchFlights(Route route)  {
        return adapter.searchFlights(route);
    }
    
    @Override
    public Flight searchFlight(String codeFlight) {
        return adapter.searchFlight(codeFlight);
    }
    
    @Override
    public Reservation makeReservation(Reservation reservation) {
        return adapter.makeReservation(reservation);
    }
    
    @Override
    public Ticket editSeatTicket(Ticket tp) {
        return adapter.editSeatTicket(tp);
    }
    
    @Override
    public ArrayList<String> searchAllCitys() {
        return adapter.searchAllCitys();
    }
    
    @Override
    public ArrayList<Meal> getAllMeals(){
        return adapter.getAllMeals();
    }
    
    @Override
    public ArrayList<HoldLuggage> getAllHoldLuggages(){
        return adapter.getAllHoldLuggages();
    }
    
    @Override
    public ArrayList<Insurance> getAllInsurances() {
        return adapter.getAllInsurances();
    }
    
    @Override
    public void setCheckIn(String codeTicket) {
        adapter.setCheckIn(codeTicket);
    }
    
    @Override
    public boolean isCheckIn(String codeTicket) {
        return adapter.isCheckIn(codeTicket);
    }
    
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
    
    private ArrayList<Meal> getMealsTicket(String codeTicket) {
        return adapter.getMealsTicket(codeTicket);
    }
    
    private ArrayList<Insurance> getInsurancesTicket(String codeTicket) {
        return adapter.getInsurancesTicket(codeTicket);
    }
    
    private ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) {
        return adapter.getHoldLuggagesTicket(codeTicket);
    }
    
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
    
    @Override
    public Ticket editTicket(Ticket tp)  {
        return adapter.editTicket(tp);
    }

    @Override
    public void sendMail(String sender, String object, String text) {
        Email.sendMail(new Email(sender,object,text));
    }
    
    @Override
    public Flight insertFlight(Flight flight) {
        return adapter.insertFlight(flight);
    }
    
    @Override
    public Flight editFlight(Flight flight) {
        return adapter.editFlight(flight);
    }
    
    @Override
    public Boolean checkLogin(String username) {
        return adapter.checkLogin(username);
    }   
    
}
