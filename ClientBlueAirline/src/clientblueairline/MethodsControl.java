/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author riccardo
 */
public class MethodsControl {
    
    public static ArrayList<String> lettura(ArrayList<String> n){
        
        Scanner input = new Scanner(System.in);
        ArrayList<String> ritorno=new ArrayList<>(n.size());
        for(int i=0;i<n.size();i++){
            String part = null;
             
            System.out.println(n.get(i));
            ritorno.add(i, input.nextLine());            
        }
        
        return ritorno;
    }
    
     
}
