/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui.client;

import controller.InterfaceClient;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Calendar;
import java.util.Date;
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

/**
 * Provides the customers' methods for the textual user interface.
 *
 * @author Giovanni
 */
public class MethodsControlClient {

    /**
     *
     * @param n
     * @return
     */
    public static ArrayList<String> scannerInput(ArrayList<String> n) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> ritorno = new ArrayList<>(n.size());
        for (int i = 0; i < n.size(); i++) {
            String part = null;
            System.out.println(n.get(i));
            ritorno.add(i, input.nextLine());
        }
        return ritorno;
    }

    /**
     * Prints the menu.
     *
     * @return
     */
    public static String toStringMenu() {
        Scanner input = new Scanner(System.in);
        String s;
        s = "\nCOMANDI DISPONIBILI\n"
                + "\n>>> HI -> Test server\n"
                + "\nAREA RICERCA:\n\n>>> CERCA_VOLO -> Ricerca il volo tra 2 città in una precisa data ( + mappa posti )\n"
                + ">>> CERCA_VOLO_CODICE -> Ricerca il volo in base al codice\n"
                + ">>> CALENDARIO_AEROPORTI -> Ricerca tutti i voli disponibili tra 2 aeroporti\n"
                + ">>> VERIFICA_TRATTA -> Controlla se esiste una tratta tra 2 città (lasciando uno o entrambi i campi vuoti ricerca tutte le tratte disponibili)\n"
                + ">>> CITTA_DISPONIBILI -> Controlla in quali città puoi viaggiare\n"
                + "\nAREA CLIENTE:\n\n>>> PRENOTA -> Effettua una prenotazione di un posto a sedere\n"
                + ">>> CHECK_IN -> Effettua il check-in del tuo biglietto aereo\n"
                + ">>> CERCA_TICKETPASSENGER -> Ricerca il tuo biglietto aereo\n"
                + ">>> CERCA_PRENOTAZIONE -> Ricerca la tua prenotazione\n"
                + ">>> MODIFICA_BIGLIETTO -> Modifica il tuo posto a sedere o inserisci ulteriori aggiunte\n"
                + ">>> EXIT";
        System.out.println(s);
        String s1 = input.nextLine().toUpperCase();
        return s1;
    }

    /**
     * Prints a message indicating the server's status.
     *
     * @param client Client.
     */
    public static void hi(InterfaceClient client) {
        if (client.hello()) {
            System.out.println("IL SERVER E' ATTIVO");
        } else {
            System.out.println("IL SERVER NON E' ATTIVO");
        }
    }

    /**
     * Search a flight in the database passing departure's date, departure's
     * city and destination's city.
     *
     * @param client Client.
     */
    public static void searchFlight(InterfaceClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> inputtxt = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute = new Route(inputtxt.get(0), inputtxt.get(1));
        inputtxt = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci data di partenza AAAA-MM-GG")));
        String data = inputtxt.get(0);
        int day, month, year;
        String[] vetDate = data.split("-");
        if (vetDate.length == 3) {
            GregorianCalendar date = new GregorianCalendar(Integer.parseInt(vetDate[0]), Integer.parseInt(vetDate[1]) - 1, Integer.parseInt(vetDate[2]));
            Flight tmpflight = new Flight(tmproute, date);
            Flight[] volit = null;
            {

                volit = client.searchFlights(tmpflight);
                if (volit.length == 0) {
                    System.out.println("Nessun volo trovato.");
                }
                for (Flight v : volit) {
                    System.out.println(v);
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

    /**
     * Search a route in the database passing the departure's and destination's
     * cities.
     *
     * @param client Client.
     */
    public static void searchRoute(InterfaceClient client) {
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci città partenza", "Inserisci città destinazione")));
        Route tmproute1 = new Route(input.get(0), input.get(1));
        Route[] rotte = null;
        {
            try {
                rotte = client.searchRoutes(tmproute1);
            } catch (IOException ex) {
                Logger.getLogger(ControllerClientTxt.class.getName()).log(Level.SEVERE, null, ex);
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
    /**
     * Searches a flight and prints the number of free seats.
     *
     * @param client Client
     * @param cod Code of the flight.
     * @return flight.
     */
    private static Flight searchFlight(InterfaceClient client, String cod) {
        Flight flight = new Flight(cod);
        {
            flight = client.searchFlights(flight)[0];
            Date date = new Date();
            Calendar data = Calendar.getInstance();
            data.setTime(date);
            if (flight == null || flight.getDateDeparture().before(data)) {
                System.out.println("Volo non trovato o risulta antecedente alla data odierna");
                return null;
            }
        }
        System.out.println(flight.toString());
        System.out.println("Posti disponibili: " + flight.getSeatFree() + "/" + flight.getSeats().size());
        System.out.println(flight.printAllSeats());
        return flight;
    }

    /**
     * Prints a written description of the supplements.
     *
     * @param meals List of meals.
     * @param insurances List of insurances.
     * @param holdLuggages List of hold lugagges.
     */
    private static void toStringSupplements(Meal[] meals, Insurance[] insurances, HoldLuggage[] holdLuggages) {
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

    /**
     * Adds on or more meals to a ticket.
     *
     * @param p Ticket.
     * @param meals List of meals.
     * @param v
     */
    private static void addMealTicket(Ticket p, Meal[] meals, String v) {
        for (Meal m : meals) {
            if (m.getCode().equals(v)) {
                p.addMeals(m);
                break;
            }
        }
    }

    /**
     * Adds on or more hold luggages to a ticket.
     *
     * @param p Ticket.
     * @param holdLuggages List of hold luggages.
     * @param v
     */
    private static void addHoldLuggageTicket(Ticket p, HoldLuggage[] holdLuggages, String v) {
        for (HoldLuggage hl : holdLuggages) {
            if (hl.getCode().equals(v)) {
                p.addHoldLuggage(hl);
                break;
            }
        }
    }

    /**
     * Adds on or more insurances to a ticket.
     *
     * @param p Ticket.
     * @param holdLuggages List of insurances.
     * @param v
     */
    private static void addInsuranceTicket(Ticket p, Insurance[] insurances, String v) {
        for (Insurance in : insurances) {
            if (in.getCode().equals(v)) {
                p.addInsurance(in);
                break;
            }
        }
    }

    /**
     * Adds supplements to a ticket.
     *
     * @param vetsplit
     * @param seat Seat's number.
     * @param classe Seat's class.
     * @param codeFlight Code of the flight.
     * @param price Price of the flight.
     * @param meals Meals.
     * @param insurances Insurances.
     * @param holdLuggages Hold luggages.
     * @return Ticket with supplements.
     */
    private static Ticket insertTicketSupplements(String[] vetsplit, int seat, int classe, String codeFlight, double price, Meal[] meals, Insurance[] insurances, HoldLuggage[] holdLuggages) {
        Ticket p = new Ticket(vetsplit[0], vetsplit[1], vetsplit[2], seat, classe, codeFlight, price);
        for (int j = 4; j < vetsplit.length; j++) {
            String v = vetsplit[j];
            switch (vetsplit[j].charAt(0)) {
                case 'M':
                    MethodsControlClient.addMealTicket(p, meals, v);
                    break;
                case 'H':
                    MethodsControlClient.addHoldLuggageTicket(p, holdLuggages, v);
                    break;
                case 'I':
                    MethodsControlClient.addInsuranceTicket(p, insurances, v);
                    break;
                default:
                    break;
            }
        }
        return p;
    }

    /**
     * Adds tickets to a list.
     *
     * @param num Number of passengers.
     * @param flight Flight.
     * @param meals Meals.
     * @param insurances Insurances.
     * @param holdLuggages Hold luggages.
     * @return List of passenger.
     */
    private static ArrayList<Ticket> insertTicket(int num, Flight flight, Meal[] meals, Insurance[] insurances, HoldLuggage[] holdLuggages) {
        //Scanner input = new Scanner(System.in);
        ArrayList<Ticket> passengers = new ArrayList<Ticket>();
        for (int k = 0; k < num; k++) {
            boolean c = false;
            do {
                ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("INSERISCI PASSEGGERO (ID - NOME - COGNOME - NUM POSTO - CODICE AGGIUNTE(facoltative))\nN.B. Le informazioni devono essere separate da tab")));
                String s = input.get(0);
                String[] vetsplit = s.split("\t");
                if (vetsplit.length > 3) {
                    int seat = Integer.parseInt(vetsplit[3]);
                    if (flight.getSeats().get(seat - 1).getTicket() == null) {
                        flight.getSeats().get(seat - 1).setTicket(vetsplit[0]);
                        int classe = flight.getSeats().get(seat - 1).getClasse();
                        Ticket p = MethodsControlClient.insertTicketSupplements(vetsplit, seat, classe, flight.getCode(), flight.getPrice(), meals, insurances, holdLuggages);
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

    /**
     * Creates a reservation.
     *
     * @param client Client.
     * @param cod Reservation's code.
     * @param flight Flight where the customer wants a reservation.
     * @return the reservation.
     * @throws IOException if has occurred an I/O exception.
     */
    private static Reservation makeReservation(InterfaceClient client, String cod, Flight flight) throws IOException {
        //Scanner input = new Scanner(System.in);
        int num;
        do {
            ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci numero passeggeri")));
            num = Integer.parseInt(input.get(0));
            if (num > flight.getSeatFree()) {
                System.out.println("Il numero dei passeggeri inseriti supera la disponibilità di posti.");
            }
        } while (num > flight.getSeatFree());
        Meal[] meals = null;
        Insurance[] insurances = null;
        HoldLuggage[] holdLuggages = null;
        {
            
                meals = client.getAllMeals();
                insurances = client.getAllInsurances();
                holdLuggages = client.getAllHoldLuggages();
            
        }
        MethodsControlClient.toStringSupplements(meals, insurances, holdLuggages);
        ArrayList<Ticket> passengers = MethodsControlClient.insertTicket(num, flight, meals, insurances, holdLuggages);
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("INSERISCI NUMERO", "INSERISCI EMAIL")));
        String numero = input.get(0);
        String mail = input.get(1);
        Reservation res = new Reservation(cod, numero, mail, passengers);
        res = client.makeReservation(res);
        return res;
    }

    /**
     * Prints a recap of a reservation.
     *
     * @param client Client.
     * @param res Reservation.
     * @param flight Flight.
     */
    private static void checkReservation(InterfaceClient client, Reservation res, Flight flight) {
        //Scanner input = new Scanner(System.in);
        try {
            flight = client.searchFlights(flight)[0]; //aggiorno il flight dopo la prenotazione
            for (Ticket tp : res.getPassengers()) { //controllo assegnamento posti
                if (tp.getNseat() == -1) {
                    System.out.println("Passeggero: " + tp.getName() + " " + tp.getSurname() + " (" + tp.getID() + ") non inserito, il posto è stato occupato.\nPosti disponibili:");
                    System.out.println(flight.printAllSeats());
                    boolean c = false;
                    do {
                        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci il nuovo posto")));
                        int set = Integer.parseInt(input.get(0));
                        if (flight.getSeats().get(set - 1).getTicket() == null) {
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
            System.out.println(res.printReservation("\n"));
            System.out.println(res.printTickets("\n"));
        } catch (IOException ex) {
            Logger.getLogger(ControllerClientTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Makes a reservation.
     *
     * @param client Client
     */
    public static void makeReservation(InterfaceClient client) {
        String cod = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci codice Volo"))).get(0);
        Flight flight = MethodsControlClient.searchFlight(client, cod);
        if (flight != null) {
            if (flight.getSeatFree() > 0) {
                try {
                    Reservation res = MethodsControlClient.makeReservation(client, cod, flight);
                    MethodsControlClient.checkReservation(client, res, flight);
                    String stamp = res.printReservation("&%") + "&%" + res.printTickets("&%");
                    client.sendMail(res.getEmail(), "ACQUISTO BIGLIETTO", stamp);
                } catch (IOException ex) {
                    Logger.getLogger(MethodsControlClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Posti non disponibili");
            }

        }
    }

    /**
     * Provides to edit the ticket and add new supplements.
     *
     * @param client Client.
     * @param tp Ticket.
     * @param flight Flight.
     * @param meals List of meals.
     * @param insurances List of insurances.
     * @param holdLuggages List of hold lugagges.
     * @throws IOException if occurs a in I/O exception.
     */
    private static void editTicket(InterfaceClient client, Ticket tp, Flight flight, Meal[] meals, Insurance[] insurances, HoldLuggage[] holdLuggages) throws IOException {
        //Scanner input = new Scanner(System.in);
        boolean c = false;
        do {
            System.out.println("Modifica il posto e inserisci le nuove scelte (saranno aggiunte alle vecchie già acquistate)");
            ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("INSERISCI MODIFICHE (NPOSTO - AGGIUNTE)")));
            String s = tp.getID() + "\t" + tp.getName() + "\t" + tp.getSurname() + "\t";
            s += input.get(0);
            String[] vetsplit = s.split("\t");
            if (vetsplit.length > 3) {
                int seat = Integer.parseInt(vetsplit[3]);
                if (flight.getSeats().get(seat - 1).getTicket() == null) { //OSS -> -1
                    flight.getSeats().get(seat - 1).setTicket(tp.getCode());
                    int classe = flight.getSeats().get(seat - 1).getClasse();
                    Ticket p2 = MethodsControlClient.insertTicketSupplements(vetsplit, seat, classe, flight.getCode(), flight.getPrice(), meals, insurances, holdLuggages);
                    p2.setCode(tp.getCode());
                    if (client.editTicket(p2).getNseat() == -1) {
                        System.out.println("Il posto è stato occupato. Riprova.");
                    } else {
                        Ticket tp2 = client.getTicket(p2);
                        System.out.println(tp2.printTicketPassenger("\n"));
                        System.out.println("Aggiunta totale di: " + (tp2.getTotalPrice() - tp.getTotalPrice()) + " euro");
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

    /**
     *
     * @param client Client.
     * @param tp Ticket.
     */
    private static void editTicket(InterfaceClient client, Ticket tp) {
        System.out.println("\nAREA MODIFICA:");
        Flight flight = null;
        Meal[] meals = null;
        Insurance[] insurances = null;
        HoldLuggage[] holdLuggages = null;
        meals = client.getAllMeals();
        insurances = client.getAllInsurances();
        holdLuggages = client.getAllHoldLuggages();
        try {
            if (!MethodsControlClient.isCheckIn(client, tp)) {
                flight = MethodsControlClient.searchFlight(client, tp.getCodeFlight());
                MethodsControlClient.editTicket(client, tp, flight, meals, insurances, holdLuggages);
            } else {
                System.out.println("Hai effettuato il check-in, non puoi modificare il biglietto.");
            }
        } catch (IOException ex) {
            Logger.getLogger(MethodsControlClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Edits a ticket.
     *
     * @param client Client.
     */
    public static void editTicket(InterfaceClient client) {
        Ticket tp = MethodsControlClient.searchTicket(client);
        if (tp != null) {
            MethodsControlClient.editTicket(client, tp);
        }
    }

    /**
     * Searches a flight passing two airports.
     *
     * @param client Client.
     */
    public static void searchFlightAirport(InterfaceClient client) {
        ArrayList<String> inputtxt2 = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci Aeroporto partenza", "Inserisci Aeroporto destinazione")));
        Route tmproute2 = new Route();
        tmproute2.setDeparutreAirport(inputtxt2.get(0));
        tmproute2.setDestinationAirport(inputtxt2.get(1));
        {
            Flight[] calendario = client.calendar(tmproute2);
            if (calendario.length == 0) {
                System.out.println("Nessun volo trovato");
            }
            for (Flight a : calendario) {
                System.out.println(a);
            }
        }
    }

    /**
     *
     * @param client Client.
     * @param tp Ticket.
     * @return<code>true</code> if the passenger already did the check in;
     * <code>false</code> otherwise.
     * @throws IOException if occurs an I/O exception.
     */
    private static boolean isCheckIn(InterfaceClient client, Ticket tp)    {
        if (client.isCheckIn(tp)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Makes the check in of a ticket.
     *
     * @param client Client.
     */
    public static void checkIn(InterfaceClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci il codice ticket per effettuare il check-in:")));
        String codeTicket = input.get(0);
        Ticket tp = new Ticket(codeTicket);
        tp = client.getTicket(tp);
        if (tp != null) {
            if (MethodsControlClient.isCheckIn(client, tp)) {
                System.out.println("Il check-in è stato già effettuato.");
            } else {
                tp = client.checkIn(tp);
                System.out.println("Check-in effettuato.\n" + tp.printTicketPassenger("\n"));
            }
        } else {
            System.out.println("Errore inserimento codice biglietto");
        }
    }

    /**
     * Search a ticket from his code.
     *
     * @param client Client.
     * @return the ticket.
     */
    public static Ticket searchTicket(InterfaceClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci il codice del biglietto: ")));
        String codeT = input.get(0);
        Ticket t = new Ticket(codeT);
        Flight f = null;
        {
            t = client.getTicket(t);
            if (t != null) {
                f = client.searchFlights(new Flight(t.getCodeFlight()))[0];
            }
        }
        if (t != null) {
            System.out.println(f.toString());
            System.out.println(t.printTicketPassenger("\n"));
            return t;
        } else {
            System.out.println("Biglietto non trovato");
            return null;
        }
    }

    /**
     * Search a reservation from his code and prints it.
     *
     * @param client
     */
    public static void searchReservation(InterfaceClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci il codice della prenotazione: ")));
        int codeReservation = Integer.parseInt(input.get(0));
        Reservation r = new Reservation(codeReservation);
        Flight f1 = null;
        {
            try {
                r = client.getReservation(r);
                if (r != null) {
                    f1 = client.searchFlights(new Flight(r.getCodeFlight()))[0];
                }
            } catch (IOException ex) {
                Logger.getLogger(ControllerClientTxt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (r != null) {
            System.out.println(f1.toString());
            System.out.println(r.printReservation("\n"));
            System.out.println(r.printTickets("\n"));
        } else {
            System.out.println("Prenotazione non trovata.");
        }
    }

    /**
     * Print all cities available.
     *
     * @param client Client.
     */
    public static void searchCitys(InterfaceClient client) {
        String[] citta = null;

        citta = client.getAllCitys();
        System.out.println("Le città disponibili sono:");
        for (String a : citta) {
            System.out.println(a);
        }

    }

    /**
     * Search a flight from his code.
     *
     * @param client Client.
     */
    public static void searchFlightCode(InterfaceClient client) {
        //Scanner input = new Scanner(System.in);
        ArrayList<String> input = MethodsControlClient.scannerInput(new ArrayList<>(asList("Inserisci il codice del volo: ")));
        MethodsControlClient.searchFlight(client, input.get(0));
    }

}
