/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alexandra
 */
public class Gridpasseggeri {
    
     final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
 
    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
}
JButton button;
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    if (shouldFill) {
    //natural height, maximum width
    c.fill = GridBagConstraints.HORIZONTAL;
    }
 JLabel nome=new JLabel("Nome");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 0;
    pane.add(nome, c);
    
     JTextField nome0 =new JTextField("Inserisci Nome");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 1;
    c.gridy = 0;
    pane.add(nome0, c);
 
    JLabel cognome = new JLabel("Cognome");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 0;
    pane.add(cognome, c);
    
     JTextField cognome0 =new JTextField ("Inserisci Cognome");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 3;
    c.gridy = 0;
    pane.add(cognome0, c);
 
    
    JLabel id = new JLabel("ID");
   // button = new JButton("button3");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 4;
    c.gridy = 0;
    pane.add(id, c);
    
     JTextField id0 =new JTextField("Inserisci id");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 5;
    c.gridy = 0;
    pane.add(id0, c);
 
 
    JLabel email=new JLabel("Email");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 1;
    pane.add(email, c);
    
     JTextField email0 =new JTextField("Inserisci Email");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 1;
    pane.add(email0, c);
 
    JLabel tell = new JLabel("telefono");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 1;
    pane.add(tell, c);
    
     JTextField tell0 =new JTextField ("Inserisci telefono");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 3;
    c.gridy = 1;
    pane.add(tell0, c);
 
    
    JLabel id2 = new JLabel("");
   // button = new JButton("button3");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 4;
    c.gridy = 1;
    pane.add(id2, c);
    
   
     JLabel classe=new JLabel("Classe");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 2;
    pane.add(classe, c);
    
     JComboBox cbclasse =new JComboBox();
   // button = new JButton("Button 1");
      cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seconda Classe ", "Prima Classe" }));
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 2;
    pane.add(cbclasse, c);
 
    JLabel id3 = new JLabel("");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 2;
    pane.add(id3, c);
    
    
      JLabel bagaglio=new JLabel("Bagaglio");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 4;
    pane.add(bagaglio, c);
    
     JComboBox cbbagaglio =new JComboBox();
   // button = new JButton("Button 1");
      cbbagaglio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "nessuna aggiunta ", "8 kg","15 kg" }));
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 4;
    pane.add(cbbagaglio, c);
 
    JLabel id4 = new JLabel("");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 4;
    pane.add(id4, c);
    
    
        JLabel assicurazione =new JLabel("Assicurazione");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 5;
    pane.add(assicurazione, c);
    
     JComboBox cbassicurazione =new JComboBox();
   // button = new JButton("Button 1");
      cbassicurazione.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "se l'aereo s' schiant vogl' murì miliardario ", "nun vogl' pagà n'eur e chiù" }));
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 5;
    pane.add(cbassicurazione, c);
 
    JLabel id5 = new JLabel("");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 4;
    pane.add(id5, c);
 
    
     JLabel posto= new JLabel("Posto");
     // JButton button1 = new JButton("combox");
     if(shouldWeightX){
     c.weightx=3;
     }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 40;      //make this component tall
    c.gridx = 0;
    c.gridy = 3;
    pane.add(posto, c);
    
    
    JTextField posto0 = new JTextField();
      // JComboBox cbposto = new JComboBox();
     // JButton button1 = new JButton("combox");
    
    if(shouldWeightX){
    c.weightx=3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 40;      //make this component tall
  
    c.gridx = 1;
    c.gridy = 3;
    pane.add(posto0, c);
    
    JLabel posto1 = new JLabel("");
       c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 3;
    pane.add(posto1, c);
    
    
    JLabel pasto=new JLabel("Pasto");
   // button = new JButton("Button 1");
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 6;
    pane.add(pasto, c);
    
     JComboBox cbpasto =new JComboBox();
   // button = new JButton("Button 1");
      cbpasto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "tutto quello gratis  ", "caviale","nu cornett" }));
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 6;
    pane.add(cbpasto, c);
 
    JLabel id6 = new JLabel("");
   // button = new JButton("Button 2");
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 6;
    pane.add(id6, c);
    
   
 
    button = new JButton("Conferma");
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;       //reset to default
    c.weighty = 1.0;   //request any extra vertical space
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.insets = new Insets(10,0,0,0);  //top padding
    c.gridx = 1;       //aligned with button 2
    c.gridwidth = 2;   //2 columns wide
    c.gridy = 8;       //third row
    pane.add(button, c);
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
