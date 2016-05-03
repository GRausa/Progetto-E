/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author cl418377
 */
public class Company {
    private ArrayList<Airplane> airplanes;
    private ArrayList<City> cities;
    private ArrayList<Airport> airports;
    private ArrayList<Route> routes;
    private ArrayList<Flight> flights;
    private String nameCompany;
    
    public Company(String nameCompany){
        this.nameCompany=nameCompany;
        this.airplanes=new ArrayList<>();
        this.airports=new ArrayList<>();
        this.cities=new ArrayList<>();
        this.routes=new ArrayList<>();
        this.flights=new ArrayList<>();
    }
    
    public void downloadAirplanes(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String code = st.nextToken();
            String name = st.nextToken();
            int nSeats = Integer.parseInt(st.nextToken());
            airplanes.add(new Airplane(code,name,nSeats));
        }
        in.close();
    }
    
    public String toStringAirplanes(){
        String s="";
        for(Airplane airplane : airplanes){
            s+=airplane.toString();
        }
        return s;
    }
    
    public void downloadCitys(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String name = st.nextToken();
            cities.add(new City(name));
        }
        in.close();
    }
    
    public String toStringCitys(){
        String s="";
        for(City city : cities){
            s+=city.toString();
        }
        return s;
    }
    
    public void downloadAirports(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String city = st.nextToken();
            City city_app=new City(city);
            String nameAirport = st.nextToken();
            for(City c : cities){
                if(c.equals(city_app)){
                    Airport a = new Airport(nameAirport,c);
                    airports.add(a);
                    c.insertAirport(a);
                }
            }
        }
        in.close();
    }
    
    public String toStringAirports(){
        String s="";
        for(Airport airport : airports){
            s+=airport.toString();
        }
        return s;
    }
    
    public void downloadRoutes(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String a1 = st.nextToken();
            String a2 = st.nextToken();
            Airport destination = null;
            Airport departure = null;
            for(Airport a : airports){
                if(a.equalsName(a1)){
                    departure = a;
                }
                if(a.equalsName(a2)){
                    destination = a;
                }
            }
            routes.add(new Route(departure,destination));
        }
        in.close();
    }
    
    public String toStringRoutes(){
        String s="";
        for(Route r : routes){
            s+=r.toString() + "\n";
        }
        return s;
    }
    
    public void downloadFlight(String nameFile) throws FileNotFoundException, IOException, ParseException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String codeFlight = st.nextToken();  
            String codeAirplane = st.nextToken();            
            String depaAirport = st.nextToken();            
            String destAirport = st.nextToken();
            
            String depDate = st.nextToken();
            String depTime = st.nextToken();
            Date departureDate = new Date (Integer.parseInt(depDate.substring(6, 10)),Integer.parseInt(depDate.substring(3, 5)),Integer.parseInt(depDate.substring(0, 2)), Integer.parseInt(depTime.substring(0, 2)), Integer.parseInt((depTime.substring(3,5)))); //OK
            
            String destDate = st.nextToken();
            String destTime = st.nextToken();
            Date destinationDate = new Date (Integer.parseInt(destDate.substring(6, 10)),Integer.parseInt(destDate.substring(3, 5)),Integer.parseInt(destDate.substring(0, 2)), Integer.parseInt(destTime.substring(0, 2)), Integer.parseInt((destTime.substring(3,5)))); //OK
            
            
            
            double price = Double.parseDouble(st.nextToken()); //OK            
            Airplane airplane = null;
            for(Airplane a : airplanes){
                if(a.equalsCode(codeAirplane)){
                    airplane = a;
                }
            }            
            Airport departureAirport = null;
            Airport destinationAirport = null;
            for(Airport a : airports){
                if(a.equalsName(depaAirport)){
                    departureAirport = a;
                }
                if(a.equalsName(destAirport)){
                    destinationAirport = a;
                }
            }
            Route r = new Route(departureAirport, destinationAirport);
            Route route = null;
            for(Route rou : routes){
                if(rou.equals(r)){
                    route = r;
                }
            }      
            flights.add(new Flight(codeFlight, airplane, route, departureDate, destinationDate, price));
        }
        in.close();
    }
    
    public String toStringFlights(){
        String s="";
        for(Flight f : flights){
            s+=f.toString() +"\n";
        }
        return s;
    }
    
    public String toStringCityAirports(String nameCity){
        String s="";
        for(City c:cities){
            if(c.getName().equals(nameCity)){
                ArrayList<Airport> a = c.getArrayAirports();
                for(Airport ar : a){
                    s+=ar.getName()+" ";
                }
            }
        }
        return s;
    }
    
    public ArrayList<Flight> searchFlights(Route route,Date data){
        ArrayList<Flight> ritorno=new ArrayList<Flight>(2);
        for(Flight ciclo:flights){
            if((ciclo.getRoute().equals(route))&&(ciclo.getDepartureDate().equals(data)))
                    ritorno.add(ciclo);
        }
        
        return ritorno;
    }
    
   
}
