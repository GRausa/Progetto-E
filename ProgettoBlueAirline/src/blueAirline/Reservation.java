/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 *La classe Reservation permette di effettuare una prenotazione di un biglietto e memorizzare le relative
 * informazioni nel database.
 * 
 * @author cl418377
 */
public class Reservation {
    /**
     * Lista di passeggeri che vogliono fare una prenotazione. I passeggeri sono coloro che riservano un posto sull'aereo, il 
     * customer è colui che fa la prenotazione. Il customer può anche essere una persona esterna e quindi non essere fra i 
     * passeggeri.
     */
    private ArrayList<Passenger> passengers;
    /**
     * Codice identificativo della prenotazione
     */
    private String prenotationCode;
    /**
     * Colui che effettua la prenotazione
     * @see blueAirline.Customer
     */
    private Customer customer;
    /**
     * Carattere booleano che ci dice se è già stato effettuato il check-in
     */
    private Boolean checkIn;
    /**
     * Volo di cui vogliamo effettuare una prenotazione
     */
    private Flight flight;
    /**
     * Costruttore della classe Reservation. Ci istanzia una nuova prenotazione con tutte le informazioni di 
     * cui abbiamo bisogno, come la lista dei passeggeri e i relativi posti occupati sull'aereo.
     * @param flight volo di cui si vuole effettuare una prenotazione
     * @param passengers lista di passeggeri
     * @param seatsPosition posizione dei nostri posti
     * @param prenotationCode codice di prenotazione
     * @param customer cliente che sta effettuando la prenotazione
     */
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
    /**
     * 
     * @return rappresentazione scritta della nostra prenotazione.
     */
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
