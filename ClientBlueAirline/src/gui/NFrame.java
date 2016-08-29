/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;




import clientblueairline.ClientBlueAirline;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import oggetti.HoldLuggage;
import oggetti.Insurance;
import oggetti.Meal;


/**
 *
 * @author andreacavagna
 */
public class NFrame extends JFrame{
    
    
    private final int WEIGHT= 200;
    private final int HEIGHT= 100;
    private int n;
    private ClientBlueAirline controller;
    private boolean opened;
    private HomeFrame home;
    ArrayList<JComboBox> combo;
    JLabel tit ;    
    String notifica;
    
    public NFrame(HomeFrame home,ClientBlueAirline controller,int n,String notifica)
    {
        this.controller = controller;
        this.opened = true;
        this.n = n;
        this.home=home;
        this.combo = new ArrayList();
        this.tit = new JLabel(notifica);
        this.notifica = notifica;
        setSize(WEIGHT, HEIGHT+(30*this.n));
        setMinimumSize(new Dimension(WEIGHT,HEIGHT+(40*this.n)));
        this.setResizable(false);
        initComponents();
        this.setVisible(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = this.getSize ();

        this.setLocation ((screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2);
      
    }

    public void setMessage(String message)
    {
    tit.setText(message);
    }
    public ArrayList<JComboBox> getCombo()
    {
    return this.combo;
    }
    
    
    public boolean isOpened() {
        return opened;
    }
    
    public void setOpened(boolean b) {
        this.opened=b;
    }

    private void initComponents() {
    setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
   
  
    c.weightx = 3;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;
    c.gridx = 0;
    c.gridy = 1;
    add(tit, c);
    tit.setFont(new Font("Helvetica",Font.BOLD,15));
    
   for(int i=0;i<this.n;i++)
    {
       
            c.weightx = 3;
      
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = i+2;
        JComboBox combo = new JComboBox();
        this.combo.add(combo);
        combo.setFont(new Font("Helvetica",Font.BOLD,15));
        
        add(combo, c);
        
    }
   
   //le parti commentate sono da togliere quando saranno riempiti i combobox, altrimenti dà errore perchè non trova la stringa da splittare
    this.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
                
                if(notifica.equals("Cosa mangi")) {
                    ArrayList<Meal> meal = new ArrayList();
                    
                    for(int i =0;i< combo.size();i++) {
                        //String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                        //meal.set(i, new Meal(tmp[0]));
                    }  
                    home.setMeals(meal);
                } 
                else if(notifica.equals("Quale assicurazione vuoi?")) {
                    ArrayList<Insurance> insurance = new ArrayList();
                    
                    for(int i =0;i< combo.size();i++) {
                        //String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                        //insurance.set(i, new Insurance(tmp[0]));
                    }   
                    home.setInsurances(insurance);
                    
                } 
                else if (notifica.equals("Scegli il tipo di bagaglio.")) {
                    ArrayList<HoldLuggage> luggage = new ArrayList();
                    
                    for(int i =0;i< combo.size();i++) {
                        //String[] tmp = combo.get(i).getSelectedItem().toString().split(" ");
                        //luggage.set(i, new HoldLuggage(tmp[0]));
                    }
                    home.setLuggages(luggage);
                }
                
                dispose();
	    }
	});
    }
            
    void rezise(JTextField numero) {
    int n = Integer.parseInt(numero.getText());
    setSize(WEIGHT, HEIGHT+(30*n));
    setMinimumSize(new Dimension(WEIGHT,HEIGHT+(30*n)));
    for(int i=0; i<combo.size();i++)
    this.remove(combo.get(i));
    for(int i=0;i<n;i++)
    {
        GridBagConstraints c = new GridBagConstraints();
        
     
            c.weightx = 3;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = i+2;
        JComboBox combo = new JComboBox();
        this.combo.add(combo);
        combo.setFont(new Font("Helvetica",Font.BOLD,15));
        
        add(combo, c);
        
    }
   
  
   
    }

    
    
}
