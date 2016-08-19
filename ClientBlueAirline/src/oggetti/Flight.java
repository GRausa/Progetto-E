/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oggetti;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author riccardo
 */
public class Flight {
    public static double COSTOPRIMACLASSE=30;
    private String code;
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double prezzo;
    private ArrayList<Seat> seats;
    
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double prezzo) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.prezzo = prezzo;
    }
    public Flight(Route r, Calendar dateDeparture) {
        this.code = null;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDeparture;
        this.prezzo = 0;
    }
    
    public Flight(String code){
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public Route getRoute() {
        return r;
    }

    public Calendar getDateDeparture() {
        return dateDeparture;
    }

    public Calendar getDateDestination() {
        return dateDestination;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return code+" "+r.toString()+"\n"+sdf.format(this.dateDeparture.getTime())+" - "+sdf.format(this.dateDestination.getTime())+"\n"+prezzo+"€";
    }
    
    public int getSeatFree(){
        int n=0;
        for(Seat s : seats){
            if(s.getPassenger()==null){
                n++;
            }
        }
        return n;
    }
    
    public String printAllSeats(){
        String st="PRIMA CLASSE -> prezzo: "+(this.getPrezzo()+Flight.COSTOPRIMACLASSE)+"€\n";
        for(Seat s : seats){
            if(s.getPassenger()==null && s.getClasse()==1){
                st+=s.getNumber()+" | ";
            }
            else{
                if(s.getPassenger()!=null && s.getClasse()==1){
                    st+="X | ";
                }
            }
        }
        st+="\nSECONDA CLASSE -> prezzo: "+this.getPrezzo()+"€\n";
        for(Seat s : seats){
            if(s.getPassenger()==null && s.getClasse()==2){
                st+=s.getNumber()+" | ";
            }
            else{
                if(s.getPassenger()!=null && s.getClasse()==2){
                    st+="X | ";
                }
            }
        }
        return st;
    }
    
    
    
}