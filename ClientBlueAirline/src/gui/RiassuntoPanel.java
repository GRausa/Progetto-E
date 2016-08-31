/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.ClientBlueAirline;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objects.Flight;
import objects.Reservation;
import objects.Ticket;

/**
 *
 * @author Michele
 */

public class RiassuntoPanel extends JPanel {

    private HomeFrame home;
    private ClientBlueAirline controller;
    private ArrayList<Ticket> passengers;
    private Reservation reservation;
    private Flight flight;
    
    JPanel infoGenerali = new JPanel();
    JLabel email = new JLabel();
    JLabel codice = new JLabel();
    JLabel andata = new JLabel();
    JLabel ritorno = new JLabel();
    JLabel data = new JLabel();
    JLabel orario = new JLabel();
    
    JPanel info =new JPanel();
    JPanel infoPasseggero = new JPanel();
    JLabel assicurazione = new JLabel();
    JLabel pasto = new JLabel();
    JLabel classe = new JLabel();
    JLabel bagaglio = new JLabel();
    JLabel prezzo = new JLabel();
    JLabel numPosto = new JLabel();
    JComboBox passeggeri = new JComboBox();
        
    JButton conferma = new JButton("Conferma");
    
    
    public RiassuntoPanel(HomeFrame home, ClientBlueAirline controller) throws IOException {
        this.home = home;
        passengers = home.getPassengers();
        reservation = home.getReservation();
        this.controller=controller;
        flight = new Flight(home.getCodeflight());
        this.setVisible(true);
        setOpaque(false);
        addComponents(this);
        home.setallFont(infoGenerali);
        home.setallFont(this.info);
        home.setallFont(this.infoPasseggero);
        this.makeComponentsTrasparent();
        passeggeri.setForeground(Color.black);
        
    }

    private void addComponents(Container pane) throws IOException {
        
        pane.setLayout(new BorderLayout());
        initInfoGenerali();
        pane.add(infoGenerali, BorderLayout.NORTH);
        initInfoPasseggero();
        pane.add(info, BorderLayout.CENTER);
        
        
        pane.add(conferma, BorderLayout.SOUTH);
           
    }

    private void initInfoGenerali() {
        try {
            infoGenerali.setLayout(new GridLayout(3,2));
            infoGenerali.setOpaque(false);
            codice.setText(reservation.getCodeFlight());
            infoGenerali.add(codice);
            email.setText(reservation.getEmail());
            infoGenerali.add(email);
            Flight f= controller.searchFlight(flight);
            andata.setText(f.getRoute().getDepartureCity() +" "+f.getRoute().getDeparutreAirport()); // Non so da dove pigliare andata,ritorno,data e ora
            infoGenerali.add(andata);
            ritorno.setText(f.getRoute().getDestinationCity()+ " " + f.getRoute().getDestinationAirport());
            infoGenerali.add(ritorno);
            SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            String d = format1.format(f.getDateDeparture().getTime());
            data.setText(d);
            infoGenerali.add(data);
            SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");
            String d2= format2.format(f.getDateDeparture().getTime());
            orario.setText(d2);
            infoGenerali.add(orario);
        } catch (IOException ex) {
            Logger.getLogger(RiassuntoPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void initInfoPasseggero() throws IOException {
       
        info.setLayout(new BorderLayout());
        info.setOpaque(false);
        info.add(passeggeri, BorderLayout.NORTH);
        for(int i=0; i<passengers.size(); i++) {
            passeggeri.addItem(passengers.get(i).getName() + " " + passengers.get(i).getSurname());
        }
        infoPasseggero.setLayout(new GridLayout(3,2));
        infoPasseggero.setOpaque(false);
        
        Flight f= controller.searchFlight(flight);
        numPosto.setText("Posto a sedere: " + passengers.get(0).getNseat());
        infoPasseggero.add(numPosto);
        
        classe.setText("Classe: " );
        infoPasseggero.add(classe);
        prezzo.setText("Prezzo: " /*+ passengers.get(0).getTotalPrice() +"€"*/);
        infoPasseggero.add(prezzo);
        bagaglio.setText("Bagaglio: ");
        infoPasseggero.add(bagaglio); 
        pasto.setText("Pasto: ");
        infoPasseggero.add(pasto);
        assicurazione.setText("Assicurazione: ");
        infoPasseggero.add(assicurazione);
        
        info.add(infoPasseggero,BorderLayout.CENTER);
       
        passeggeri.addActionListener(PassengerListener());
    }

    private void makeComponentsTrasparent() {
        home.trasparentButton(conferma);
        //home.transparentComboBox(passeggeri);
    }
    
    private ActionListener PassengerListener()
    {
     ActionListener evento = new ActionListener() {
         
           @Override
           public void actionPerformed(ActionEvent e) {
               String tmp = passeggeri.getSelectedItem().toString();
               String[] spl = tmp.split(" ");
               for(int j = 0; j<passengers.size(); j++) {
                   if(spl[0].equals(passengers.get(j).getName()) && spl[1].equals(passengers.get(j).getSurname())) {
                       //come facciamo a mettere gli arrayList? io ho pensato a un comboBox non modificabile ma non so se va bene
                        infoPasseggero.removeAll();
                        infoPasseggero.setLayout(new GridLayout(3,2));
                        pasto.setText("Pasto: ");
                        infoPasseggero.add(pasto);
                        assicurazione.setText("Assicurazione: " );
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
     };
     return evento;
    }

   
}
