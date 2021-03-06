/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import objects.Route;

/**
 *
 * @author riccardo
 */
public class Test02_searchRoute {

    public static void main(String[] args){
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        Route route = new Route("Milano", "");
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
