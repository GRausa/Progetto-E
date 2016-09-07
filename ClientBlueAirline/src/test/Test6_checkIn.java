/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import objects.Ticket;

/**
 *
 * @author Giovanni
 */
public class Test6_checkIn {

    public static void main(String[] args)  {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");

        Ticket tp = client.getTicket(new Ticket("A4D5F621"));
        System.out.println(tp.printTicketPassenger("\n"));

        System.out.println("Check-in:");
        if (!tp.isCheckIn()) {
            client.checkIn(tp);
            System.out.println(tp.printTicketPassenger("\n"));
        } else {
            System.out.println("Il Check-in è già stato effettuato");
        }
    }

}
