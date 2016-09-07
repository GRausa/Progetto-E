/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui.administrator;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Provides the controller for the textual user interface for administrators.
 * 
 * @author 
 */
public class ControllerAdministratorTxt implements Runnable {

    private InterfaceClient client;
    /**
     * Constructs a new controller 
     * 
     * @param controllerClient Object that provides methods in order to administrate the application and the
     *                         information that are set in the database.
     */
    public ControllerAdministratorTxt(InterfaceClient controllerClient) {
        this.client = (FacadeControllerClient) controllerClient;
    }
    
    @Override
    public void run() {    

        loop:while (true) {            
            try {            
                String s = MethodsControlAdministrator.toStringMenu();
                switch (s) {
                    
                    case "HI":
                        MethodsControlAdministrator.hi((FacadeControllerClient) client);
                        break;
                        
                        
                    case "INSERISCI_VOLO":
                        MethodsControlAdministrator.insertFlight((FacadeControllerClient) client);
                        break;
                    
                    case "MODIFICA_VOLO":
                        MethodsControlAdministrator.editFlight((FacadeControllerClient) client);
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
            } catch (IOException ex) {               
                Logger.getLogger(ControllerAdministratorTxt.class.getName()).log(Level.SEVERE, null, ex);
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
