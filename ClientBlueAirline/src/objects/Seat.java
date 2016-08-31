/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author Giovanni
 */
public class Seat {
    private int number;
    private int classe;
    private String passenger;
    
    public Seat(int numero,int classe, String passeggero){
        this.passenger=passeggero;
        this.classe=classe;
        this.number=numero;
    }

    public int getNumber() {
        return number;
    }

    public int getClasse() {
        return classe;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }  
    
    
    @Override
    public String toString(){
        
        return "Posto n°"+this.getNumber()+" classe:"+this.getClasse()+" occupato"+this.getPassenger();
    }
    
}
