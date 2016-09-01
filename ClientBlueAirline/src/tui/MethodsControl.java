/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui;

import controller.FacadeControllerClient;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Ticket;



public class MethodsControl {
    
    public static ArrayList<String> scannerInput(ArrayList<String> n){        
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
            +   ">>> CALENDARIO_AEROPORTI -> Ricerca tutti i voli disponibili tra 2 aeroporti\n"
            +   ">>> VERIFICA_TRATTA -> Controlla se esiste una tratta tra 2 città (lasciando uno o entrambi i campi vuoti ricerca tutte le tratte disponibili)\n"
            +   ">>> CITTA_DISPONIBILI -> Controlla in quali città puoi viaggiare\n"
            +   "\nAREA CLIENTE:\n\n>>> PRENOTA -> Effettua una prenotazione di un posto a sedere\n"
            +   ">>> CHECK_IN -> Effettua il check-in del tuo biglietto aereo\n"
            +   ">>> CERCA_TICKETPASSENGER -> Ricerca il tuo biglietto aereo\n"
            +   ">>> CERCA_PRENOTAZIONE -> Ricerca la tua prenotazione\n"
            +   ">>> MODIFICA_BIGLIETTO -> Modifica il tuo posto a sedere o inserisci ulteriori aggiunte\n"
            +   ">>> EXIT";
        System.out.println(s);
        String s1 = input.nextLine().toUpperCase();
        return s1;
    }
    
    public static void hi(FacadeControllerClient client){
        try {
            client.hello();
        } catch (IOException ex) {
            Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void searchFlight(FacadeControllerClient client){
        //Scanner input = new Scanner(System.in);
        ArrayList<String> inputtxt=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute = new Route(inputtxt.get(0), inputtxt.get(1));
        inputtxt=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci data di partenza AAAA-MM-GG")));
        String data = inputtxt.get(0);
        int day,month,year;
        String[] vetDate = data.split("-");
        if (vetDate.length == 3) {
            GregorianCalendar date = new GregorianCalendar(Integer.parseInt(vetDate[0]), Integer.parseInt(vetDate[1])-1, Integer.parseInt(vetDate[2]));
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
                    Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void searchRoute(FacadeControllerClient client){
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute1 = new Route(input.get(0), input.get(1));
        Route[] rotte = null;
        {
            try {
                rotte = client.searchRoutes(tmproute1);
            } catch (IOException ex) {
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (rotte.length > 0) {
           for (Route r : rotte) {
                System.out.println(r.printRoute());
            }
        } else {
            System.out.println("Non esiste tratta per queste città");
        }
    }
    
    
    
    
    /*METODI PER PRENOTAZIONE*/
    
    public static Flight searchFlight(FacadeControllerClient client, String cod){
        Flight flight = new Flight(cod);        
        {
            try {
                flight = client.searchFlight(flight); 
                if (flight == null) { 
                    System.out.println("Volo non trovato o risulta antecedente alla data odierna");
                    return null;
                }
            } catch (IOException ex) {
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(flight.toString());
        System.out.println("Posti disponibili: " + flight.getSeatFree() + "/" + flight.getSeats().size());
        System.out.println(flight.printAllSeats());
        return flight;
    }
    
    public static void toStringSupplements(Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
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
    
    public static void addMealTicket(Ticket p, Meal[] meals, String v){
        for (Meal m : meals) {
            if (m.getCode().equals(v)) {
                p.addMeals(m);
                break;
            }
        }
    }
    
    public static void addHoldLuggageTicket(Ticket p, HoldLuggage[] holdLuggages, String v){
        for (HoldLuggage hl : holdLuggages) {
            if (hl.getCode().equals(v)) {
                p.addHoldLuggage(hl);
                break;
            }
        }
    }
    
    public static void addInsuranceTicket(Ticket p, Insurance[] insurances, String v){
        for (Insurance in : insurances) {
            if (in.getCode().equals(v)) {
                p.addInsurance(in);
                break;
            }
        }
    }
    
    public static Ticket insertTicketSupplements(String[] vetsplit, int seat, int classe, String codeFlight, double price, Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
        Ticket p = new Ticket(vetsplit[0], vetsplit[1], vetsplit[2], seat, classe, codeFlight, price);
        for (int j = 4; j < vetsplit.length; j++) {
            String v = vetsplit[j];
            switch (vetsplit[j].charAt(0)) {
                case 'M':
                    MethodsControl.addMealTicket(p, meals, v);
                    break;
                case 'H':
                    MethodsControl.addHoldLuggageTicket(p, holdLuggages, v);
                    break;
                case 'I':
                    MethodsControl.addInsuranceTicket(p, insurances, v);
                    break;
                default:
                    break;
            }
        }
        return p;
    }
    
    public static ArrayList<Ticket> insertTicket(int num, Flight flight,Meal[] meals,Insurance[] insurances,HoldLuggage[] holdLuggages){
        //Scanner input = new Scanner(System.in);
        ArrayList<Ticket> passengers = new ArrayList<Ticket>();
        for (int k = 0; k < num; k++) {
            boolean c = false;
            do {
                ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("INSERISCI PASSEGGERO (ID- NOME - COGNOME - NPOSTO - AGGIUNTE)")));
                String s = input.get(0);
                String[] vetsplit = s.split("\t");
                if (vetsplit.length > 3) {
                    int seat = Integer.parseInt(vetsplit[3]);
                    if (flight.getSeats().get(seat-1).getTicket() == null) {
                        flight.getSeats().get(seat-1).setTicket(vetsplit[0]);
                        int classe = flight.getSeats().get(seat-1).getClasse();
                        Ticket p = MethodsControl.insertTicketSupplements(vetsplit, seat, classe, flight.getCode(), flight.getPrezzo(), meals, insurances, holdLuggages);
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
    
    public static Reservation makeReservation(FacadeControllerClient client, String cod, Flight flight) throws IOException{
        //Scanner input = new Scanner(System.in);
        int num;
        do {
            ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci numero passeggeri")));
            num = Integer.parseInt(input.get(0));
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
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        MethodsControl.toStringSupplements(meals,insurances,holdLuggages);
        ArrayList<Ticket> passengers = MethodsControl.insertTicket(num,flight,meals,insurances,holdLuggages); 
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("INSERISCI NUMERO","INSERISCI EMAIL")));
        String numero = input.get(0);
        String mail = input.get(1);
        Reservation res = new Reservation(cod, numero, mail, passengers);
        res = client.makeReservation(res);
        return res;
    }
    
    public static void checkReservation(FacadeControllerClient client, Reservation res, Flight flight){
        //Scanner input = new Scanner(System.in);
        try {            
            flight = client.searchFlight(flight); //aggiorno il flight dopo la prenotazione
            for (Ticket tp : res.getPassengers()) { //controllo assegnamento posti
                if (tp.getNseat() == -1) {
                    System.out.println("Passeggero: " + tp.getName() + " " + tp.getSurname() + " (" + tp.getID() + ") non inserito, il posto è stato occupato.\nPosti disponibili:");
                    System.out.println(flight.printAllSeats());
                    boolean c = false;
                    do {
                        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci il nuovo posto")));
                        int set = Integer.parseInt(input.get(0));
                        if (flight.getSeats().get(set - 1).getTicket() == null ) {
                            tp.setNSeat(set);
                            tp = client.editSeatTicket(tp);
                            if (tp.getNseat() == set) {
                                System.out.println("Modifica effettuata.");
                                c = true;
                            } else {
                                System.out.println("Posto non assegnato.");
                            }
                        } else {
                            System.out.println("Errore inserimento");
                        }
                    } while (!c);
                }
            }
            System.out.println("PRENOTAZIONE EFFETTUATA\nCodice Prenotazione: " + res.getCode());
            System.out.println("Riepilogo:");
            res = client.getReservation(res);
            System.out.println(flight.toString());
            System.out.println(res.printReservation());
            System.out.println(res.printTickets());
        } catch (IOException ex) {
            Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //prenotazione
    public static void makeReservation(FacadeControllerClient client){
        String cod=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci codice Volo"))).get(0);        
        Flight flight = MethodsControl.searchFlight(client, cod);
        if(flight!=null){     
            if(flight.getSeatFree()>0){
                try {
                    Reservation res = MethodsControl.makeReservation(client, cod, flight);        
                    MethodsControl.checkReservation(client, res, flight);
                } catch (IOException ex) {
                    Logger.getLogger(MethodsControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                System.out.println("Posti non disponibili");
            }
                        
        }
    }
    
    
    /*FINE PRENOTAZIONE*/
    
    /*MODIFICA BIGLIETTO*/
    
    public static void editTicket(FacadeControllerClient client, Ticket tp, Flight flight, Meal[] meals, Insurance[] insurances, HoldLuggage[] holdLuggages) throws IOException{
        //Scanner input = new Scanner(System.in);
        boolean c = false;
        do {
            System.out.println("Modifica il posto e inserisci le nuove scelte (saranno aggiunte alle vecchie già acquistate)");
            ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("INSERISCI MODIFICHE (NPOSTO - AGGIUNTE)")));
            String s=tp.getID()+"\t"+tp.getName()+"\t"+tp.getSurname()+"\t";
            s += input.get(0);
            String[] vetsplit = s.split("\t");
            if (vetsplit.length > 3) {
                int seat = Integer.parseInt(vetsplit[3]);
                if (flight.getSeats().get(seat-1).getTicket() == null) { //OSS -> -1
                    flight.getSeats().get(seat-1).setTicket(tp.getCode()); 
                    int classe = flight.getSeats().get(seat-1).getClasse();
                    Ticket p2 = MethodsControl.insertTicketSupplements(vetsplit, seat, classe, flight.getCode(), flight.getPrezzo(), meals, insurances, holdLuggages);
                    p2.setCode(tp.getCode());
                    if(client.editTicket(p2).getNseat()==-1){
                        System.out.println("Il posto è stato occupato. Riprova.");
                    }
                    else{
                        Ticket tp2 = client.getTicket(p2);
                        System.out.println(tp2.printTicketPassenger());
                        System.out.println("Aggiunta totale di: "+(tp2.getTotalPrice()-tp.getTotalPrice())+" euro");
                        c = true;
                    }                                
                } else {
                    System.out.println("Posto non disponibile.");
                }
            } else {
                System.out.println("Errore inserimento");
            }
        } while (!c);
    }
    
    public static void editTicket(FacadeControllerClient client, Ticket tp){
        System.out.println("\nAREA MODIFICA:");
        Flight flight = null;
        Meal[] meals = null;
        Insurance[] insurances = null;
        HoldLuggage[] holdLuggages = null;
        try {
            meals = client.getAllMeals();
            insurances = client.getAllInsurances();
            holdLuggages = client.getAllHoldLuggages();
        } catch (IOException ex) {
            Logger.getLogger(MethodsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if(!MethodsControl.isCheckIn(client, tp)){
                flight = MethodsControl.searchFlight(client, tp.getCodeFlight());
                MethodsControl.editTicket(client, tp, flight, meals, insurances, holdLuggages);
            }
            else{
                System.out.println("Hai effettuato il check-in, non puoi modificare il biglietto.");
            }
        } catch (IOException ex) {
            Logger.getLogger(MethodsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void editTicket(FacadeControllerClient client){
        Ticket tp = MethodsControl.searchTicket(client);
        if(tp!=null){
            MethodsControl.editTicket(client, tp);            
        }        
    }
    //FINE MODIFICA//
    
    
    
    public static void searchFlightAirport(FacadeControllerClient client){
        ArrayList<String> inputtxt2=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci Aeroporto partenza", "Inserisci Aeroporto destinazione")));
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
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static boolean isCheckIn(FacadeControllerClient client, Ticket tp) throws IOException{
        if(client.isCheckIn(tp)){
            return true;                    
        }
        else{
            return false;                    
        }            
    }
    
    public static void checkIn(FacadeControllerClient client){
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci il codice ticket per effettuare il check-in:")));
        String codeTicket = input.get(0);
        Ticket tp = new Ticket(codeTicket);
        try {
            tp = client.getTicket(tp);
            if(tp!=null){
                if(MethodsControl.isCheckIn(client, tp)){
                    System.out.println("Il check-in è stato già effettuato.");
                }
                else{
                    tp = client.checkIn(tp);
                    System.out.println("Check-in effettuato.\n"+tp.printTicketPassenger());                
                }
            }
            else{
                System.out.println("Errore inserimento codice biglietto");
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MethodsControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Ticket searchTicket(FacadeControllerClient client){
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci il codice del biglietto: ")));
        String codeT = input.get(0);
        Ticket t = new Ticket(codeT);
        Flight f = null;
        {
            try {
                t = client.getTicket(t);   
                if(t!=null)
                    f = client.searchFlight(new Flight(t.getCodeFlight()));
            } catch (IOException ex) {
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(t!=null){
            System.out.println(f.toString());
            System.out.println(t.printTicketPassenger());
            return t;
        }
        else{
            System.out.println("Biglietto non trovato");
            return null;
        }
    }
    
    public static void searchReservation(FacadeControllerClient client){
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci il codice della prenotazione: ")));
        int codeReservation = Integer.parseInt(input.get(0));
        Reservation r = new Reservation(codeReservation);
        Flight f1 = null;
        {
            try {
                r = client.getReservation(r);   
                if(r!=null)
                    f1 = client.searchFlight(new Flight(r.getCodeFlight()));
            } catch (IOException ex) {
                Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(r!=null){
            System.out.println(f1.toString());
            System.out.println(r.printReservation());
            System.out.println(r.printTickets());                        
        }
        else{
            System.out.println("Prenotazione non trovata.");
        }  
    }

    public static void searchCitys(FacadeControllerClient client) {
        String[] citta=null;
        try{
            citta = client.getAllCitys();
            System.out.println("Le città disponibili sono:");
            for (String a :citta)
            {
                System.out.println(a);
            }
        }
        catch(IOException ex)
        {
            Logger.getLogger(ControllerTxt.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    static void searchFlightCode(FacadeControllerClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input=MethodsControl.scannerInput(new ArrayList<>(asList("Inserisci il codice del volo: ")));
        MethodsControl.searchFlight(client, input.get(0));
    }
     
}
