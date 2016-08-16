/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

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
    
    public Meal (String code){
        this.code=code;
    }
    /**
     * 
     * @return nome del pasto 
     */
    public String getName() {
        return name;
    }
    /**
     * 
     * @return tempo di volo dal quale è disponibile il pasto
     */
    public int getTimeMeal() {
        return timeMeal;
    }
    /**
     * 
     * @return prezzo del pasto
     */
    public double getPrice() {
        return price;
    }
    /**
     * 
     * @return codice del pasto 
     */
    public String getCode() {
        return code;
    }
    /**
     * 
     * @return rappresentazione scritta del pasto
     */
    public String toString(){
        return code+" "+name+" "+price+" €";
    }
}
