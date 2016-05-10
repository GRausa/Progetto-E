/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Meal indica la presenza del pasto. Non in tutti i voli si ha la possibilità di scegliere il pasto.
 *
 * @author cl418377
 */
public class Meal {
    /**
     * Tipo di pasto
     */
    private String name;
    /**
     * Prezzo del pasto
     */
    private double price;
    
    public Meal (String name, double price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
