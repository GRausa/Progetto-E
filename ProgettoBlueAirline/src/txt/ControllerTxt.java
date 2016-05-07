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
import blueAirline.Flight;
import blueAirline.Passenger;
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
            s += "1) Ricerca se è prensente un volo in una data\n";
            s += "2) Ricerca se sono presenti dei posti in un volo\n";
            s += "3) Effettua una prenotazione\n";
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
            for (Flight f : arrayFlight) {
                System.out.println("Trovato il seguente volo:");
                System.out.println(f);
            }
            if (arrayFlight.size() == 0) {
                System.out.println("Nessun volo per questa data, sono state trovate le seguenti tratte: "
                        + "");
                
                    ArrayList<Flight> calendar= c.calendarFlight(r);
                    for(Flight a:calendar)
                        System.out.println(a);
                
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
                for (int i = 0; i < n; i++) {
                    System.out.println("Inserisci passegero n" + (i + 1) + " (IDCard Cognome Nome): ");
                    s1 = input.nextLine();
                    if (s1.isEmpty())
                    {
                        s1 = input.nextLine();
                    }
                    String[] vet = new String[3];
                    vet = s1.split(" ");
                    listPassengers.add(new Passenger(vet[0], vet[1], vet[2]));
                    System.out.println("Inserisci scelta posto: ");
                    listSeats.add(input.nextInt());                    
                }
                c.makeReservation(flight, listPassengers, listSeats, emailCustomer, numberCustomer);
                System.out.println("Prenotazione effettuata.");
            }
        } else {
            System.out.println("Errore nell'inserimento, codice non trovato.");
        }
        System.out.println("Clicca per continuare.");
        input.nextLine();
    }
}