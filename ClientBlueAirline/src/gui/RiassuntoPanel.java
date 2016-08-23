/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static GUI.PasseggeriPanel.changeFont;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import oggetti.Reservation;
import oggetti.TicketPassenger;

/**
 *
 * @author Michele
 */

public class RiassuntoPanel extends JPanel {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    private HomeFrame home;
    private ArrayList<TicketPassenger> passengers;
    private Reservation reservation;
    
    JPanel infoGenerali = new JPanel();
    JLabel email = new JLabel();
    JLabel codice = new JLabel();
    JLabel andata = new JLabel();
    JLabel ritorno = new JLabel();
    JLabel data = new JLabel();
    JLabel orario = new JLabel();
    
    JPanel infoPasseggero = new JPanel();
    JLabel assicurazione = new JLabel();
    JLabel pasto = new JLabel();
    JLabel classe = new JLabel();
    JLabel bagaglio = new JLabel();
    JLabel prezzo = new JLabel();
    JLabel numPosto = new JLabel();
    JComboBox passeggeri = new JComboBox();
        
    JButton conferma = new JButton("Conferma");
    
    
    public RiassuntoPanel(HomeFrame home) {
        this.home = home;
        passengers = home.getPassengers();
        reservation = home.getReservation();
        this.setVisible(true);
        setOpaque(false);
        changeFont(this,new Font("Helvetica", Font.BOLD,15));
        addComponents(this);
        home.setallFont(this);
        this.makeComponentsTrasparent();
    
    }

    private void addComponents(Container pane) {
        
        setLayout(new BorderLayout());
        initInfoGenerali();
        pane.add(infoGenerali, BorderLayout.NORTH);
        
        initInfoPasseggero();
        pane.add(infoPasseggero, BorderLayout.CENTER);
        
        
        pane.add(conferma, BorderLayout.SOUTH);
           
    }

    private void initInfoGenerali() {
        infoGenerali.setLayout(new GridLayout(3,2));
        codice.setText(reservation.getCodeFlight());
        infoGenerali.add(codice);
        email.setText(reservation.getEmail());
        infoGenerali.add(email);
        andata.setText(" "); // Non so da dove pigliare andata,ritorno,data e ora
        infoGenerali.add(andata);
        ritorno.setText(" ");
        infoGenerali.add(ritorno);
        data.setText(" ");
        infoGenerali.add(data);
        orario.setText(" ");
        infoGenerali.add(orario);
        
    }

    private void initInfoPasseggero() {
        
       add(passeggeri, BorderLayout.CENTER);
        for(int i=0; i<passengers.size(); i++) {
            passeggeri.addItem(passengers.get(i).getName() + " " + passengers.get(i).getSurname());
        }
        infoPasseggero.setLayout(new GridLayout(3,2));
        passeggeri.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String tmp = passeggeri.getSelectedItem().toString();
               String[] spl = tmp.split(" ");
               for(int j = 0; j<passengers.size(); j++) {
                   if(spl[0].equals(passengers.get(j).getName()) && spl[1].equals(passengers.get(j).getSurname())) {
                       //come facciamo a mettere gli arrayList? io ho pensato a un comboBox non modificabile ma non so se va bene
                        pasto.setText("Pasto: ");
                        infoPasseggero.add(pasto);
                        assicurazione.setText("Assicurazione: ");
                        infoPasseggero.add(assicurazione);
                        numPosto.setText("Posto a sedere: " + passengers.get(j).getNseat());
                        infoPasseggero.add(numPosto);
                        classe.setText("Classe: " );
                        infoPasseggero.add(classe);
                        prezzo.setText("Prezzo: " + passengers.get(j).getTotalPrice());
                        infoPasseggero.add(prezzo);
                        bagaglio.setText("Bagaglio: ");
                        infoPasseggero.add(bagaglio); 
                   } 
               }              
           }
       });    
    }

    private void makeComponentsTrasparent() {
        home.transparentButton(conferma);
        home.transparentComboBox(passeggeri);
    }
    
    
}
