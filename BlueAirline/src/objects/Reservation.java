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
public class Reservation {
    private int code;
    private String email, number, codeFlight;

    public Reservation(String email, String number, String codeFlight) {
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }
    
    

    public Reservation(int code, String email, String number, String codeFlight) {
        this.code = code;
        this.email = email;
        this.number = number;
        this.codeFlight = codeFlight;
    }

    public int getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }


    public String getCodeFlight() {
        return codeFlight;
    }
    
    public String toString(){
        return code+" "+email+" "+number+" "+codeFlight;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    
    
}
