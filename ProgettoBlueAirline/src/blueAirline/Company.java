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
import java.util.ArrayList;
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
    private String nameCompany;
    
    public Company(String nameCompany){
        this.nameCompany=nameCompany;
        this.airplanes=new ArrayList<>();
        this.airports=new ArrayList<>();
        this.cities=new ArrayList<>();
        this.routes=new ArrayList<>();
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
            StringTokenizer st = new StringTokenizer(line);
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
        //routes.add(new Route(new Airport(("Ciao"),new City("ma")),new Airport(("Hello"),new City("hdh"))));
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            String a1 = st.nextToken();
            String a2 = st.nextToken();
            //System.out.println("bbb"+a1.toString()+" "+a2.toString());
            Airport destination = null;
            Airport departure = null;
            for(Airport a : airports){
                Airport air = new Airport(a1,a.getCity());
                Airport air2 = new Airport(a2,a.getCity());
                if(a.equals(air)){
                    departure = air;
                }
                if(a.equals(air2)){
                    destination = air2;
                }
            }
            routes.add(new Route(departure,destination));
            //System.out.println("aad"+departure.toString()+" "+destination.toString());
        }
        in.close();
    }
    
    public String toStringRoutes(){
        String s="";
        for(Route r : routes){
            s+=r.toString();
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
    
   
}
