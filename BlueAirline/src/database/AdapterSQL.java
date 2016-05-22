/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Flight;
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
    
    public ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException{
        ArrayList<Flight> flights;
        String query=
        "SELECT A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO "+
        "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V "+
        "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA AND A1.CITTA='"+departure+"' AND A2.CITTA ='"+destination+"' AND V.DATAPARTENZA = '"+date+"'";
        ResultSet resultQuery = SQL.queryRead(query);
        flights = parser.parseFlights(resultQuery);
        resultQuery.close();
        return flights;  
    }    
}

