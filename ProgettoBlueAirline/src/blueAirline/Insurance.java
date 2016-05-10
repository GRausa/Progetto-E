/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *La classe Insurance indica la presenza dell'assicurazione. Questa può essere di vario tipo 
 * e non è obbligatoria ai fini dell'acquisto del biglietto.
 * 
 * @author cl418377
 */
public class Insurance { //ASSICURAZIONE
    private String code;
    private String name;
    private double price;
    
    public Insurance (String code, String name, double price){
        this.code=code;
        this.name=name;
        this.price=price;
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
    
    
}
