/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package txt;

import blueAirline.Company;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author cl418377
 */
public class Simulation {
    public static void main(String args[]) throws IOException, FileNotFoundException, ParseException{
        //CARICAMENTO
        
        Company c = new Company("BlueAirlines");
        c.downloadAirplanes("file/Airplanes.txt");
        System.out.println(c.toStringAirplanes());
        c.downloadCitys("file/Citys.txt");
        System.out.println(c.toStringCitys());
        c.downloadAirports("file/Airports.txt");
        System.out.println(c.toStringAirports());
        c.downloadRoutes("file/Routes.txt");
        System.out.println(c.toStringRoutes());
        
        c.downloadFlight("file/Flights.txt");
        System.out.println(c.toStringFlights());
        //System.out.println(c.toStringCityAirports("Monaco"));
        
        //ControllerTxt.searchTxtFlight(c);
        //ControllerTxt.checkSeats(c);
        ControllerTxt.Order(c);
        System.out.println(c.toStringReservation());
    }
}
