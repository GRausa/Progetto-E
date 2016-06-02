/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import clients.ControllerClient;
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
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
      
    ControllerClient c = new ControllerClient();
    HomeFrame frame = new HomeFrame("BLUE AIRLINES",c);
        
      
}
}
