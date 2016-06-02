/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

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
    
    public Reservation(String codeFlight,String email, String number, ArrayList<TicketPassenger> p) {
        this.code = code;
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
        this.passengers=p;
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
    public String toString(){
        return code+" "+email+" "+number+" "+codeFlight;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
    
}
