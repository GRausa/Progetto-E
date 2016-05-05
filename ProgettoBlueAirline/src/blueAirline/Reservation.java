/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class Reservation {
    private ArrayList<Passenger> passengers;
    private String prenotationCode;
    private Customer customer;
    private Boolean checkIn;
    private Flight flight;

    public Reservation(Flight flight,ArrayList<Passenger> passengers, ArrayList<Integer> seatsPosition, String prenotationCode, Customer customer) {
        this.flight=flight;
        this.passengers = passengers;
        this.prenotationCode = "PR"+flight.getProgressiveReservation();
        flight.addProgressiveReservation();
        this.customer=customer;
        for(int i=0;i<passengers.size();i++){
            passengers.get(i).setTicket(new Ticket("COD"+flight.getProgressiveTicket(), flight.getPrice(), seatsPosition.get(i)));
            flight.addProgressiveTicket();
            flight.insertSeat(seatsPosition.get(i));
        }
        this.customer=customer;
        this.checkIn=false;
    }
    
    public String toString(){
        String s="";
        s+="Codice prenotazione: "+prenotationCode+"\n"+"Volo:"+flight.toString()+"\n"+"Prenotato da:"+customer.getEmail()+" "+customer.getNumber()+"\n";
        s+="Passeggeri: ";
        for(Passenger p : passengers){
            s+=p.toString();
        }
        return s+"\n";
    }
    
    
}
