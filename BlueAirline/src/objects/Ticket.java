/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * La classe Ticket indica un biglietto di un volo della compagnia.
 * 
 * @author cl418377
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
     * Istanzia un nuovo biglietto.
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
     * @param code codice del biglietto
     * @param priceFlight prezzo del volo
     * @param ID ID del passeggero
     * @param name nome del passeggero
     * @param surname cognome del passeggero
     * @param codeFlight codice del volo
     * @param nseat numero del posto a sedere 
     * @param codeReservation codice della prenotazione
     * @param classe classe del posto 
     * @param checkIn variabile che indica se il check-in è stato effettuato
     */
    public Ticket(String code, double priceFlight, String ID, String name, String surname, String codeFlight, int nseat, int codeReservation, int classe, boolean checkIn) {
        this.code = code;
        this.priceFlight = priceFlight;
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.codeFlight = codeFlight;
        this.nseat = nseat;
        this.codeReservation = codeReservation;
        this.classe = classe;
        this.checkIn = checkIn;
    }
    /**
     * Istanzia un nuovo biglietto a partire dal suo codice.
     * 
     * @param code codice del biglietto
     */
    public Ticket(String code){
        this.code = code;
    }
/*
    @Override
    public String toString() {
        return "TicketPassenger{" + "code=" + code + ", ID=" + ID + ", name=" + name + ", surname=" + surname + ", codeFlight=" + codeFlight + ", nseat=" + nseat + ", codeReservation=" + codeReservation + ", totalPrice=" + this.getTotalPrice() + ", meals=" + meals + ", holdLuggages=" + holdLuggages + ", insurances=" + insurances + '}';
    }
*/
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
    
   public void addMeals(String meal) {
        this.meals.add(new Meal(meal));
    }

    public void addHoldLuggage(String holdLuggage) {
        this.holdLuggages.add(new HoldLuggage(holdLuggage));
    }

    public void addInsurance(String insurance) {
        this.insurances.add(new Insurance(insurance));
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

    public void setCodeReservation(int codeReservation) {
        this.codeReservation = codeReservation;
    }    

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }

    public void setHoldLuggages(ArrayList<HoldLuggage> holdLuggages) {
        this.holdLuggages = holdLuggages;
    }

    public void setInsurances(ArrayList<Insurance> insurances) {
        this.insurances = insurances;
    }
    
    public boolean isCheckIn() {
        return checkIn;
    }    
    /**
     * 
     * @return descrizione scritta del biglietto
     */
    public String printTicketPassenger(){
        String s = "Codice Biglietto"+code+" "+name+" "+surname+" "+this.printTicketWithoutCode();
        return s;
    }
    public String printTicketWithoutCode(){
        double priceAggiunte=0;
        String s = ID+"\nVolo: "+codeFlight+" Posto: "+nseat+" ("+classe+" classe)\nAggiunte:\n";
        boolean c=false;
        for(Meal m:meals){
            s+=m.toString()+" ";
            priceAggiunte += m.getPrice();
            s+="\n";
            c=true;
        }
        for(HoldLuggage hl:holdLuggages){
            s+=hl.toString()+" ";
            priceAggiunte += hl.getPrice();
            s+="\n";
            c=true;
        }
        for(Insurance in:insurances){
            s+=in.toString()+" ";
            priceAggiunte += in.getPrice();
            s+="\n";
            c=true;
        }
        if(!c){
            s+="0";
        }
        if(classe==1){
            s+="\nPrezzo biglietto: "+(priceFlight+priceAggiunte+Flight.COSTOPRIMACLASSE)+"€ (Volo: "+priceFlight+"€ + Prima Classe: "+Flight.COSTOPRIMACLASSE+"€ + Aggiunte: "+priceAggiunte+"€)";
        }
        else{
            s+="\nPrezzo biglietto: "+(priceFlight+priceAggiunte)+"€ (Volo "+priceFlight+"€ + Aggiunte: "+priceAggiunte+"€)";
        }
        
        if(checkIn)
            s+="\nCheck-In: Effettuato";
        else
            s+="\nCheck-In: Non effettuato";
        return s;
    }   

    
    
}
