/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package txt;

import blueAirlines.Company;
import java.io.IOException;

/**
 *
 * @author cl418377
 */
public class Simulation {
    public static void main(String args[]) throws IOException{
        Company c = new Company("file/BlueAirlines");
        c.downloadAirplanes("Airplanes.txt");
        System.out.println(c.toStringAirplanes());
    }
}
