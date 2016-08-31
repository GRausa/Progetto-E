/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 *
 * @author Giovanni
 */
public class Reservation {
    private ArrayList<Ticket> tickets;
    private int code;
    private String email, number, codeFlight;

    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    public Reservation(String codeFlight, String number, String email,ArrayList<Ticket> p) {
        
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
        this.tickets=p;
    }
    
    public Reservation(int code){
        this.code=code;
    }

    public ArrayList<Ticket> getPassengers() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> passengers) {
        this.tickets = passengers;
    }
    
    public int getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }


    public String getCodeFlight() {
        return codeFlight;
    }
    
    public String printReservation(){
        String s="";
        s+="Prenotazione: "+code+" ("+email+" "+number+")\n";
        s+="Volo: "+codeFlight+"\n";
        s+="Numero passeggeri: "+tickets.size();
        return s;
    }   
 
    public void setCode(int code) {
        this.code = code;
    }

    public String printTickets() {
        String s="";
        for(Ticket tp:tickets){
            s+=tp.printTicketPassenger()+"\n";
        }
        return s;
    }
    
    
    
}
