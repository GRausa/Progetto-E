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
public final class ControllerTxt {
    
    public static void searchTxtFlight(Company c){
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
            String [] vet = new String[3];
            vet=s1.split("/");
            Date date = new Date (Integer.parseInt(vet[2]),Integer.parseInt(vet[1]),Integer.parseInt(vet[0])); 
            ArrayList<Flight> arrayFlight = c.searchFlights(r,date);
            for(Flight f:arrayFlight){
                System.out.println(f);
            }
        }
        else{
            System.out.println("Route non presente.");
        }
    }
    
    public static void checkSeats(Company c){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci codice volo: ");
        String s1 = input.nextLine();
        System.out.println("Inserisci numero passeggeri: ");
        int n = input.nextInt();
        
    }
}
