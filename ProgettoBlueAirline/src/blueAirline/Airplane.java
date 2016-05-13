/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Airplane offre metodi per la gestione di un aereo.
 *
 * @author cl418377
 */
public class Airplane{
    private String name;
    private String code;
    private Seat[] seats;
    private int numberFirstClass;
    
    /**
     * Inizializza un nuovo aereo.
     * 
     * @param code codice identificativo dell'aereo
     * @param name nome dell'aereo
     * @param nSeats numero dei posti a sedere di un aereo
     * @param numberFirstClass numero dei posti riservati alla prima classe
     */
    public Airplane(String code, String name, int nSeats, int numberFirstClass){
        this.name=name;
        this.code=code;
        this.numberFirstClass=numberFirstClass;
        this.seats=new Seat[nSeats];       
        for(int i=0; i< nSeats; i++){
            if(i<numberFirstClass){
                seats[i]=new Seat(i,1);
            }
            else{
                seats[i]=new Seat(i,2);
            }
            
        }
    }
    
     /**
      * 
      * @return numero dei posti a sedere
      */
    public int getNumSeat(){
        return seats.length;
    }
    
    /**
     * 
     * @return vettore dei posti a sedere
     */
    public Seat[] getSeats() {
        return seats;
    }
    
    
    /**
     * 
     * @return nome dell'aereo 
     */
    public String getName(){
        return name;
    }
    
    /**
     * Confronta due aerei per vedere se sono uguali.
     * 
     * @param airplane riferimento all'aereo che vogliamo comparare
     * @return <code>true</code> se l'aereo su cui è invocato il metodo è uguale 
     *         a quello passato come argomento, altrimenti ritorna <code>false</code>
     */
    public boolean equals(Airplane airplane){
        return this.code.equals(airplane.getCode());
    }
    
    /**
     * Confronta il codice di due aerei.
     * 
     * @param code codice identificativo dell'aereo
     * @return <code>true</code> se l'aereo su cui è invocato il metodo ha il codice
     *         uguale alla stringa passata come parametro, altrimenti ritorna <code>false</code>
     */
    public boolean equalsCode(String code){
        return this.code.equals(code);
    }
    
    /**
     * 
     * @return codice dell'aereo
     */
    public String getCode(){
        return code;
    }
    
    /**
     * 
     * @return rappresentazione scritta dell'oggetto
     */
    @Override
    public String toString(){
        return code+" "+name+" "+seats.length+"\n";
    }
    /**
     * 
     * @return numero dei posti in prima classe
     */
    public int getNumberFirstClass() {
        return numberFirstClass;
    }
    
}
