/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oggetti.Flight;
import oggetti.Route;

/**
 *
 * @author riccardo
 */
public class lettura implements Runnable {

    ClientBlueAirline client;

    public lettura(ClientBlueAirline n) {
        this.client = n;
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        while (true) {
            String s1 = input.nextLine().toUpperCase();
            switch (s1) {

                case "HI": {
                    try {
                        client.hello();
                    } catch (IOException ex) {
                        Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case "VERIFICA_VOLO":
                    System.out.println("Inserisci partenza");
                    String part = input.nextLine();
                    System.out.println("Inserisci destinazione");
                    String dest = input.nextLine();
                    Route tmproute = new Route(part, dest);
                    System.out.println("Inserisci data di partenza AAAA-MM-GG");
                    String data = input.nextLine();
                    
                    int day,month,year;
                    String[] vetDate = data.split("-");
                    if(vetDate.length==3){
                    year = Integer.parseInt(vetDate[0]);
                    month = Integer.parseInt(vetDate[1])-1;
                    day = Integer.parseInt(vetDate[2]);
                    }
                    else{
                        System.out.println("INSERITO DATA SBAGLIATA");
                        break;
                    }
                        

                    GregorianCalendar date = new GregorianCalendar(year, month, day);

                    Flight tmpflight = new Flight(tmproute, date);
                    
                     {
                        try {
                            Flight[] volit=client.checkFligt(tmpflight);
                            for(Flight v:volit){
                                System.out.println(v);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case "VERIFICA_TRATTA":
                    System.out.println("Inserisci partenza");
                    String part1 = input.nextLine();
                    System.out.println("Inserisci destinazione");
                    String dest1 = input.nextLine();
                    Route tmproute1 = new Route(part1, dest1);
                     {
                        try {
                            client.checkRoute(tmproute1);
                        } catch (IOException ex) {
                            Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case "CALENDARIO_VOLI":

                 System.out.println("Inserisci Aeroporto partenza");
                 String part2 = input.nextLine();
                 System.out.println("Inserisci Aeroporto destinazione");
                 String dest2 = input.nextLine();
                 Route tmproute2= new Route();
                 tmproute2.setDeparutreAirport(part2);
                 tmproute2.setDestinationAirport(dest2);
                 {
                 try {
                 Flight[] calendario=client.calendar(tmproute2);
                 for(Flight a:calendario){
                     System.out.println(a);
                 }
                 } catch (IOException ex) {
                 Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 }
                 break;
                default:
                    System.out.println("idiota scrivi giusto");

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
