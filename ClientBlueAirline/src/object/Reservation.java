/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author Giovanni
 */
public class Reservation {
    private ArrayList<TicketPassenger> passengers;
    private int code;
    private String email, number, codeFlight;

    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    public Reservation(String codeFlight, String number, String email,ArrayList<TicketPassenger> p) {
        
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
        this.passengers=p;
    }
    
    public Reservation(int code){
        this.code=code;
    }

    public ArrayList<TicketPassenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<TicketPassenger> passengers) {
        this.passengers = passengers;
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

    @Override
    public String toString() {
        return "Reservation{" + "passengers=" + passengers.get(0) + ", code=" + code + ", email=" + email + ", number=" + number + ", codeFlight=" + codeFlight + '}';
    }
    
    public String printReservation(){
        String s="";
        s+="Prenotazione: "+code+" ("+email+" "+number+")\n";
        s+="Volo: "+codeFlight+"\n";
        s+="Numero passeggeri: "+passengers.size();
        return s;
    }
    
 
    public void setCode(int code) {
        this.code = code;
    }

    public String printPassengers() {
        String s="";
        for(TicketPassenger tp:passengers){
            s+=tp.printTicketPassenger()+"\n";
        }
        return s;
    }
    
    
    
}
