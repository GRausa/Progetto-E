/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.io.IOException;

/**
 *
 * @author cl418377
 */
public class Main {
    public static void main(String[] args) throws IOException {
            ClientBlueAirline clientBlueAirline=new ClientBlueAirline();
          
            
            ReadClass r1 = new ReadClass(clientBlueAirline);
            //scrittura r2 = new scrittura(ciao);
		
            Thread nuovoThread1 = new Thread(r1);
            //Thread nuovoThread2 = new Thread(r2);

            nuovoThread1.start();
            //nuovoThread2.start();
    }
}
