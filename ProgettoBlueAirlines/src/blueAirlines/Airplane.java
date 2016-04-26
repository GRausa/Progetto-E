/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirlines;

import java.util.ArrayList;

/**
 *
 * @author cl418377
 */
public class Airplane {
    private String nome;
    private ArrayList <Seat> seat;
    
    public Airplane(String nome){
        this.nome=nome;
        this.seat = new ArrayList<>();
    }
    
    public int getNumSeat(){
        return seat.size();
    }
    
    public String getNome(){
        return nome;
    }
}
