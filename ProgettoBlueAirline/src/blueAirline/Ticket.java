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
    /**
     * Codice identificativo del biglietto
     */
    private String code;
    /**
     * Prezzo del biglietto. Non segue nessun algoritmo ma è fisso per ogni volo.
     */
    private double price;

    private int nPosition;
    /**
     * Crea un nuovo biglietto.
     * @param code codice identificativo del biglietto
     * @param price prezzo del biglietto
     * @param num_pos 
     */
    public Ticket(String code, double price, int num_pos){
        this.code=code;
        this.price=price;
        this.nPosition= num_pos;
    
    }
    /**
     * 
     * @return codice identificativo del biglietto
     */
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
    /**
     * 
     * @return 
     */
    public int getNum_pos(){
        return nPosition;
    }
    
}
