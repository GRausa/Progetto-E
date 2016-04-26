/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *
 * @author cl418377
 */
public class HoldLuggage {
    private Double price;
    private Double kg;
    
    public HoldLuggage (Double kg, Double price){
        this.kg=kg;
        this.price=price;
    }

    public Double getKg() {
        return kg;
    }

    public Double getPrice() {
        return price;
    }
}
