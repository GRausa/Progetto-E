/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

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
    
    public HoldLuggage (String code,Double kg, Double price){
        this.code=code;
        this.kg=kg;
        this.price=price;
    }

    public Double getKg() {
        return kg;
    }

    public Double getPrice() {
        return price;
    }
    
    public String toString(){
        return code+" "+kg+"Kg "+price+"€";
    }

    public String getCode() {
        return code;
    }
    
    
}
