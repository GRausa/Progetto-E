/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *
 * @author cl418377
 */
public class Passenger {
    private String CI;
    private String surname;
    private String name;
    private double additionalPrice;
    private Ticket ticket;
    private Insurance insurance;
    private HoldLuggage holdLuggage;
    private Meal meal;

    public Passenger(String CI, String surname, String name) {
        this.CI = CI;
        this.surname = surname;
        this.name = name;
    }
    
    public String toString(){
        return "Card Identity: "+CI+"\nCognome: "+surname+"\nNome: "+name+"\nTicket: "+ticket.toString(); //errore se stampo ticket
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
        return additionalPrice;
    }
    
    public void addTotalPrice(double addPrice){
        this.additionalPrice+=addPrice;
    }
    
    public void setTicket(Ticket ticket){
        this.ticket=ticket;
    }
    
    public Ticket getTicket(){
        return ticket;
    }

  
    
    
    
}
