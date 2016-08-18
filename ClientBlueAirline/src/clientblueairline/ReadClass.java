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
public class ReadClass implements Runnable {

    ClientBlueAirline client;

    public ReadClass(ClientBlueAirline n) {
        this.client = n;
    }

    @Override
    public void run() {
        Scanner input = new Scanner(System.in);

        loop:while (true) {
            System.out.println("COMANDI DISPONIBILI");
            System.out.println(">>>HI");
            System.out.println(">>>VERIFICA_VOLO");
            System.out.println(">>>VERIFICA_TRATTA");
            System.out.println(">>>PRENOTA");
            System.out.println(">>>CALENDARIO_VOLI");
            System.out.println(">>>CHECK_IN");
            System.out.println(">>>CERCA_TICKETPASSENGER");
            System.out.println(">>>CERCA_PRENOTAZIONE");
            System.out.println(">>>CITTA_DISPONIBILI");
            System.out.println(">>>EXIT");
            

            String s1 = input.nextLine().toUpperCase();
            switch (s1) {

                case "HI": {
                    try {
                        client.hello();
                    } catch (IOException ex) {
                        Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
                
                
                case "VERIFICA_VOLO":
                    ArrayList<String> inputtxt=MethodsControl.lettura(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
                    /*System.out.println("Inserisci città partenza");
                    String part = input.nextLine();
                    System.out.println("Inserisci città destinazione");
                    String dest = input.nextLine();*/
                    Route tmproute = new Route(inputtxt.get(0), inputtxt.get(1));
                    System.out.println("Inserisci data di partenza AAAA-MM-GG");
                    String data = input.nextLine();
                    int day,
                     month,
                     year;
                    String[] vetDate = data.split("-");
                    if (vetDate.length == 3) {
                        year = Integer.parseInt(vetDate[0]);
                        month = Integer.parseInt(vetDate[1]) - 1;
                        day = Integer.parseInt(vetDate[2]);
                    } else {
                        System.out.println("Hai inserito la data sbagliata");
                        break;
                    }
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
                    break;

                case "VERIFICA_TRATTA":
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
                    break;

                case "PRENOTA":
                    String cod=MethodsControl.lettura(new ArrayList<>(asList("Inserisci codice Volo"))).get(0);
                    Flight flight = new Flight(cod);
                    //ottengo il volo
                     {
                        try {
                            flight = client.searchFlight(flight);
                            if (flight == null) {
                                System.out.println("Volo non trovato");
                                break;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    System.out.println("Posti disponibili: " + flight.getSeatFree() + "/" + flight.getSeats().size());
                    System.out.println(flight.printSeatsFree());
                    if (flight.getSeatFree() == 0) {
                        System.out.println("Posti esauriti.");
                        break;
                    }
                    int num;
                    do {
                        System.out.println("Inserisci numero passaggeri");
                        num = Integer.parseInt(input.nextLine());
                        if (num > flight.getSeatFree()) {
                            System.out.println("Il numero dei passeggeri inseriti supera la disponibilità di posti.");
                        }
                    } while (num > flight.getSeatFree());
                    ArrayList<TicketPassenger> passengers = new ArrayList<>(num);
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
                                    TicketPassenger p = new TicketPassenger(vetsplit[0], vetsplit[1], vetsplit[2], seat, classe, flight.getCode(), flight.getPrezzo());
                                    for (int j = 4; j < vetsplit.length; j++) {
                                        String v = vetsplit[j];
                                        switch (vetsplit[j].charAt(0)) {
                                            case 'M':
                                                for (Meal m : meals) {
                                                    if (m.getCode().equals(v)) {
                                                        p.addMeals(m);
                                                        //p.addTotalPrice(m.getPrice());
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 'H':
                                                for (HoldLuggage hl : holdLuggages) {
                                                    if (hl.getCode().equals(v)) {
                                                        p.addHoldLuggage(hl);
                                                        //p.addTotalPrice(hl.getPrice());
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 'I':
                                                for (Insurance in : insurances) {
                                                    if (in.getCode().equals(v)) {
                                                        p.addInsurance(in);
                                                        //p.addTotalPrice(in.getPrice());
                                                        break;
                                                    }
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }
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

                    System.out.println("INSERISCI NUMERO");
                    String numero = input.nextLine();
                    System.out.println("INSERISCI MAIL");
                    String mail = input.nextLine();

                    Reservation res = new Reservation(cod, numero, mail, passengers);
                    {
                        try {
                            res = client.makeReservation(res);
                            flight = client.searchFlight(flight); //aggiorno il flight dopo la prenotazione
                            for (TicketPassenger tp : res.getPassengers()) { //controllo assegnamento posti
                                if (tp.getNseat() == -1) {
                                    System.out.println("Passeggero: " + tp.getName() + " " + tp.getSurname() + " (" + tp.getID() + ") non inserito, il posto è stato occupato.\nPosti disponibili:");
                                    System.out.println(flight.printSeatsFree());
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
                            //il seguito sarà modificato con il RICERCA PRENOTAZIONE e il stampa total price sarà in Reservation
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
                    break;
                    /*
                    CONTROLLARE CHE LA PRENOTAZIONE FUNZIONA (ORA CHE HO MESSO GLI ARRAY)
                    CONTROLLARE CHE IL CHECKIN FUNZIONA (E CHE IL GET-TICKET-PASSENGER FUNZIONA)
                    IN QUESTO MODO SI PUò IMPLEMENTARE:
                    - GET PRENOTAZIONE -> FACILE DAL GET TICKET
                    - MODIFICA POSTO E AGGIUNTE (SE NON HO FATTO IL CHECKIN -> USO METODO IMPLEMENTATO ISCHECK)
                    */
                    
                case "EXIT":
                    break loop;
                    
                case "CALENDARIO_VOLI":
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
                    break;
                
                case "CHECK_IN":
                    System.out.println("Inserisci il codice ticket per effettuare il check-in:");
                    String codeTicket = input.nextLine();
                    TicketPassenger tp = new TicketPassenger(codeTicket);
                    {
                        try {
                            tp = client.checkIn(tp);
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("Check-in effettuato.\n"+tp.printTicketPassenger());
                    break;
                
                case "CERCA_TICKETPASSENGER":
                    System.out.println("Inserisci il codice del biglietto: ");
                    String codeT = input.nextLine();
                    TicketPassenger tp1 = new TicketPassenger(codeT);
                    Flight f = null;
                    {
                        try {
                            tp1 = client.getTicketPassenger(tp1);   
                            f = client.searchFlight(new Flight(tp1.getCodeFlight()));
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(tp1!=null){
                        System.out.println(f.toString());
                        System.out.println(tp1.printTicketPassenger());
                    }
                    else
                        System.out.println("Biglietto non trovato");
                    break;
                 
                case "CERCA_PRENOTAZIONE":
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

                    break;
                    
                case "CITTA_DISPONIBILI":
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
                    break;
                
                default:
                    System.out.println("Errore inserimento");

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
