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
    private ArrayList<Passenger> passengers;
    private String prenotationCode;
    private Customer customer;
    private Boolean checkIn;
    private Flight flight;
    /**
     * Costruttore della classe Reservation. Ci istanzia una nuova prenotazione con tutte le informazioni di 
     * cui abbiamo bisogno, come la lista dei passeggeri e i relativi posti occupati sull'aereo.
     * 
     * @param flight volo di cui si vuole effettuare una prenotazione
     * @param passengers lista di passeggeri
     * @param seatsPosition posizione dei nostri posti
     * @param prenotationCode codice di prenotazione
     * @param customer cliente che sta effettuando la prenotazione
     */
    public Reservation(Flight flight,ArrayList<Passenger> passengers, ArrayList<Integer> seatsPosition, String prenotationCode, Customer customer) {
        this.flight=flight;
        this.passengers = passengers;
        this.prenotationCode = flight.getCode()+"PR"+flight.getProgressiveReservation();
        this.customer=customer;
        for(int i=0;i<passengers.size();i++){
            passengers.get(i).getTicket().setCode(flight.getCode()+"COD"+flight.getProgressiveTicket());
            passengers.get(i).getTicket().setnPosition(seatsPosition.get(i));
            passengers.get(i).getTicket().addPrice(flight.getPrice());
            flight.addProgressiveTicket();
            flight.insertSeat(seatsPosition.get(i));
            if(flight.getAirplane().getNumberFirstClass()>=seatsPosition.get(i)){ //incremento il prezzo se è in prima classe
                passengers.get(i).getTicket().addPrice(Flight.pricePrimeClass);
            }
        }
        this.customer=customer;
        this.checkIn=false;
    }
    /**
     * 
     * @return rappresentazione scritta della prenotazione.
     */
    public String toString(){
        String s="";
        s+="Codice prenotazione: "+prenotationCode+"\n"+"Volo:"+flight.toString()+"\n"+"Prenotato da: "+customer.toString()+"\n";
        s+="Passeggeri: \n";
        for(Passenger p : passengers){
            s+=p.toString();
        }
        return s+"\n";
    }
    /**
     * 
     * @return codice di prenotazione
     */
    public String getPrenotationCode(){
        return prenotationCode;
    }
    /**
     * 
     * @return volo della prenotazione
     */
    public Flight getFlight(){
        return flight;
    }
    /**
     * 
     * @return lista di passeggeri della prenotazione
     */
    public ArrayList<Passenger> getPassenger(){
        return passengers;
    }
    /**
     * 
     * @return colui che acquista i biglietti
     */
    public Customer getCustomer(){
        return customer;
    }   
    
}
