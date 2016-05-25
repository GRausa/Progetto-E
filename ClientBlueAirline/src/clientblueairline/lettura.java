/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;


import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                
                case "VERIFICA_TRATTA":
                    System.out.println("Inserisci partenza");
                    String part = input.nextLine();
                    System.out.println("Inserisci destinazione");
                    String dest = input.nextLine();
                     {
                        try {
                            client.checkRoute(part, dest);
                        } catch (IOException ex) {
                            Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                
                case "CALENDARIO_VOLI":
                    
                    System.out.println("Inserisci Aeroporto partenza");
                    String part1 = input.nextLine();
                    System.out.println("Inserisci Aeroporto destinazione");
                    String dest1 = input.nextLine();
                     {
                        try {
                            client.calendar(part1, dest1);
                        } catch (IOException ex) {
                            Logger.getLogger(lettura.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;
                
                default:
                    System.out.println("idiota scrivi giusto");

            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
