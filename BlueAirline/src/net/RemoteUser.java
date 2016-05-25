/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import clients.ControllerClient;
import com.google.gson.Gson;
import database.ParserSQL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeMath.log;
import objects.Flight;
import objects.Route;

/**
 *
 * @author riccardo
 */
class RemoteUser extends Thread {

    ControllerClient company;
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    private static int counter = 0;
    private int progressiven;
    Gson gson = new Gson();

    boolean stop;
    Map<String, Command> commands;

    /**
     * Create a user connected with a socket.
     *
     * @param chat the main chat object
     * @param socket the socket used to communicate
     */
    static synchronized private int generateProgressive() {
        return counter++;
    }

    RemoteUser(ControllerClient company, Socket socket) throws IOException {

        this.company = company;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        stop = false;
        registerCommands();
        this.progressiven = generateProgressive();
    }

    // Send an error message to the remote user.
    private void error(String message) {
        log(Level.WARNING, "Sent error: " + message);
        out.println("ERR " + message);
    }

    // Send an ok maesage to the remote user.
    private void ok() {
        log(Level.INFO, "'OK' sent");
        out.println("OK");
    }

    // Create the dispatch table mapping commands to actions.
    private void registerCommands() {
        commands = new HashMap<>();
        commands.put("HI!", new Command() {
            @Override
            public void execute(String args) {
                out.println("HI!");
            }
        });
        commands.put("BYE", new Command() {
            @Override
            public void execute(String args) {
                stop = true;
            }
        });

        /*commands.put("CALENDAR", new Command() {
         @Override
         public void execute(String args) {
         String argoments[] = args.split(" ");
         Route rotta = company.searchRouteForAirport(argoments[0], argoments[1]);
         if (rotta==null){
         out.println("0k");
         }
         else{
         ArrayList<Flight> voli = company.calendarFlight(rotta);

         if (voli.isEmpty()) {
         out.println("-1k");
         } else {
         //voli.sort(null);
                    
         out.println("2k\t"+voli.size());
         for (Flight vo : voli) {
         out.println(vo);
         }
         }
         }

         }
         });
         */
        commands.put("CHECK", new Command() {
            @Override
            public void execute(String args) {

                ArrayList<Route> rottes = null;
                Route r = gson.fromJson(args, Route.class);
                try {
                    rottes = company.searchRoutes();
                } catch (SQLException ex) {
                    Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
                }
                if ((r.getDepartureCity().isEmpty()) & (r.getDestinationCity().isEmpty())) {
                    out.println(gson.toJson(rottes));
                } else {
                    ArrayList<Route> app = new ArrayList<Route>();
                    if (r.getDestinationCity().isEmpty()) {
                        for (Route rotte : rottes) {
                            if (r.getDepartureCity().toString().equalsIgnoreCase(rotte.getDepartureCity().toString())) {
                                app.add(rotte);
                            }
                        }
                    } else {
                        for (Route rotte : rottes) {
                            if ((r.getDepartureCity().toString().equalsIgnoreCase(rotte.getDepartureCity().toString())) & (r.getDestinationCity().toString().equalsIgnoreCase(rotte.getDestinationCity().toString()))) {
                                app.add(rotte);
                            }
                        }
                    }
                    out.println(gson.toJson(app));
                }

            }

        }
        );

        commands.put(
                "RICERCAVOLO", new Command() {

                    @Override
                    public void execute(String args) {

                        try {
                            Flight r = gson.fromJson(args, Flight.class);
                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            String formatted = format1.format(r.getDateDeparture().getTime());
                            
                            //DA MRIODIFICARE LA DATA DEVE ESSERE NEL FORMATO DI COME DICE IL DB
                            ArrayList<Flight> flights = new ArrayList<>();
                            flights = company.searchFlights(r.getRoute().getDepartureCity(), r.getRoute().getDestinationCity(), formatted);
                            out.println(gson.toJson(flights));
                        } catch (SQLException ex) {
                            Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
        );
        /*
         commands.put("RICERCAPOSTI", new Command() {
            
         @Override
         public void execute(String args) {
         ricercaPosti();
         }
         });
        
         commands.put("PRENOTAZIONE", new Command() {
            
         @Override
         public void execute(String args) {
         Order();
         }
         });*/
    }

    /**
     * Execute the given command.
     *
     * @param command name of the command
     * @param args optional argument(s) for the command
     * @throws ChatError
     */
    private void executeCommand(String command, String args) {
        Command cmd = commands.get(command);
        if (cmd == null) {
            error("Unknwown command '" + command + "'");
            return;
        }
        cmd.execute(args);
    }

    @Override
    public void run() {
        try {
            stop = false;
            // Main loop of the thread.
            while (!stop) {
                // Read and process a line
                String line = in.readLine();
                if (line == null) {
                    log(Level.WARNING, "Invalid read: closing the connection");
                    break;

                }
                Logger.getLogger(Server.class
                        .getName()).log(Level.INFO,
                                "Received: " + line + "\tclient : " + progressiven);
                String[] tokens = line.trim().split("\\s+", 2);
                if (tokens.length
                        == 0) {
                    continue;  // skip empty lines
                }
                String command = tokens[0].toUpperCase();
                String arg = (tokens.length > 1 ? tokens[1] : "");

                executeCommand(command, arg);

            }

        } catch (IOException ex) {
            Logger.getLogger(RemoteUser.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            Logger.getLogger(Server.class
                    .getName()).log(Level.WARNING, "Chiusa connessione con il client : " + progressiven);

            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /*
     private void ricercaPosti() {
     try {
     out.println("RICERCA POSTI IN UN VOLO. ");
     Scanner input = new Scanner(System.in);
     out.println("Inserisci codice volo: ");
     String s1 = in.readLine();
     Flight flight = company.searchFlights(s1);
     if (flight != null) {
     out.println("Inserisci numero passeggeri: ");
     int n = Integer.parseInt(in.readLine());
     if (n > flight.getSeatFree()) {
     out.println("Posti non disponibili.");
     } else {
     out.println("Posti disponibili: " + flight.getSeatFree());
     }
     } else {
     out.println("Errore nell'inserimento, codice non trovato.");
     }
     } catch (IOException ex) {
     Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
     }
     }*/
    /*
     private void Order() {
     try {
     out.println("AREA 3: PRENOTAZIONE VOLO. ");

     out.println("Inserisci codice volo: ");
     String s1 = in.readLine();
     Flight flight = company.searchFlights(s1);
     if (flight != null) {
     out.println("Inserisci numero passeggeri: ");
     int n = Integer.parseInt(in.readLine());
     if (n > flight.getSeatFree()) {
     out.println("Posti non disponibili.");
     } else {
     ArrayList<Passenger> listPassengers = new ArrayList<>();
     ArrayList<Integer> listSeats = new ArrayList<>();
     out.println("Posti disponibili: " + flight.getSeatFree());
     out.println("Inserisci email Customer: ");
     String emailCustomer = in.readLine();
     if (emailCustomer.isEmpty()) {
     emailCustomer = in.readLine();
     }
     out.println("Inserisci numero di telefono Customer: ");
     String numberCustomer = in.readLine();
     Customer customer = new Customer(emailCustomer, numberCustomer);
     for (int i = 0; i < n; i++) {
     try {
     out.println("Inserisci passegero n" + (i + 1) + " (IDCard Cognome Nome): ");
     if (s1.isEmpty()) {
     s1 = in.readLine();
     }
     String[] vet = new String[3];
     vet = s1.split(" ");
     Passenger p = new Passenger(vet[0], vet[1], vet[2]);
     listPassengers.add(p);
     int j = 1, seat = 0;
     while (j != 0) {
     out.println("Inserisci scelta posto: ");
     seat = Integer.parseInt(in.readLine());
     if (seat > flight.getAirplane().getNumSeat()) {
     out.println("Posto inserito superiore alla capienza massima");
     continue;
     }
     if (!flight.seatIsOccuped(seat) & !listSeats.contains(seat)) {
     listSeats.add(seat);
     j = 0;
     } else {
     out.println("Il posto è occupato, digiti: \n1)Decidere nuovamente il posto\n2)Assegnazione automatica");
     j = Integer.parseInt(in.readLine());
     switch (j) {
     case 1:
     continue;
     case 2:
     seat = flight.automaticSeatOccuped();
     listSeats.add(seat);
     j = 0;
     break;
     }
     }
     }
     out.println("Posto " + seat + "assegnato.");
     out.println("-> OPZIONI DI VIAGGIO AGGIUNTIVE <-");
     //MEAL
     while (true) {
     out.println("Aggiungi un pasto! Inserisci il codice (0 per uscire): ");
     String str = in.readLine();
     if (str.isEmpty()) {
     str = in.readLine();
     }
     if (str.equals("0")) {
     break;
     }
     Meal m = company.searchMeal(str);
     if (m != null) {
     if (flight.getFlightTime() >= m.getTimeMeal()) {
     p.addMeal(m);
     out.println("Hai scelto il pasto:\n" + m.toString());
     } else {
     out.println("Questo pasto è riservato a tempi di volo più lunghi, riprova.");
     }
     } else {
     out.println("Errore inserimento del codice pasto.");
     }
     }   //HOLD LUGGAGE
     while (true) {
     out.println("Aggiungi un bagaglio! Inserisci il codice (0 per uscire): ");
     String str = in.readLine();
     if (str.isEmpty()) {
     str = in.readLine();
     }
     if (str.equals("0")) {
     break;
     }
     HoldLuggage h = company.searchHoldLuggage(str);
     if (h != null) {
     p.addHoldLuggage(h);
     out.println("Hai scelto il bagaglio:\n" + h.toString());
     } else {
     out.println("Errore inserimento del codice bagaglio.");
     }
     }   //INSURANCE (solo una)
     out.println("Aggiungi un' assicurazione! Inserisci il codice (0 per uscire): ");
     String str = in.readLine();
     if (str.isEmpty()) {
     str = in.readLine();
     }
     if (!str.equals("0")) {
     Insurance ins = company.searchInsurance(str);
     if (ins != null) {
     p.addInsurance(ins);
     out.println("Hai scelto l'assicurazione:\n" + ins.toString());
     } else {
     out.println("Errore inserimento del codice assicurazione.");
     }
     }
     } catch (IOException ex) {
     Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     String code = (company.makeReservation(flight, listPassengers, listSeats, customer)).getPrenotationCode();
     out.println("Prenotazione effettuata.");
     out.println(company.searchReservation(code).toString());
     }
     } else {
     out.println("Errore nell'inserimento, codice non trovato.");
     }
     } catch (IOException ex) {
     Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     */
}

/**
 * A command received from the remote user.
 *
 * @author Andrea Cavagna
 */
interface Command {

    void execute(String args);
}
