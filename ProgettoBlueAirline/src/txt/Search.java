/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package txt;

import blueAirline.Company;
import blueAirline.Flight;
import blueAirline.Route;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author cl418377
 */
public final class Search {
    
    public static Flight searchFlight(Company c){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci partenza: ");
        String s1 = input.nextLine();
        System.out.println("Inserisci arrivo: ");
        String s2 = input.nextLine();
        Route r = c.searchRoute(s1, s2);
        if(r!=null){
            System.out.println("Route presente.");
            System.out.println("Inserisci Data (gg/mm/aaaa) : ");
            s1 = input.nextLine();
            Date date = new Date (Integer.parseInt(s1.substring(0, 1)),Integer.parseInt(s1.substring(3, 4)),Integer.parseInt(s1.substring(6, 9))); 
            ArrayList<Flight> arrayFlight = c.searchFlights(r,date);
            return null;
        }
        else{
            System.out.println("Route non presente.");
            return null;
        }
    }
}
