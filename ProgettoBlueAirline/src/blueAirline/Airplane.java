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
public class Airplane implements Comparable{
    private String name;
    private String code;
    private Seat[] seats;
    private int numberFirstClass;
    
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
    
    public int getNumSeat(){
        return seats.length;
    }

    public Seat[] getSeats() {
        return seats;
    }
    
    
    
    public String getName(){
        return name;
    }
    
    public boolean equals(Airplane airplane){
        return this.code.equals(airplane.getCode());
    }
    
    public boolean equalsCode(String code){
        return this.code.equals(code);
    }
    
    public String getCode(){
        return code;
    }
    
    @Override
    public String toString(){
        return code+" "+name+" "+seats.length+"\n";
    }
    
    public int compareTo(Object o){
        Airplane a = (Airplane) o;
        return(name.compareTo(a.getCode()));
    }

    
            
}
