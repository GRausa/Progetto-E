/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *La classe HoldLuggage indica la presenza del bagaglio da stiva. A differenza del bagaglio a mano richiede
 * un supplemento. Non è obbligatorio.
 * 
 * @author cl418377
 */
public class HoldLuggage {
    private String code;
    private Double price;
    private Double kg;
    private String description;
    
    /**
     * Istanzia un nuovo bagaglio da stiva.
     * 
     * @param code codice del bagaglio
     * @param kg peso del bagaglio in kili
     * @param price prezzo del bagaglio
     */
    public HoldLuggage (String code,Double kg, Double price, String description){
        this.code=code;
        this.kg=kg;
        this.price=price;
        this.description=description;
    }
    
    /**
     * Istanzia un nuovo bagaglio da stiva a partire dal codice.
     * @param code 
     */
    public HoldLuggage(String code){
        this.code=code;
    }
    
    public Double getKg() {
        return kg;
    }
    
    public Double getPrice() {
        return price;
    }

    public String toString(){
        return code+" "+description+" "+kg+"Kg "+price+"€";
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
       
}
