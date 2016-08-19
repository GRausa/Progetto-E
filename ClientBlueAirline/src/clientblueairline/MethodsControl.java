/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oggetti.Flight;
import oggetti.HoldLuggage;
import oggetti.Insurance;
import oggetti.Meal;
import oggetti.Reservation;
import oggetti.Route;
import oggetti.TicketPassenger;

/**
 *
 * @author riccardo
 */
public class MethodsControl {
    
    public static ArrayList<String> lettura(ArrayList<String> n){        
        Scanner input = new Scanner(System.in);
        ArrayList<String> ritorno=new ArrayList<>(n.size());
        for(int i=0;i<n.size();i++){
            String part = null;
             
            System.out.println(n.get(i));
            ritorno.add(i, input.nextLine());            
        }        
        return ritorno;
    }
    
    public static String toStringMenu(){
        Scanner input = new Scanner(System.in);
        String s;
        s   =   "\nCOMANDI DISPONIBILI\n"
            +   "\n>>> HI -> Test server\n"
            +   "\nAREA RICERCA:\n\n>>> CERCA_VOLO -> Ricerca il volo tra 2 città in una precisa data ( + mappa posti )\n"
            +   ">>> CERCA_VOLO_CODICE -> Ricerca il volo in base al codice\n"
            +   ">>> CERCA_VOLO_AEROPORTI -> Ricerca i voli disponibili tra 2 aeroporti\n"
            +   ">>> VERIFICA_TRATTA -> Controlla se esiste una tratta tra 2 città\n"
            +   ">>> CITTA_DISPONIBILI -> Controlla in quali città puoi viaggiare\n"
            +   "\nAREA CLIENTE:\n\n>>> PRENOTA -> Effettua una prenotazione di un posto a sedere\n"
            +   ">>> CHECK_IN -> Effettua il check-in del tuo biglietto aereo\n"
            +   ">>> CERCA_TICKETPASSENGER -> Ricerca il tuo biglietto aereo\n"
            +   ">>> CERCA_PRENOTAZIONE -> Ricerca la tua prenotazione\n"
            +   ">>> EXIT";
        System.out.println(s);
        String s1 = input.nextLine().toUpperCase();
        return s1;
    }
    
    public static void hi(ClientBlueAirline client){
        try {
            client.hello();
        } catch (IOException ex) {
            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void searchFlight(ClientBlueAirline client){
        Scanner input = new Scanner(System.in);
        ArrayList<String> inputtxt=MethodsControl.lettura(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute = new Route(inputtxt.get(0), inputtxt.get(1));
        System.out.println("Inserisci data di partenza AAAA-MM-GG");
        String data = input.nextLine();
        int day,month,year;
        String[] vetDate = data.split("-");
        if (vetDate.length == 3) {
            year = Integer.parseInt(vetDate[0]);
            month = Integer.parseInt(vetDate[1]) - 1;
            day = Integer.parseInt(vetDate[2]);            
            GregorianCalendar date = new GregorianCalendar(year, month, day);
            Flight tmpflight = new Flight(tmproute, date);
            Flight[] volit = null;
            {
                try {
                    volit = client.searchFlights(tmpflight);
                    if(volit.length==0){
                        System.out.println("Nessun volo trovato.");
                    }
                    for (Flight v : volit) {
                        System.out.println(v);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (volit.length == 1) {
                System.out.println("Mappa posti: :");
                System.out.println(volit[0].printAllSeats());
            }
        } else {
            System.out.println("Hai inserito la data sbagliata");            
        }
    }
    
    public static void searchRoute(ClientBlueAirline client){
        ArrayList<String> inputtxt1=MethodsControl.lettura(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute1 = new Route(inputtxt1.get(0), inputtxt1.get(1));
        Route[] rotte = null;
        {
            try {
                rotte = client.checkRoute(tmproute1);
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rotte.length > 0) {
           for (Route r : rotte) {
                System.out.println(r);
            }
        } else {
            System.out.println("Non esiste tratta per queste città");
        }
    }
    
    
    
    
    /*METODI PER PRENOTAZIONE*/
    
    public static Flight searchFlight(ClientBlueAirline client, String cod){
        Flight flight = new Flight(cod);
         {
            try {
                flight = client.searchFlight(flight);
                if (flight == null) {
                    System.out.println("Volo non trovato");
                    return null;
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(flight.toString());
        System.out.println("Posti disponibili: " + flight.getSeatFree() + "/" + flight.getSeats().size());
        System.out.println(flight.printAllSeats());
        if (flight.getSeatFree() == 0) {
            System.out.println("Posti esauriti.");            
        }
        return flight;
    }
    
    public static void toStringAggiunte(Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
        System.out.println("Scelte in aggiunta:\nPASTI: ");
        for (Meal m : meals) {
            System.out.println(m.toString());
        }
        System.out.println("BAGAGLI: ");
        for (HoldLuggage hl : holdLuggages) {
            System.out.println(hl.toString());
        }
        System.out.println("ASSICURAZIONI: ");
        for (Insurance in : insurances) {
            System.out.println(in.toString());
        }        
    }
    
    public static void addMealPassenger(TicketPassenger p, Meal[] meals, String v){
        for (Meal m : meals) {
            if (m.getCode().equals(v)) {
                p.addMeals(m);
                break;
            }
        }
    }
    
    public static void addHoldLuggagePassenger(TicketPassenger p, HoldLuggage[] holdLuggages, String v){
        for (HoldLuggage hl : holdLuggages) {
            if (hl.getCode().equals(v)) {
                p.addHoldLuggage(hl);
                break;
            }
        }
    }
    
    public static void addInsurancePassenger(TicketPassenger p, Insurance[] insurances, String v){
        for (Insurance in : insurances) {
            if (in.getCode().equals(v)) {
                p.addInsurance(in);
                break;
            }
        }
    }
    
    public static TicketPassenger insertAggiuntePassenger(String[] vetsplit, int seat, int classe, String codeFlight, double price, Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
        TicketPassenger p = new TicketPassenger(vetsplit[0], vetsplit[1], vetsplit[2], seat, classe, codeFlight, price);
        for (int j = 4; j < vetsplit.length; j++) {
            String v = vetsplit[j];
            switch (vetsplit[j].charAt(0)) {
                case 'M':
                    MethodsControl.addMealPassenger(p, meals, v);
                    break;
                case 'H':
                    MethodsControl.addHoldLuggagePassenger(p, holdLuggages, v);
                    break;
                case 'I':
                    MethodsControl.addHoldLuggagePassenger(p, holdLuggages, v);
                    break;
                default:
                    break;
            }
        }
        return p;
    }
    
    public static ArrayList<TicketPassenger> insertPassengers(int num, Flight flight,Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
        Scanner input = new Scanner(System.in);
        ArrayList<TicketPassenger> passengers = new ArrayList<TicketPassenger>();
        for (int k = 0; k < num; k++) {
            boolean c = false;
            do {
                System.out.println("INSERISCI PASSEGGERO (ID- NOME - COGNOME - NPOSTO - AGGIUNTE)");
                String s = input.nextLine();
                String[] vetsplit = s.split("\t");
                if (vetsplit.length > 3) {
                    int seat = Integer.parseInt(vetsplit[3]);
                    if (flight.getSeats().get(seat).getPassenger() == null) {
                        flight.getSeats().get(seat).setPassenger(vetsplit[0]);
                        int classe = flight.getSeats().get(seat).getClasse();
                        TicketPassenger p = MethodsControl.insertAggiuntePassenger(vetsplit, seat, classe, flight.getCode(), flight.getPrezzo(), meals, insurances, holdLuggages);
                        c = true;
                        passengers.add(p);
                    } else {
                        System.out.println("Posto non disponibile.");
                    }
                } else {
                    System.out.println("Errore inserimento");
                }
            } while (!c);
        }
        return passengers;
    }
    
    public static Reservation makeReservation(ClientBlueAirline client, String cod, Flight flight){
        Scanner input = new Scanner(System.in);
        int num;
        do {
            System.out.println("Inserisci numero passaggeri");
            num = Integer.parseInt(input.nextLine());
            if (num > flight.getSeatFree()) {
                System.out.println("Il numero dei passeggeri inseriti supera la disponibilità di posti.");
            }
        } while (num > flight.getSeatFree());
        
        Meal[] meals = null;
        Insurance[] insurances = null;
        HoldLuggage[] holdLuggages = null;
        {
            try {
                meals = client.getAllMeals();
                insurances = client.getAllInsurances();
                holdLuggages = client.getAllHoldLuggages();
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        MethodsControl.toStringAggiunte(meals,insurances,holdLuggages);
        ArrayList<TicketPassenger> passengers = MethodsControl.insertPassengers(num,flight,meals,insurances,holdLuggages); 
        System.out.println("INSERISCI NUMERO");
        String numero = input.nextLine();
        System.out.println("INSERISCI MAIL");
        String mail = input.nextLine();
        Reservation res = new Reservation(cod, numero, mail, passengers);
        return res;
    }
    
    public static void controlReservation(ClientBlueAirline client, Reservation res, Flight flight){
        Scanner input = new Scanner(System.in);
        try {
            res = client.makeReservation(res);
            flight = client.searchFlight(flight); //aggiorno il flight dopo la prenotazione
            for (TicketPassenger tp : res.getPassengers()) { //controllo assegnamento posti
                if (tp.getNseat() == -1) {
                    System.out.println("Passeggero: " + tp.getName() + " " + tp.getSurname() + " (" + tp.getID() + ") non inserito, il posto è stato occupato.\nPosti disponibili:");
                    System.out.println(flight.printAllSeats());
                    boolean c = false;
                    do {
                        System.out.println("Inserisci il nuovo posto");
                        int set = input.nextInt();
                        if (flight.getSeats().get(set - 1).getPassenger() == null) {
                            tp.setNSeat(set);
                            tp = client.editSeatTicketPassenger(tp);
                            if (tp.getNseat() == set) {
                                System.out.println("Modifica effettuata.");
                                c = true;
                            } else {
                                System.out.println("Posto non assegnato.");
                            }
                        } else {
                            System.out.println("Errore inserimento.");
                        }
                    } while (!c);
                }
            }
            System.out.println("PRENOTAZIONE EFFETTUATA\nCodice Prenotazione: " + res.getCode());
            System.out.println("Riepilogo:");
            res = client.getReservation(res);
            System.out.println(flight.toString());
            System.out.println(res.printReservation());
            System.out.println(res.printPassengers());
        } catch (IOException ex) {
            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //prenotazione
    public static void makeReservation(ClientBlueAirline client){
        String cod=MethodsControl.lettura(new ArrayList<>(asList("Inserisci codice Volo"))).get(0);        
        Flight flight = MethodsControl.searchFlight(client, cod);
        if(flight!=null){             
            Reservation res = MethodsControl.makeReservation(client, cod, flight);          
            MethodsControl.controlReservation(client, res, flight);        
        }
    }
    
    
    /*FINE PRENOTAZIONE*/
    
    /*MODIFICA BIGLIETTO*/
    
    /*
    public static void addOtherMealPassenger(TicketPassenger p, Meal[] meals, String v){
        ArrayList<Meal> mealsPassenger = p.getMeals();
        ArrayList<Meal> otherMeals = new ArrayList<Meal>();
        boolean c = false;
        for(Meal m : meals){ //analizzo quelle che voglio aggiungre
            c=false;
            for(Meal m1 : mealsPassenger){ //analizzo se non è già presente
                if(m.getCode().equals(m1.getCode())){
                    c=true;
                    break;
                }
            }
            if(!c){ //non ho trovato corrispondenza -> ok aggiungo quella che manca
                otherMeals.add(m);
            }
        }
        p.addOtherMeals(otherMeals);        
    }
    
    public static void addOtherHoldLuggagesPassenger(TicketPassenger p, HoldLuggage[] holdLuggages, String v){
        ArrayList<HoldLuggage> holdLuggagesPassenger = p.getHoldLuggages();
        ArrayList<HoldLuggage> otherHoldLuggages = new ArrayList<HoldLuggage>();
        boolean c = false;
        for(HoldLuggage h : holdLuggages){ //analizzo quelle che voglio aggiungre
            c=false;
            for(HoldLuggage h1 : holdLuggagesPassenger){ //analizzo se non è già presente
                if(h.getCode().equals(h1.getCode()) & v.equals(h.getCode())){
                    c=true;
                    break;
                }
            }
            if(!c){ //non ho trovato corrispondenza -> ok aggiungo quella che manca
                otherHoldLuggages.add(h);
            }
        }
        p.addOtherHoldLuggages(otherHoldLuggages);        
    }
    
    
    public static void editTicketPassenger(ClientBlueAirline client){
        Scanner input = new Scanner(System.in);
        TicketPassenger tp = MethodsControl.searchTicketPassenger(client);
        if(tp!=null){
            Flight flight = MethodsControl.searchFlight(client, tp.getCodeFlight());
            Meal[] meals = client.getAllMeals();
            Insurance[] insurances = client.getAllInsurances();
            HoldLuggage[] holdLuggages = client.getAllHoldLuggages();
            if(MethodsControl.isCheckIn(client, tp)){
                boolean c = false;
                do{
                    System.out.println("Modifica il posto e inserisci le nuove scelte (saranno aggiunte alle vecchie già acquistate)");
                    System.out.println("INSERISCI MODIFICHE (NPOSTO - AGGIUNTE)");
                    String s = input.nextLine();
                    String[] vetsplit = s.split("\t");
                    if (vetsplit.length > 1) {
                        int seat = Integer.parseInt(vetsplit[0]);
                        if (flight.getSeats().get(seat).getPassenger() == null) {
                            for (int j = 1; j < vetsplit.length; j++) {
                                String v = vetsplit[j];
                                switch (vetsplit[j].charAt(0)) {
                                    case 'M':
                                        MethodsControl.addOtherMealPassenger(tp, meals, v);
                                        break;
                                    case 'H':
                                        MethodsControl.addOtherHoldLuggagesPassenger(tp, meals, v);
                                        //MethodsControl.addHoldLuggagePassenger(p, holdLuggages, v);
                                        break;
                                    case 'I':
                                        //MethodsControl.addHoldLuggagePassenger(p, holdLuggages, v);
                                        break;
                                    default:
                                        break;
                                }
                            }                            
                            c = true;
                        } else {
                            System.out.println("Posto non disponibile.");
                        }
                    } 
                    else {
                        System.out.println("Errore inserimento");
                    }
                } while (!c);
                
            } 
            else{
                System.out.println("Hai effettuato il check-in, non puoi modificare il biglietto.");
            }
        }        
    }
    */
    public static void searchFlightAirport(ClientBlueAirline client){
        ArrayList<String> inputtxt2=MethodsControl.lettura(new ArrayList<>(asList("Inserisci Aeroporto partenza", "Inserisci Aeroporto destinazione")));
        Route tmproute2 = new Route();
        tmproute2.setDeparutreAirport(inputtxt2.get(0));
        tmproute2.setDestinationAirport(inputtxt2.get(1));
        {
            try {
                Flight[] calendario = client.calendar(tmproute2);
                if (calendario.length == 0) {
                    System.out.println("Nessun volo trovato");
                }
                for (Flight a : calendario) {
                    System.out.println(a);
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean isCheckIn(ClientBlueAirline client, TicketPassenger tp) throws IOException{
        if(client.isCheckIn(tp)){
            return true;                    
        }
        else{
            return false;                    
        }            
    }
    
    public static void checkIn(ClientBlueAirline client){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice ticket per effettuare il check-in:");
        String codeTicket = input.nextLine();
        TicketPassenger tp = new TicketPassenger(codeTicket);
        try {
            if(MethodsControl.isCheckIn(client, tp)){
                System.out.println("Il check-in è stato già effettuato.");
            }
            else{
                tp = client.checkIn(tp);
                System.out.println("Check-in effettuato.\n"+tp.printTicketPassenger());                
            }
        } catch (IOException ex) {
            Logger.getLogger(MethodsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TicketPassenger searchTicketPassenger(ClientBlueAirline client){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice del biglietto: ");
        String codeT = input.nextLine();
        TicketPassenger tp1 = new TicketPassenger(codeT);
        Flight f = null;
        {
            try {
                tp1 = client.getTicketPassenger(tp1);   
                if(tp1!=null)
                    f = client.searchFlight(new Flight(tp1.getCodeFlight()));
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(tp1!=null){
            System.out.println(f.toString());
            System.out.println(tp1.printTicketPassenger());
            return tp1;
        }
        else{
            System.out.println("Biglietto non trovato");
            return null;
        }
    }
    
    public static void searchReservation(ClientBlueAirline client){
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice della prenotazione: ");
        int codeReservation = input.nextInt();
        Reservation r = new Reservation(codeReservation);
        Flight f1 = null;
        {
            try {
                r = client.getReservation(r);   
                if(r!=null)
                    f1 = client.searchFlight(new Flight(r.getCodeFlight()));
            } catch (IOException ex) {
                Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(r!=null){
            System.out.println(f1.toString());
            System.out.println(r.printReservation());
            System.out.println(r.printPassengers());                        
        }
        else{
            System.out.println("Prenotazione non trovata.");
        }  
    }

    public static void searchCitys(ClientBlueAirline client) {
        String[] citta=null;
        try{
            citta = client.listOfCity();
            System.out.println("Le città disponibili sono:");
            for (String a :citta)
            {
                System.out.println(a);
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    static void searchFlightCode(ClientBlueAirline client) {
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci il codice del volo: ");
        MethodsControl.searchFlight(client, input.nextLine());
    }
     
}
