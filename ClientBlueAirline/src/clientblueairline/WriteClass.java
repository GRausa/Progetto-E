package clientblueairline;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author riccardo
 */
public class WriteClass implements Runnable {
        ClientBlueAirline client;
        
        public WriteClass(ClientBlueAirline prova) {
            this.client=prova;
        }

    
        @Override
        public void run() {

            while (true) {
                
                try {
                    client.scrittura();
                } catch (IOException ex) {
                    Logger.getLogger(WriteClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientBlueAirline.class.getName()).log(Level.SEVERE, null, ex);
            }    
            }
            
        }
}