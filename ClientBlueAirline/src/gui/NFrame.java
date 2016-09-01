/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Ticket;

/**
 *
 * @author andreacavagna
 */
public class NFrame extends JFrame {

    private final int WEIGHT = 200;
    private final int HEIGHT = 100;
    private int n;
    private char k;
    private Controller controller;
    private boolean opened;
    private HomeFrame home;
    ArrayList<JComboBox> combo;
    JLabel tit;
    String notifica;

    public NFrame(HomeFrame home, Controller controller, int n, String notifica, char k) throws IOException {
        this.controller = controller;
        this.opened = true;
        this.n = n;
        this.k = k;
        this.home = home;
        this.combo = new ArrayList();
        this.tit = new JLabel(notifica);
        this.notifica = notifica;

        setSize(WEIGHT, HEIGHT + (30 * this.n));
        setMinimumSize(new Dimension(WEIGHT, HEIGHT + (40 * this.n)));
        this.setResizable(false);
        initComponents();
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();

        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);

    }

    public void setMessage(String message) {
        tit.setText(message);
    }

    public ArrayList<JComboBox> getCombo() {
        return this.combo;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean b) {
        this.opened = b;
    }

    private void initComponents() throws IOException {
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.gridx = 0;
        c.gridy = 1;
        add(tit, c);
        tit.setFont(new Font("Helvetica", Font.BOLD, 15));

        for (int i = 0; i < this.n; i++) {

            c.weightx = 3;

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20;
            c.gridx = 0;
            c.gridy = i + 2;
            JComboBox combo = new JComboBox();
            this.combo.add(combo);
            combo.setFont(new Font("Helvetica", Font.BOLD, 15));

            add(combo, c);

        }

        switch (k) {

            case 'M':

                for (JComboBox c1 : combo) {
                    for (Meal m : home.getAllmeals()) {
                        c1.addItem(m);
                    }
                }
                break;
            case 'I':

                for (JComboBox c1 : combo) {
                    for (Insurance i : home.getAllinsurances()) {
                        c1.addItem(i);
                    }
                }
                break;
            case 'H':

                for (JComboBox c1 : combo) {
                    for (HoldLuggage h : home.getAllholdluggages()) {
                        c1.addItem(h);
                    }
                }
                break;
            default:
                break;

        };
        //le parti commentate sono da togliere quando saranno riempiti i combobox, altrimenti dà errore perchè non trova la stringa da splittare
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                home.getMeals().clear();
                home.getInsurances().clear();
                home.getLuggages().clear();
            }

            @Override
            public void windowClosing(WindowEvent e) {

                switch (k) {

                    case 'M':

                        for (int i = 0; i < combo.size(); i++) {
                            String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                            System.out.println(tmp[0]);
                            home.addMeal(tmp[0]);
                        }

                        break;
                    case 'I':

                        for (int i = 0; i < combo.size(); i++) {
                            String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                            System.out.println(tmp[0]);
                            home.addInsurance(tmp[0]);
                        }

                        break;

                    case 'H':

                        for (int i = 0; i < combo.size(); i++) {
                            String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                            home.addHoldLuggage(tmp[0]);
                        }

                    default:
                        break;
                }
                dispose();
            }
        });

    }

    void rezise(JTextField numero) {
        int n = Integer.parseInt(numero.getText());
        setSize(WEIGHT, HEIGHT + (30 * n));
        setMinimumSize(new Dimension(WEIGHT, HEIGHT + (30 * n)));
        for (JComboBox combo1 : combo) {
            this.remove(combo1);
        }
        for (int i = 0; i < n; i++) {
            GridBagConstraints c = new GridBagConstraints();

            c.weightx = 3;

            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 20;
            c.gridx = 0;
            c.gridy = i + 2;
            JComboBox combo = new JComboBox();
            this.combo.add(combo);
            combo.setFont(new Font("Helvetica", Font.BOLD, 15));

            add(combo, c);

        }

    }

}
