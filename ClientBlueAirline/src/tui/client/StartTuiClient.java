/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui.client;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Provides the running of the textual user interface for customers.
 * 
 * @author riccardoz
 */
public class StartTuiClient {
    /**
     * Main method.
     * 
     * @param args
     * @throws IOException if has occurred an I/O exception.
     */
    public static void main(String[] args) throws IOException {
        InterfaceClient clientBlueAirline = FacadeControllerClient.getIstance();
        if (clientBlueAirline.connect(MethodsControlClient.scannerInput(new ArrayList<>(asList("PUT IP SERVER"))).get(0))) {
            ControllerClientTxt r1 = new ControllerClientTxt(clientBlueAirline);
            Thread nuovoThread1 = new Thread(r1);
            nuovoThread1.start();
        }
        else{
            System.out.println("ERRORE CONNESSIONE SERVER NON RIUSCITA");
        }        
    }
}

