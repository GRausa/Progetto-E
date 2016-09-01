/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *La classe Insurance indica la presenza dell'assicurazione. Questa può essere di vario tipo 
 * e non è obbligatoria ai fini dell'acquisto del biglietto.
 * 
 * @author cl418377
 */
public class Insurance { 
    private String code;
    private String name;
    private double price;
    private String description;
    
    /**
     * Istanzia una nuova assicurazione.
     * 
     * @param code codice dell'assicurazione
     * @param name nome dell'assicurazione
     * @param price prezzo dell'assicurazione
     */
    public Insurance (String code, String name, double price, String description){
        this.code=code;
        this.name=name;
        this.price=price;
        this.description=description;
    }
    
    /**
     * Istanzia una nuova assicurazione a partire dal suo codice.
     * 
     * @param code codice dell'assicurazione
     */
    public Insurance(String code) {
        this.code = code;
    }
   
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
 
    public String toString(){
        return code+" "+name+" "+price+" €";
    }
   
    public String getCode(){
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    
}
