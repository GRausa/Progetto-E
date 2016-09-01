/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controller;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import objects.*;

/**
 *
 * @author Michele
 */
public class RiassuntoPanel extends JPanel {

    private HomeFrame home;
    private Controller controller;
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

    JPanel info = new JPanel();
    JPanel infoPasseggero = new JPanel();

    JLabel classe = new JLabel();

    JLabel prezzo = new JLabel();
    JLabel numPosto = new JLabel();
    JComboBox passeggeri = new JComboBox();
    JTextArea riassunto = new JTextArea(30, 10);

    JButton conferma = new JButton("Conferma");

    public RiassuntoPanel(HomeFrame home, Controller controller) throws IOException {
        this.home = home;
        passengers = home.getPassengers();
        reservation = home.getReservation();
        this.controller = controller;
        flight = controller.searchFlight(new Flight(home.getCodeflight()));
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
        conferma.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.makeReservation(home.getReservation());
                    JOptionPane.showMessageDialog(home, "Congratulazioni!\n" + "Prenotazione Effettuata\n");
                    home.returnHome();
                } catch (IOException ex) {
                    Logger.getLogger(RiassuntoPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    private void initInfoGenerali() {
        infoGenerali.setLayout(new GridLayout(3, 2));
        infoGenerali.setOpaque(false);
        codice.setText(reservation.getCodeFlight());
        infoGenerali.add(codice);
        email.setText(reservation.getEmail());
        infoGenerali.add(email);
        andata.setText(flight.getRoute().getDepartureCity() + " " + flight.getRoute().getDeparutreAirport());
        infoGenerali.add(andata);
        ritorno.setText(flight.getRoute().getDestinationCity() + " " + flight.getRoute().getDestinationAirport());
        infoGenerali.add(ritorno);
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        String d = format1.format(flight.getDateDeparture().getTime());
        data.setText(d);
        infoGenerali.add(data);
        SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");
        String d2 = format2.format(flight.getDateDeparture().getTime());
        orario.setText(d2);
        infoGenerali.add(orario);

    }

    private void initInfoPasseggero() throws IOException {

        info.setLayout(new BorderLayout());
        info.setOpaque(false);
        info.add(passeggeri, BorderLayout.NORTH);
        for (int i = 0; i < passengers.size(); i++) {
            passeggeri.addItem(passengers.get(i).getName() + " " + passengers.get(i).getSurname());
        }
        infoPasseggero.setLayout(new GridLayout(3, 2));
        infoPasseggero.setOpaque(false);

        numPosto.setText("Posto a sedere: " + passengers.get(0).getNseat());
        infoPasseggero.add(numPosto);

        int cl = home.getFlighttmp().getSeats().get(passengers.get(0).getNseat() - 1).getClasse();
        classe.setText("Classe: " + cl);
        infoPasseggero.add(classe);
        prezzo.setText("Prezzo passeggero: " + passengers.get(0).getTotalPrice() + "€");
        infoPasseggero.add(prezzo);
       
            for (Meal m : passengers.get(0).getMeals()) {
                riassunto.append(m.toString()+"\n");
            }
       

        riassunto.append("\n|___________________________|\n");

       
            for (Insurance i : passengers.get(0).getInsurances()) {
                riassunto.append(i.toString() +"\n");
            }

        riassunto.append("\n|___________________________|\n");

       
            for (HoldLuggage h : passengers.get(0).getHoldLuggages()) {
                riassunto.append(h.toString()+"\n");
            }

     

        JScrollPane pane = new JScrollPane(riassunto);
        infoPasseggero.add(pane);
        info.add(infoPasseggero, BorderLayout.CENTER);

        passeggeri.addActionListener(PassengerListener());

    }

    private void makeComponentsTrasparent() {
        home.trasparentButton(conferma);
        home.setallFont(conferma);

    }

    private ActionListener PassengerListener() {
        ActionListener evento = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                infoPasseggero.removeAll();
                infoPasseggero.setOpaque(false);
                infoPasseggero.setLayout(new GridLayout(3, 2));
                riassunto.setText("");
                numPosto.setText("Posto a sedere: " + passengers.get(passeggeri.getSelectedIndex()).getNseat());
                infoPasseggero.add(numPosto);
                int cl = home.getFlighttmp().getSeats().get(passengers.get(passeggeri.getSelectedIndex()).getNseat() - 1).getClasse();
                classe.setText("Classe: " + cl);
                infoPasseggero.add(classe);
                prezzo.setText("Prezzo passeggero: " + passengers.get(passeggeri.getSelectedIndex()).getTotalPrice() + "€");
                infoPasseggero.add(prezzo);

               
                    for (Meal m : passengers.get(passeggeri.getSelectedIndex()).getMeals()) {
                        riassunto.append(m.toString() +"\n");
                        
                    }
                
                riassunto.append("\n|___________________________|\n");
                
                    for (Insurance i : passengers.get(passeggeri.getSelectedIndex()).getInsurances()) {
                        riassunto.append(i.toString() +"\n");
                    }
              

                riassunto.append("\n|___________________________|\n");
               
                    for (HoldLuggage h : passengers.get(passeggeri.getSelectedIndex()).getHoldLuggages()) {
                        riassunto.append(h.toString()+"\n");
                    }

                JScrollPane pane1 = new JScrollPane(riassunto);
                infoPasseggero.add(pane1);
                infoPasseggero.repaint();
            }
        };
        return evento;
    }

}
