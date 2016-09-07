/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;
import java.io.IOException;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;

/**
 *
 * @author Giovanni
 */
//TUTTE CITTA'
public class Test4_getSupplements {
    public static void main(String[] args) throws IOException {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        Meal[] meals = client.getAllMeals();
        HoldLuggage[] holdLuggages = client.getAllHoldLuggages();
        Insurance[] insurances = client.getAllInsurances();
        
        System.out.println("PASTI: ");
        for (Meal m : meals) {
                System.out.println(m.toString());
        }
        System.out.println("BAGAGLI: ");
        for (HoldLuggage hl : holdLuggages) {
                System.out.println(hl.toString());   
        }
        System.out.println("ASSICURAZIONI: ");
        for (Insurance i : insurances) {
                System.out.println(i.toString());
        }        
    }
        
}
