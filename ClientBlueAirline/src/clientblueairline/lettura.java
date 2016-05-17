/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientblueairline;

import com.sun.security.ntlm.Client;
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
                String s1 = input.nextLine();
                switch (s1) {
                    case "prenotazione!":
                        
                {
                    try {
                        client.hello();
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
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }