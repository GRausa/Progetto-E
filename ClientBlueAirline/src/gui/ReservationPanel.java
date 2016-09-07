/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.FacadeControllerClient;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import objects.*;

/**
 *
 * @author andreacavagna
 */
public class ReservationPanel extends JPanel {

    FacadeControllerClient controller;
    HomeFrame home;

    JComboBox andata = new JComboBox();
    JComboBox ritorno = new JComboBox();
    JLabel titandata = new JLabel("ANDATA :");
    JLabel titritorno = new JLabel("RITORNO :");
    JButton prenota;
    JPanel reservationPanel = new JPanel(new GridLayout(4, 2, 10, 10));
    JLabel titdata = new JLabel("Data :");
    JTextField npasseggeri = new JTextField();
    JLabel titnpasseggeri = new JLabel("n. Passeggeri: ");
    JPanel passeggeri = new JPanel(new GridLayout(0, 3));
    JButton passeggeripiu = new JButton("+");
    JButton passeggerimeno = new JButton("-");
    JLabel logo = new JLabel();
    JPanel panelsud = new JPanel(new GridLayout(0, 2));
    JButton ricerca = new JButton("Ricerca");
    JDateChooser data = new JDateChooser();

    public ReservationPanel(FacadeControllerClient company, HomeFrame home) {
        try {
            this.home = home;
            this.controller = company;
            this.setLayout(new BorderLayout(5, 5));
            initComponents();
            this.setVisible(true);
            setOpaque(false);
            MakeComponentsTrasparent();
            home.setallFont(this);
        } catch (IOException ex) {
            Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() throws IOException {
        final String[] city = controller.getAllCitys();

        andata.setFont(new java.awt.Font("Helvetica", 0, 14));
        andata.setEditable(true);
        andata.setSelectedItem("");
        andata.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = andata.getEditor().getItem().toString();
                    andata.removeAllItems();
                    int st = 0;
                    for (String fin : city) {
                        if (fin.toLowerCase().startsWith(a.toLowerCase())) {
                            andata.addItem(fin);
                            st++;
                        }
                    }
                    andata.getEditor().setItem(new String(a));
                    andata.hidePopup();
                    if (st != 0) {
                        andata.showPopup();
                    }
                }
            }
        });
        andata.setToolTipText("");

        ritorno.setFont(new java.awt.Font("Helvetica", 0, 14));
        ritorno.setEditable(true);
        ritorno.setSelectedItem("");

        ritorno.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = ritorno.getEditor().getItem().toString();
                    ritorno.removeAllItems();
                    int st = 0;

                    for (String fin : city) {
                        if (fin.toLowerCase().startsWith(a.toLowerCase())) {
                            ritorno.addItem(fin);
                            st++;
                        }
                    }
                    ritorno.getEditor().setItem(new String(a));
                    ritorno.hidePopup();
                    if (st != 0) {
                        ritorno.showPopup();
                    }
                }
            }
        });
        ritorno.setToolTipText("");

        reservationPanel.add(titandata);
        reservationPanel.add(andata);

        andata.setFont(new Font("Helvetica", Font.BOLD, 15));
        andata.setBackground(new Color(0, 0, 0, 0));
        andata.setOpaque(false);

        reservationPanel.add(titritorno);
        reservationPanel.add(ritorno);

        ritorno.setFont(new Font("Helvetica", Font.BOLD, 15));

        reservationPanel.add(titdata);
        titdata.setFont(new Font("Helvetica", Font.BOLD, 25));
        data.setDateFormatString("yyyy-MM-dd");
        //data.setMinSelectableDate(new Date());
        data.setPreferredSize(new java.awt.Dimension(170, 30));
        data.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '-')) {
                    e.consume();
                    home.notifiche.setText("Inserire data nel formato yyyy-mm-dd");
                }
            }
        });
        data.setDate(Date.from(Instant.now()));
        data.setSize(new java.awt.Dimension(170, 30));
        data.setFont(new Font("Helvetica", Font.BOLD, 15));
        titandata.setFont(new Font("Helvetica", Font.BOLD, 25));
        titritorno.setFont(new Font("Helvetica", Font.BOLD, 25));

        reservationPanel.add(data);
        reservationPanel.setOpaque(false);
        npasseggeri.setText("0");
        npasseggeri.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi.");
                }
            }
        });
        npasseggeri.setFont(new Font("Helvetica", Font.BOLD, 15));
        home.noMinorZero(npasseggeri, passeggerimeno, passeggeripiu);

        passeggeri.add(passeggerimeno);

        passeggerimeno.setFont(new Font("Helvetica", Font.BOLD, 15));

        passeggerimeno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasseggeri.getText());
                a--;
                if (a < 0) {
                    passeggerimeno.setEnabled(false);
                    a = 0;
                } else {
                    npasseggeri.setText("" + a);
                    home.notifiche.setText("Rimosso un passeggero");
                }
            }

        });

        passeggeri.add(npasseggeri);
        passeggeri.add(passeggeripiu);

        passeggeripiu.setFont(new Font("Helvetica", Font.BOLD, 15));

        passeggeripiu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasseggeri.getText());
                a++;
                if (a >= 0) {
                    npasseggeri.setText("" + a);
                    home.notifiche.setText("Aggiunto un passeggero");
                    passeggerimeno.setEnabled(true);
                }

            }

        });
        passeggeri.setOpaque(false);

        reservationPanel.add(titnpasseggeri);
        titnpasseggeri.setFont(new Font("Helvetica", Font.BOLD, 25));
        reservationPanel.add(passeggeri);

        add(reservationPanel, BorderLayout.CENTER);

        ImageIcon immagine = new ImageIcon("immagini/Logo.png");
        logo.setIcon(this.scalaImmagine(immagine, 400, 350));
        logo.setEnabled(true);

        panelsud.add(logo);
        panelsud.add(ricerca);

        ricerca.addActionListener(nuovoCustomer());

        ricerca.setFont(new Font("Helvetica", Font.BOLD, 35));
        ricerca.addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {

                home.notifiche.setText("Clicca per ricercare se la tratta è disponibile.");
            }

        });
        panelsud.setOpaque(false);
        add(panelsud, BorderLayout.SOUTH);
    }

    public ImageIcon scalaImmagine(ImageIcon immagine, int lunghezza, int altezza) {
        return new ImageIcon(immagine.getImage().getScaledInstance(lunghezza, altezza, java.awt.Image.SCALE_SMOOTH));
    }

    private ActionListener nuovoCustomer() {
        ActionListener evento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((!npasseggeri.getText().isEmpty()) && !(andata.getSelectedIndex() == -1) && !(ritorno.getSelectedIndex() == -1)) {
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(data.getDate());
                    if (Integer.parseInt(npasseggeri.getText()) > 0) {

                        
                            home.notifiche.setText("Ricerco informazioni sulla rotta.. ");
                            Calendar c = new GregorianCalendar();
                            c.setTime(data.getDate());
                            Flight[] a = controller.searchFlights(new Flight(new Route(andata.getSelectedItem().toString(), ritorno.getSelectedItem().toString()), c));
                            //System.out.println(a);
                            if (a.length == 0) {
                                JOptionPane.showConfirmDialog(home, "Siamo spiacenti. In questa data non risulta" + " nessun volo per la tratta desiderata.", "Errore", JOptionPane.OK_CANCEL_OPTION);
                            } else {
                                home.setNpasseggeri(Integer.parseInt(npasseggeri.getText()));
                                ArrayList<Flight> ar = new ArrayList();
                                for (int i = 0; i < a.length; i++) {
                                    ar.add(a[i]);
                                }
                                home.refreshGUI(new FlightsPanel(controller, home, ar));
                        home.notifiche.setText("Ricerco informazioni sulla rotta.. ");
                        /*Calendar c = new GregorianCalendar();
                        c.setTime(data.getDate());
                        Flight[] a = controller.searchFlights(new Flight(new Route(andata.getSelectedItem().toString(), ritorno.getSelectedItem().toString()), c));
                        //System.out.println(a);
                        if (a.length == 0) {
                            JOptionPane.showConfirmDialog(home, "Non risulta nessuna rotta nei voli" + "della compagnia aerea", "Errore", JOptionPane.OK_CANCEL_OPTION);
                        } else {
                            home.setNpasseggeri(Integer.parseInt(npasseggeri.getText()));
                            ArrayList<Flight> ar = new ArrayList();
                            for (int i = 0; i < a.length; i++) {
                                ar.add(a[i]);
                            }
                            home.refreshGUI(new FlightsPanel(controller, home, ar));
                        }*/}

                    } else {
                        JOptionPane.showConfirmDialog(home, "Selezionare almeno un passeggero" + " per poter proseguire", "Errore", JOptionPane.OK_CANCEL_OPTION);
                        home.notifiche.setText("Attenzione: aggiungere almeno un passeggero.");
                    }
                } else {
                    int dialogResult = JOptionPane.showConfirmDialog(home, "Riempire tutti i campi\n" + " per poter proseguire.\n", "Errore", JOptionPane.OK_CANCEL_OPTION);
                }
            }

        };
        return evento;
    }

    public void MakeComponentsTrasparent() {
        home.trasparentJDateChooser(data);
        home.trasparentButton(ricerca);
        home.trasparentButton(passeggeripiu);
        home.trasparentButton(passeggerimeno);
        home.trasparentTextField(npasseggeri);
    }

}
