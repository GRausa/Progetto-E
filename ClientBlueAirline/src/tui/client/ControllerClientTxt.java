/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui.client;

import controller.FacadeControllerClient;
import controller.InterfaceClient;

public class ControllerClientTxt implements Runnable {

    private InterfaceClient client;

    public ControllerClientTxt(InterfaceClient controllerClient) {
        this.client = (FacadeControllerClient) controllerClient;
    }
    

    @Override
    public void run() {    

        loop:while (true) {            
            String s = MethodsControlClient.toStringMenu();            
            switch (s) {
                
                case "HI": 
                    MethodsControlClient.hi((FacadeControllerClient) client);                
                    break;               
                
                case "CERCA_VOLO":
                    MethodsControlClient.searchFlight((FacadeControllerClient) client);
                    break;

                case "VERIFICA_TRATTA":
                    MethodsControlClient.searchRoute((FacadeControllerClient) client);                    
                    break;

                case "PRENOTA":
                    MethodsControlClient.makeReservation((FacadeControllerClient) client);
                    break;
                    
                case "CALENDARIO_AEROPORTI":
                    MethodsControlClient.searchFlightAirport((FacadeControllerClient) client);                    
                    break;
                
                case "CHECK_IN":
                    MethodsControlClient.checkIn((FacadeControllerClient) client); 
                    break;
                
                case "CERCA_TICKETPASSENGER":
                    MethodsControlClient.searchTicket((FacadeControllerClient) client);                     
                    break;
                 
                case "CERCA_PRENOTAZIONE":
                    MethodsControlClient.searchReservation((FacadeControllerClient) client);
                    break;
                    
                case "CITTA_DISPONIBILI":
                    MethodsControlClient.searchCitys((FacadeControllerClient) client);                    
                    break;
                    
                case "CERCA_VOLO_CODICE":
                    MethodsControlClient.searchFlightCode((FacadeControllerClient) client);
                    break;
                 
                case "MODIFICA_BIGLIETTO":
                    MethodsControlClient.editTicket((FacadeControllerClient) client);
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
