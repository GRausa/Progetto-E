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
    private ArrayList<TicketPassenger> passengers;
    private int code;
    private String email, number, codeFlight;

    public Reservation(String codeFlight, String email, String number) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    public Reservation(int code, String email, String number, String codeFlight) {
        this.code = code;
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
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
    
    

    public void setCode(int code) {
        this.code = code;
    }
    
    
    
}
