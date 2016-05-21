/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import administrators.ControllerAdministrator;
import clients.ControllerClient;
import controllertxt.ControllerTxt;
import objects.City;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Airplane;

/**
 *
 * @author Giovanni
 */
public class Start {
    public static void main(String[] args) throws SQLException {
        
        ControllerTxt C = new ControllerTxt();
        C.searchRoutes();
    }    
}
