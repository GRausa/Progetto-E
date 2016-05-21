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
public class ParserSQL {

    public ArrayList<City> parseCitys(ResultSet resultQuery) throws SQLException {
        ArrayList<City> citys = new ArrayList<>();
        while (resultQuery.next()) {
            String nome = resultQuery.getString("NOME");
            String stato = resultQuery.getString("STATO");
            citys.add(new City(nome,stato));
        }
        return citys;
    }
    
    public ArrayList<Airplane> parseAirplanes(ResultSet resultQuery) throws SQLException {
        ArrayList<Airplane> airplanes = new ArrayList<>();
        while (resultQuery.next()) {
            String codAirplane = resultQuery.getString("COD_AEREO");
            String name = resultQuery.getString("NOME");
            int seats = resultQuery.getInt("POSTI");
            int seatsFirstClass = resultQuery.getInt("POSTI1CLASSE");
            airplanes.add(new Airplane(codAirplane,name,seats,seatsFirstClass));
        }
        return airplanes;
    }
    
    public ArrayList<Route> parseRoutes(ResultSet resultQuery) throws SQLException{
        ArrayList<Route> routes = new ArrayList<>();
        while (resultQuery.next()) {
            String deparutreAirport = resultQuery.getString("AEROPORTOPARTENZA");
            String destinationAirport = resultQuery.getString("AEROPORTOARRIVO");
            String departureCity = resultQuery.getString("CITTAPARTENZA");
            String destinationCity = resultQuery.getString("CITTAARRIVO");
            routes.add(new Route(deparutreAirport, destinationAirport, departureCity, destinationCity));
        }
        return routes;
        
    }

}
