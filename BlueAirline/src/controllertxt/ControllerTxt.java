/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllertxt;

import administrators.ControllerAdministrator;
import clients.ControllerClient;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import objects.Flight;
import objects.Reservation;
import objects.Route;
import objects.TicketPassenger;

/**
 *
 * @author Giovanni
 */
public class ControllerTxt {

    ControllerClient CC;
    ControllerAdministrator CA;

    public ControllerTxt() {
        CC = new ControllerClient();
        CA = new ControllerAdministrator();
    }

    public void riempi() throws SQLException {
        ArrayList<Flight> flights = CC.riempi();

    }

    public void searchRoutes() throws SQLException {
        ArrayList<Route> routes = CC.searchRoutes();
        for (Route r : routes) {
            System.out.println(r);
        }
    }

    public void searchFlights() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Ricerca volo per citta': (Partenza - Arrivo - Data(AAAA-MM-GG))");
        String s = input.nextLine();
        String[] vet = s.split("\t");
        ArrayList<Flight> flights = CC.searchFlights(vet[0], vet[1], vet[2]);
        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    public void makeReservation() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Effettua prenotazione (CodiceVolo - Email - Numero)");
        String s = input.nextLine();
        String[] vet = s.split("\t");
        Reservation reservation = new Reservation(vet[0], vet[1], vet[2]);
        System.out.println("Inserisci numero passeggeri: ");
        int n = input.nextInt();
        int i = 0;
        ArrayList<TicketPassenger> passengers = new ArrayList<>();
        while (i < n) {
            Scanner input2 = new Scanner(System.in);
            System.out.println("Inserisci passeggero (ID - Nome - Cognome - NPosto - Classe - Aggiunte");
            s = input2.nextLine();
            String[] vet1 = s.split("\t");
            
            //aggiunte
            ArrayList<String> meals = new ArrayList<>();
            ArrayList<String> holdLuggages = new ArrayList<>();
            ArrayList<String> insurances = new ArrayList<>();
            for (int j = 5; j < vet1.length; j++) {
                String v = vet1[j];
                switch (vet1[j].charAt(0)) {
                    case 'M':
                        meals.add(v);
                        break;
                    case 'H':
                        holdLuggages.add(v);
                        break;
                    case 'I':
                        insurances.add(v);
                        break;
                    default:
                        break;
                }
            }
            TicketPassenger p = new TicketPassenger(vet1[0], vet1[1], vet1[2], Integer.parseInt(vet1[3]), Integer.parseInt(vet1[4]));
            p.setMeals(meals);
            p.setHoldLuggages(holdLuggages);
            p.setInsurances(insurances);
            passengers.add(p);
            i++;
        }
        CC.makeReservation(reservation, passengers);
    }
}
