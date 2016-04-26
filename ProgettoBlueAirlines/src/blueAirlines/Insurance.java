/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

/**
 *
 * @author cl418377
 */
public class Insurance { //ASSICURAZIONE
    private String name;
    private double price;
    
    public Insurance (String name, double price){
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
