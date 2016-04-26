/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

/**
 *
 * @author cl418377
 */
public class Seat {
    private Integer number;
    private Integer numberClass; //class 1-> prima classe , 2 -> Seconda Classe

    public Seat(Integer number, Integer numberClass) {
        this.number = number;
        this.numberClass = numberClass;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getNumberClass() {
        return numberClass;
    }
    
         
}
