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


    /**
     * Istanzia un nuovo passeggero.
     * 
     * @param CI Codice presente sulla carta d'identità
     * @param surname cognome del passeggero
     * @param name nome del passeggero
     */
    public Passenger(String CI, String surname, String name) {
        this.CI = CI;
        this.surname = surname;
        this.name = name;
        this.arrayMeals=new ArrayList<>();
        this.arrayHoldLuggages=new ArrayList<>();
        this.ticket=new Ticket(null,0,0);
    }
    /**
     * 
     * @return rappresentazione scritta del passeggero 
     */
    public String toString(){
        return "Card Identity: "+CI+"\nCognome: "+surname+"\nNome: "+name+"\nTicket: "+ticket.toString(); //errore se stampo ticket
    }
    /**
     * 
     * @return codice presente sulla carta d'identità 
     */
    public String getCI() {
        return CI;
    }
    /**
     * 
     * @return cognome del passeggero
     */
    public String getSurname() {
        return surname;
    }
    /**
     * 
     * @return nome del passeggero
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return prezzo totale che deve pagare il passeggero per effettuare la sua prenotazione
     */
    public double getTotalPrice() {
        return additionalPrice;
    }
    /**
     * Aggiunge al prezzo della prenotazione i costi degli extra (es: pasto, bagaglio da stiva).
     * 
     * @param addPrice costo degli extra
     */
    public void addTotalPrice(double addPrice){
        this.additionalPrice+=addPrice;
    }
    /**
     * 
     * @return biglietto del passeggero
     */
    public Ticket getTicket(){
        return ticket;
    }
    /**
     * Aggiunge un pasto alla propria prenotazione
     * 
     * @param meal pasto da aggiungere
     */
    public void addMeal(Meal meal){
        arrayMeals.add(meal);
        this.ticket.addPrice(meal.getPrice());
    }
    /**
     * Aggiunge un bagaglio da stiva alla propria prenotazione
     * 
     * @param holdLuggage bagaglio da stiva da aggiungere
     */
    public void addHoldLuggage(HoldLuggage holdLuggage) {
        arrayHoldLuggages.add(holdLuggage);
        this.ticket.addPrice(holdLuggage.getPrice());
    }
    /**
     * Aggiunge l'assicurazione alla propria prenotazione
     * 
     * @param ins assicurazione che il passeggero vuole avere
     */
    public void addInsurance(Insurance ins) {
        this.insurance=insurance;
        this.ticket.addPrice(ins.getPrice());
    }
    /**
     * 
     * @return rappresentazione scritta del passeggero da stampare nel file delle prenotazioni
     */
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
