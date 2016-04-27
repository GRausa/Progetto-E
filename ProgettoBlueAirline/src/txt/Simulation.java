/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package txt;

import blueAirline.Company;
import java.io.IOException;

/**
 *
 * @author cl418377
 */
public class Simulation {
    public static void main(String args[]) throws IOException{
        Company c = new Company("BlueAirlines");
        c.downloadAirplanes("file/Airplanes.txt");
        System.out.println(c.toStringAirplanes());
        c.downloadCitys("file/Citys.txt");
        System.out.println(c.toStringCitys());
    }
}
