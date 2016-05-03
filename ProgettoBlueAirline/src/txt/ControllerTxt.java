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
    
    public static int checkSeats(Company c){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci codice volo: ");
        String s1 = input.nextLine();
        System.out.println("Inserisci numero passeggeri: ");
        int n = input.nextInt();
        Flight app=c.searchFlights(s1);
        if(app!=null){
            if(n>app.getSeatFree()){
                System.out.println("Non ci sono Posti sufficienti per questo volo, n° posti disponibili = "+app.getSeatFree());
                return 1;//1 STA PER ERRORE:POSTI INSUFFICIENTI
            }
            else{
                System.out.println("ok! CI sono abbastanza posti per questo volo. numero posti disponibili per questo volo :"+app.getSeatFree());
                return 0;//0 STA PER TUTTO OK!
            }
        }
        else{
            return -1;//ERRORE NON ESISTE UN VOLO CON QUESTO CODICE
        }
        
    }
}
