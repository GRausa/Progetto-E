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
}
