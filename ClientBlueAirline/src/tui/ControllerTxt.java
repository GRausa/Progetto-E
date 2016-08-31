/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui;

import controller.Controller;

public class ControllerTxt implements Runnable {

    private Controller client;
    

    public ControllerTxt(Controller n) {
        this.client = n;
    }

    @Override
    public void run() {    

        loop:while (true) {            
            String s = MethodsControl.toStringMenu();            
            switch (s) {
                
                case "HI": 
                    MethodsControl.hi(client);                
                    break;               
                
                case "CERCA_VOLO":
                    MethodsControl.searchFlight(client);
                    break;

                case "VERIFICA_TRATTA":
                    MethodsControl.searchRoute(client);                    
                    break;

                case "PRENOTA":
                    MethodsControl.makeReservation(client);
                    break;
                    
                case "CERCA_VOLO_AEROPORTI":
                    MethodsControl.searchFlightAirport(client);                    
                    break;
                
                case "CHECK_IN":
                    MethodsControl.checkIn(client); 
                    break;
                
                case "CERCA_TICKETPASSENGER":
                    MethodsControl.searchTicketPassenger(client);                     
                    break;
                 
                case "CERCA_PRENOTAZIONE":
                    MethodsControl.searchReservation(client);
                    break;
                    
                case "CITTA_DISPONIBILI":
                    MethodsControl.searchCitys(client);                    
                    break;
                    
                case "CERCA_VOLO_CODICE":
                    MethodsControl.searchFlightCode(client);
                    break;
                 
                case "MODIFICA_BIGLIETTO":
                    MethodsControl.editTicket(client);
                    break;
                   
                case "EXIT":
                    break loop;
                
                default:
                    System.out.println("Errore inserimento");

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
