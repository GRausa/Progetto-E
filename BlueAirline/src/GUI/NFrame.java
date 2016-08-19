/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import clients.ControllerClient;
import static GUI.CustomerPanel.RIGHT_TO_LEFT;
import static GUI.CustomerPanel.shouldFill;
import static GUI.CustomerPanel.shouldWeightX;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author andreacavagna
 */
public class NFrame extends JFrame{
    
    
    private final int WEIGHT= 200;
    private final int HEIGHT= 100;
    private int n;
    private ControllerClient controller;
    private boolean opened;
   
    ArrayList<JComboBox> combo;
    JLabel tit ;
    
    public NFrame(ControllerClient controller,int n,String notifica)
    {
        this.controller = controller;
        this.opened = true;
        this.n = n;
        this.combo = new ArrayList();
        this.tit = new JLabel(notifica);
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
    
     if (RIGHT_TO_LEFT) {
            setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
}
    
    GridBagConstraints c = new GridBagConstraints();
    if (shouldFill) {
    
    c.fill = GridBagConstraints.HORIZONTAL;
    } 
     
     if (shouldWeightX) {
    c.weightx = 3;
    }
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipady = 30;
    c.gridx = 0;
    c.gridy = 1;
    add(tit, c);
    tit.setFont(new Font("Helvetica",Font.BOLD,15));
    
   for(int i=0;i<this.n;i++)
    {
        if (shouldWeightX) {
            c.weightx = 3;
        }
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

    void rezise(JTextField numero) {
    int n = Integer.parseInt(numero.getText());
    setSize(WEIGHT, HEIGHT+(30*n));
    setMinimumSize(new Dimension(WEIGHT,HEIGHT+(30*n)));
    for(int i=0; i<combo.size();i++)
    this.remove(combo.get(i));
    for(int i=0;i<n;i++)
    {
        GridBagConstraints c = new GridBagConstraints();
        
        if (shouldWeightX) {
            c.weightx = 3;
        }
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
