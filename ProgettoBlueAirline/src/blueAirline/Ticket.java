/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Ticket permette la gestione dei biglietti aerei.  
 * @author cl418377
 */
public class Ticket {

    private String code;
    private double price;
    private int nPosition;
    /**
     * Crea un nuovo biglietto.
     * @param code codice identificativo del biglietto
     * @param price prezzo del biglietto
     * @param num_pos posizione del posto a sedere
     */
    public Ticket(String code, double price, int num_pos){
        this.code=code;
        this.price=price;
        this.nPosition= num_pos;
    
    }
    
    public String toString(){
        return code+" "+price+"€ Posto: "+nPosition;
    }
    
    public void setCode(String code){
        this.code=code;
    }
    
    public String getCode(){
        return code;
    }
    /**
     * 
     * @return prezzo del biglietto
     */
    public double getPrice(){
        return price; 
    }
    
    public void addPrice(double p){
        this.price+=p;
    }

    public int getnPosition() {
        return nPosition;
    }

    public void setnPosition(int nPosition) {
        this.nPosition = nPosition;
    }    
}
