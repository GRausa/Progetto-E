/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import oggetti.Flight;
import oggetti.Route;

/**
 *
 * @author cl418377
 */
public class ClientBlueAirline {

    public Socket clientSocket;
    int PortNumber = 8888;
    public PrintWriter out;
    public BufferedReader in;
    Gson gson = new Gson();

    public ClientBlueAirline() throws IOException {
        clientSocket = new Socket("localhost", PortNumber);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public void hello() throws IOException {
        out.println("HI!");
        if(in.readLine().equals("HI!")){
            System.out.println("RICEVUTA RISPOSTA DAL SERVER, IL SERVER è ATTIVO");
        }
    }
    
    public Flight[] checkFligt(Flight flight) throws IOException{
        
        out.println("RICERCAVOLO "+gson.toJson(flight));
        Flight[] flights= gson.fromJson(in.readLine(), Flight[].class);
        return flights;
    }
    
    public boolean checkRoute(Route rotta) throws IOException{
        
        out.println("CHECK "+gson.toJson(rotta));
        String serverout=in.readLine();
       
        Route []rotte=gson.fromJson(serverout, Route[].class);
        if(rotte.length>0){
        for(Route r:rotte){
            System.out.println(r);
        }
        }
        else 
            System.out.println("NON ESISTE TRATTA PER QUESTA CITTA'");
        return false;
    }
    
    public Flight[] calendar(Route rotta) throws IOException{
        out.println("CALENDAR "+gson.toJson(rotta));
        Flight[] flights= gson.fromJson(in.readLine(), Flight[].class);
        return flights;
    }

    public void scrittura() throws IOException {
        System.out.println("RICEVUTO DAL SERVER: " + in.readLine());

    }

}
