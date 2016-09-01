/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui;

import controller.FacadeControllerClient;
import controller.InterfaceClient;

public class ControllerTxt implements Runnable {

    private InterfaceClient client;

    public ControllerTxt(InterfaceClient controllerClient) {
        this.client = (FacadeControllerClient) controllerClient;
    }
    

    @Override
    public void run() {    

        loop:while (true) {            
            String s = MethodsControl.toStringMenu();            
            switch (s) {
                
                case "HI": 
                    MethodsControl.hi((FacadeControllerClient) client);                
                    break;               
                
                case "CERCA_VOLO":
                    MethodsControl.searchFlight((FacadeControllerClient) client);
                    break;

                case "VERIFICA_TRATTA":
                    MethodsControl.searchRoute((FacadeControllerClient) client);                    
                    break;

                case "PRENOTA":
                    MethodsControl.makeReservation((FacadeControllerClient) client);
                    break;
                    
                case "CALENDARIO_AEROPORTI":
                    MethodsControl.searchFlightAirport((FacadeControllerClient) client);                    
                    break;
                
                case "CHECK_IN":
                    MethodsControl.checkIn((FacadeControllerClient) client); 
                    break;
                
                case "CERCA_TICKETPASSENGER":
                    MethodsControl.searchTicket((FacadeControllerClient) client);                     
                    break;
                 
                case "CERCA_PRENOTAZIONE":
                    MethodsControl.searchReservation((FacadeControllerClient) client);
                    break;
                    
                case "CITTA_DISPONIBILI":
                    MethodsControl.searchCitys((FacadeControllerClient) client);                    
                    break;
                    
                case "CERCA_VOLO_CODICE":
                    MethodsControl.searchFlightCode((FacadeControllerClient) client);
                    break;
                 
                case "MODIFICA_BIGLIETTO":
                    MethodsControl.editTicket((FacadeControllerClient) client);
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
