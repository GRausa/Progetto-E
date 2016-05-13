/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 *
 * @author Giovanni
 */
public class Administrator {
    private String user;
    private String password;
    
    public Administrator(String user, String password){
        this.user=user;
        this.password=password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    public String toString(){
        return user+" "+password;
    }
    
}
