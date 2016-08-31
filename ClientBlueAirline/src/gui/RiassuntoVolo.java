/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import controller.ClientBlueAirline;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objects.Flight;
/**
 *
 * @author alexandra
 */
public class RiassuntoVolo extends JPanel{
     
     
      ClientBlueAirline controller;
      HomeFrame home;
      Flight f;
    
JPanel modificapanel = new JPanel(new GridLayout(4,2,10,10));
JLabel nome= new JLabel("BlueAirlines");
JLabel codice=new JLabel("Inserisci il Codice");
JLabel andata=new JLabel("andata");
JLabel ritorno=new JLabel("ritorno");
JLabel data=new JLabel("data");
JLabel orario=new JLabel("orario");
JLabel npasseggeri=new JLabel("N-Passeggeri");//in base al numero di passeggeri incrementa i nomi e cognomi
JLabel nomep1=new JLabel("nomepasseggero1");
JLabel cognomep1=new JLabel("cognomepasseggero1");
JLabel pasto=new JLabel("pasto");
JLabel assicurazione=new JLabel("assicurazione");
JButton stampa =new JButton("Stampa");
 JButton indietro =new JButton ("Indietro");
 

    public RiassuntoVolo(HomeFrame home,ClientBlueAirline company)
    {
        this.home = home;
        this.controller=company;
        
        this.setLayout(new BorderLayout(5,5));
        
        this.setVisible(true);
        setOpaque(false);
        // MakeComponentsTrasparent();
        home.setallFont(this);
        home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
    }
    

    private JPanel homePanel() 
    { 
        JPanel schedafinale = new JPanel();
     
        schedafinale.setOpaque(false);
       
        schedafinale.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        schedafinale.add(nome, new GridBagConstraints( 1, 0, 1, 1, 1.0,1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 0, 0,0, 0 ), 300, 40 ) );
    
      schedafinale.add( codice, new GridBagConstraints( 0, 1, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0,0, 0 ), 200, 40 ) );
      schedafinale.add( andata, new GridBagConstraints( 0, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( ritorno, new GridBagConstraints( 1, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( data, new GridBagConstraints( 2, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( orario, new GridBagConstraints( 3, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( npasseggeri, new GridBagConstraints( 0, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( nomep1, new GridBagConstraints( 1, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add(cognomep1 , new GridBagConstraints( 2, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add(pasto , new GridBagConstraints( 0, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add(assicurazione , new GridBagConstraints( 1, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
    
      
      schedafinale.add( indietro, new GridBagConstraints( 0, 5, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
      schedafinale.add( stampa, new GridBagConstraints( 1, 5, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );

     schedafinale.setOpaque(false);
    
        return schedafinale;
    }

}