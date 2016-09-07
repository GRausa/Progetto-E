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
 * Provides the administrator's methods for the textual user interface.
 * 
 * @author Giovanni
 */
public class MethodsControlAdministrator {
    /**
     * 
     * @param n
     * @return 
     */
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
    /**
     * Prints the menu.
     * 
     * @return 
     */
    public static String toStringMenu() {
        Scanner input = new Scanner(System.in);
        String s;
        s = "\nCOMANDI DISPONIBILI\n"
                + "\n>>> HI -> Test server\n"
                + "\nAREA INSERIMENTO:\n\n>>> INSERISCI_VOLO -> Inserisci un nuovo volo\n"
                + ">>> MODIFICA_VOLO -> Modifica data ora e prezzo di un volo\n"
                + ">>> EXIT";                
        System.out.println(s);
        String s1 = input.nextLine().toUpperCase();
        return s1;
    }
    /**
     * Prints a message indicating the server's status.
     * 
     * @param client Client.
     */
    public static void hi(InterfaceClient client) {
        if(client.hello()){
            System.out.println("IL SERVER E' ATTIVO");
        }
        else{
            System.out.println("IL SERVER NON E' ATTIVO");           
        }
    }
    /**
     * Text for inserting a new flight.
     * 
     * @param client Client
     * @throws IOException if has occurred an I/O exception.
     */
    public static void insertFlight(InterfaceClient client) throws IOException{
        ArrayList<String> input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Codice volo:")));
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
    /**
     * Text for editing a flight.
     * 
     * @param client Client
     * @throws IOException if has occurred an I/O exception.
     */
    public static void editFlight(InterfaceClient client) throws IOException{
        ArrayList<String> input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Codice volo:")));
        Flight flight = client.searchFlight(new Flight(input.get(0)));
        if(flight!=null){
            input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("AREA MODIFICA\nData partenza YYYY-MM-GG :", "Ora partenza HH:MM :")));
            Calendar dateDeparture = MethodsControlAdministrator.returnCalendar(input.get(0), input.get(1));
            input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Data arrivo YYYY-MM-GG:", "Ora arrivo HH:MM:")));
            Calendar dateDestination = MethodsControlAdministrator.returnCalendar(input.get(0), input.get(1));
            input = MethodsControlAdministrator.scannerInput(new ArrayList<>(asList("Prezzo volo:")));
            double price = Double.parseDouble(input.get(0));
            flight.setDateDeparture(dateDeparture);
            flight.setDateDestination(dateDestination);
            flight.setPrice(price);
            if(client.editFlight(flight)!=null){
                System.out.println("Modifica effettuata\n"+flight.toString());
                
            }
            else{
                System.out.println("Non è stato possibile modificare il volo");
            }
        }
        else{
            System.out.println("Errore inserimento codice");
        }
    }
        
    /**
     * Text for inserting user/pass for the administration area.
     * 
     * @param client Client.
     * @return <code>true</code> if the combination user/pass is correct.
     *         <code>false</code> otherwise.
     * @throws IOException 
     */
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
    
    /**
     * Converts the string date/time in a date in GregorianCalendar format.
     * 
     * @param stringDate Date in String format.
     * @param stringTime Time in String format.
     * @return Date in GregorianCalendar format.
     */
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
