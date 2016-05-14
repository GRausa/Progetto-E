/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blueAirline;

/**
 * La classe Administrator offre metodi per la gestione di tutte le informazioni dei voli e dei passeggeri.
 * Affinchè il sistema riconosca un amministratore, è d'obbligo il login con Username e password.
 * 
 * @author Giovanni
 */
public class Administrator {
    private String user;
    private String password;
    /**
     * Istanzia un nuovo amministratore.
     * 
     * @param user username dell'amministratore
     * @param password password dell'amministratore
     */
    public Administrator(String user, String password){
        this.user=user;
        this.password=password;
    }
    /**
     * 
     * @return username di un amministratore
     */
    public String getUser() {
        return user;
    }
    /**
     * 
     * @return password di un amministratore 
     */
    public String getPassword() {
        return password;
    }
    /**
     * 
     * @return rappresentazione scritta di un amministratore
     */
    public String toString(){
        return user+" "+password;
    }
    
}
