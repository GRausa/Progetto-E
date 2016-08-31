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
    private String ticket;
    
    public Seat(int numero,int classe, String ticket){
        this.ticket=ticket;
        this.classe=classe;
        this.number=numero;
    }

    public int getNumber() {
        return number;
    }

    public int getClasse() {
        return classe;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }  
    
    /*
    @Override
    public String toString(){        
        return "Posto n°"+this.getNumber()+" classe:"+this.getClasse()+" occupato"+this.getTicket();
    }*/
    
}
