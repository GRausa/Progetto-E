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
public class Ticket {
    private String code;
    private float price;
    private int num_pos;
    
    public Ticket(String code, float price, int num_pos){
        this.code=code;
        this.price=price;
        this.num_pos= num_pos;
    
    }
        public String getCode(){
        return code;
        }
        
        public float getPrice(){
           return price; 
        }
        
        public int getNum_pos(){
            return num_pos;
        }
    
    //aggg
}
