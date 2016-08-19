/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import objects.Flight;
import objects.HoldLuggage;
import objects.Insurance;
import objects.Meal;
import objects.Reservation;
import objects.Route;
import objects.Seat;
import objects.TicketPassenger;

/**
 *
 * @author Giovanni
 */
public class AdapterSQL {

    ConnectionSQL SQL;

    public AdapterSQL() {
        SQL = new ConnectionSQL();
        SQL.startConnection();

    }

    public int numberSeatFlight(String codeFlight) throws SQLException {
        String query
                = "SELECT POSTI\n"
                + "FROM Aereo A,Volo V\n"
                + "WHERE A.COD_AEREO = V.AEREO AND V.COD_VOLO='" + codeFlight + "'";
        ResultSet resultQuery = SQL.queryRead(query);
        return (int) ParserSQL.parseFunctionSQL(resultQuery, "POSTI");
    }

    public int numberSeatFree(String codeFlight) throws SQLException {
        String query
                = "SELECT COUNT(*) AS NUM\n"
                + "FROM Posto\n"
                + "WHERE Volo='" + codeFlight + "' AND PASSEGGERO IS NULL ";
        ResultSet resultQuery = SQL.queryRead(query);
        return (int) ParserSQL.parseFunctionSQL(resultQuery, "NUM");
    }

    public int numberSeatFirstClassFlight(String cod) throws SQLException {

        String query
                = "SELECT POSTI1CLASSE \n"
                + "FROM Aereo A,Volo V\n"
                + "WHERE A.COD_AEREO = V.AEREO AND V.COD_VOLO='" + cod + "'";
        ResultSet resultQuery = SQL.queryRead(query);
        return (int) ParserSQL.parseFunctionSQL(resultQuery, "POSTI1CLASSE");

    }

    public void setAllSeatFlight(Flight volo) throws SQLException {
        int numeroseat = numberSeatFlight(volo.getCode());
        int prima = numberSeatFirstClassFlight(volo.getCode());
        for (int i = 0; i < numeroseat; i++) {
            if (i < prima) {
                setSeat(volo.getCode(), i + 1, 1);
            } else {
                setSeat(volo.getCode(), i + 1, 2);
            }
        }
    }

    public void setSeat(String volo, int num, int classe) throws SQLException {
        String query = "INSERT INTO Posto VALUES ('" + num + "', '" + volo + "', '" + classe + "', null)";
        SQL.queryWrite(query);

    }
    
    /* USATO NELL'INIZIALIZZAZIONE DEI POSTI*/
    public ArrayList<Flight> setAllSeatFlights() throws SQLException {
        ArrayList<Flight> flights;
        String query
                = "SELECT V.COD_VOLO, A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO "
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V "
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA";
        ResultSet resultQuery = SQL.queryRead(query);
        flights = ParserSQL.parseFlights(resultQuery);

        for (Flight fli : flights) {
            System.out.println(fli);
            AdapterSQL.this.setAllSeatFlight(fli);
        }

        resultQuery.close();
        return flights;

    }

    public ArrayList<Route> searchRoutes() throws SQLException {
        ArrayList<Route> routes;
        String query
                = "SELECT A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\"\n"
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2\n"
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO";
        ResultSet resultQuery = SQL.queryRead(query);
        routes = ParserSQL.parseRoutes(resultQuery);
        resultQuery.close();
        return routes;
    }

    public ArrayList<Flight> searchFlights(String departure, String destination, String date) throws SQLException {
        ArrayList<Flight> flights;
        String query
                = "SELECT V.COD_VOLO, A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO "
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V "
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA AND A1.CITTA='" + departure + "' AND A2.CITTA ='" + destination + "' AND V.DATAPARTENZA = '" + date + "'";
        ResultSet resultQuery = SQL.queryRead(query);
        flights = ParserSQL.parseFlights(resultQuery);
        for(Flight f : flights){
            this.setSeatsFlight(f);
        }
        resultQuery.close();
        return flights;
    }
    
    public ArrayList<Flight> searchFlights(Route route) throws SQLException {
        
        ArrayList<Flight> flights;
        String query
                = "SELECT V.COD_VOLO, A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO "
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V "
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA AND A1.NOME='" + route.getDeparutreAirport() + "' AND A2.NOME ='" + route.getDestinationAirport() + "'";
        ResultSet resultQuery = SQL.queryRead(query);
        flights = ParserSQL.parseFlights(resultQuery);
        resultQuery.close();
        return flights;
    }
    
    public Flight searchFlight(String codeFlight) throws SQLException{
        Flight flight;
        String query
                = "SELECT V.COD_VOLO, A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO, P.NUMERO, P.Classe, P.PASSEGGERO "
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V, Posto P "
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA AND V.COD_VOLO='" + codeFlight + "' AND P.Volo=V.COD_VOLO ";
        ResultSet resultQuery = SQL.queryRead(query);
        flight = ParserSQL.parseFlight(resultQuery);
        resultQuery.close();
        return flight;
    }
    
    public ArrayList<String> searchAllCitys() throws SQLException {
        ArrayList<String> citys;
        String query
                = "SELECT NOME\n"
                + "FROM Citta ";
        ResultSet resultQuery = SQL.queryRead(query);
        citys = ParserSQL.parseCitis(resultQuery);
        resultQuery.close();
        return citys;
    }

    public double returnPriceFlight(String codflight) throws SQLException {
        String query
                = "SELECT PREZZO AS PREZZOVOLO\n"
                + "FROM Volo\n"
                + "WHERE COD_VOLO='" + codflight + "'";
        ResultSet resultQuery = SQL.queryRead(query);
        return ParserSQL.parseFunctionSQL(resultQuery, "PREZZOVOLO");
    }

    public Reservation makeReservation(Reservation reservation) throws SQLException {
        
        //prenotazione
        String query
                = "SELECT MAX(COD_PRENOTAZIONE) AS MAXCOD\n"
                + "FROM Prenotazione";
        ResultSet resultQuery = SQL.queryRead(query);
        int codeReservation = (int) ParserSQL.parseFunctionSQL(resultQuery, "MAXCOD");
        codeReservation++;
        query = "INSERT INTO Prenotazione VALUES ('" + codeReservation + "', '" + reservation.getCodeFlight() + "', '" + reservation.getEmail() + "', '" + reservation.getNumber() + "')";
        SQL.queryWrite(query);
        reservation.setCode(codeReservation);
            
        //aggiunta passeggeri
        int i = 0;
        String codeTicket="";
        for (TicketPassenger tp : reservation.getPassengers()) {
            i++;
            codeTicket = reservation.getCodeFlight() +""+ reservation.getCode()+"" + i;
            //codeTicketreservation.getCodeFlight() +"3"+i;
            tp.setCode(codeTicket);
            tp.setCodeFlight(reservation.getCodeFlight());
            tp.setCodeReservation(codeReservation);
            query = "INSERT INTO TicketPasseggero\n"
                    + "VALUES ('" + tp.getCode() + "', '" + tp.getID() + "', '" + tp.getName() + "', '" + tp.getSurname() + "', '" + reservation.getCode() + "',0)";
            SQL.queryWrite(query);
            //aggiunte
            ArrayList<Meal> meals = tp.getMeals();
            for (Meal s : meals) {
                this.insertAggiunta(s.getCode(), codeTicket);
            }
            ArrayList<Insurance> insurances = tp.getInsurances();
            for (Insurance s : insurances) {
                this.insertAggiunta(s.getCode(), codeTicket);
            }
            ArrayList<HoldLuggage> holdLuggages = tp.getHoldLuggages();
            for (HoldLuggage s : holdLuggages) {
                this.insertAggiunta(s.getCode(), codeTicket);
            }
            if(this.seatIsFree(reservation.getCodeFlight(), tp.getNseat())){
                this.setSeatBoolean(reservation.getCodeFlight(), tp.getNseat(), tp.getCode()); //lo metto a 1 cioè occupato
            }
            else{
                tp.setNSeat(-1); //posto -1 non assegnato
            }
        }
        return reservation;
    }

    public void setSeatBoolean(String codeFlight, int nSeat, String ticketPassenger) throws SQLException {
        String query = "UPDATE Posto SET PASSEGGERO = '"+ticketPassenger+"'\n"
                + "WHERE Numero=" +nSeat+ " AND Volo = '" + codeFlight+ "'";
        SQL.queryWrite(query);
    }

    public void insertAggiunta(String code, String codeTicket) throws SQLException {
        String query;
        double price;
        ResultSet resultQuery;
        switch (code.charAt(0)) {
            case 'M':
                query
                        = "SELECT PREZZO\n"
                        + "FROM Pasto\n"
                        + "WHERE COD_PASTO='" + code + "'";

                resultQuery = SQL.queryRead(query);
                price = ParserSQL.parseFunctionSQL(resultQuery, "PREZZO");
                query = "INSERT INTO Pasto_Passeggero\n"
                        + "VALUES (NULL,'" + code + "', '" + codeTicket + "','" + price + "')";
                SQL.queryWrite(query);
                break;
            case 'H':
                query
                        = "SELECT PREZZO\n"
                        + "FROM Bagaglio\n"
                        + "WHERE COD_BAGAGLIO='" + code + "'";
                ;

                resultQuery = SQL.queryRead(query);
                price = ParserSQL.parseFunctionSQL(resultQuery, "PREZZO");
                query = "INSERT INTO Bagaglio_Passeggero\n"
                        + "VALUES (NULL,'" + code + "', '" + codeTicket + "','" + price + "')";
                SQL.queryWrite(query);
                break;
            case 'I':
                query
                        = "SELECT PREZZO\n"
                        + "FROM Assicurazione\n"
                        + "WHERE COD_ASSICURAZIONE='" + code + "'";

                resultQuery = SQL.queryRead(query);
                price = ParserSQL.parseFunctionSQL(resultQuery, "PREZZO");
                query = "INSERT INTO Assicurazione_Passeggero\n"
                        + "VALUES (NULL,'" + code + "', '" + codeTicket + "','" + price + "')";
                SQL.queryWrite(query);
                break;
            default:
                break;
        }
    }

    public Flight setSeatsFlight(Flight flight) throws SQLException {
        flight.setSeats(this.getSeatsFlight(flight.getCode()));
        return flight;
    }
    
    public ArrayList<Seat> getSeatsFlight(String codeFlight) throws SQLException{
        ArrayList<Seat> seats;
        String query
                = "SELECT NUMERO, Classe, PASSEGGERO\n"
                + "FROM Posto\n"
                + "WHERE Volo='"+codeFlight+"' ";
        ResultSet resultQuery = SQL.queryRead(query);
        seats = ParserSQL.parseSeats(resultQuery);
        resultQuery.close();
        return seats;
    }
    
    public boolean seatIsFree(String codeFlight, int nseat) throws SQLException{
        String query
                = "SELECT COUNT(*) AS C\n"
                + "FROM Posto\n"
                + "WHERE Volo='"+codeFlight+"' AND NUMERO='"+nseat+"' AND PASSEGGERO IS NULL ";
        ResultSet resultQuery = SQL.queryRead(query);
        double b = ParserSQL.parseFunctionSQL(resultQuery, "C");
        if(b==1){ //is null -> libero
            return true;
        }
        else{   // is not null -> occupato
            return false;
        }
    }
    
    public ArrayList<Meal> getAllMeals() throws SQLException{
        ArrayList<Meal> meals;
        String query
                = "SELECT *\n"
                + "FROM Pasto ";
        ResultSet resultQuery = SQL.queryRead(query);
        meals = ParserSQL.parseMeals(resultQuery);
        resultQuery.close();
        return meals;
    }
    
    public ArrayList<HoldLuggage> getAllHoldLuggages() throws SQLException{
        ArrayList<HoldLuggage> holdLuggages;
        String query
                = "SELECT *\n"
                + "FROM Bagaglio ";
        ResultSet resultQuery = SQL.queryRead(query);
        holdLuggages = ParserSQL.parseHoldLuggages(resultQuery);
        resultQuery.close();
        return holdLuggages;
    }
    
    public ArrayList<Insurance> getAllInsurances() throws SQLException{
        ArrayList<Insurance> insurances;
        String query
                = "SELECT *\n"
                + "FROM Assicurazione ";
        ResultSet resultQuery = SQL.queryRead(query);
        insurances = ParserSQL.parseInsurances(resultQuery);
        resultQuery.close();
        return insurances;
    }
    
    public TicketPassenger editSeatTicketPassenger(TicketPassenger tp) throws SQLException{
        if(this.seatIsFree(tp.getCodeFlight(), tp.getNseat())){
            this.setSeatBoolean(tp.getCodeFlight(), tp.getNseat(), tp.getCode());
        }
        else{
            tp.setNSeat(-1);
        }
        return tp;
    }
    
    //verifico se è stato fatto il checkIn
    public boolean isCheckIn(String codeTicket) throws SQLException{
        String query
                = "SELECT CHECKIN\n" 
                + "FROM `TicketPasseggero`\n" 
                + "WHERE COD_TICKET = '"+ codeTicket + "' ";
        ResultSet resultQuery = SQL.queryRead(query);
        int r = (int) ParserSQL.parseFunctionSQL(resultQuery, "CHECKIN");
        if(r==0){
            return false;
        }
        else{
            return true;
        }
    }

    public void setCheckIn(String codeTicket) throws SQLException{
        //verificare se è stato fatto altrimenti fallo
        if(!this.isCheckIn(codeTicket)){
            String query
                    = "UPDATE `TicketPasseggero`\n"
                    + "SET `CHECKIN` = '1'\n"
                    + "WHERE `TicketPasseggero`.`COD_TICKET` = '"+ codeTicket +"';";
            SQL.queryWrite(query);            
        }
    }
    
    public ArrayList<Meal> getMealsTicketPassenger(String codeTicket) throws SQLException{
        ArrayList<Meal> mealsTicketPassenger;
        String query
                = "SELECT COD_PASTO, NOME, PREZZOPASTO AS PREZZO, TEMPOVIAGGIO\n" 
                + "FROM `Pasto_Passeggero` , Pasto\n" 
                + "WHERE PASSEGGERO = '" + codeTicket + "'\n" 
                + "AND PASTO = COD_PASTO" ;
        ResultSet resultQuery = SQL.queryRead(query);
        mealsTicketPassenger = ParserSQL.parseMeals(resultQuery);
        resultQuery.close();
        return mealsTicketPassenger;               
    }
    
    public ArrayList<Insurance> getInsurancesTicketPassenger(String codeTicket) throws SQLException{
        ArrayList<Insurance> insurancesTicketPassenger;
        String query
                = "SELECT COD_ASSICURAZIONE, NOME, PREZZOASSICURAZIONE AS PREZZO, DESCRIZIONE\n" 
                + "FROM `Assicurazione_Passeggero` , Assicurazione\n" 
                + "WHERE PASSEGGERO = '" + codeTicket + "'\n" 
                + "AND ASSICURAZIONE = COD_ASSICURAZIONE";
        ResultSet resultQuery = SQL.queryRead(query);
        insurancesTicketPassenger = ParserSQL.parseInsurances(resultQuery);
        resultQuery.close();
        return insurancesTicketPassenger;        
    }
    
    public ArrayList<HoldLuggage> getHoldLuggagesTicketPassenger(String codeTicket) throws SQLException{
        ArrayList<HoldLuggage> holdLuggagesTicketPassenger;
        String query
                = "SELECT COD_BAGAGLIO, KG, PREZZOBAGAGLIO AS PREZZO, DESCRIZIONE\n" 
                + "FROM Bagaglio_Passeggero, Bagaglio\n" 
                + "WHERE PASSEGGERO = '" + codeTicket + "'\n" 
                + "AND COD_BAGAGLIO = BAGAGLIO";
        ResultSet resultQuery = SQL.queryRead(query);
        holdLuggagesTicketPassenger = ParserSQL.parseHoldLuggages(resultQuery);
        resultQuery.close();
        return holdLuggagesTicketPassenger;
    }
    
    public TicketPassenger getTicketPassenger(String codeTicket) throws SQLException{
        TicketPassenger tp;
        String query
                = "SELECT COD_TICKET, PREZZO, COD_PRENOTAZIONE, ID, NOME, COGNOME, Prenotazione.VOLO, Posto.NUMERO AS NPOSTO, Classe AS CLASSE, CHECKIN\n" 
                + "FROM TicketPasseggero, Prenotazione, Posto, Volo\n" 
                + "WHERE COD_TICKET = '" + codeTicket + "'\n" 
                + "AND PRENOTAZIONE = COD_PRENOTAZIONE\n" 
                + "AND Posto.VOLO = COD_VOLO\n"
                + "AND COD_TICKET = PASSEGGERO";
        ResultSet resultQuery = SQL.queryRead(query);
        tp = ParserSQL.parseTicketPassenger(resultQuery);
        resultQuery.close();
        return tp;
    }
    
    public Reservation getReservation(int codeReservation) throws SQLException{
        Reservation res;
        String query
                = "SELECT COD_PRENOTAZIONE, VOLO, EMAIL, NUMERO, COD_TICKET\n" 
                + "FROM Prenotazione, TicketPasseggero\n" 
                + "WHERE COD_PRENOTAZIONE =" + codeReservation + "\n" 
                + "AND PRENOTAZIONE = COD_PRENOTAZIONE";
        ResultSet resultQuery = SQL.queryRead(query);
        res = ParserSQL.parseReservation(resultQuery);
        resultQuery.close();
        return res;
    }
    
    
}
