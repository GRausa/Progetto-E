/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.AdapterSQL;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Flight;

/**
 *
 * @author Giovanni
 */
public class FacadeControllerAdministrator extends FacadeController{
    
    private AdapterSQL adapter;

    public FacadeControllerAdministrator() {
        adapter = new AdapterSQL();
    }
    
    //riempie tutti i posti NULL -> usato per riempire il DB
    
    public ArrayList<Flight> setAllSeatFlights() throws SQLException{
        return adapter.setAllSeatFlights();
    }   

}
