package clientblueairline;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.security.ntlm.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class scrittura implements Runnable {
        ClientBlueAirline client;
        
        public scrittura(ClientBlueAirline prova) {
            this.client=prova;
        }

    
        @Override
        public void run() {

            while (true) {
                
                try {
                    client.scrittura();
                } catch (IOException ex) {
                    Logger.getLogger(scrittura.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }    
            }
            
        }
}