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
    private ArrayList<City> citys;
    private ArrayList<Airport> airports;
    private String nameCompany;
    
    public Company(String nameCompany){
        this.nameCompany=nameCompany;
        this.airplanes=new ArrayList<>();
        this.airports=new ArrayList<>();
        this.citys=new ArrayList<>();
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
            citys.add(new City(name));
        }
        in.close();
    }
    
    public String toStringCitys(){
        String s="";
        for(City city : citys){
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
            for(City c : citys){
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
    
    
   
}
