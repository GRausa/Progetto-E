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
    
    /**
     * Istanzia un nuovo bagaglio da stiva.
     * 
     * @param code codice del bagaglio
     * @param kg peso del bagaglio in kili
     * @param price prezzo del bagaglio
     */
    public HoldLuggage (String code,Double kg, Double price){
        this.code=code;
        this.kg=kg;
        this.price=price;
    }
    /**
     * 
     * @return peso del bagaglio in kili 
     */
    public Double getKg() {
        return kg;
    }
    /**
     * 
     * @return prezzo in euro 
     */
    public Double getPrice() {
        return price;
    }
    /**
     * 
     * @return descrizione scritta dell'oggetto 
     */
    public String toString(){
        return code+" "+kg+"Kg "+price+"€";
    }
    /**
     * 
     * @return codice del bagaglio 
     */
    public String getCode() {
        return code;
    }
    
    
}
