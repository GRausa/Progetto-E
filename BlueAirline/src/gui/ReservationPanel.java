/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import clients.ControllerClient;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import objects.Flight;
import objects.Route;

/**
 *
 * @author andreacavagna
 */
public class ReservationPanel extends JPanel{
    
    ControllerClient controller;
    HomeFrame home;
    
    JComboBox andata = new JComboBox();
    JComboBox ritorno = new JComboBox();
    JLabel titandata = new JLabel("ANDATA :");
    JLabel titritorno = new JLabel("RITORNO :");
    JTextField data = new JTextField();
    JButton prenota;
    JPanel reservationPanel = new JPanel(new GridLayout(4,2,10,10));
    JLabel titdata = new JLabel("Data :");
    JTextField npasseggeri = new JTextField();
    JLabel titnpasseggeri = new JLabel("n. Passeggeri: ");
    JPanel passeggeri = new JPanel(new GridLayout(0,3));
    JButton passeggeripiu = new JButton("+");
    JButton passeggerimeno = new JButton("-");
    JLabel logo = new JLabel();
    JPanel panelsud = new JPanel(new GridLayout(0,2));
    JButton ricerca = new JButton("Ricerca");
    
    public ReservationPanel(ControllerClient company, HomeFrame home) 
    {
        this.home = home;
        this.controller=company;
        this.setLayout(new BorderLayout(5,5));
        initComponents();
        this.setVisible(true);
        setOpaque(false);
        MakeComponentsTrasparent();
        home.setallFont(this);
    }

    
    
    private void initComponents() {
        andata.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        andata.setEditable(true);
        andata.setSelectedItem("");
        andata.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                String a = andata.getEditor().getItem().toString();
                andata.removeAllItems();
                int st = 0;
                try {
                    for (String fin : controller.searchAllCitys()) {
                        if (fin.startsWith(a)) { andata.addItem(fin); st++;}
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                andata.getEditor().setItem(new String(a));
                andata.hidePopup();
                if (st != 0) { andata.showPopup(); }
            }
        } } );
        andata.setToolTipText("");
        
        
        ritorno.setFont(new java.awt.Font("Helvetica", 0, 14)); 
        ritorno.setEditable(true);
        ritorno.setSelectedItem("");
        
        ritorno.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
            String a = ritorno.getEditor().getItem().toString();
            ritorno.removeAllItems();
            int st = 0;

           
            try {
                for (String fin : controller.searchAllCitys()) {
                    if (fin.startsWith(a)) { ritorno.addItem(fin); st++; }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            ritorno.getEditor().setItem(new String(a));
            ritorno.hidePopup();
            if (st != 0) { ritorno.showPopup(); }
        }
         } } );
        ritorno.setToolTipText("");
    
        reservationPanel.add(titandata);
        reservationPanel.add(andata);
        
        andata.setFont(new Font("Helvetica",Font.BOLD,15));
        andata.setBackground(new Color(0, 0, 0, 0));
        andata.setOpaque(false); 
        
        reservationPanel.add(titritorno);
        reservationPanel.add(ritorno);
        
        ritorno.setFont(new Font("Helvetica",Font.BOLD,15));
     
        reservationPanel.add(titdata);
        titdata.setFont(new Font("Helvetica",Font.BOLD,25));
        
        data.setEditable(true);
        data.setPreferredSize(new java.awt.Dimension(170, 30));
        data.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter!='-')) {
                    e.consume();
                    home.notifiche.setText("Inserire data nel formato dd-mm-yyyy");
                }
            }
        });
        data.setSize(new java.awt.Dimension(170, 30));
        data.setText("Inserisci data");
        data.setFont(new Font("Helvetica",Font.BOLD,15));
        
        
        
        titandata.setFont(new Font("Helvetica",Font.BOLD,25));
        titritorno.setFont(new Font("Helvetica",Font.BOLD,25));
        
        
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
        npasseggeri.setFont(new Font("Helvetica",Font.BOLD,15));
        
        
        passeggeri.add(passeggerimeno);
        
        
        passeggerimeno.setFont(new Font("Helvetica",Font.BOLD,15));
        
        passeggerimeno.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasseggeri.getText());
                a--;
                npasseggeri.setText(""+a);
                home.notifiche.setText("Rimosso un passeggero");
            }

        });
         
        passeggeri.add(npasseggeri);
        passeggeri.add(passeggeripiu);
        
        
        passeggeripiu.setFont(new Font("Helvetica",Font.BOLD,15));
        
        passeggeripiu.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(npasseggeri.getText());
                a++;
                npasseggeri.setText(""+a);
                home.notifiche.setText("Aggiunto un passeggero");
            }

        });
        passeggeri.setOpaque(false);
        
        reservationPanel.add(titnpasseggeri);
        titnpasseggeri.setFont(new Font("Helvetica",Font.BOLD,25));
        reservationPanel.add(passeggeri);
        
        add(reservationPanel, BorderLayout.CENTER);
        ImageIcon immagine=new ImageIcon("immagini/Logo.png");
        logo.setIcon(this.scalaImmagine(immagine, 350, 400));
        logo.setEnabled(true);
      
        panelsud.add(logo);
        panelsud.add(ricerca);
        
        
        
        ricerca.addActionListener(nuovoCustomer());
        
        ricerca.setFont(new Font("Helvetica",Font.BOLD,35));
        ricerca.addMouseMotionListener( new MouseAdapter() {
           
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
                if((!npasseggeri.getText().isEmpty()) && !(andata.getSelectedIndex()==-1) && !(ritorno.getSelectedIndex()==-1)){
                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // ho cambiato il formato data e funziona ma non mi piace, cambiare formato data adapter!
                        Date       date = format.parse(data.getText());
                        Calendar   calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        if(Integer.parseInt(npasseggeri.getText())>0){
                            
                            home.notifiche.setText("Ricerco informazioni sulla rotta.. ");
                            ArrayList<Flight> a=  controller.searchFlights(andata.getSelectedItem().toString(),ritorno.getSelectedItem().toString(),data.getText());
                            System.out.println(a);
                            if(a.isEmpty())
                            JOptionPane.showConfirmDialog(home, "Non risulta nessuna rotta nei voli"+"della compagnia aerea", "Errore", JOptionPane.OK_CANCEL_OPTION);
                            else
                            {
                            home.setNpasseggeri(Integer.parseInt(npasseggeri.getText()));
                            home.refreshGUI(new FlightsPanel(controller,home,a)); 
                            }
                        }
                        else{
                        home.notifiche.setText("Attenzione: aggiungere almeno un passeggero.");
                        }
                    } catch (ParseException ex) {
                      int dialogResult = JOptionPane.showConfirmDialog(home, "Errore nell'inserimento della data.\n"+"Inserire la data nel formato\n dd-mm-yyyy", "Errore", JOptionPane.OK_CANCEL_OPTION); 
                    } catch (SQLException ex) {
                        Logger.getLogger(ReservationPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                   int dialogResult = JOptionPane.showConfirmDialog(home, "Riempire tutti i campi\n"+"per poter proseguire.\n", "Errore", JOptionPane.OK_CANCEL_OPTION);
                }
            }

           
        };
        return evento;
    }
  
   public void MakeComponentsTrasparent()
   {
       home.trasparentTextField(data);
       home.trasparentButton(ricerca);
       home.trasparentButton(passeggeripiu);
       home.trasparentButton(passeggerimeno);
       home.trasparentTextField(npasseggeri);
   }

}
