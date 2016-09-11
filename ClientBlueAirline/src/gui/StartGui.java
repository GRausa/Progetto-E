/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.FacadeControllerClient;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andreacavagna
 */
public class StartGui {

    public static void main(String[] args) throws IOException, InterruptedException {
        FacadeControllerClient clientBlueAirline = FacadeControllerClient.getIstance();
        
        
        
        String[] options = {"OK"};
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Enter IP server: ");
        JTextField txt = new JTextField(10);
        panel.add(lbl);
        panel.add(txt);
        ImageIcon im = new ImageIcon("immagini/logo2.png");
        im = scalaImmagine(im, 60, 60);
        int selectedOption = JOptionPane.showOptionDialog(null, panel, "BENVENUTI IN THETABLUEAIRLINE", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, im, options, options[0]);

        if (selectedOption == 0) {
            String text = txt.getText();
            if (clientBlueAirline.connect(text)) {
                HomeFrame frame = new HomeFrame("BLUE AIRLINE", clientBlueAirline);
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

        
    }
    
      public static ImageIcon scalaImmagine(ImageIcon immagine, int lunghezza, int altezza) {
        return new ImageIcon(immagine.getImage().getScaledInstance(lunghezza, altezza, java.awt.Image.SCALE_SMOOTH));
    }
  
}
