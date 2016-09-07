/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import com.google.gson.Gson;
import controller.InterfaceServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Route;
import objects.Reservation;
import objects.Ticket;
import mail.Email;
import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * The class RemoteUser is used for creating a new connection with the socket.
 *
 * @author riccardo
 */
class RemoteUser extends Thread {

    private InterfaceServer company;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static int counter = 0;
    private int progressiven;
    private Gson gson = new Gson();
    private boolean stop;
    private Map<String, Command> commands;

    static synchronized private int generateProgressive() {
        return counter++;
    }

    /**
     * Constructs a new remote user for the connection with the socket.
     *
     * @param company
     * @param socket Socket used for the connection.
     *
     */
    RemoteUser(InterfaceServer company, Socket socket) throws IOException {
        this.company = company;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        stop = false;
        registerCommands();
        this.progressiven = generateProgressive();
    }

    /**
     * Send an errore message.
     *
     * @param message Message to be sended.
     */
    private void error(String message) {
        log(Level.WARNING, "Sent error: " + message);
        out.println("ERR " + message);
    }

    /**
     * Create a new table that maps the commands for performing various action.
     *
     */
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

        commands.put("CITYS", new Command() {
            @Override
            public void execute(String args) {
                ArrayList<String> cities;
                cities = company.searchAllCitys();
                out.println(gson.toJson(cities));
            }

        }
        );

        commands.put("CALENDAR", new Command() {
            @Override
            public void execute(String args) {
                Route r = gson.fromJson(args, Route.class);
                ArrayList<Flight> flights = company.searchFlights(r);
                out.println(gson.toJson(flights));
            }
        });

        commands.put("RESERVATION", new Command() {
            @Override
            public void execute(String args) {
                Reservation res = gson.fromJson(args, Reservation.class);
                res = company.makeReservation(res);
                out.println(gson.toJson(res));
            }
        });

        commands.put("EDITTICKET", new Command() {
            @Override
            public void execute(String args) {
                Ticket t = gson.fromJson(args, Ticket.class);
                t = company.editTicket(t);
                out.println(gson.toJson(t));
            }
        });

        commands.put("EDITSEATTICKET", new Command() {
            @Override
            public void execute(String args) {
                Ticket tp = gson.fromJson(args, Ticket.class);
                tp = company.editSeatTicket(tp);
                out.println(gson.toJson(tp));
            }
        });

        commands.put("ROUTES", new Command() {
            @Override
            public void execute(String args) {
                ArrayList<Route> rottes = null;
                Route r = gson.fromJson(args, Route.class);
                //tutte le rotte
                rottes = company.searchRoutes();

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
        //MODIFICA 7set19.13
        commands.put("SEARCHFLIGHTS", new Command() {

            @Override
            public void execute(String args) {
                Flight r = gson.fromJson(args, Flight.class);
                ArrayList<Flight> flights = new ArrayList<>();
                if (r.getCode() == null) {
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    String formatted = format1.format(r.getDateDeparture().getTime());
                    flights = company.searchFlights(r.getRoute().getDepartureCity(), r.getRoute().getDestinationCity(), formatted);
                    out.println(gson.toJson(flights));
                } else {
                    //Flight flight = gson.fromJson(args, Flight.class);
                    //flight = company.searchFlight(flight.getCode());
                    flights.add(company.searchFlight(r.getCode()));

                    out.println(gson.toJson(flights));
                }
            }
        }
        );
        //PICCOLA MODIFICA7set19
        /*
        commands.put("SEARCHFLIGHTCODE", new Command() {
            @Override
            public void execute(String args) {
                try {
                    Flight flight = gson.fromJson(args, Flight.class);
                    flight = company.searchFlight(flight.getCode());
                    out.println(gson.toJson(flight));
                } catch (SQLException ex) {
                    Logger.getLogger(RemoteUser.class.getName()).log(Level.SEVERE, null, ex);
                    out.println(gson.toJson(null));
                }
            }
        }
        );*/

        commands.put("MEALS", new Command() {
            @Override
            public void execute(String args) {
                ArrayList<Meal> meals;
                meals = company.getAllMeals();
                out.println(gson.toJson(meals));
            }
        });

        commands.put("HOLDLUGGAGES", new Command() {
            @Override
            public void execute(String args) {
                ArrayList<HoldLuggage> holdLuggages;
                holdLuggages = company.getAllHoldLuggages();
                out.println(gson.toJson(holdLuggages));
            }
        });

        commands.put("INSURANCES", new Command() {
            @Override
            public void execute(String args) {
                ArrayList<Insurance> insurances;
                insurances = company.getAllInsurances();
                out.println(gson.toJson(insurances));
            }
        });

        commands.put("CHECKIN", new Command() {
            @Override
            public void execute(String args) {
                Ticket tp = gson.fromJson(args, Ticket.class);
                company.setCheckIn(tp.getCode());
                tp = company.getTicket(tp.getCode());
                out.println(gson.toJson(tp));

            }
        });

        commands.put("ISCHECKIN", new Command() {
            @Override
            public void execute(String args) {
                Ticket tp = gson.fromJson(args, Ticket.class);
                Boolean checkIn = company.isCheckIn(tp.getCode());
                out.println(gson.toJson(checkIn));

            }
        });

        commands.put("TICKET", new Command() {
            @Override
            public void execute(String args) {
                Ticket tp = gson.fromJson(args, Ticket.class);
                tp = company.getTicket(tp.getCode());
                out.println(gson.toJson(tp));

            }
        });

        commands.put("GETRESERVATION", new Command() {
            @Override
            public void execute(String args) {
                Reservation res = gson.fromJson(args, Reservation.class);
                res = company.getReservtion(res.getCode());
                out.println(gson.toJson(res));

            }
        });

        commands.put("INSERTFLIGHT", new Command() {
            @Override
            public void execute(String args) {
                Flight flight = gson.fromJson(args, Flight.class);
                flight = company.insertFlight(flight);
                out.println(gson.toJson(flight));

            }
        });

        commands.put("EDITFLIGHT", new Command() {
            @Override
            public void execute(String args) {
                Flight flight = gson.fromJson(args, Flight.class);
                flight = company.editFlight(flight);
                out.println(gson.toJson(flight));

            }
        });

        commands.put("LOGIN", new Command() {
            @Override
            public void execute(String args) {
                String userpass = gson.fromJson(args, String.class);
                Boolean b = company.checkLogin(userpass);
                out.println(gson.toJson(b));
            }
        });

        commands.put("SENDMAIL", new Command() {
            @Override
            public void execute(String args) {
                String MailDetail = args;
                MailDetail = MailDetail.replaceAll("&%", "\n");
                String[] splittedMailDetail = MailDetail.split("\t");
                System.out.println(args);

                Email.sendMail(new Email(splittedMailDetail[0], splittedMailDetail[1], splittedMailDetail[2]));
                out.println("true");
            }
        });

    }

    /**
     * Perform a specified command.
     *
     * @param command Command to be performed.
     * @param args
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
}

/**
 * A command received from the remote user.
 *
 * @author Andrea Cavagna
 */
interface Command {

    void execute(String args);
}
