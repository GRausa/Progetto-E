/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.util.GregorianCalendar;
import objects.Flight;

/**
 *
 * @author Giovanni
 */
public class Test10_editFlight {

    public static void main(String[] args) {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");

        if (client.checkLogin("user-password")) {
            Flight f = client.searchFlights(new Flight("00IDG7"))[0];
            f.setDateDeparture(new GregorianCalendar(2016, 8, 16, 15, 30)); //16 Settembre 2016 alle 15:30
            f.setDateDestination(new GregorianCalendar(2016, 8, 16, 18, 00)); //16 Settembre 2016 alle 18:00
            f.setPrice(225);
            f = client.editFlight(f);
            System.out.println("Modifica effettuata\n" + f.toString());
        } else {
            System.out.println("Credenziali non valide");
        }

    }

}
