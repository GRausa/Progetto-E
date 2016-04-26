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
public class Customer {
    private String email;
    private String number;
    
    public Customer(String email, String number){
        this.email=email;
        this.number=number;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getNumber(){
        return number;
    }
}   

