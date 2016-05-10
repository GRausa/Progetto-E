/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Seat gestisce i singoli posti a sedere sull'aereo.
 * 
 * @author cl418377
 */
public class Seat {
    /**
     * Numero identificativo del posto a sedere.
     */
    private Integer number;
    /**
     * Variabile intera che indica se il posto a sedere è di prima o di seconda classe.
     * Può assumerere, quindi, il valore 1 o il valore 2.
     */
    private Integer numberClass; 
    /**
     * Alloca un nuovo posto a sedere.
     * @param number numero del posto
     * @param numberClass classe del posto
     */
    public Seat(Integer number, Integer numberClass) {
        this.number = number;
        this.numberClass = numberClass;
    }
    /**
     * 
     * @return numero del posto a sedere 
     */
    public Integer getNumber() {
        return number;
    }
    /**
     * 
     * @return classe del posto a sedere 
     */
    public Integer getNumberClass() {
        return numberClass;
    }
    
         
}
