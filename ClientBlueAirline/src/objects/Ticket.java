/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 *La classe Ticket indica un biglietto di un volo della compagnia.
 * 
 * @author Giovanni
 */
public class Ticket {

    private String code, ID, name, surname, codeFlight;
    private int nseat, codeReservation, classe;
    private double priceFlight;
    private ArrayList<Meal> meals;
    private ArrayList<HoldLuggage> holdLuggages;
    private ArrayList<Insurance> insurances;
    private boolean checkIn;
    /**
     * Istanzia un nuovo biglietto
     * 
     * @param ID ID del passeggero
     * @param name nome del passeggero
     * @param surname cognome del passeggero
     * @param nseat numero del posto a sedere
     * @param classe classe del posto a sedere
     * @param codeFlight codice del volo
     * @param priceFlight prezzo del volo
     */
    public Ticket(String ID, String name, String surname, int nseat, int classe, String codeFlight, double priceFlight) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.meals = new ArrayList<>();
        this.holdLuggages = new ArrayList<>();
        this.insurances = new ArrayList<>();
        this.classe=classe;
        this.codeFlight=codeFlight;
        this.priceFlight=priceFlight;
        this.checkIn=false;
    }
    /**
     * Istanzia un nuovo biglietto.
     * 
     * @param ID ID del passeggero
     * @param name nome del passeggero
     * @param surname cognome del passeggero
     * @param nseat numero del posto a sedere
     * @param classe classe del posto a sedere
     * @param codeFlight codice del volo
     * @param priceFlight prezzo del volo
     * @param meal lista dei pasti richiesti dal passeggero
     * @param insurance lista delle assicurazioni richiesti dal passeggero
     * @param holdluggage lista dei bagagli da stiva richiesti dal passeggero
     */
    public Ticket(String ID, String name, String surname, int nseat, int classe, String codeFlight, double priceFlight,ArrayList<Meal> meal,ArrayList<Insurance> insurance,ArrayList<HoldLuggage> holdluggage) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.meals = new ArrayList<>();
        this.holdLuggages = new ArrayList<>();
        this.insurances = new ArrayList<>();
        this.classe=classe;
        this.codeFlight=codeFlight;
        this.priceFlight=priceFlight;
        this.checkIn=false;
        this.meals= meal;
        this.insurances=insurance;
        this.holdLuggages=holdluggage;
    }
    /**
     * Istanzia un nuovo biglietto.
     * 
     * @param code 
     */
    public Ticket(String code){
        this.code=code;
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

    public int getCodeReservation() {
        return codeReservation;
    }
    
    public double getPriceFlight(){
        return priceFlight;
    }
    
    /**
     * 
     * @return prezzo totale comprensivo delle varie aggiunte
     */
    public double getTotalPrice() {
        double totalPrice=priceFlight;
        if(classe==1){
            totalPrice+=Flight.COSTOPRIMACLASSE;
        }
        for(Meal m :meals){
            totalPrice+=m.getPrice();
        }
        for(Insurance i:insurances){
            totalPrice+=i.getPrice();
        }
        for(HoldLuggage hl:holdLuggages){
            totalPrice+=hl.getPrice();
        }
        return totalPrice;
    }

    public void addMeals(Meal meal) {
        this.meals.add(meal);
    }

    public void addHoldLuggage(HoldLuggage holdLuggage) {
        this.holdLuggages.add(holdLuggage);
    }

    public void addInsurance(Insurance insurance) {
        this.insurances.add(insurance);
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }

    public ArrayList<HoldLuggage> getHoldLuggages() {
        return holdLuggages;
    }

    public ArrayList<Insurance> getInsurances() {
        return insurances;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public void setNSeat(int nseat){
        this.nseat=nseat;
    }

    public void setCodeFlight(String codeFlight) {
        this.codeFlight = codeFlight;
    }
    
    /*
    @Override
    public String toString() {
        return "TicketPassenger{" + "code=" + code + ", ID=" + ID + ", name=" + name + ", surname=" + surname + ", codeFlight=" + codeFlight + ", nseat=" + nseat + ", codeReservation=" + codeReservation + ", totalPrice=" + this.getTotalPrice() + ", meals=" + meals + ", holdLuggages=" + holdLuggages + ", insurances=" + insurances + '}';
    }
    */
    
    /**
     * 
     * @return descrizione scritta del biglietto
     */
    public String printTicketPassenger(String tab){
        String s = "Codice Biglietto"+code+" "+name+" "+surname+" "+this.printTicketWithoutCode(tab);
        return s;
    }
    
    /**
     * 
     * @return descrizione scritta del biglietto senza il codice biglietto
     */
    public String printTicketWithoutCode(String tab){
        double priceAggiunte=0;
        String s = ID+tab+"Volo: "+codeFlight+" Posto: "+nseat+" ("+classe+" classe)"+tab+"Aggiunte:"+tab;
        boolean c=false;
        for(Meal m:meals){
            s+=m.toString()+" ";
            priceAggiunte += m.getPrice();
            s+=tab;
            c=true;
        }
        for(HoldLuggage hl:holdLuggages){
            s+=hl.toString()+" ";
            priceAggiunte += hl.getPrice();
            s+=tab;
            c=true;
        }
        for(Insurance in:insurances){
            s+=in.toString()+" ";
            priceAggiunte += in.getPrice();
            s+=tab;
            c=true;
        }
        if(!c){
            s+="0";
        }
        if(classe==1){
            s+=tab+"Prezzo biglietto: "+(priceFlight+priceAggiunte+Flight.COSTOPRIMACLASSE)+"€ (Volo: "+priceFlight+"€ + Prima Classe: "+Flight.COSTOPRIMACLASSE+"€ + Aggiunte: "+priceAggiunte+"€)";
        }
        else{
            s+=tab+"Prezzo biglietto: "+(priceFlight+priceAggiunte)+"€ (Volo "+priceFlight+"€ + Aggiunte: "+priceAggiunte+"€)";
        }
        
        if(checkIn)
            s+=tab+"Check-In: Effettuato";
        else
            s+=tab+"Check-In: Non effettuato";
        return s;
    }   

    public boolean isCheckIn() {
        return checkIn;
    }

  
    
    
    
}
