/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author cl418377
 */
public class TicketPassenger {

    private String code, ID, name, surname, codeFlight;
    private int nseat, seatClass, codeReservation;
    private double totalPrice;

    public TicketPassenger(String ID, String name, String surname, int nseat, int seatClass) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.seatClass = seatClass;
    }

    public String getCode() {
        return code;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCodeFlight() {
        return codeFlight;
    }

    public int getNseat() {
        return nseat;
    }

    public int getSeatClass() {
        return seatClass;
    }

    public int getCodeReservation() {
        return codeReservation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    

}
