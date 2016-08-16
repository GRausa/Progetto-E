/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class TicketPassenger {

    private String code, ID, name, surname, codeFlight;
    private int nseat, codeReservation, classe;
    private double priceFlight;
    private ArrayList<Meal> meals;
    private ArrayList<HoldLuggage> holdLuggages;
    private ArrayList<Insurance> insurances;
    boolean checkIn;

    public TicketPassenger(String ID, String name, String surname, int nseat, int classe, String codeFlight, double priceFlight) {
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

    public TicketPassenger(String code, double priceFlight, String ID, String name, String surname, String codeFlight, int nseat, int codeReservation, int classe, boolean checkIn) {
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
    

    @Override
    public String toString() {
        return "TicketPassenger{" + "code=" + code + ", ID=" + ID + ", name=" + name + ", surname=" + surname + ", codeFlight=" + codeFlight + ", nseat=" + nseat + ", codeReservation=" + codeReservation + ", totalPrice=" + this.getTotalPrice() + ", meals=" + meals + ", holdLuggages=" + holdLuggages + ", insurances=" + insurances + '}';
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
    
    
}
