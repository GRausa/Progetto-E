/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import blueAirline.Company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreacavagna
 */
public class TestUi {
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        //</editor-fold>
        //</editor-fold>
        
          
       
       
    
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) {}
                
                
                Company c = new Company("BlueAirlines");
                try {
                    c.downloadAirplanes("file/Airplanes.txt");
                
       
                c.downloadCitys("file/Citys.txt");
        
                c.downloadAirports("file/Airports.txt");
        
                c.downloadRoutes("file/Routes.txt");
       
        
                c.downloadFlight("file/Flights.txt");
        } catch (IOException ex) {
                    Logger.getLogger(TestUi.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TestUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                new FrameReservation(c).setVisible(true);
                
                
            }
        });
    
    }
}
