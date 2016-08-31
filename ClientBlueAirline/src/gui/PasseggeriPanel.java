/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;





import TUI.MethodsControl;
import controller.ClientBlueAirline;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Seat;
import objects.Ticket;

/**
 *
 * @author alexandra
 */
public class PasseggeriPanel extends JPanel{
    
 
    private HomeFrame home;
    private ClientBlueAirline controller;
    protected ArrayList<JComboBox> meal;
    protected ArrayList<JComboBox> insurance;
    protected ArrayList<JComboBox> holdluggage;
    
    private ArrayList<Meal> meals;
    private ArrayList<Insurance> insurances;
    private ArrayList<HoldLuggage> luggages;
    
    JButton conferma = new JButton("Conferma");  
    JPanel pasti= new JPanel(new GridLayout(0,4));
    JTextField npasti = new JTextField(1);
    JButton pastimeno = new JButton("-");
    JButton pastipiu = new JButton("+");
    JButton pastibutton = new JButton();
    JLabel pasto=new JLabel("Pasto");

    JLabel posto1 = new JLabel("");
    JComboBox posto0 = new JComboBox();
    JLabel posto= new JLabel("Posto");

    JPanel assicurazioni = new JPanel(new GridLayout(0,4));
    JTextField nassicurazioni = new JTextField(1);
    JButton assicurazionimeno = new JButton("-");
    JButton assicurazionipiu = new JButton("+");
    JButton assicurazionibutton = new JButton();
    JLabel assicurazione =new JLabel("Assicurazione");

    JPanel bagagli = new JPanel(new GridLayout(0,4));
    JTextField nbagagli = new JTextField(1);

    JButton bagaglimeno = new JButton("-");
    JButton bagaglipiu = new JButton("+");
    JLabel bagaglio=new JLabel("Bagaglio");
    JButton bagagliobutton = new JButton();

    JComboBox cbclasse =new JComboBox();
    JLabel classe=new JLabel("Classe");


    JTextField cognome0 =new JTextField ("Inserisci Cognome");
    JLabel cognome = new JLabel("Cognome");

    JTextField nome0 =new JTextField("Inserisci Nome");

    JLabel nome=new JLabel("Nome");

    JTextField id0 =new JTextField("Inserisci id");
    JLabel id = new JLabel("ID");
        
    public PasseggeriPanel(HomeFrame home, ClientBlueAirline controller) {
        this.home = home;
        this.controller = controller;
        this.setOpaque(false);
        changeFont(this,new Font("Helvetica", Font.BOLD,15));
        addComponentsToPane(this);
        home.setallFont(this);
        this.makeComponentsTrasparent();
        cbclasse.setForeground(Color.black);
        cbclasse.setPopupVisible(false);
        posto0.setForeground(Color.black);

       this.add( nome0, new GridBagConstraints( 0, 0, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 100, 0, 0 ), 200, 40 ) );
       this.add( cognome0, new GridBagConstraints( 1, 0, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 150, 0, 0 ), 150, 40 ) );
       this.add( nome, new GridBagConstraints( 0, 0, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
       this.add( cognome, new GridBagConstraints( 1, 0, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 200, 40 ) );
       
       this.add( posto0, new GridBagConstraints( 1, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 150, 40 ) );
       this.add( posto, new GridBagConstraints( 0, 3, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 100, 40 ) );
       
       this.add( classe, new GridBagConstraints( 0, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 100, 40 ) );
       this.add( cbclasse, new GridBagConstraints( 1, 2, 1, 1, 1.0,1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets( 0, 0, 0, 0 ), 50, 40 ) );
  
    }
    
    
    
    public  void addComponentsToPane(Container pane) {
  
    pane.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
 
    
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx=0.1;
     c.ipady = 15;
     
    c.gridx = 0;
    c.gridy = 0;
    pane.add(nome, c);
 
    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 0;
    pane.add(nome0, c);
 

    c.weightx=0.1;
    c.ipady = 15;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 0;
    pane.add(cognome, c);
    

    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 3;
    c.gridy = 0;
    
    pane.add(cognome0, c);
 

    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 0;
    c.gridy = 1;
    
    pane.add(id, c);
    
    c.weightx=0.1;
    c.ipady = 15;
    c.gridx = 1;
    c.gridy = 1;
    
    pane.add(id0, c);
    
 
    
    c.weightx = 3;
    c.ipady = 50;
    c.gridx = 0;
    c.gridy = 2;
    pane.add(classe, c);
    
    
    cbclasse.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Prima Classe ", "Seconda Classe" }));
      

    c.weightx = 3;

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 40;
    c.gridx = 1;
    c.gridy = 2;
    cbclasse.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            posto0.removeAllItems();
            ArrayList<Integer> numeri = new ArrayList();
            numeri.add(0);
            for(Seat s:getSeatFlight())
        {
            for(Ticket t:home.getPassengers())
                {
                numeri.add(t.getNseat());
                }
            if(s.getPassenger()==null && (!numeri.contains((int)s.getNumber())))  
            {
                if(s.getClasse()==1 && cbclasse.getSelectedIndex()==0)
                {
                posto0.addItem(s.getNumber());
                }

                 if(s.getClasse()==2 && cbclasse.getSelectedIndex()==1)
                {
                posto0.addItem(s.getNumber());
               }
            }
        }
        }
    });
    pane.add(cbclasse, c);
    

    c.weightx = 3;
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
        luggages = new ArrayList();
        home.setLuggages(luggages);
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
        

    c.weightx = 1;

    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 2;
    c.gridx = 1;
    c.gridy = 4;
    pane.add(bagagli, c);
 

    c.weightx = 3;
    
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
        ImageIcon immagine2=new ImageIcon("immagini/Air_insurance.png");
        assicurazionibutton.setIcon(immagine2);
        insurances = new ArrayList();
        home.setInsurances(insurances);
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
        

    c.weightx = 1;

    c.ipady = 2;
    c.gridx = 1;
    c.gridy = 5;
    pane.add(assicurazioni, c);
 

    c.weightx=3;
    c.ipady = 15;     
    c.gridx = 0;
    c.gridy = 3;
    pane.add(posto, c);


    c.weightx=3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 15;   
    c.ipadx = 30;
    c.gridx = 1;
    c.gridy = 3;
    posto0.setVisible(true);
    posto0.setEditable(false);
    posto0.setForeground(Color.black);
    posto0.addItem("selezionare Classe");
        
  
    Flight f = home.getFlighttmp();
    
    pane.add(posto0, c);
    
    c.ipady = 40;
    c.weightx = 3;
    c.gridx = 2;
    c.gridy = 3;
    pane.add(posto1, c);
    
    

    c.weightx = 3;
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
        ImageIcon immagine1=new ImageIcon("immagini/Meal.png");
        pastibutton.setIcon(immagine1);
        meals = new ArrayList();
        home.setMeals(meals);
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
        
    
    

    c.weightx = 1;
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
   
    }
 
    
    private ActionListener nuovoPasseggero() {
        ActionListener evento = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meals = home.getMeals();
                insurances = home.getInsurances();
                luggages = home.getLuggages();
               if(cognome0.getText().isEmpty() ||  nome0.getText().isEmpty() || id0.getText().isEmpty() || cognome0.getText().equals("Inserisci Cognome") || nome0.getText().equals("Inserisci Nome") || id0.getText().equals("Inserisci id"))
                  JOptionPane.showConfirmDialog(home, "Riempire tutti i campi"+" per poter proseguire.", "Errore", JOptionPane.OK_CANCEL_OPTION);
                       else
                    {
                   try {
                       home.addPassenger(id0.getText(), nome0.getText(), cognome0.getText(),(int) posto0.getSelectedItem(), cbclasse.getSelectedIndex()+1,home.getCodeflight(),home.getPriceflight(), meals, insurances, luggages); 
                       
                       if(home.PassengerMeno())
                           home.refreshGUI(new PasseggeriPanel(home,controller));
                       else
                       {
                            home.notifiche.setText("Riassunto volo:");
                            home.refreshGUI(new RiassuntoPanel(home,controller));
                       }
                   } catch (IOException ex) {
                       Logger.getLogger(PasseggeriPanel.class.getName()).log(Level.SEVERE, null, ex);
                   }
                    }
            }

           
        };
        return evento;
    }
    
    

  
  private ActionListener NListener(final JTextField numero,final String messaggio,final String notifica) {
   ActionListener evento = new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent e) {
           NFrame frame = new NFrame(home,controller,Integer.parseInt(numero.getText()),messaggio);
           frame.setVisible(true);
           home.notifiche.setText(notifica);
           
           frame.setAlwaysOnTop(true);
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
    }
}
   
    
    public void makeComponentsTrasparent()
    {
        //tutti i Button e i TextField diventano trasparenti
    home.trasparentButton(conferma);
    home.trasparentButton(this.bagaglimeno);
    home.trasparentButton(this.bagaglipiu);
    home.trasparentButton(this.pastimeno);
    home.trasparentButton(this.pastipiu);
    home.trasparentButton(this.assicurazionimeno);
    home.trasparentButton(this.assicurazionipiu);
    home.trasparentButton(bagagliobutton,"immagini/HoldLuggage_b.png");
    home.trasparentButton(assicurazionibutton,"immagini/Air_insurance_b.png");
    home.trasparentButton(pastibutton,"immagini/Meal_b.png");
    
    home.trasparentTextField(this.cognome0);
    home.trasparentTextField(this.nbagagli);
    home.trasparentTextField(this.nome0);
    
    home.trasparentTextField(this.npasti);
    home.trasparentTextField(this.nassicurazioni);
    home.trasparentTextField(this.id0);
    }
    
    
    private ArrayList<Seat> getSeatFlight()
    {
       return    home.getFlighttmp().getSeats();
    }   
    
    
    private int getnSecondaClasse()
    {
        int ret=0;
                for (Seat s:getSeatFlight())
                {
                    if(s.getClasse()==2)
                        ret= s.getNumber();
                }
                return ret;
    }
     
    private boolean isSeatFree(int posto)
    {
    if (getSeatFlight().get(posto-1).getPassenger() == null) 
        return true;
    else 
        return false;            
    }
            

}