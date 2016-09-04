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
import objects.Ticket;

/**
 * L'interfaccia AdapterDB definisce i metodi per la gestione del DB.
 * 
 * @author Giovanni
 */
public interface AdapterDB {
    /**
     * 
     * @param tp Ticket di cui vogliamo modificare il posto a sedere
     * @return Ticket modificato
     * @throws SQLException 
     */
    Ticket editSeatTicket(Ticket tp) throws SQLException;
    /**
     * 
     * @param tp Ticket da modificare
     * @return Ticket modificato
     * @throws SQLException 
     */
    Ticket editTicket(Ticket tp) throws SQLException;
    /**
     * 
     * @return Lista dei bagagli da stiva 
     */
    ArrayList<HoldLuggage> getAllHoldLuggages();
    /**
     * 
     * @return Lista delle assicurazioni 
     */
    ArrayList<Insurance> getAllInsurances();
    /**
     * 
     * @return Lista dei pasti
     */
    ArrayList<Meal> getAllMeals();
    /**
     * 
     * @param codeTicket codice del Ticket 
     * @return lista di bagagli da stiva associati al Ticket
     * @throws SQLException 
     */
    ArrayList<HoldLuggage> getHoldLuggagesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket codice del Ticket
     * @return lista delle assicurazioni associate al Ticket
     * @throws SQLException 
     */
    ArrayList<Insurance> getInsurancesTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket codice del Ticket
     * @return lista dei pasti associati al Ticket
     * @throws SQLException 
     */
    ArrayList<Meal> getMealsTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeReservation codice della prenotazione
     * @return prenotazione completa
     * @throws SQLException 
     */
    Reservation getReservation(int codeReservation) throws SQLException;
    /**
     * 
     * @param codeTicket codice ticket
     * @return Ticket completo
     * @throws SQLException 
     */
    Ticket getTicket(String codeTicket) throws SQLException;
    /**
     * 
     * @param codeTicket codice del biglietto
     * @return <code>true</code> se il check in è gia stato effettuato;
     *         <code>false</code> altrimenti
     * @throws SQLException 
     */
    boolean isCheckIn(String codeTicket) throws SQLException;
    /**
     * 
     * @param reservation prenotazione da inserire nel database
     * @return prenotazione effettuata
     * @throws SQLException 
     */
    Reservation makeReservation(Reservation reservation) throws SQLException;
    /**
     * 
     * @param codeFlight codice del volo
     * @return numero dei posti disponibili sul volo indicato
     * @throws SQLException 
     */
    int numberSeatFreeFlight(String codeFlight) throws SQLException;
    /**
     * 
     * @return lista di tutte le città.
     */
    ArrayList<String> searchAllCitys();
    /**
     * 
     * @param codeFlight codice del volo
     * @return volo ricercato
     * @throws SQLException 
     */
    Flight searchFlight(String codeFlight) throws SQLException;
    /**
     * 
     * @param departure città di partenza
     * @param destination città di destinazione
     * @param date data
     * @return corrispondenze trovate
     * @throws SQLException 
     */
    ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException;
    /**
     * 
     * @param route rotta del volo
     * @return voli che percorrono la rotta indicata
     * @throws SQLException 
     */
    ArrayList<Flight> searchFlights(Route route) throws SQLException;
    /**
     * 
     * @return lista delle rotte
     */
    ArrayList<Route> searchRoutes();
    /**
     * 
     * @param codeFlight codice volo
     * @param nseat numero del posto a sedere
     * @return <code>true</code> se il posto è libero;
     *         <code>false</code> altrimenti
     * @throws SQLException 
     */
    boolean seatIsFree(String codeFlight, int nseat) throws SQLException;
    
    ArrayList<Flight> setAllSeatFlights();
    /**
     * Effettua il check-in di un biglietto.
     * 
     * @param codeTicket codice del biglietto
     * @throws SQLException 
     */
    void setCheckIn(String codeTicket) throws SQLException;
    /**
     * Setta tutti i posti a sedere di un volo.
     * 
     * @param flight volo
     * @return volo settato
     * @throws SQLException 
     */
    Flight setSeatsFlight(Flight flight) throws SQLException;
    /**
     * 
     * @param flight volo 
     * @return volo inserito.
     * @throws SQLException 
     */
    Flight insertFlight(Flight flight) throws SQLException ;
    /**
     * 
     * @param airportDeparture aeroporto di partenza
     * @param airportDestination aeroporto di destinazione
     * @return codice della rotta
     * @throws SQLException 
     */
    String getCodeRoute(String airportDeparture, String airportDestination) throws SQLException;
    /**
     * 
     * @param userpass dati d'accesso
     * @return <code>true</code> se le credenziali sono esatte
 *   *          <code>false</code> altrimenti
     * @throws SQLException
     */
    Boolean checkLogin(String userpass) throws SQLException;
    /**
     * 
     * @param flight volo da modificare
     * @return volo modificato
     * @throws SQLException 
     */
    Flight editFlight(Flight flight) throws SQLException;
    
    //void setSeat(String volo, int num, int classe) throws SQLException;
    
    //int numberSeatFirstClassFlight(String cod) throws SQLException;
    
    //void insertSupplement(String code, String codeTicket) throws SQLException;

    //void insertSupplementHoldLuggage(String code, String codeTicket) throws SQLException;
    
    //void insertSupplementInsurance(String code, String codeTicket) throws SQLException;
    
    //void insertSupplementMeal(String code, String codeTicket) throws SQLException;
    
    //ArrayList<Seat> getSeatsFlight(String codeFlight) throws SQLException;
    
    //verifico se è stato fatto il checkIn
    
    //int numberSeatFlight(String codeFlight) throws SQLException;
    
    //void setSeatBoolean(String codeFlight, int nSeat, String ticketPassenger, boolean b) throws SQLException;
    
    //void setAllSeatFlight(Flight volo) throws SQLException;
    
    //Reservation addTickets(Reservation reservation) throws SQLException;
}
