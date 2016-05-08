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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;
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
    private ArrayList<Meal> meals;
    private String nameCompany;
    private ArrayList<Reservation> reservations;
    
    public Company(String nameCompany){
        this.nameCompany=nameCompany;
        this.airplanes=new ArrayList<>();
        this.airports=new ArrayList<>();
        this.cities=new ArrayList<>();
        this.routes=new ArrayList<>();
        this.flights=new ArrayList<>();
        this.reservations=new ArrayList<>();
        this.meals=new ArrayList<>();
    }
    
    public String getName(){
        return nameCompany;
    }
    
    public void downloadAirplanes(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String code = st.nextToken();
            String name = st.nextToken();
            int nSeats = Integer.parseInt(st.nextToken());
            int nFirstClass = Integer.parseInt(st.nextToken());
            airplanes.add(new Airplane(code,name,nSeats,nFirstClass));
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
            
            String [] data = new String[3];
            String [] ora = new String[2];
            
            String depDate = st.nextToken();
            data = depDate.split("/");
            String depTime = st.nextToken();
            ora = depTime.split(":");
            
            GregorianCalendar departureDate = new GregorianCalendar (Integer.parseInt(data[2]),Integer.parseInt(data[1])-1,Integer.parseInt(data[0]), Integer.parseInt(ora[0]), Integer.parseInt(ora[1])); //i mesi vanno da 0 a 
            
            String flightTime = st.nextToken();
            
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
            flights.add(new Flight(codeFlight, airplane, route, departureDate, Integer.parseInt(flightTime), price));
        }
        in.close();
    }
    
    public String toStringFlights(){
        String s="";
        for(Flight f : flights){
            s+=f.toString()+"\n";
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
    
    public void downloadMeals(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            Meal m = new Meal(st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken()),Integer.parseInt(st.nextToken()));
            meals.add(m);
            }
        in.close();
    }
    
    public String toStringMeals(){
        String s="";
        for(Meal m: meals){
            s+=m.toString()+"\n";
        }
        return s;
    }
    
    
    
    public Flight searchFlights(String cod) {
        for(Flight f:flights){
            if(f.getCode().equals(cod)){
                return f;
            }
        }
        return null;    
    }
    
    public Meal searchMeal(String codeMeal){
        for(Meal m:meals){
            if(m.getCode().equals(codeMeal)){
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<Flight> searchFlights(Route route,GregorianCalendar data){
        ArrayList<Flight> ritorno=new ArrayList<>(2);
        for(Flight ciclo:flights){
            if((ciclo.getRoute().equals(route))&& ciclo.getDepartureDate().get(Calendar.YEAR)==data.get(Calendar.YEAR)&& ciclo.getDepartureDate().get(Calendar.MONTH)==data.get(Calendar.MONTH) && ciclo.getDepartureDate().get(Calendar.DATE)==data.get(Calendar.DATE))
            {
                ritorno.add(ciclo);
            }
        }
        return ritorno;
    }
    
    public Route searchRoute(String stringDeparture, String stringDestination){
        for(Route rou : routes){
            if(rou.getDeparture().getCity().getName().equals(stringDeparture) & rou.getDestination().getCity().getName().equals(stringDestination)){
                return rou;
            }
        }
        return null;
    }
    
    public ArrayList<Airport> linkedAirport(Airport departure){
        ArrayList<Airport> ritorno=new ArrayList<>(5);
        for(Route rotta:routes){
            if(rotta.getDeparture().equals(departure)){
                ritorno.add(rotta.getDestination());
            }
        }        
        return ritorno;
    }

    public Reservation makeReservation(Flight flight,ArrayList<Passenger> passengers, ArrayList<Integer> seatsPosition, Customer customer) {
        Reservation res=new Reservation(flight,passengers,seatsPosition, flight.getCode(), customer);
        flight.addProgressiveReservation();
        reservations.add(res);
        return res;
    }
    
    public ArrayList<Flight> calendarFlight(Route route){
        ArrayList<Flight> ritorno=new ArrayList<>(2);
        for(Flight ciclo:flights){
            if((ciclo.getRoute().equals(route)))
            {
                ritorno.add(ciclo);
            }
        }
        return ritorno;
    }
    
    public String toStringReservation(){
        String s="";
        for(Reservation r: reservations){
            s+=r.toString();
        }
        return s;
    }

    
    
    

    
   
}
