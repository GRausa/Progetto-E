/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import objects.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Airplane;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class AdapterSQL {
    
    ConnectionSQL SQL;
    ParserSQL parser;

    public AdapterSQL() {
        SQL = new ConnectionSQL();
        SQL.startConnection();
        parser = new ParserSQL();
    }
    
    public ArrayList<City> searchCitys() throws SQLException {
        ArrayList<City> citys;
        String query = "SELECT NOME, STATO FROM Citta";
        ResultSet resultQuery = SQL.queryRead(query);
        citys = parser.parseCitys(resultQuery);
        resultQuery.close();
        return citys;
    }   
    
    public ArrayList<Airplane> searchAirplanes() throws SQLException {
        ArrayList<Airplane> airplanes;
        String query = "SELECT COD_AEREO,POSTI,POSTI1CLASSE,NOME FROM Aereo";
        ResultSet resultQuery = SQL.queryRead(query);
        airplanes = parser.parseAirplanes(resultQuery);
        resultQuery.close();
        return airplanes;
    }   
    
    public ArrayList<Route> searchRoutes() throws SQLException{
        ArrayList<Route> routes;
        String query = 
        "SELECT A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\"\n" +
        "FROM Rotta R, Aeroporto A1, Aeroporto A2\n" +
        "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO";
        ResultSet resultQuery = SQL.queryRead(query);
        routes = parser.parseRoutes(resultQuery);
        resultQuery.close();
        return routes;        
    }
    
}

