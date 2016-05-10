/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

import java.util.ArrayList;

/**
 * La classe Passenger rappresenta un passeggero e offre metodi per la gestione dei biglietti.
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
    private Meal meal;
    private ArrayList<Meal> arrayMeals;
    private ArrayList<HoldLuggage> arrayHoldLuggages;


    
    public Passenger(String CI, String surname, String name) {
        this.CI = CI;
        this.surname = surname;
        this.name = name;
        this.arrayMeals=new ArrayList<>();
        this.arrayHoldLuggages=new ArrayList<>();
        this.ticket=new Ticket(null,0,0);
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
    
    public Ticket getTicket(){
        return ticket;
    }
    
    public void addMeal(Meal meal){
        arrayMeals.add(meal);
        this.ticket.addPrice(meal.getPrice());
    }

    public void addHoldLuggage(HoldLuggage holdLuggage) {
        arrayHoldLuggages.add(holdLuggage);
        this.ticket.addPrice(holdLuggage.getPrice());
    }

    public void addInsurance(Insurance ins) {
        this.insurance=insurance;
        this.ticket.addPrice(ins.getPrice());
    }
    
    public String toStringPrintFile(){
        String s="";
        s+=CI+"\t"+surname+"\t"+name+"\n";
        for(Meal m : arrayMeals){
            s+=m.getCode()+"\t";
        }
        for(HoldLuggage hd: arrayHoldLuggages){
            s+=hd.getCode()+"\t";
        }
        if(insurance!=null)
            s+=insurance.getCode();
        s+="\n"+ticket.getnPosition();
        return s;
    }
}
