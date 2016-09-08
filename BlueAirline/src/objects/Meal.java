/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * Represents a meal on the airplane. Meals are supplements of the ticket. Meals can be added 
 * only in certain flights.
 *
 * @author Giovanni
 */
public class Meal {
    private String code;
    private String name;
    private double price;
    private int timeMeal; // Il pasto è disponibile solo su voli che hanno una certa durata
    /**
     * Constructs a new meal.
     * 
     * @param code Code of the meal.
     * @param name Name of the meal.
     * @param price Price of the meal.
     * @param timeMeal Time of the flight from which the meal will be available.
     */
    public Meal (String code, String name, double price, int timeMeal){
        this.code=code;
        this.name=name;
        this.price=price;
        this.timeMeal=timeMeal;
    }

    /**
     * Constructs a new meal with only the code.
     * 
     * @param code Code of the meal.
     */
    public Meal(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
 
    public int getTimeMeal() {
        return timeMeal;
    }
  
    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

}
