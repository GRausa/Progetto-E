/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//IMPORTANTE 
/*
 DISCUTERE MEGLIO DI QUESTA CLASSE
 IDEA DI ZODRACCIR-> RENDERLA UN CONTROLLER VERO E PROPRIO, LA MODALITÀ TXT DOVRA' FUNZIONARE
 COME UNA SHELL,NEL SENSO CHE DA LINEA DI COMANDO DIAMO L'ISTRUZIONE DA FARE E LUI ESEGUE,
 NON VA MESSO NEL MAIN(COME ABBIAMO FATTO ADESSO) :  ControllerTxt.checkSeats(c);
 QUINDI DA RIVEDERE.


 */
package txt;

import blueAirline.Company;
import blueAirline.Customer;
import blueAirline.Flight;
import blueAirline.HoldLuggage;
import blueAirline.Insurance;
import blueAirline.Meal;
import blueAirline.Passenger;
import blueAirline.Reservation;
import blueAirline.Route;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author cl418377
 */
public final class ControllerTxt {

    public static void start(Company c) {
        int i = 1;
        Scanner input = new Scanner(System.in);
        while (i != 0) {
            String s = "";
            s += "\nBENVENUTO IN: " + c.getName() + "\n";
            s += "1) Ricerca un volo in una data\n";
            s += "2) Ricerca dei posti in un volo\n";
            s += "3) Effettua una prenotazione\n";
            s += "4) Ricerca una prenotazione\n";
            s += "5) Login Administrator\n";
            s += "0) Esci\n";
            System.out.println(s + "\nScelta: ");
            i = input.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    ControllerTxt.searchTxtFlight(c);
                    break;
                case 2:
                    ControllerTxt.checkSeats(c);
                    break;
                case 3:
                    ControllerTxt.Order(c);
                    break;
                case 4:
                    ControllerTxt.searchTxtReservation(c);
                    break;
                case 5:
                    ControllerTxt.loginAdministrator(c);
                    break;
                default: 
                    System.out.println("Errore scelta.");
            }
        }
    }

    public static void searchTxtFlight(Company c) {
        System.out.println("AREA 1: RICERCA VOLO PER DATA. ");
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci partenza: ");
        String s1 = input.nextLine();
        System.out.println("Inserisci arrivo: ");
        String s2 = input.nextLine();
        Route r = c.searchRoute(s1, s2);
        if (r != null) {
            System.out.println("Route presente.");
            System.out.println("Inserisci Data (gg/mm/aaaa): ");
            s1 = input.nextLine();
            String[] vet = new String[3];
            vet = s1.split("/");
            GregorianCalendar date = new GregorianCalendar(Integer.parseInt(vet[2]), Integer.parseInt(vet[1]) - 1, Integer.parseInt(vet[0]));
            ArrayList<Flight> arrayFlight = c.searchFlights(r, date);
            if (!arrayFlight.isEmpty()) {
                for (Flight f : arrayFlight) {
                    System.out.println("Trovato il seguente volo:");
                    System.out.println(f);
                }
            }
            else{
                ArrayList<Flight> calendar= c.calendarFlight(r);
                if(calendar.isEmpty()){
                    System.out.println("La compagnia gestisce la route ma non ci sono voli presenti.");
                }
                else{
                    System.out.println("Nessun volo per questa data, suggerimenti: ");
                    for(Flight a:calendar)
                        System.out.println(a); 
                }
            }
        } else {
            System.out.println("Route non presente.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }

    public static void checkSeats(Company c) {
        System.out.println("AREA 2: RICERCA POSTI IN UN VOLO. ");
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci codice volo: ");
        String s1 = input.nextLine();
        Flight flight = c.searchFlights(s1);
        if (flight != null) {
            System.out.println("Inserisci numero passeggeri: ");
            int n = input.nextInt();
            if (n > flight.getSeatFree()) {
                System.out.println("Posti non disponibili.");
            } else {
                System.out.println("Posti disponibili: " + flight.getSeatFree());
            }
        } else {
            System.out.println("Errore nell'inserimento, codice non trovato.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }

   public static void Order(Company c) {
        System.out.println("AREA 3: PRENOTAZIONE VOLO. ");
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci codice volo: ");
        String s1 = input.nextLine();
        Flight flight = c.searchFlights(s1);
        if (flight != null) {
            System.out.println("Inserisci numero passeggeri: ");
            int n = input.nextInt();
            if (n > flight.getSeatFree()) {
                System.out.println("Posti non disponibili.");
            } 
            else {
                ArrayList<Passenger> listPassengers = new ArrayList<>();
                ArrayList<Integer> listSeats = new ArrayList<>();
                System.out.println("Posti disponibili: " + flight.getSeatFree());
                System.out.println("Inserisci email Customer: ");
                String emailCustomer = input.nextLine();
                if (emailCustomer.isEmpty())
                    emailCustomer = input.nextLine();
                System.out.println("Inserisci numero di telefono Customer: ");
                String numberCustomer = input.nextLine();
                Customer customer = new Customer(emailCustomer,numberCustomer);
                for (int i = 0; i < n; i++) {
                    System.out.println("Inserisci passegero n" +(i+1)+" (IDCard Cognome Nome): ");
                    s1 = input.nextLine();
                    if (s1.isEmpty())
                        s1 = input.nextLine();                    
                    String[] vet = new String[3];
                    vet = s1.split(" ");
                    Passenger p = new Passenger(vet[0], vet[1], vet[2]);
                    listPassengers.add(p);
                    
                    //verifica posto
                    int j=1,seat=0;
                    while(j!=0){                        
                        System.out.println("Da 0 a "+flight.getAirplane().getNumberFirstClass()+": posti in prima classe (costo supplementare "+Flight.pricePrimeClass+" euro)");
                        System.out.println("Da "+(flight.getAirplane().getNumberFirstClass()+1)+" a "+flight.getAirplane().getNumSeat()+": posti seconda classe.");
                        System.out.println("Inserisci scelta posto: ");
                        seat=input.nextInt();
                        if(seat>flight.getAirplane().getNumSeat()){
                            System.out.println("Posto inserito superiore alla capienza massima");
                            continue;
                        }
                        if(!flight.seatIsOccuped(seat) & !listSeats.contains(seat)){                            
                            listSeats.add(seat);  
                            j=0;
                        }
                        else{
                            System.out.println("Il posto è occupato, digiti: \n1)Decidere nuovamente il posto\n2)Assegnazione automatica");
                            j=input.nextInt();
                            switch(j){
                                case 1: continue;                                        
                                case 2: seat=flight.automaticSeatOccuped();
                                        listSeats.add(seat);
                                        j=0;
                                        break;                                
                            }
                        }
                    }
                    System.out.println("Posto "+seat+" assegnato.");   
                    System.out.println("-> OPZIONI DI VIAGGIO AGGIUNTIVE <-");  
                    
                    //MEAL
                    while(true){    
                        System.out.println("Aggiungi un pasto! Inserisci il codice (0 per uscire): ");
                        String str = input.nextLine();
                        if (str.isEmpty())
                            str = input.nextLine(); 
                        if(str.equals("0")){
                            break;
                        }
                        Meal m = c.searchMeal(str);
                        if(m!=null){
                            if(flight.getFlightTime()>=m.getTimeMeal()){
                                p.addMeal(m);
                                System.out.println("Hai scelto il pasto:\n"+m.toString());
                            }
                            else{
                                System.out.println("Questo pasto è riservato a tempi di volo più lunghi, riprova.");
                            }
                        }
                        else{
                            System.out.println("Errore inserimento del codice pasto.");
                        }
                    }
                    
                    //HOLD LUGGAGE
                    while(true){    
                        System.out.println("Aggiungi un bagaglio! Inserisci il codice (0 per uscire): ");
                        String str = input.nextLine();
                        if (str.isEmpty())
                            str = input.nextLine(); 
                        if(str.equals("0")){
                            break;
                        }
                        HoldLuggage h = c.searchHoldLuggage(str);
                        if(h!=null){
                            p.addHoldLuggage(h);
                            System.out.println("Hai scelto il bagaglio:\n"+h.toString());
                        }
                        else{
                            System.out.println("Errore inserimento del codice bagaglio.");
                        }
                    }
                    
                    //INSURANCE (solo una)
                    System.out.println("Aggiungi un' assicurazione! Inserisci il codice (0 per uscire): ");
                    String str = input.nextLine();
                    if (str.isEmpty()) {
                        str = input.nextLine();
                    }
                    if (!str.equals("0")) {
                        Insurance ins = c.searchInsurance(str);
                        if (ins != null) {
                            p.addInsurance(ins);
                            System.out.println("Hai scelto l'assicurazione:\n" + ins.toString());
                        } else {
                            System.out.println("Errore inserimento del codice assicurazione.");
                        }
                    }                    
                }
                Reservation r = c.makeReservation(flight, listPassengers, listSeats, customer);
                if(r!=null){
                    System.out.println("Prenotazione effettuata. Codice prenotazione: "+r.getPrenotationCode());
                    System.out.println(r);
                }
                else{
                    System.out.println("Errore nella prenotazione, i posti sono stati assegnati a qualcun'altro."); 
                }
            }
        } 
        else{
            System.out.println("Errore nell'inserimento, codice volo non trovato.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }
   
   
    public static void searchTxtReservation(Company c) {
        System.out.println("AREA 4: RICERCA DI UNA PRENOTAZIONE. ");
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci codice volo: ");
        String s1 = input.nextLine();
        Reservation r = c.searchReservation(s1);
        if(r!=null){
            System.out.println(r);
        }
        else{
            System.out.println("Errore nell'inserimento, codice prenotazione non trovato.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }
    
    public static void loginAdministrator(Company c) {
        System.out.println("AREA 5: LOGIN AMMINISTRATORE. ");
        Scanner input = new Scanner(System.in);
        System.out.println("Inserisci username: ");
        String s1 = input.nextLine();
        System.out.println("Inserisci password: ");
        String s2 = input.nextLine();
        if(c.searchAdministrator(s1, s2)!=null){
            System.out.println("Loggato");
        }
        else{
            System.out.println("Errore nell'inserimento, credenziali non trovate.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }
}