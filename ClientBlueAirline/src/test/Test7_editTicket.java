/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import java.util.ArrayList;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Ticket;

/**
 *
 * @author Giovanni
 */
public class Test7_editTicket {
    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        
        Ticket t = new Ticket("A4D5F621");
        t = client.getTicket(t);
        Flight f = client.searchFlight(new Flight(t.getCodeFlight()));
        
        
        Meal[] meals = client.getAllMeals();
        Insurance[] insurances = client.getAllInsurances();
        HoldLuggage[] holdLuggages = client.getAllHoldLuggages();
        
        System.out.println(t.printTicketPassenger("\n"));
        
        //POSTO 10
        if(f.getSeats().get(10-1).getTicket()==null){
            t.setNSeat(10);

            t.addMeals(meals[0]);
            t.addMeals(meals[3]);

            t.addInsurance(insurances[2]);

            t.addHoldLuggage(holdLuggages[1]);

            t=client.editTicket(t);
            
            System.out.println("Modifica effettuata\n"+t.printTicketPassenger("\n"));
        }
        else{
            System.out.println("Posto occupato");
        }
    
    }
    
}
