/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *
 * @author cl418377
 */
public class Seat {
    private boolean reserved;
    private Integer number;
    private Integer numberClass; //class 1-> prima classe , 2 -> Seconda Classe

    public Seat(Integer number, Integer numberClass) {
        this.reserved=false;
        this.number = number;
        this.numberClass = numberClass;
    }

    public Integer getNumber() {
        return number;
    }
    
    public void setReserved(boolean condition){
        this.reserved=condition;
    }
    
    public boolean isReserved(){
        return reserved;
    }

    public Integer getNumberClass() {
        return numberClass;
    }
    
         
}
