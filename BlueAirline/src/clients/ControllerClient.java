/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clients;

import objects.City;
import database.AdapterSQL;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class ControllerClient {
    
    private AdapterSQL adapter;

    public ControllerClient() {
        adapter = new AdapterSQL();
    }
    
    public ArrayList<City> searchCitys() throws SQLException {
        return adapter.searchCitys();       
    }
    
    public ArrayList<Route> searchRoutes() throws SQLException{
        return adapter.searchRoutes();
    }
    
}
