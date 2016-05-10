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
    private String code;
    private String name;
    private double price;
    private int timeMeal; //pasto che vale per tempo (timeMeal) maggiore di volo
    
    public Meal (String code, String name, double price, int timeMeal){
        this.code=code;
        this.name=name;
        this.price=price;
        this.timeMeal=timeMeal;
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
    
    public String toString(){
        return code+" "+name+" "+price+" €";
    }
}
