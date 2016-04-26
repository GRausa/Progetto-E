/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

/**
 *
 * @author cl418377
 */
public class Passenger {
    private String CI;
    private String surname;
    private String name;
    private double totalPrice;
    
    private Ticket ticket;
    private Insurance insurance;
    private HoldLuggage holdLuggage;
    private Meal meal;

    public Passenger(String CI, String surname, String name, double totalPrice) {
        this.CI = CI;
        this.surname = surname;
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public String getCI() {
        return CI;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    
    
}
