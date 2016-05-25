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
        if(in.readLine().equals("HI!")){
            System.out.println("RICEVUTA RISPOSTA DAL SERVER, IL SERVER è ATTIVO");
        }
    }
    
    public boolean checkRoute(String partenza,String destinazione) throws IOException{
        out.println("CHECK "+partenza+" "+destinazione);
        String serverout=in.readLine();
        if(serverout.equals("0c")){
            System.out.println("NON EFFETTUIAMO QUESTA TRATTA");
            return true;
        }
        else{
            String[] app=serverout.split("\t");
            if(app[0].equals("1c")){
                System.out.println("LE DUE CITTA SONO COLLEGATE");
                int n=Integer.parseInt(app[1]);
                System.out.println("I COLLEGAMENTI PER :"+partenza+"->"+destinazione+" sono :"+n);
                for(int i=0;i<n;i++){
                    System.out.println(in.readLine());
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean calendar(String partenza,String destinazione) throws IOException{
        out.println("CALENDAR "+partenza+" "+destinazione);
        String serverout=in.readLine();
        if(serverout.equals("0k")){
            System.out.println("NON ESISTE LA ROTTA, CONSULTA LE ROTTE CON IL COMANDO VERIFICA_TRATTA");
            return false;
        }
        else if(serverout.equals("-1k")){
            System.out.println("NON CI SONO VOLI PROGRAMMATI PER QUESTA TRATTA");
            return true;
        }
        else{
            String[] app=serverout.split("\t");
            if(app[0].equals("1k")){
                System.out.println("SONO PRESENTI VOLI PER QUESTA TRATTA");
                int n=Integer.parseInt(app[1]);
                System.out.println("STAMPO CALENDARIO PER :"+partenza+"->"+destinazione);
                for(int i=0;i<n;i++){
                    System.out.println(in.readLine());
                }
                return true;
            }
        }
        return false;
    }

    public void scrittura() throws IOException {
        System.out.println("RICEVUTO DAL SERVER: " + in.readLine());

    }

}
