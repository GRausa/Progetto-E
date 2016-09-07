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
 * @author riccardo
 */
public class Test04_searchFlight {

    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        Route route = new Route("New York", "Roma");

        GregorianCalendar date = new GregorianCalendar(2016, 8, 15); //15 Settembre 2016

        Flight flight = new Flight(route, date);
        Flight[] flights = client.searchFlights(flight);
        if (flights.length > 0) {
            for (Flight r : flights) {
                System.out.println(r.toString());
            }
        } else {
            System.out.println("Non esiste tratta per queste città");
        }
    }
}
