/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import objects.Flight;
import objects.Route;

/**
 *
 * @author riccardo
 */
public class Test0_searchFlightRoute {

    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        Route route = new Route("Tegel", "Linate", "Berlino", "Milano");
        Flight[] flights = client.calendar(route);
        if (flights.length > 0) {
            for (Flight r : flights) {
                System.out.println(r.toString());
            }
        } else {
            System.out.println("Non esistono voli per queste tratta");
        }
    }
}
