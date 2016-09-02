/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tui.administrator;

import controller.InterfaceClient;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import objects.Flight;
import objects.Route;

/**
 *
 * @author Giovanni
 */
public class MethodsControlAdministrator {
    public static ArrayList<String> scannerInput(ArrayList<String> n) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> ritorno = new ArrayList<>(n.size());
        for (int i = 0; i < n.size(); i++) {
            String part = null;

            System.out.println(n.get(i));
            ritorno.add(i, input.nextLine());
        }
        return ritorno;
    }

    public static String toStringMenu() {
        Scanner input = new Scanner(System.in);
        String s;
        s = "\nCOMANDI DISPONIBILI\n"
                + "\n>>> HI -> Test server\n"
                + "\nAREA INSERIMENTO:\n\n>>> INSERISCI_VOLO -> Inserisci un nuovo volo\n"
                + ">>> EXIT";                
        System.out.println(s);
        String s1 = input.nextLine().toUpperCase();
        return s1;
    }

    public static void hi(InterfaceClient client) {
        if(client.hello()){
            System.out.println("IL SERVER E' ATTIVO");
        }
        else{
            System.out.println("IL SERVER NON E' ATTIVO");           
        }
    }
    
    public static void insertFlight(InterfaceClient client) throws IOException{
        ArrayList<String> input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Inserisci codice volo:")));
        String codeFlight = input.get(0);
        input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Città partenza:", "Aeroporto partenza:", "Città destinazione:","Aeroporto destnazione:")));
        Route route = new Route(input.get(1),input.get(3),input.get(0),input.get(2));
        input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Data partenza YYYY-MM-GG :", "Ora partenza HH:MM :")));
        Calendar dateDeparture = MethodsControlAdministrator.returnCalendar(input.get(0), input.get(1));
        input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Data arrivo YYYY-MM-GG:", "Ora arrivo HH:MM:")));
        Calendar dateDestination = MethodsControlAdministrator.returnCalendar(input.get(0), input.get(1));
        input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Prezzo volo:")));
        double price = Double.parseDouble(input.get(0));
        input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Codice aereo:")));
        String codeAirplane = input.get(0);
        Flight flight = new Flight(codeFlight, route, dateDeparture, dateDestination, price, codeAirplane);
        if(client.insertFlight(flight)==null){
            System.out.println("Errore inserimento, non è stato possibile inserire il volo richiesto");
        }
        else{
            System.out.println("Volo inserito");
        }
    }
    
    public static boolean checkLogin(InterfaceClient client) throws IOException{
        boolean b = false;
        do{
            ArrayList<String> input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("AREA AMMINISTRATORE\nUsername:","Password:")));
            String userpass = input.get(0)+"-"+input.get(1);
            b = client.checkLogin(userpass);
            if(b==false){
                input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Errore inserimento. Riprovare: Y/N")));
                if(!input.get(0).equals("Y")){
                    return false;
                }            
            }
        }while(!b);
        return true;
    }
    
    //METODI GENERICI
    
    public static Calendar returnCalendar(String stringDate, String stringTime){
        String[] vetDate = stringDate.split("-");
        int year = Integer.parseInt(vetDate[0]);
        int month = Integer.parseInt(vetDate[1])-1;
        int day = Integer.parseInt(vetDate[2]);
        String[] vetTime = stringTime.split(":");
        int hour = Integer.parseInt(vetTime[0]);
        int minute = Integer.parseInt(vetTime[1]);
        GregorianCalendar date = new GregorianCalendar(year,month,day,hour,minute);
        return date;
    }
    
}
