/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Route;
import tui.client.ControllerClientTxt;
import tui.client.MethodsControlClient;

/**
 *
 * @author Giovanni
 */
//SINGOLA ROTTA
public class Test1_searchRoute {

    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        Route route = new Route("New York", "Roma");
        Route[] routes = client.searchRoutes(route);
        if (routes.length > 0) {
            for (Route r : routes) {
                System.out.println(r.printRoute());
            }
        } else {
            System.out.println("Non esiste tratta per queste città");
        }
    }

}
