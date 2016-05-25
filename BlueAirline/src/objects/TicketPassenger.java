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
    private int nseat, codeReservation;
    private double totalPrice;
    private ArrayList<String> meals, holdLuggages, insurances;

    public TicketPassenger(String ID, String name, String surname, int nseat) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.meals = new ArrayList<>();
        this.holdLuggages = new ArrayList<>();
        this.insurances = new ArrayList<>();
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setMeals(ArrayList<String> meals) {
        this.meals = meals;
    }

    public void setHoldLuggages(ArrayList<String> holdLuggages) {
        this.holdLuggages = holdLuggages;
    }

    public void setInsurances(ArrayList<String> insurances) {
        this.insurances = insurances;
    }

    public ArrayList<String> getMeals() {
        return meals;
    }

    public ArrayList<String> getHoldLuggages() {
        return holdLuggages;
    }

    public ArrayList<String> getInsurances() {
        return insurances;
    }
    
    
}
