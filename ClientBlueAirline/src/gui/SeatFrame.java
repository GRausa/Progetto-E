/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.ClientBlueAirline;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import objects.*;


/**
 *K9SLPO
 * @author andreacavagna
 */
public class SeatFrame extends JFrame{
    
    private ClientBlueAirline controller;
    private HomeFrame home;
    private ArrayList<Seat> seats;
    
     
    JLabel titolo = new JLabel("Voli Disponibili: ");
    JButton conferma =new JButton("conferma");
    
    JTable tabella ;
    
      public SeatFrame(HomeFrame home,ClientBlueAirline controller)
    {
        this.controller = controller;
        
        this.home=home;
        this.seats= home.getFlighttmp().getSeats();
      
        setSize(WIDTH , HEIGHT+(30));
        setMinimumSize(new Dimension(WIDTH,HEIGHT+30));
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        Dimension frameSize = this.getSize ();

        this.setLocation ((screenSize.width - frameSize.width) / 2,
        (screenSize.height - frameSize.height) / 2);
      
    }
      
      private void initComponents()
      {
        this.setLayout(new BorderLayout());
        tabella = new JTable(new JTableModel()); 
        JScrollPane scrollPane = new JScrollPane(tabella);
        tabella.setFillsViewportHeight(true); 

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        tabella.getColumn("Button1").setCellRenderer(buttonRenderer);
        tabella.getColumn("Button2").setCellRenderer(buttonRenderer);
        this.add(tabella,BorderLayout.CENTER);
        this.add(titolo,BorderLayout.NORTH);
        this.add(conferma,BorderLayout.SOUTH);
      }
      
      
 public static class JTableModel extends AbstractTableModel {
        private static final long serialVersionUID = 1L;
        private static final String[] COLUMN_NAMES = new String[] {"Id", "Stuff", "Button1", "Button2"};
        private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {Integer.class, String.class, JButton.class,  JButton.class};

        @Override public int getColumnCount() {
            return COLUMN_NAMES.length;
        }

        @Override public int getRowCount() {
            return 4;
        }

        @Override public String getColumnName(int columnIndex) {
            return COLUMN_NAMES[columnIndex];
        }

        @Override public Class<?> getColumnClass(int columnIndex) {
            return COLUMN_TYPES[columnIndex];
        }

        @Override public Object getValueAt(final int rowIndex, final int columnIndex) {
                /*Adding components*/
            switch (columnIndex) {
                case 0: return rowIndex;
                case 1: return "Text for "+rowIndex;
                case 2: // fall through
               /*Adding button and creating click listener*/
                case 3: final JButton button = new JButton(COLUMN_NAMES[columnIndex]);
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button), 
                                        "Button clicked for row "+rowIndex);
                            }
                        });
                        return button;
                default: return "Error";
            }
        }   
    }
private static class JTableButtonRenderer implements TableCellRenderer {        
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            SeatButton button = (SeatButton)value;
            return button;  
        }
    }        
  

private static class JTableButtonMouseListener extends MouseAdapter {
        private final JTable table;

        public JTableButtonMouseListener(JTable table) {
            this.table = table;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
            int row    = e.getY()/table.getRowHeight(); //get the row of the button

                    /*Checking the row or column is valid or not*/
            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    /*perform a click event*/
                    ((JButton)value).doClick();
                }
            }
        }
    }
      
    
    private class SeatButton extends JButton
    {
        public SeatButton(boolean free,int number)
        {
        super(""+number);
        if(!free)
        {
            this.setBackground(Color.red);
            this.setEnabled(false);
        }
        else
            this.setBackground(Color.green);
            this.setEnabled(true);
        }
    }
}
