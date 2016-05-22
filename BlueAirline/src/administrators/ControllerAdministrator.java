/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrators;

import database.AdapterSQL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Giovanni
 */
public class ControllerAdministrator {
    
    private AdapterSQL adapter;

    public ControllerAdministrator() {
        adapter = new AdapterSQL();
    }

}
