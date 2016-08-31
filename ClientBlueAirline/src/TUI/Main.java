/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TUI;

import controller.ClientBlueAirline;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 *
 * @author cl418377
 */
public class Main {

    public static void main(String[] args) throws IOException {
        ClientBlueAirline clientBlueAirline = new ClientBlueAirline();
        if (clientBlueAirline.connect(MethodsControl.lettura(new ArrayList<>(asList("PUT IP SERVER"))).get(0))) {
            ControllerTxt r1 = new ControllerTxt(clientBlueAirline);

            Thread nuovoThread1 = new Thread(r1);

            nuovoThread1.start();
        }

        System.out.println("ERRORE CONNESSIONE SERVER NON RIUSCITA");
    }
}
