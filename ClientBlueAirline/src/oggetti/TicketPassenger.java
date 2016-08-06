/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

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

    public void addMeals(String meal) {
        this.meals.add(meal);
    }

    public void addHoldLuggage(String holdLuggage) {
        this.holdLuggages.add(holdLuggage);
    }

    public void addInsurance(String insurance) {
        this.insurances.add(insurance);
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

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "TicketPassenger{" + "code=" + code + ", ID=" + ID + ", name=" + name + ", surname=" + surname + ", codeFlight=" + codeFlight + ", nseat=" + nseat + ", codeReservation=" + codeReservation + ", totalPrice=" + totalPrice + ", meals=" + meals + ", holdLuggages=" + holdLuggages + ", insurances=" + insurances + '}';
    }
    
    
    
}