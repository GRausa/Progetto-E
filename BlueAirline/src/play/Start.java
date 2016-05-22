/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package play;

import controllertxt.ControllerTxt;
import java.sql.SQLException;

/**
 *
 * @author Giovanni
 */
public class Start {
    public static void main(String[] args) throws SQLException {
        
        ControllerTxt C = new ControllerTxt();
        C.searchRoutes();
        System.out.println();
        C.searchFlights(); //test con: Roma	New York	2016-05-24
    }    
}
