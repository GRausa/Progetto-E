/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.FacadeControllerClient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objects.Reservation;

/**
 *
 * @author alexandra
 */
public class CustomerPanel extends JPanel {

    private HomeFrame home;
    private FacadeControllerClient controller;

    JButton conferma = new JButton("Conferma");
    JTextField tell0 = new JTextField("Inserisci telefono");
    JLabel tell = new JLabel("Telefono");
    // JLabel scritta= new JLabel("inserire i recapiti a cui essere contattati:");
    JTextField email0 = new JTextField("Inserisci Email");
    JLabel email = new JLabel("Email");

    public CustomerPanel(HomeFrame home, FacadeControllerClient controller) {
        this.home = home;
        this.controller = controller;
        this.setOpaque(false);
        addComponentsToPane(this);
        home.setallFont(this);
        this.makeComponentsTrasparent();

        this.add(email0, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 100, 0, 0), 200, 40));
        this.add(tell, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 100, 40));
        this.add(email, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 150, 40));
        this.add(tell0, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 150, 0, 0), 150, 40));
//       this.add( scritta, new GridBagConstraints( 0, 1, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 150, 40 ) );

    }

    public void addComponentsToPane(Container pane) {

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.1;
        c.ipady = 15;
        c.gridx = 0;
        c.gridy = 3;
        pane.add(email, c);

        c.weightx = 0.1;
        c.ipady = 15;
        c.gridx = 1;
        c.gridy = 3;
        pane.add(email0, c);

        email0.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                home.notifiche.setText("La mail servirà per recapitare i biglietti alla fine della prenotazione.");
            }

        });

        c.ipady = 15;
        c.weightx = 3;
        c.gridx = 2;
        c.gridy = 3;
        pane.add(tell, c);

        c.weightx = 3;

        c.ipady = 15;
        c.gridx = 3;
        c.gridy = 3;
        pane.add(tell0, c);
        tell0.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                home.notifiche.setText("Recapito telefonico per eventuali problematiche relative al volo.");
            }

        });

        tell0.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9') || caracter == '+')) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi oppure il carattere +");
                }
            }
        });

        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10, 0, 0, 0);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 8;
        pane.add(conferma, c);
        conferma.addActionListener(nuovoPasseggero());
        conferma.setFont(new Font("Helvetica", Font.BOLD, 20));

    }

    private ActionListener nuovoPasseggero() {
        ActionListener evento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (email0.getText().isEmpty() || tell0.getText().isEmpty() || email0.getText().equals("Inserisci Email") || tell0.getText().equals("Inserisci telefono")) {
                    JOptionPane.showConfirmDialog(home, "Riempire tutti i campi" + " per poter proseguire", "Errore", JOptionPane.OK_CANCEL_OPTION);
                } else if (email0.getText().contains("%@%")) {
                    JOptionPane.showConfirmDialog(home, "Email non valida,inserirne una valida" + " per poter proseguire", "Errore", JOptionPane.OK_CANCEL_OPTION);
                } else {
                    home.setReservation(new Reservation(home.getCodeflight(), email0.getText(), tell0.getText()));
                    home.refreshGUI(new PasseggeriPanel(home, controller));
                }
            }

        };
        return evento;
    }

    public void makeComponentsTrasparent() {
        //tutti i Button e i TextField diventano trasparenti
        home.trasparentButton(conferma);

        home.trasparentTextField(this.email0);
        home.trasparentTextField(this.tell0);
    }

}
