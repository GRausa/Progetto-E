/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.util.ArrayList;

/**
 * Represents a ticket associated to a one and only seat on a specific flight on
 * an airplane. A passengers can customize the ticket with supplements.
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
     * Constructs a ticket with the specified informations. CheckIn set to false
     * and no supplements.
     *
     * @param ID Number of identity document of the passenger.
     * @param name Name of the passenger.
     * @param surname Surname of the passenger.
     * @param nseat Seat number.
     * @param classe Class of the seat.
     * @param codeFlight Code of the flight.
     * @param priceFlight Price of the flight.
     */
    public Ticket(String ID, String name, String surname, int nseat, int classe, String codeFlight, double priceFlight) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.meals = new ArrayList<>();
        this.holdLuggages = new ArrayList<>();
        this.insurances = new ArrayList<>();
        this.classe = classe;
        this.codeFlight = codeFlight;
        this.priceFlight = priceFlight;
        this.checkIn = false;
    }

    /**
     * Constructs a ticket with the specified informations. CheckIn set to false
     * and supplements added.
     *
     * @param ID Number of identity document of the passenger.
     * @param name Name of the passenger.
     * @param surname Surname of the passenger.
     * @param nseat Seat number.
     * @param classe Class of the seat.
     * @param codeFlight Code of the flight.
     * @param priceFlight Price of the flight.
     * @param meal List of meals required by the passenger.
     * @param insurance List of insurances required by the passenger.
     * @param holdluggage List of hold luggages required by the passenger.
     */
    public Ticket(String ID, String name, String surname, int nseat, int classe, String codeFlight, double priceFlight, ArrayList<Meal> meal, ArrayList<Insurance> insurance, ArrayList<HoldLuggage> holdluggage) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.nseat = nseat;
        this.meals = new ArrayList<>();
        this.holdLuggages = new ArrayList<>();
        this.insurances = new ArrayList<>();
        this.classe = classe;
        this.codeFlight = codeFlight;
        this.priceFlight = priceFlight;
        this.checkIn = false;
        this.meals = meal;
        this.insurances = insurance;
        this.holdLuggages = holdluggage;
    }

    /**
     * Constructs an empty ticket with only the ticket's code.
     *
     * @param code Code of the ticket.
     */
    public Ticket(String code) {
        this.code = code;
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

    public double getPriceFlight() {
        return priceFlight;
    }

    /**
     *
     * @return Total price of the ticket calculating also the supplements.
     */
    public double getTotalPrice() {
        double totalPrice = priceFlight;
        if (classe == 1) {
            totalPrice += Flight.COSTOPRIMACLASSE;
        }
        for (Meal m : meals) {
            totalPrice += m.getPrice();
        }
        for (Insurance i : insurances) {
            totalPrice += i.getPrice();
        }
        for (HoldLuggage hl : holdLuggages) {
            totalPrice += hl.getPrice();
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

    public void setNSeat(int nseat) {
        this.nseat = nseat;
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
     * @return Written description of the ticket.
     */
    public String printTicketPassenger(String tab) {
        String s = tab + "Codice Biglietto: " + code + " " + name + " " + surname + " " + this.printTicketWithoutCode(tab);
        return s;
    }

    /**
     *
     * @return Written description of the ticket. The code ticket not added.
     */
    public String printTicketWithoutCode(String tab) {
        double priceAggiunte = 0;
        String s = ID + tab + "Volo: " + codeFlight + " Posto: " + nseat + " (" + classe + " classe)" + tab + "Aggiunte:" + tab;
        boolean c = false;
        for (Meal m : meals) {
            s += m.toString() + " ";
            priceAggiunte += m.getPrice();
            s += tab;
            c = true;
        }
        for (HoldLuggage hl : holdLuggages) {
            s += hl.toString() + " ";
            priceAggiunte += hl.getPrice();
            s += tab;
            c = true;
        }
        for (Insurance in : insurances) {
            s += in.toString() + " ";
            priceAggiunte += in.getPrice();
            s += tab;
            c = true;
        }
        if (!c) {
            s += "0" + tab;
        }
        if (classe == 1) {
            s += tab + "Prezzo biglietto: " + (priceFlight + priceAggiunte + Flight.COSTOPRIMACLASSE) + "€ (Volo: " + priceFlight + "€ + Prima Classe: " + Flight.COSTOPRIMACLASSE + "€ + Aggiunte: " + priceAggiunte + "€)";
        } else {
            s += tab + "Prezzo biglietto: " + (priceFlight + priceAggiunte) + "€ (Volo " + priceFlight + "€ + Aggiunte: " + priceAggiunte + "€)";
        }

        if (checkIn) {
            s += tab + "Check-In: Effettuato";
        } else {
            s += tab + "Check-In: Non effettuato";
        }
        return s;
    }

    /**
     *
     * @return <code>true</code> if the passenger already did the check in;
     * <code>false</code> otherwise.
     */
    public boolean isCheckIn() {
        return checkIn;
    }

}
