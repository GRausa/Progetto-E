/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import clientblueairline.ClientBlueAirline;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author alexandra
 */
public class RiassuntoVolo extends JPanel  {
    
   
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
 
    private HomeFrame home;
    private ClientBlueAirline controller;

    JLabel riepilogo=new JLabel("Riepilogo del volo");
        JLabel codice = new JLabel("Codice Volo: GTAM-23");
        JLabel andata =new JLabel("Andata");
         JLabel ritorno =new JLabel("Ritorno");
          JLabel data =new JLabel("Data");
          JLabel data0 =new JLabel("Datainserita");
       
          JLabel andata0 =new JLabel("Andatascritta");
         JLabel ritorno0 =new JLabel("Ritornoscritta");
        
        JLabel pasto=new JLabel("Pasto");
        JLabel posto= new JLabel("Posto");
        JLabel assicurazione =new JLabel("Assicurazione");
        JLabel bagagli = new JLabel("Bagagli");
       
        JLabel nome = new JLabel("Nome");
        JLabel cognome = new JLabel("Cognome");
        JLabel nome0 = new JLabel("Nomescritto");
        JLabel cognome0 = new JLabel("Cognomescritto");
       
        JButton conferma = new JButton("Conferma");
        JButton stampa = new JButton("Stampa");
        
       
        
    public RiassuntoVolo(HomeFrame home, ClientBlueAirline controller) {
        this.home = home;
        this.controller = controller;
       // this.setOpaque(false);
     //   changeFont(this,new Font("Helvetica", Font.BOLD,15));
        addComponentsToPane(this);
      //  home.setallFont(this);
        this.makeComponentsTrasparent();
     
  this.add( riepilogo, new GridBagConstraints( 0, 0, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
        
  this.add( codice, new GridBagConstraints( 0, 1, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 150, 40 ) );
  this.add( andata, new GridBagConstraints( 0, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
  this.add( ritorno, new GridBagConstraints( 1, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 150, 40 ) );
  this.add( data, new GridBagConstraints( 2, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
  this.add( nome, new GridBagConstraints( 0, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
  this.add( cognome, new GridBagConstraints( 1, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 150, 40 ) );
  this.add( posto, new GridBagConstraints( 0, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 100, 40 ) );
   this.add( andata0, new GridBagConstraints( 0, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 200, 40 ) );
  this.add( ritorno0, new GridBagConstraints( 1, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 150, 40 ) );
  this.add( data0, new GridBagConstraints( 2, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 200, 40 ) );
  this.add( nome0, new GridBagConstraints( 0, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 200, 40 ) );
  this.add( cognome0, new GridBagConstraints( 1, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 150, 40 ) );
        
  this.add( bagagli, new GridBagConstraints(0, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 100, 40 ) );
  this.add( assicurazione, new GridBagConstraints( 1, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 50, 40 ) );
  this.add( pasto, new GridBagConstraints( 2, 4, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
        
    }
    
    
    
    public  void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
}

    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
   
    if (shouldFill) {
    //natural height, maximum width
    c.fill = GridBagConstraints.HORIZONTAL;
    
    }
 
    
  
     
  
   // if (shouldWeightX) {
   // c.weightx = 3;
   // }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx=0.1;
     c.ipady = 15;
     
    c.gridx = 0;
    c.gridy = 0;
  //  conferma.addActionListener(SendReservation());
    pane.add(conferma, c);
    
  /*  

   // if (shouldWeightX) {
  //  c.weightx = 1;
    
   // }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 0;
    pane.add(nome0, c);
 

     
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx=0.1;
    c.ipady = 15;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 0;
    pane.add(cognome, c);
    

   // if (shouldWeightX) {
   // c.weightx = 3;
    
   // }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 3;
    c.gridy = 0;
    
    pane.add(cognome0, c);
 
  
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 2;
    pane.add(classe, c);
    
    
      cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "poveracci ", "Prima Classe" }));
   
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.gridx = 1;
    c.gridy = 2;
    pane.add(cbclasse, c);
    
   

    
    
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 4;
    pane.add(bagaglio, c);
     
    
     nbagagli.setText("0");
        nbagagli.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi.");
                }
            }
        });
        nbagagli.setFont(new Font("Helvetica",Font.BOLD,15));
        bagagli.add(bagaglimeno);
        bagaglimeno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(nbagagli.getText());
                
                a--;
                nbagagli.setText(""+a);
                home.notifiche.setText("Rimosso un bagaglio da stiva.");
            }

        });
        nbagagli.setColumns(1);
        bagagli.add(nbagagli);
        bagagli.add(bagaglipiu);
        ImageIcon immagine=new ImageIcon("immagini/HoldLuggage.png");
        bagagliobutton.setIcon(immagine);
        bagagliobutton.addActionListener(NListener(nbagagli,"Scegli il tipo di bagaglio.","Specifica quale tipo di bagaglio vuoi."));
        
      
        
        
        bagagli.add(bagagliobutton);
        bagaglipiu.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(nbagagli.getText());
                a++;
                nbagagli.setText(""+a);
                home.notifiche.setText("Aggiunto un bagaglio da stiva");
            }

        });
        bagagli.setOpaque(false);
        
    
    
    if (shouldWeightX) {
    c.weightx = 1;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 2;
    c.gridx = 1;
    c.gridy = 4;
    pane.add(bagagli, c);
 
    
   
  
    
    
    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 50;
    c.gridx = 0;
    c.gridy = 5;
    pane.add(assicurazione, c);
    
    
   //aggiunta tasto per assicurazioni
     nassicurazioni.setText("0");
        nassicurazioni.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi.");
                }
            }
        });
        nassicurazioni.setFont(new Font("Helvetica",Font.BOLD,15));
        assicurazioni.add(assicurazionimeno);
        assicurazionimeno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(nassicurazioni.getText());
                a--;
                nassicurazioni.setText(""+a);
                home.notifiche.setText("Rimossa un'assicurazione");
            }

        });
        nassicurazioni.setColumns(1);
        assicurazioni.add(nassicurazioni);
        assicurazioni.add(assicurazionipiu);
        ImageIcon immagine2=new ImageIcon("gui/Air_insurance.png");
        assicurazionibutton.setIcon(immagine2);
        assicurazionibutton.addActionListener(NListener(nassicurazioni,"Quale assicurazione vuoi?","Inserisci le assicurazioni per il tuo volo."));
        
        
        
        
        assicurazioni.add(assicurazionibutton);
        assicurazionipiu.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(nassicurazioni.getText());
                a++;
                nassicurazioni.setText(""+a);
                home.notifiche.setText("Aggiunta un'assicurazione.");
            }

        });
        assicurazioni.setOpaque(false);
        
    
    
    if (shouldWeightX) {
    c.weightx = 1;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 2;
    c.gridx = 1;
    c.gridy = 5;
    pane.add(assicurazioni, c);
 
    
   
   
    
     if(shouldWeightX){
     c.weightx=3;
     }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 15;     
    c.gridx = 0;
    c.gridy = 3;
    pane.add(posto, c);

    if(shouldWeightX){
    c.weightx=3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 15;   
    c.ipadx = 30;
    c.gridx = 1;
    c.gridy = 3;
    pane.add(posto0, c);
    posto0.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi.");
                }
            }
        });
    
       c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 40;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 3;
    pane.add(posto1, c);
    
    

    if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
     c.ipady = 50;
    c.gridx = 0;
    c.gridy = 6;
    pane.add(pasto, c);
     
    
  //aggiunta tasto per numero pasti
     npasti.setText("0");
        npasti.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9'))) {
                    e.consume();
                    home.notifiche.setText("Inserire solo numeri interi.");
                }
            }
        });
        npasti.setFont(new Font("Helvetica",Font.BOLD,15));
        pasti.add(pastimeno);
        pastimeno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasti.getText());
                a--;
                npasti.setText(""+a);
                home.notifiche.setText("Rimosso un pasto.");
            }

        });
        npasti.setColumns(1);
        pasti.add(npasti);
        pasti.add(pastipiu);
        ImageIcon immagine1=new ImageIcon("gui/Meal.png");
        pastibutton.setIcon(immagine1);
        pastibutton.addActionListener(NListener(npasti,"Cosa mangi","Inserisci i pasti per il volo"));
        
       
        
        pasti.add(pastibutton);
        pastipiu.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasti.getText());
                a++;
                npasti.setText(""+a);
                home.notifiche.setText("Aggiunto un pasto");
            }

        });
        pasti.setOpaque(false);
        
    
    
    if (shouldWeightX) {
    c.weightx = 1;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 2;
    c.gridx = 1;
    c.gridy = 6;
    pane.add(pasti, c);
 
 
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 0;       //reset to default
    c.weighty = 1.0;   //request any extra vertical space
    c.anchor = GridBagConstraints.PAGE_END; //bottom of space
    c.insets = new Insets(10,0,0,0);  //top padding
    c.gridx = 0;       //aligned with button 2
    c.gridwidth = 2;   //2 columns wide
    c.gridy = 8;       //third row
    pane.add(conferma, c);
    conferma.addActionListener(nuovoPasseggero());
    conferma.setFont(new Font("Helvetica",Font.BOLD,20));
    
    }
 
    
    private ActionListener nuovoPasseggero() {
        ActionListener evento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                home.refreshGUI(new PasseggeriPanel(home,controller));
                home.notifiche.setText("Aggiungi una nuova Prenotazione: ");
            }

           
        };
        return evento;
    }

  
    private ActionListener NListener(final JTextField numero,final String messaggio,final String notifica) {
   ActionListener evento = new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           NFrame frame = new NFrame(controller,Integer.parseInt(numero.getText()),messaggio);
           home.notifiche.setText(notifica);
           frame.setAlwaysOnTop(true);
           frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       }


   };
   return evento;
}
    

        
    public static void changeFont ( Component component, Font font )
                    
{
    component.setFont ( font );
    if ( component instanceof Container )
    {
        for ( Component child : ( ( Container ) component ).getComponents () )
        {
            changeFont ( child, font );
        }
    }*/
}
   
    
    public void makeComponentsTrasparent()
    {
        //tutti i Button e i TextField diventano trasparenti
    home.trasparentButton(conferma);
   /* home.trasparentButton(this.bagaglimeno);
    home.trasparentButton(this.bagaglipiu);
    home.trasparentButton(this.pastimeno);
    home.trasparentButton(this.pastipiu);
    home.trasparentButton(this.assicurazionimeno);
    home.trasparentButton(this.assicurazionipiu);
    home.trasparentButton(bagagliobutton,"gui/HoldLuggage_b.png");
    home.trasparentButton(assicurazionibutton,"gui/Air_insurance_b.png");
    home.trasparentButton(pastibutton,"gui/Meal_b.png");
    
    home.trasparentTextField(this.cognome0);
    home.trasparentTextField(this.nbagagli);
    home.trasparentTextField(this.nome0);
    home.trasparentTextField(this.posto0);
    home.trasparentTextField(this.npasti);
    home.trasparentTextField(this.nassicurazioni);
    */
    }
   /* 
      private ActionListener SendReservation() {
        ActionListener evento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    try {
                   // controller.makeReservation(home.getReservation(), home.getPassengers());
                      int dialogResult = JOptionPane.showConfirmDialog(home, "Prenotaziona effettuata\n", "Errore", JOptionPane.YES_OPTION);
                      if(dialogResult==1)
                      home.returnHome();
          //      } catch (SQLException ex) {
                    Logger.getLogger(Riassuntovolo2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

           
        };
        return evento;
    }*/
    
    }


 

