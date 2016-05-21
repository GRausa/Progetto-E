/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllertxt;

import administrators.ControllerAdministrator;
import clients.ControllerClient;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class ControllerTxt {
    ControllerClient CC;
    ControllerAdministrator CA;
    
    public ControllerTxt(){
        CC = new ControllerClient();
        CA = new ControllerAdministrator();
    }
    
    public void searchRoutes() throws SQLException{
        ArrayList<Route> routes = CC.searchRoutes();
        for(Route r:routes){
            System.out.println(r);
        }
    }
    
}
