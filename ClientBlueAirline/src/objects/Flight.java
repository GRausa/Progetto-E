/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * La classe Flight indica un volo che effettua la compagnia.
 * 
 * @author Giovanni
 */
public class Flight {
    public static double COSTOPRIMACLASSE=30;
    private String code;
    private Route r;
    private Calendar dateDeparture;
    private Calendar dateDestination;
    private double price;
    private ArrayList<Seat> seats;
    private String codeAirplane;
    
    /**
     * Istanzia un nuovo volo
     * 
     * @param code codice del volo
     * @param r rotta del volo
     * @param dateDeparture data di partenza 
     * @param dateDestination data di arrivo
     * @param price prezzo del volo
     */
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double price, String codeAirplane) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.price = price;
        this.codeAirplane = codeAirplane;
    }
    
    public Flight(String code, Route r, Calendar dateDeparture, Calendar dateDestination, double price) {
        this.code = code;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDestination;
        this.price = price;
    }
    /**
     * Istanzia un nuovo volo
     * 
     * @param r rotta del volo
     * @param dateDeparture data di partenza
     */
    public Flight(Route r, Calendar dateDeparture) {
        this.code = null;
        this.r = r;
        this.dateDeparture = dateDeparture;
        this.dateDestination = dateDeparture;
        this.price = 0;
    }
    
    /**
     * Istanzia un nuovo volo
     * 
     * @param code codice del volo
     */
    public Flight(String code){
        this.code=code;
    }

    public void setDateDeparture(Calendar dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public void setDateDestination(Calendar dateDestination) {
        this.dateDestination = dateDestination;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    /**
     * 
     * @return descrizione scritta del volo
     */
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy HH:mm");
        return code+" "+r.printRoute()+"\n"+sdf.format(this.dateDeparture.getTime())+" - "+sdf.format(this.dateDestination.getTime())+"\n"+price+"€";
    }
    
    /**
     * 
     * @return numero di posti liberi del volo
     */
    public int getSeatFree(){
        int n=0;
        for(Seat s : seats){
            if(s.getTicket()==null){
                n++;
            }
        }
        return n;
    }
    
    /**
     * 
     * @return tutti i posti liberi ed occupati del volo
     */
    public String printAllSeats(){
        String st="PRIMA CLASSE -> prezzo: "+(this.getPrice()+Flight.COSTOPRIMACLASSE)+"€\n";
        for(Seat s : seats){
            if(s.getTicket()==null && s.getClasse()==1){
                st+=s.getNumber()+" | ";
            }
            else{
                if(s.getTicket()!=null && s.getClasse()==1){
                    st+="X | ";
                }
            }
        }
        st+="\nSECONDA CLASSE -> prezzo: "+this.getPrice()+"€\n";
        for(Seat s : seats){
            if(s.getTicket()==null && s.getClasse()==2){
                st+=s.getNumber()+" | ";
            }
            else{
                if(s.getTicket()!=null && s.getClasse()==2){
                    st+="X | ";
                }
            }
        }
        return st;
    }
}