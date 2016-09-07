/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FacadeControllerClient implements InterfaceClient {
    
    static FacadeControllerClient istance;

    private Socket clientSocket;
    private int PortNumber = 8888;
    private PrintWriter out;
    private BufferedReader in;
    private Gson gson = new Gson();
    
    private FacadeControllerClient(){        
    }
    /**
     * 
     * @return an istance of facadeControllerClient.
     */
    public static FacadeControllerClient getIstance(){
        if(istance==null)
            istance = new FacadeControllerClient();
        return istance;
    }

    @Override
    public boolean connect(String ipServer) {
        try {
            try {
                clientSocket = new Socket(ipServer, PortNumber);
            } catch (UnknownHostException | ConnectException a) {
                return false;
                //Logger.getLogger(ClientBlueAirline.class.getName()).log(Level.SEVERE, "ERROR", ex);
            } catch (IOException ex) {
                Logger.getLogger(FacadeControllerClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException ex) {
            Logger.getLogger(FacadeControllerClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public boolean hello() {
        try {
            out.println("HI!");
            if (in.readLine().equals("HI!")) {
                System.out.println("RICEVUTA RISPOSTA DAL SERVER, IL SERVER E' ATTIVO");

            }
        } catch (IOException ex) {
            Logger.getLogger(FacadeControllerClient.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    @Override
    public Flight[] searchFlights(Flight flight){
        try {
            out.println("SEARCHFLIGHTS " + gson.toJson(flight));
            Flight[] flights = gson.fromJson(in.readLine(), Flight[].class);
            return flights;
        } catch (IOException ex) {
            Logger.getLogger(FacadeControllerClient.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
/*
    @Override
    public Flight searchFlight(Flight flight) throws IOException {
        out.println("SEARCHFLIGHTCODE " + gson.toJson(flight));
        Flight f = gson.fromJson(in.readLine(), Flight.class);
        return f;
    }
*/
    @Override
    public Reservation makeReservation(Reservation res) throws IOException {
        out.println("RESERVATION " + gson.toJson(res));
        return gson.fromJson(in.readLine(), Reservation.class);
    }

    @Override
    public Ticket editSeatTicket(Ticket tp) throws IOException {
        out.println("EDITSEATTICKET " + gson.toJson(tp));
        return gson.fromJson(in.readLine(), Ticket.class);
    }

    @Override
    public Route[] searchRoutes(Route rotta) throws IOException {
        out.println("ROUTES " + gson.toJson(rotta));
        String serverout = in.readLine();
        Route[] rotte = gson.fromJson(serverout, Route[].class);
        return rotte;
    }

    @Override
    public String[] getAllCitys() throws IOException {
        out.println("CITYS");
        String[] cities = gson.fromJson(in.readLine(), String[].class);
        return cities;
    }

    @Override
    public Flight[] calendar(Route rotta) throws IOException {
        out.println("CALENDAR " + gson.toJson(rotta));
        Flight[] flights = gson.fromJson(in.readLine(), Flight[].class);
        return flights;
    }

    @Override
    public Meal[] getAllMeals() throws IOException {
        out.println("MEALS ");
        Meal[] meals = gson.fromJson(in.readLine(), Meal[].class);
        return meals;
    }

    @Override
    public HoldLuggage[] getAllHoldLuggages() throws IOException {
        out.println("HOLDLUGGAGES ");
        HoldLuggage[] holdLuggages = gson.fromJson(in.readLine(), HoldLuggage[].class);
        return holdLuggages;
    }

    @Override
    public Insurance[] getAllInsurances() throws IOException {
        out.println("INSURANCES ");
        Insurance[] insurances = gson.fromJson(in.readLine(), Insurance[].class);
        return insurances;
    }

    @Override
    public Ticket checkIn(Ticket tp) throws IOException {
        out.println("CHECKIN " + gson.toJson(tp));
        Ticket tp1 = gson.fromJson(in.readLine(), Ticket.class);
        return tp1;
    }

    @Override
    public boolean isCheckIn(Ticket tp) throws IOException {
        out.println("ISCHECKIN " + gson.toJson(tp));
        Boolean isCheckIn = gson.fromJson(in.readLine(), Boolean.class);
        return isCheckIn;
    }

    @Override
    public Ticket getTicket(Ticket tp) throws IOException {
        out.println("TICKET " + gson.toJson(tp));
        Ticket tp1 = gson.fromJson(in.readLine(), Ticket.class);
        return tp1;
    }

    @Override
    public Ticket editTicket(Ticket ticketPassenger) throws IOException {
        out.println("EDITTICKET " + gson.toJson(ticketPassenger));
        Ticket tp = gson.fromJson(in.readLine(), Ticket.class);
        return tp;
    }

    @Override
    public Reservation getReservation(Reservation res) throws IOException {
        out.println("GETRESERVATION " + gson.toJson(res));
        Reservation res1 = gson.fromJson(in.readLine(), Reservation.class);
        return res1;
    }

    @Override
    public Flight insertFlight(Flight flight) throws IOException {
        out.println("INSERTFLIGHT " + gson.toJson(flight));
        Flight f = gson.fromJson(in.readLine(), Flight.class);
        return f;
    }
    
    @Override
    public boolean checkLogin(String userpass) throws IOException {
        out.println("LOGIN " + gson.toJson(userpass));
        Boolean b = gson.fromJson(in.readLine(), Boolean.class);
        return b;
    }

    @Override
    public boolean sendMail(String destination, String object, String text) throws IOException {
        String mail = destination + "\t" + object + "\t" + text;
        out.println("SENDMAIL " + mail);

        boolean returned = in.readLine().equals("true");
        return returned;
    }

    @Override
    public Flight editFlight(Flight flight) throws IOException {
        out.println("EDITFLIGHT " + gson.toJson(flight));
        Flight f = gson.fromJson(in.readLine(), Flight.class);
        return f;
    }

}
