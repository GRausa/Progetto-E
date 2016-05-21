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
public class Airplane {
    private String codAirplane, name;
    private int seats, seatsFirstClass;

    public Airplane(String codAirplane, String name, int seats, int seatsFirstClass) {
        this.codAirplane = codAirplane;
        this.name = name;
        this.seats = seats;
        this.seatsFirstClass = seatsFirstClass;
    }

    public String getCodAirplane() {
        return codAirplane;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public int getSeatsFirstClass() {
        return seatsFirstClass;
    }
    
    
    
}
