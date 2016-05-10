/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import blueAirline.Company;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
/**
 *
 * @author andreacavagna
 */
public class FrameReservation extends javax.swing.JFrame {
    Company c;
    /**
     * Creates new form NewJFrame
     */
    public  FrameReservation(Company c) {
        this.c=c;
        initComponents();
        
    }

  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        
        andata = new javax.swing.JComboBox();
        tit_andata = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        tit_Ritorno = new javax.swing.JLabel();
        ritorno = new javax.swing.JComboBox();
        login = new javax.swing.JLabel();
        Data = new javax.swing.JTextField();
        sfondo = new javax.swing.JLabel();
        
        
        
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("prova");
        setBounds(new java.awt.Rectangle(0, 0, 600, 400));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(800, 700));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(600, 400));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                Company(evt);
            }
        });
        getContentPane().setLayout(null);

        andata.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        
        
        andata.setEditable(true);
        
        
            andata.setSelectedItem("");
        
        
        
        andata.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                String a = andata.getEditor().getItem().toString();
                andata.removeAllItems();
                int st = 0;
                
                StringTokenizer s = new StringTokenizer(c.toStringCitys(),"\n");
                String []vet = new String[s.countTokens()];
                for(int i=0; s.hasMoreTokens(); i++)
                {
                    vet[i]= s.nextToken();
                }
                
                for (int i = 0; i < vet.length; i++) {
                    if (vet[i].startsWith(a)) { andata.addItem(vet[i]); st++; }
                }
                andata.getEditor().setItem(new String(a));
                andata.hidePopup();
                if (st != 0) { andata.showPopup(); }
            }
        } } );
      
        
        andata.setToolTipText("");
        andata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        andata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox2KeyReleased(evt);
            }
        });
        getContentPane().add(andata);
        andata.setBounds(60, 110, 170, 30);

        tit_andata.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        tit_andata.setText("Andata");
        getContentPane().add(tit_andata);
        tit_andata.setBounds(60, 70, 120, 28);

        logo.setFont(new java.awt.Font("Courier New", 0, 36)); // NOI18N
        logo.setText("LOGO");
        getContentPane().add(logo);
        logo.setBounds(410, 230, 130, 130);

        tit_Ritorno.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        tit_Ritorno.setText("Ritorno");
        getContentPane().add(tit_Ritorno);
        tit_Ritorno.setBounds(340, 70, 120, 28);

        ritorno.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        ritorno.setEditable(true);
        ritorno.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                String a = ritorno.getEditor().getItem().toString();
                ritorno.removeAllItems();
                int st = 0;
                
                StringTokenizer s = new StringTokenizer(c.toStringCitys(),"\n");
                String []vet = new String[s.countTokens()];
                for(int i=0; s.hasMoreTokens(); i++)
                {
                    vet[i]= s.nextToken();
                }
                
                for (int i = 0; i < vet.length; i++) {
                    if (vet[i].startsWith(a)) { ritorno.addItem(vet[i]); st++; }
                }
                ritorno.getEditor().setItem(new String(a));
                ritorno.hidePopup();
                if (st != 0) { ritorno.showPopup(); }
            }
        } } );
        
        ritorno.setPreferredSize(new java.awt.Dimension(104, 30));
        ritorno.setSize(new java.awt.Dimension(170, 30));
        getContentPane().add(ritorno);
        ritorno.setBounds(340, 110, 170, 30);

        login.setText("Login");
        getContentPane().add(login);
        login.setBounds(510, 0, 90, 30);

        Data.setText("Data");
        Data.setPreferredSize(new java.awt.Dimension(170, 30));
        Data.setSize(new java.awt.Dimension(170, 30));
        Data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(Data);
        Data.setBounds(60, 210, 170, 30);

      
        //sfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/sfondo1.jpg"))); // NOI18N
        sfondo.setBounds(-10, -10, 990, 1000);
        getContentPane().add(sfondo);
        pack();
    }// </editor-fold>                        

    private void jComboBox2KeyReleased(java.awt.event.KeyEvent evt) {                                       
        andata.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() { public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                String a = andata.getEditor().getItem().toString();
                andata.removeAllItems();
                int st = 0;
                
                for (int i = 0; i < andata.getItemCount() ; i++) {
                    if (andata.getItemAt(i).toString().startsWith(a)) { andata.addItem(andata.getItemAt(i).toString()); st++; }
                }
                andata.getEditor().setItem(new String(a));
                andata.hidePopup();
                if (st != 0) { andata.showPopup(); }
            }
        } } );    }                                      

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void Company(java.awt.event.ContainerEvent evt) {                         
        // TODO add your handling code here:
    }                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

  

    // Variables declaration - do not modify                     
    private javax.swing.JComboBox ritorno;
    private javax.swing.JComboBox andata;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel sfondo;
    private javax.swing.JLabel tit_andata;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel tit_Ritorno;
    private javax.swing.JLabel login;
    private javax.swing.JTextField Data;
    // End of variables declaration                   
}
