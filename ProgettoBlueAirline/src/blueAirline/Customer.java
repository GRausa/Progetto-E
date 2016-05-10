/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Customer offre metodi per la gestione degli acquirenti. Un Customer può
 * prenotare più di un biglietto e può non essere presente nei passeggeri.
 * 
 * @author cl418377
 */
public class Customer {
    /**
     * L'email dell'acquirente, al quale verranno inviate le informazioni sulla prenotazione.
     */
    private String email;
    /**
    * Numero di biglietti che intende acquistare.
    */
    private String number;
    
    /**
     * Istanzia un nuovo Customer.
     * 
     * @param email email dell'acquirente
     * @param number numero di biglietti acquistati nella prenotazione
     */
    public Customer(String email, String number){
        this.email=email;
        this.number=number;
    }
    /**
     * 
     * @return email del cliente 
     */
    public String getEmail(){
        return email;
    }
    /**
     * 
     * @return numero di biglietti acquistati nella prenotazione 
     */
    public String getNumber(){
        return number;
    }
    
    public String toString(){
        return email+" "+number;
    } 
}   

