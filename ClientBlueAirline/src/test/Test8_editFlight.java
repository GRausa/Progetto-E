/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import java.util.GregorianCalendar;
import objects.Flight;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class Test8_editFlight {
    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        
        if(client.checkLogin("user-password")){
            Flight f = client.searchFlight(new Flight("00IDG7"));
            f.setDateDeparture(new GregorianCalendar(2016, 9, 10, 15, 30)); //10 Ottobre 2016 alle 15:30
            f.setDateDestination(new GregorianCalendar(2016, 9, 10, 18, 00)); //10 Ottobre 2016 alle 18:00
            f.setPrice(225);
            f = client.editFlight(f);
            System.out.println("MODIFICA EFFETTUATA\n"+f.toString());
        }
        else{
            System.out.println("Credenziali non valide");
        }
        
    }
    
}
