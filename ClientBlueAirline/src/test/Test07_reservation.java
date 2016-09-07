/*
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Ticket;

/**
 *
 * @author Giovanni
 */
public class Test07_reservation {

    public static void main(String[] args)  {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");

        //CERCA VOLO New York - Roma 15 Settembre 2016
        Route route = new Route("New York", "Roma");
        GregorianCalendar date = new GregorianCalendar(2016, 8, 15); //15 Settembre 2016 (mese=mese-1)
        Flight tmpflight = new Flight(route, date);
        Flight[] flights = client.searchFlights(tmpflight);
        for (Flight v : flights) {
            System.out.println(v);
        }

        //SCELTA VOLO--> A4D5F6        
        Flight f = flights[0];
        System.out.println(f.printAllSeats());
        f = client.searchFlights(f)[0];
        Meal[] meals = client.getAllMeals();
        Insurance[] insurances = client.getAllInsurances();
        HoldLuggage[] holdLuggages = client.getAllHoldLuggages();

        //TICKET 01
        ArrayList<Meal> mealsTp1 = new ArrayList();
        mealsTp1.add(meals[0]);
        mealsTp1.add(meals[1]);

        ArrayList<Insurance> insurancesTp1 = new ArrayList();
        insurancesTp1.add(insurances[0]);

        ArrayList<HoldLuggage> holdLuggagesTp1 = new ArrayList();

        Ticket t1 = new Ticket("ID1", "Marco", "Rossi", 8, 1, f.getCode(), f.getPrice(), mealsTp1, insurancesTp1, holdLuggagesTp1);

        //TICKET 02
        ArrayList<Meal> mealsTp2 = new ArrayList();
        mealsTp1.add(meals[1]);
        mealsTp1.add(meals[2]);

        ArrayList<Insurance> insurancesTp2 = new ArrayList();

        ArrayList<HoldLuggage> holdLuggagesTp2 = new ArrayList();
        holdLuggagesTp2.add(holdLuggages[0]);
        holdLuggagesTp2.add(holdLuggages[1]);

        Ticket t2 = new Ticket("ID2", "Lucia", "Rossi", 9, 1, f.getCode(), f.getPrice(), mealsTp2, insurancesTp2, holdLuggagesTp2);

        ArrayList<Ticket> tickets = new ArrayList();
        tickets.add(t1);
        tickets.add(t2);

        //RESERVATION
        Reservation reservation = new Reservation(f.getCode(), "+39 328******", "m***@gmail.con", tickets);
        if (f.getSeats().get(t1.getNseat() - 1).getTicket() == null & f.getSeats().get(t2.getNseat() - 1).getTicket() == null) {
            reservation = client.makeReservation(reservation);
            System.out.println(reservation.printReservation("\n"));
            System.out.println(reservation.printTickets("\n"));
        } else {
            System.out.println("Prenotazione già effettuata");
        }
    }

}
