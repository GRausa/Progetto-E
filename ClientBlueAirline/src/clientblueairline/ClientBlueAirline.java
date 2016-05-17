/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientblueairline;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cl418377
 */
public class ClientBlueAirline {

    public Socket clientSocket;
    int PortNumber = 8888;
    public PrintWriter out;
    public BufferedReader in;

    public ClientBlueAirline() throws IOException {
        clientSocket = new Socket("localhost", PortNumber);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    }

    public void hello() throws IOException {
        out.println("HI!");
    }

    public void scrittura() throws IOException {
        System.out.println("RICEVUTO DAL SERVER: " + in.readLine());

    }

}
