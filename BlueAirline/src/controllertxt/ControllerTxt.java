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

    public void searchRoutes() throws SQLException {
        ArrayList<Route> routes = CC.searchRoutes();
        for (Route r : routes) {
            System.out.println(r);
        }
    }

    public void searchFlights() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Ricerca volo per citta': (Partenza /tab/ Arrivo /tab/ Data(AAAA-MM-GG))");
        String s = input.nextLine();
        String[] vet = s.split("\t");
        ArrayList<Flight> flights = CC.searchFlights(vet[0], vet[1], vet[2]);
        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    public void makeReservation() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Effettua prenotazione (CodiceVolo /tab/ Email /tab/ Numero)");
        String s = input.nextLine();
        String[] vet = s.split("\t");
        Reservation reservation = new Reservation(vet[0], vet[1], vet[2]);
        System.out.println("Inserisci numpero passeggeri: ");
        int n = input.nextInt();
        int i = 0;
        ArrayList<TicketPassenger> passengers = new ArrayList<>();
        while (i < n) {
            System.out.println("Inserisci passeggero (ID /tab/ Nome /tab/ Cognome /tab/ NPosto /tab/ Classe");
            s = input.nextLine();
            vet = s.split("\t");
            TicketPassenger p = new TicketPassenger(vet[0], vet[1], vet[2], Integer.parseInt(vet[3]), Integer.parseInt(vet[4]));
            passengers.add(p);
            i++;
        }
        
        CC.makeReservation(reservation,passengers);
        //System.out.println("Prenotazione effettuata, codice prenotazione: " + (CC.makeReservation(vet[0], vet[1], vet[2])).getCode());
    }

}
