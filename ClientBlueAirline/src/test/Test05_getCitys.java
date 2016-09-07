/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.FacadeControllerClient;
import controller.InterfaceClient;

/**
 *
 * @author Giovanni
 */
//TUTTE CITTA'
public class Test05_getCitys {

    public static void main(String[] args) {
        InterfaceClient client = FacadeControllerClient.getIstance();
        client.connect("localhost");
        String[] citys = client.getAllCitys();
        if (citys.length > 0) {
            for (String c : citys) {
                System.out.println(c.toString());
            }
        }
    }

}
