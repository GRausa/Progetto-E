package gui;

import controller.FacadeControllerClient;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import objects.Flight;

/**
 *
 * @author Andrea
 */
public class FlightsPanel extends JPanel {

    FacadeControllerClient controller;
    HomeFrame home;
    private ArrayList<Flight> flights;

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    JLabel volidisponibili = new JLabel("Voli Disponibili: ");
    JButton conferma0 = new JButton("conferma");

    JTable voli;

    public FlightsPanel(FacadeControllerClient company, HomeFrame home, ArrayList<Flight> flight) {
        this.home = home;
        this.controller = company;
        this.flights = flight;
        addComponentsToPane(this);
        this.setVisible(true);
        setOpaque(false);
        home.setallFont(this);
    }

    private void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            c.weightx = 2;
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.gridx = 3;
        c.gridy = 9;
        MyTableModel model = new MyTableModel();
        int nfl = 0;
        for (Flight f : flights) {
            if (home.getNpasseggeri() <= f.getSeatFree()) {
                nfl++;
            }
        }
        Object data[][] = new Object[nfl][8];
        if (!flights.isEmpty()) {
            int i = 0;
            for (Flight f : flights) {
                if (home.getNpasseggeri() <= f.getSeatFree()) {
                    data[i][0] = f.getCode();
                    data[i][1] = f.getRoute().getDeparutreAirport();
                    data[i][2] = f.getRoute().getDestinationAirport();
                    SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                    String d = format1.format(f.getDateDeparture().getTime());
                    SimpleDateFormat format2 = new SimpleDateFormat("hh:mm a");
                    String d2 = format2.format(f.getDateDeparture().getTime());
                    data[i][3] = d;
                    data[i][4] = d2;
                    data[i][5] = f.getPrice();
                    data[i][6] = f.getSeatFree();
                    data[i][7] = false;
                    i++;
                }
            }
        }
        model.setData(data);
        voli = new JTable(model);
        voli.setFillsViewportHeight(true);
        voli.setFont(new Font("Helvetica", Font.BOLD, 15));
        JScrollPane conf = new JScrollPane(voli);
        voli.setGridColor(Color.BLACK);
        voli.setOpaque(false);
        ((DefaultTableCellRenderer) voli.getDefaultRenderer(String.class)).setOpaque(false);
        ((DefaultTableCellRenderer) voli.getDefaultRenderer(Date.class)).setOpaque(false);
        ((DefaultTableCellRenderer) voli.getDefaultRenderer(Double.class)).setOpaque(false);
        ((DefaultTableCellRenderer) voli.getDefaultRenderer(Integer.class)).setOpaque(false);

        conf.setOpaque(false);
        conf.getViewport().setOpaque(false);
        pane.add(conf, c);

        if (shouldWeightX) {
            c.weightx = 2;
        }

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 30;
        c.gridx = 3;
        c.gridy = 10;

        conferma0.addActionListener(nuovoPasseggero());
        home.trasparentButton(conferma0);
        conferma0.setFont(new Font("Helvetica", Font.BOLD, 15));

        pane.add(conferma0, c);
    }

    private ActionListener nuovoPasseggero() {

        ActionListener evento = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                home.notifiche.setText("Seleziono il volo da prenotare..");
                MyTableModel m = (MyTableModel) voli.getModel();
                if (m.getRiga() != null) {
                    Flight f = controller.searchFlights(new Flight((String) voli.getValueAt(m.getRiga(), 0)))[0];
                    home.setFlighttmp(f);
                    home.setCodeflight((String) voli.getValueAt(m.getRiga(), 0));
                    home.setPriceflight((double) voli.getValueAt(m.getRiga(), 5));
                    home.refreshGUI(new CustomerPanel(home, controller));
                } else {
                    JOptionPane.showConfirmDialog(home, "Selezionare almeno un volo\n" + "per poter proseguire.\n", "Errore", JOptionPane.OK_CANCEL_OPTION);
                }
            }

        };
        return evento;
    }

    class MyTableModel extends AbstractTableModel {

        private Integer riga;

        String columnName[] = {"CODICE volo", "Partenza", "Destinazione", "Data", "Orario", "Prezzo (€)", "Posti Disponibili", "Seleziona"};
        Object data[][] = {{"VOLO001", "Milano (Linate)", "New York(JFK)", "dd/MM/yyyy", "hh:mm", 100, 50, false},
        {"VOLO001", "Milano (Linate)", "New York(JFK)", "dd/MM/yyyy", "hh:mm", 300, 50, false}};

        public void setData(Object[][] data) {
            this.data = data;
        }

        @Override
        public int getColumnCount() {
            return columnName.length;
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnName[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return col >= 7;
        }

        @Override
        public void setValueAt(Object value, int row, int col) {

            if (col != 7) {
                data[row][col] = value;
                fireTableCellUpdated(row, col);
            } else {
                if ((boolean) value == false) {
                    data[row][col] = value;
                    fireTableCellUpdated(row, col);
                    riga = null;
                }
                if ((boolean) value == true) {
                    if (!(riga == null)) {
                        data[riga][col] = false;
                        fireTableCellUpdated(riga, col);
                    }
                    riga = row;
                    data[row][col] = value;
                    fireTableCellUpdated(row, col);

                }

            }
        }

        public Integer getRiga() {
            return riga;
        }

    }
}
