/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 * Represents an hold luggage. Unlike the hand baggage, it is a supplement of the ticket.
 * 
 * @author Giovanni
 */
public class HoldLuggage {
    private String code;
    private Double price;
    private Double kg;
    private String description;
    
    /**
     * Contructs a new hold luggage.
     * 
     * @param code Code of the luggage.
     * @param kg Max wheight of the luggage in Kilograms.
     * @param price Price of the luggage.
     */
    public HoldLuggage (String code,Double kg, Double price, String description){
        this.code=code;
        this.kg=kg;
        this.price=price;
        this.description=description;
    }
    
    /**
     * Constructs a new hold luggage with only the code.
     * 
     * @param code codice del bagaglio
     */
    public HoldLuggage(String code) {
        this.code = code;
    }
    
    public Double getKg() {
        return kg;
    }

    public Double getPrice() {
        return price;
    }   
  
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    
    
    
    
}
