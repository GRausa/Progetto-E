/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.io.IOException;
import java.util.ArrayList;
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
import oggetti.Seat;
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

        while (true) {
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
                    System.out.println("Inserisci città partenza");
                    String part = input.nextLine();
                    System.out.println("Inserisci città destinazione");
                    String dest = input.nextLine();
                    Route tmproute = new Route(part, dest);
                    System.out.println("Inserisci data di partenza AAAA-MM-GG");
                    String data = input.nextLine();
                    int day,month,year;
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
                    System.out.println("Inserisci città partenza");
                    String part1 = input.nextLine();
                    System.out.println("Inserisci città destinazione");
                    String dest1 = input.nextLine();
                    Route tmproute1 = new Route(part1, dest1);
                    Route[] rotte = null;
                    {
                        try {
                            rotte=client.checkRoute(tmproute1);
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (rotte.length > 0) {
                        for (Route r : rotte) {
                            System.out.println(r);
                            }
                    }
                    else {
                        System.out.println("Non esiste tratta per queste città");
                    }
                    break;

                case "PRENOTA":
                    System.out.println("Inserisci codice volo:");
                    String cod = input.nextLine();
                    Flight flight = new Flight(cod);
                    //ottengo il volo
                    {
                        try {                            
                            flight=client.searchFlight(flight);
                            if(flight==null){
                                System.out.println("Volo non trovato");
                                break;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }               
                    
                    System.out.println("Posti disponibili: "+flight.getSeatFree()+"/"+flight.getSeats().size());
                    System.out.println(flight.printSeatsFree());
                    if(flight.getSeatFree()==0){
                        System.out.println("Posti esauriti.");
                        break;
                    }
                    int num;
                    do{
                        System.out.println("Inserisci numero passaggeri");
                        num = Integer.parseInt(input.nextLine());
                        if(num>flight.getSeatFree()){
                            System.out.println("Il numero dei passeggeri inseriti supera la disponibilità di posti.");
                        }
                    }while(num>flight.getSeatFree());
                    ArrayList<TicketPassenger> passengers = new ArrayList<>(num);
                    Meal[] meals=null;
                    Insurance[] insurances=null;
                    HoldLuggage[] holdLuggages=null;
                    {
                        try {                            
                            meals=client.getAllMeals();
                            insurances=client.getAllInsurances();
                            holdLuggages=client.getAllHoldLuggages();
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("Scelte in aggiunta:\nPASTI: ");
                    for(Meal m:meals){
                        System.out.println(m.toString());
                    }
                    System.out.println("BAGAGLI: ");
                    for(HoldLuggage hl:holdLuggages){
                        System.out.println(hl.toString());
                    }
                    System.out.println("ASSICURAZIONI: ");
                    for(Insurance in:insurances){
                        System.out.println(in.toString());
                    }
                    
                    for (int k = 0; k < num; k++) {
                        boolean c = false;
                        do{
                            System.out.println("INSERISCI PASSEGGERO (ID- NOME - COGNOME - NPOSTO - AGGIUNTE)");
                            String s = input.nextLine();
                            String[] vetsplit = s.split("\t");                        
                            if(vetsplit.length>3){
                                int seat = Integer.parseInt(vetsplit[3]);
                                if(flight.getSeats().get(seat-1).getPassenger()==null){
                                    flight.getSeats().get(seat-1).setPassenger(vetsplit[0]);
                                    TicketPassenger p = new TicketPassenger(vetsplit[0], vetsplit[1], vetsplit[2], seat);
                                    for (int j = 4; j < vetsplit.length; j++) {
                                        String v = vetsplit[j];
                                        switch (vetsplit[j].charAt(0)) {
                                            case 'M':
                                                for(Meal m : meals){
                                                    if(m.getCode().equals(v)){
                                                        p.addMeals(v);
                                                        break;
                                                    }
                                                }                                                
                                                break;
                                            case 'H':
                                                for(HoldLuggage hl : holdLuggages){
                                                    if(hl.getCode().equals(v)){
                                                        p.addHoldLuggage(v);
                                                        break;
                                                    }
                                                }
                                                break;
                                            case 'I':
                                                for(Insurance in : insurances){
                                                    if(in.getCode().equals(v)){
                                                        p.addInsurance(v);
                                                        break;
                                                    }
                                                }
                                                break;
                                            default:
                                                break;
                                        }
                                    }  
                                    c=true;
                                    passengers.add(p);                                     
                                }
                                else{
                                    System.out.println("Posto non disponibile.");
                                }
                            }
                            else{
                                System.out.println("Errore inserimento");
                            }
                        }while(!c);                                             
                    }
                    
                    System.out.println("INSERISCI NUMERO");
                    String numero = input.nextLine();
                    System.out.println("INSERISCI MAIL");
                    String mail = input.nextLine();
                    
                    Reservation res = new Reservation(cod, numero, mail, passengers);                    
                    {
                        try {                            
                            res=client.makeReservation(res);
                            flight=client.searchFlight(flight); //aggiorno il flight dopo la prenotazione
                            for(TicketPassenger tp : res.getPassengers()){ //controllo assegnamento posti
                                if(tp.getNseat()==-1){
                                    System.out.println("Passeggero: "+tp.getName()+" "+tp.getSurname()+" ("+tp.getID()+") non inserito, il posto è stato occupato.\nPosti disponibili:");
                                    System.out.println(flight.printSeatsFree());
                                    boolean c=false;
                                    do{
                                        System.out.println("Inserisci il nuovo posto");
                                        int set = input.nextInt();
                                        if(flight.getSeats().get(set-1).getPassenger()==null){
                                            tp.setNSeat(set);
                                            tp=client.editSeatTicketPassenger(tp);
                                            if(tp.getNseat()==set){
                                                System.out.println("Modifica effettuata.");
                                                c=true;
                                            }
                                            else{
                                                System.out.println("Posto non assegnato.");
                                            }
                                        }
                                        else{
                                            System.out.println("Errore inserimento.");
                                        }
                                    }while(!c);                                    
                                }
                            }
                            System.out.println("PRENOTAZIONE EFFETTUATA\nCodice Prenotazione: "+res.getCode());
                        } catch (IOException ex) {
                            Logger.getLogger(ReadClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    break;

                case "CALENDARIO_VOLI":

                    System.out.println("Inserisci Aeroporto partenza");
                    String part2 = input.nextLine();
                    System.out.println("Inserisci Aeroporto destinazione");
                    String dest2 = input.nextLine();
                    Route tmproute2 = new Route();
                    tmproute2.setDeparutreAirport(part2);
                    tmproute2.setDestinationAirport(dest2);
                     {
                        try {
                            Flight[] calendario = client.calendar(tmproute2);
                            if(calendario.length==0){
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
