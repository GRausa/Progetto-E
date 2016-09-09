/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.FacadeControllerClient;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import objects.Flight;
import objects.Reservation;
import objects.Seat;
import objects.Ticket;

/**
 *
 * @author andreacavagna
 */
public class FinalControlPanel extends JPanel {

    HomeFrame home;
    FacadeControllerClient controller;
    Reservation res;
    Ticket t;
    Flight f;
    JLabel titolo = new JLabel();
    JComboBox cbclasse = new JComboBox();
    JComboBox posto0 = new JComboBox();
    JButton ok = new JButton("OK");
    boolean c = false;

    public FinalControlPanel(HomeFrame home, FacadeControllerClient controller, Reservation res, Ticket t, Flight f) {
        this.home = home;
        this.controller = controller;
        this.res = res;
        this.t = t;
        this.f = f;

        setSize(500, 500);
        setMinimumSize(new Dimension(500, 500));

        initComponents();
        this.setLayout(new GridLayout(4, 1));
        this.setVisible(true);

        home.setEnabled(false);
    }

    private void initComponents() {
        titolo.setText("il Ticket di " + t.getSurname() + " " + t.getName() + " non è più disponibile,\n selezionarne un altro:");
        add(titolo);

        cbclasse.setForeground(Color.black);
        cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Prima Classe ", "Seconda Classe"}));
        cbclasse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                posto0.removeAllItems();
                ArrayList<Integer> numeri = new ArrayList();
                numeri.add(0);
                for (Seat s : f.getSeats()) {
                    for (Ticket t : home.getPassengers()) {
                        numeri.add(t.getNseat());
                    }
                    if (s.getTicket() == null && (!numeri.contains((int) s.getNumber()))) {
                        if (s.getClasse() == 1 && cbclasse.getSelectedIndex() == 0) {
                            posto0.addItem(s.getNumber());
                        }

                        if (s.getClasse() == 2 && cbclasse.getSelectedIndex() == 1) {
                            posto0.addItem(s.getNumber());
                        }
                    }
                }
            }
        });

        add(cbclasse);

        posto0.setForeground(Color.black);
        posto0.setVisible(true);
        posto0.setEditable(false);
        posto0.setForeground(Color.black);
        posto0.addItem("selezionare Classe");
        this.add(posto0);

        this.add(ok);
        
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int set = (int) posto0.getSelectedItem();
                if (f.getSeats().get(set - 1).getTicket() == null) {
                    t.setNSeat(set);
                    t = controller.editSeatTicket(t);
                    if (!(t.getNseat() == set)) {
                        JOptionPane.showMessageDialog(home, "Il posto è gia stato assegnato\n");
                    }
                }
            }

        });
    }

}
