/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllertxt;

import administrators.ControllerAdministrator;
import clients.ControllerClient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import objects.Flight;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class ControllerTxt {
    ControllerClient CC;
    ControllerAdministrator CA;
    
    public ControllerTxt(){
        CC = new ControllerClient();
        CA = new ControllerAdministrator();
    }
    
    public void searchRoutes() throws SQLException{
        ArrayList<Route> routes = CC.searchRoutes();
        for(Route r:routes){
            System.out.println(r);
        }
    }
    
    public void searchFlights() throws SQLException{
        Scanner input = new Scanner(System.in);
        System.out.println("Ricerca volo per citta': (Partenza /tab/ Arrivo /tab/ Data(AAAA-MM-GG))");
        String s = input.nextLine();
        String[] vet = s.split("\t");     
        ArrayList<Flight> flights = CC.searchFlights(vet[0],vet[1],vet[2]);    
        for(Flight f:flights){
            System.out.println(f);
        }
    }
    
}
