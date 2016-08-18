/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import clientblueairline.ClientBlueAirline;
import clientblueairline.ReadClass;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author andreacavagna
 */
public class TestUi {
  
    public static void main(String[] args) throws IOException {
            ClientBlueAirline clientBlueAirline=new ClientBlueAirline();
             HomeFrame frame = new HomeFrame("BLUE AIRLINES",clientBlueAirline);
        
            
            ReadClass r1 = new ReadClass(clientBlueAirline);
            //scrittura r2 = new scrittura(ciao);
		
            Thread nuovoThread1 = new Thread(r1);
            //Thread nuovoThread2 = new Thread(r2);

            nuovoThread1.start();
            //nuovoThread2.start();
    } 
}
