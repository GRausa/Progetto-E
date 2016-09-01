/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.FacadeControllerClient;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andreacavagna
 */
public class TestUi {

    public static void main(String[] args) throws IOException, InterruptedException {
        FacadeControllerClient clientBlueAirline = new FacadeControllerClient();
        JFrame frame1 = new JFrame();

        //String s = (String) JOptionPane.showInputDialog(frame1, "Complete the sentence:\n" + "\"Green eggs and...\"", "Customized Dialog", JOptionPane.PLAIN_MESSAGE, null, null, "ham");
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Enter IP server: ");
        JTextField txt = new JTextField(10);
        panel.add(lbl);
        panel.add(txt);
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "BENVENUTI IN THETABLUEAIRLINE", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (selectedOption == 0) {
            String text = txt.getText();
            if (clientBlueAirline.connect(text)) {
                HomeFrame frame = new HomeFrame("BLUE AIRLINES", clientBlueAirline);
            } else {
                String backupDir = "IP ADDRESS ERROR";

                // create a jframe
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");

                // show a joptionpane dialog using showMessageDialog
                JOptionPane.showMessageDialog(frame,
                        "STOP RUNNING FOR : '" + backupDir + "'.");
                System.exit(0);
            }
            // ...
        }

//If a string was returned, say so.

        /*
         int input = JOptionPane.showOptionDialog(null, "CONNESSIONE CON IL SERVER NON RIUSCITA", "ERRORE CONNESSIONE", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);

         if (input == JOptionPane.OK_OPTION) {
         System.exit(1);
         }
         */
    }
    /*
     ControllerTxt r1 = new ControllerTxt(clientBlueAirline);
     //scrittura r2 = new scrittura(ciao);
		
     Thread nuovoThread1 = new Thread(r1);
     //Thread nuovoThread2 = new Thread(r2);

     nuovoThread1.start();
     //nuovoThread2.start();*/
}
