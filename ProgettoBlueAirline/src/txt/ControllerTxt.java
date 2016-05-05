/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//IMPORTANTE 
/*
DISCUTERE MEGLIO DI QUESTA CLASSE
IDEA DI ZODRACCIR-> RENDERLA UN CONTROLLER VERO E PROPRIO, LA MODALITÀ TXT DOVRA' FUNZIONARE
COME UNA SHELL,NEL SENSO CHE DA LINEA DI COMANDO DIAMO L'ISTRUZIONE DA FARE E LUI ESEGUE,
NON VA MESSO NEL MAIN(COME ABBIAMO FATTO ADESSO) :  ControllerTxt.checkSeats(c);
QUINDI DA RIVEDERE.


*/

package txt;

import blueAirline.Company;
import blueAirline.Flight;
import blueAirline.Route;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
            GregorianCalendar date = new GregorianCalendar(Integer.parseInt(vet[2]),Integer.parseInt(vet[1])-1,Integer.parseInt(vet[0])); 
            ArrayList<Flight> arrayFlight = c.searchFlights(r,date);
            for(Flight f:arrayFlight){
                System.out.println("TROVATO IL SEGUENTE VOLO");
                System.out.println(f);
            }
            if(arrayFlight!=null)
                System.out.println("Nessun volo per questa data, riprova con un'altra data");
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
                return 0;//0 STA PER ERRORE:POSTI INSUFFICIENTI
            }
            else{
                System.out.println("ok! CI sono abbastanza posti per questo volo. numero posti disponibili per questo volo :"+app.getSeatFree());
                return n;//n STA PER TUTTO OK!, così proseguiamo la prenotazione con questo numero
            }
        }
        else{
            return -1;//ERRORE NON ESISTE UN VOLO CON QUESTO CODICE
        }
        
    }
    
    /*public static int Order(Company c){
        int k=checkSeats(c);
        if(k>0){
            c.makeReservation();         
            
            si deve cambiare anche per questo
            
            
        }
        
        
    }*/
}
