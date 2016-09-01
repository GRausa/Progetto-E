/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * La classe Meal indica la presenza del pasto. Non in tutti i voli si ha la possibilità di scegliere il pasto.
 *
 * @author cl418377
 */
public class Meal {
    private String code;
    private String name;
    private double price;
    private int timeMeal; // Il pasto è disponibile solo su voli che hanno una certa durata
    /**
     * Istanzia un nuovo pasto.
     * 
     * @param code codice del pasto
     * @param name nome del pasto
     * @param price prezzo del pasto in euro
     * @param timeMeal tempo di volo dal quale è disponibile il pasto
     */
    public Meal (String code, String name, double price, int timeMeal){
        this.code=code;
        this.name=name;
        this.price=price;
        this.timeMeal=timeMeal;
    }

    /**
     * Istanzia un nuovo pasto a partire dal suo codice
     * 
     * @param code codice del pasto
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

    /*
    public String toString(){
        return code+" "+name+" "+price+" €";
    }
    */
}
