/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.*;
import java.util.StringTokenizer;

/**
 * La classe Company è il Controller di tutta l'applicazione. Offre quindi metodi per la gestione 
 * dell'intero sistema.
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
    private ArrayList<HoldLuggage> holdLuggages;
    private ArrayList<Insurance> insurances;
    private String nameCompany;
    private ArrayList<Reservation> reservations;
    private ArrayList<Administrator> administrators;
    /**
     * Istanzia un nuova compagnia aerea, creando tutte le liste che verranno poi riempite di tutte le informazioni necessarie.
     * @param nameCompany 
     */
    public Company(String nameCompany){
        this.nameCompany=nameCompany;
        this.airplanes=new ArrayList<>();
        this.airports=new ArrayList<>();
        this.cities=new ArrayList<>();
        this.routes=new ArrayList<>();
        this.flights=new ArrayList<>();
        this.reservations=new ArrayList<>();
        this.meals=new ArrayList<>();
        this.holdLuggages=new ArrayList<>();
        this.insurances=new ArrayList<>();
        this.administrators=new ArrayList<>();
    }
    /**
     * 
     * @return nome della compagnia
     */
    public String getName(){
        return nameCompany;
    }
    /**
     * Scarica le informazioni da file sugli aerei e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sugli aerei
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * 
     * @return rappresentazione scritta degli aerei presenti nel database 
     */
    public String toStringAirplanes(){
        String s="";
        for(Airplane airplane : airplanes){
            s+=airplane.toString();
        }
        return s;
    }
    /**
     * Scarica le informazioni da file sulle città e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sulle città
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * 
     * @return rappresentazione scritta delle città presenti nel database
     */
    public String toStringCitys(){
        String s="";
        for(City city : cities){
            s+=city.toString();
        }
        return s;
    }
    /**
     * Scarica le informazioni da file sugli aeoporti e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sulle città
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * 
     * @return rappresentazione scritta degli aeroporti
     */
    public String toStringAirports(){
        String s="";
        for(Airport airport : airports){
            s+=airport.toString();
        }
        return s;
    }
    /**
     * Scarica le informazioni da file e le inserisce in una lista.
     * 
     * @param nameFile nome del file contenente le informazioni sulle rotte
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * 
     * @return rappresentazione scritta delle rotte
     */
    public String toStringRoutes(){
        String s="";
        for(Route r : routes){
            s+=r.toString() + "\n";
        }
        return s;
    }
    /**
     * 
     * Scarica le informazioni da file sui voli e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sui voli
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException 
     */
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
    /**
     * 
     * @return rappresentazione scritta dei voli 
     */
    public String toStringFlights(){
        String s="";
        for(Flight f : flights){
            s+=f.toString()+"\n";
        }
        return s;
    }
    /**
     * 
     * @param nameCity nome della città di cui vogliamo la descrizione
     * @return rappresentazione scritta degli aeroporti presenti in una determinata città
     */
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
    /**
     * Scarica le informazioni da file sui pasti e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sui pasti
     * @throws FileNotFoundException
     * @throws IOException 
     */
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
    /**
     * 
     * @return rappresentazione scritta dei pasti
     */
    public String toStringMeals(){
        String s="";
        for(Meal m: meals){
            s+=m.toString()+"\n";
        }
        return s;
    }
    /**
     * Scarica le informazioni da file sui bagagli da stiva e le inserisce in una lista.
     * 
     * @param nameFile nome del file contentente le informazioni sui bagagli da stiva
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void downloadHoldLuggages(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            HoldLuggage h = new HoldLuggage(st.nextToken(),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
            holdLuggages.add(h);
            }
        in.close();
    }
    /**
     * 
     * @return rappresentazione scritta dei possibili "slot" per i bagagli a stiva che il passeggero può acquistare
     */
    public String toStringHoldLuggages(){
        String s="";
        for(HoldLuggage h: holdLuggages){
            s+=h.toString()+"\n";
        }
        return s;
    }
    /**
     * Scarica le informazioni da file sulle possibili assicurazioni che il passeggero può acquistare.
     * 
     * @param nameFile nome del file contenente le informazioni sulle assicurazioni
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void downloadInsurances(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            Insurance ins = new Insurance(st.nextToken(),st.nextToken(),Double.parseDouble(st.nextToken()));
            insurances.add(ins);
            }
        in.close();
    }
    /**
     * 
     * @return rapprensentazione scritta delle assicurazioni che il passeggero può acquistare
     */
    public String toStringInsurances(){
        String s="";
        for(Insurance ins: insurances){
            s+=ins.toString()+"\n";
        }
        return s;
    }

    /**
     * Scarica le informazioni da file sugli amministratori dell'applicazione della compagnia.
     * 
     * @param nameFile nome del file contenente le informazioni sugli amministratori
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void downloadAdministrators(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            StringTokenizer st = new StringTokenizer(line,"\t");
            administrators.add(new Administrator(st.nextToken(),st.nextToken()));
        }
        in.close();
    }
    /**
     * 
     * @return rappresentazione scritta degli amministratori presenti nel database
     */
    public String toStringAdministrator(){
        String s="";
        for(Administrator a : administrators){
            s+=a.toString()+"\n";
        }
        return s;
    }
    /**
     * Ricerca di un pasto a partire dal suo codice.
     * 
     * @param codeMeal codice del pasto
     * @return pasto se presente nel databse o <code>null</code> se non è presente
     */
    public Meal searchMeal(String codeMeal){
        for(Meal m:meals){
            if(m.getCode().equals(codeMeal)){
                return m;
            }
        }
        return null;
    }    
    /**
     * Ricerca di uno "slot" per il bagaglio da stiva a partire dal suo codice.
     * 
     * @param codeHoldLuggage codice dello "slot" del bagaglio da stiva
     * @return tipo di bagaglio se presente nel database o <code>null<code> se non è presente
     */
    public HoldLuggage searchHoldLuggage(String codeHoldLuggage){
        for(HoldLuggage h:holdLuggages){
            if(h.getCode().equals(codeHoldLuggage)){
                return h;
            }
        }
        return null;
    } 
    /**
     * Ricerca di un'assicurazione a partire dal suo codice.
     * 
     * @param codeInsurance codice dell'assicurazione
     * @return assicurazione se presente nel database o <code>null</code> se non è presente
     */
    public Insurance searchInsurance(String codeInsurance){
        for(Insurance ins:insurances){
            if(ins.getCode().equals(codeInsurance)){
                return ins;
            }
        }
        return null;
    }    
    /**
     * Ricerca di un volo a partire dal suo codice.
     * 
     * @param cod codice del volo
     * @return volo se presente nel database o <code>null</code> se non è presente
     */
    public Flight searchFlights(String cod) {
        for(Flight f:flights){
            if(f.getCode().equals(cod)){
                return f;
            }
        }
        return null;    
    }
    
    /**
     * Ricerca di un volo presente in una determinata data e con una determinata.
     * 
     * @param route rotta del volo
     * @param data data del volo
     * @return lista dei voli compatibili con le scelte effettuate.
     */
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
    /**
     * Ricerca di una rotta.
     * @param stringDeparture luogo di partenza
     * @param stringDestination luogo di arrivo
     * @return rotta se presente nel database o <code>null</code> se non è presente
     */
    public Route searchRoute(String stringDeparture, String stringDestination){
        for(Route rou : routes){
            if(rou.getDeparture().getCity().getName().equals(stringDeparture) & rou.getDestination().getCity().getName().equals(stringDestination)){
                return rou;
            }
        }
        return null;
    }
    /**
     * Ricerca di una prenotazione a partire dal suo codice identificativo.
     * 
     * @param reservation codice della prenotazione
     * @return prenotazione se presente nel database o <code>null</code> se non è presente
     */
    public Reservation searchReservation(String reservation){
        for(Reservation r:reservations){
            if(r.getPrenotationCode().equals(reservation)){
                return r;
            }
        }
        return null;
    }
    /**
     * Ricerca delle coppie di aeroporti che hanno un collegamente (rotta) a partire da un aeroporto di partenza.
     * @param departure aeroporto di partenza della rotta
     * @return list di aeroporti che hanno un collegamento con l'aeroporto di partenza
     */
    public ArrayList<Airport> linkedAirport(Airport departure){
        ArrayList<Airport> airports=new ArrayList<>(5);
        for(Route rotta:routes){
            if(rotta.getDeparture().equals(departure)){
                airports.add(rotta.getDestination());
            }
        }        
        return airports;
    }
    /**
     * Crea una nuova prenotazione e la aggiunge nel database.
     * 
     * @param flight volo su cui effettuare la prenotazione
     * @param passengers passeggeri presenti nella prenotazione
     * @param seatsPosition posizione dei posti a sedere da prenotare
     * @param customer cliente che effettua la prenotazione
     * @return prenotazione del volo se andata a buon fine, altrimenti <code>null</code>
     * 
     */
    public Reservation makeReservation(Flight flight,ArrayList<Passenger> passengers, ArrayList<Integer> seatsPosition, Customer customer) {
        Flight f = flight;
        boolean k=false;
        //VERIFICA PRENOTAZIONI IN CONTEMPORANEA
        for(Integer i : seatsPosition){
            if(flight.seatIsOccuped(i)){
                k=true;
                break;
            }                
        }
        if(!k){
            Reservation res=new Reservation(flight,passengers,seatsPosition, flight.getCode(), customer);
            flight.addProgressiveReservation();
            reservations.add(res);
            return res;
        }
        else{
           return null; 
        }        
    }
    /**
     * Stampa le prenotazioni presenti nel database nel file "Reservations.txt".
     * 
     * @throws IOException 
     */
    public void printFileReservation() throws IOException{
        String s="";
        PrintWriter f = new PrintWriter(new FileWriter("file/Reservations.txt"));
        for(Reservation r : reservations){
            s+=r.getFlight().getCode()+"\t"+r.getCustomer().getEmail()+"\t"+r.getCustomer().getNumber()+"\n";
            ArrayList<Passenger> ar = r.getPassenger();  
            s+=ar.size()+"\n";
            for(Passenger p:ar){
                s+=p.toStringPrintFile()+"\n";
            }            
            f.print(s);
        }       
        f.close();
    }
    /**
     * Importa le prenotazioni da un file.
     * 
     * @param nameFile nome del file con le varie prenotazioni
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void importFileReservation(String nameFile) throws FileNotFoundException, IOException{
        BufferedReader in = new BufferedReader(new FileReader(nameFile));
        String line;
        while((line=in.readLine())!=null){
            //1 RIGA
            StringTokenizer st = new StringTokenizer(line,"\t");
            Flight f = this.searchFlights(st.nextToken());
            Customer c = new Customer(st.nextToken(),st.nextToken());
            //2 RIGA
            int k=Integer.parseInt(line=in.readLine());
            //3 E + RIGHE PASSEGGERI                      
            ArrayList<Passenger> passengers = new ArrayList<>();
            ArrayList<Integer> seats = new ArrayList<>();
            for(int i=0;i<k;i++){
                line=in.readLine();  
                StringTokenizer st2 = new StringTokenizer(line,"\t");
                Passenger p=new Passenger(st2.nextToken(),st2.nextToken(),st2.nextToken());
                passengers.add(p);
                //riga supplementi
                line=in.readLine();
                StringTokenizer st3 = new StringTokenizer(line,"\t");                
                while(st3.hasMoreTokens()){
                    String element = st3.nextToken();
                    switch(element.charAt(0)){
                        case 'M':
                            p.addMeal(this.searchMeal(element));
                            break;
                        case 'H':
                            p.addHoldLuggage(this.searchHoldLuggage(element));
                            break;
                        case 'I':
                            p.addInsurance(this.searchInsurance(element));
                            break;
                    }
                }
                //4 RIGA POSTO
                seats.add(Integer.parseInt(in.readLine()));                
            }
            this.makeReservation(f, passengers, seats, c);
        }
        in.close();
    }
    /**
     * Calendario di volo di una determinata rotta.
     * 
     * @param route rotta di cui vogliamo sapere i voli
     * @return lista di voli che hanno una determinata rotta
     */
    public ArrayList<Flight> calendarFlight(Route route){
        ArrayList<Flight> flight=new ArrayList<>(2);
        for(Flight f:flights){
            if((f.getRoute().equals(route)))
            {
                flight.add(f);
            }
        }
        return flight;
    }
    /**
     * 
     * @return rappresentazione scritta delle prenotazioni presenti nel database
     */
    public String toStringReservation(){
        String s="";
        for(Reservation r: reservations){
            s+=r.toString()+"\n";
        }
        return s;
    }
    /**
     * Ricerca di un amministratore.
     * 
     * @param username username dell'amministratore
     * @param password password dell'amministratore
     * @return amministratore richiesto se presente nel database o <code>null</code> se non è presente
     */
    public Administrator searchAdministrator(String username, String password){
        for(Administrator a:administrators){
            if(a.getUser().equals(username)&a.getPassword().equals(password)){
                return a;
            }
        }
        return null;
    }
     
}
