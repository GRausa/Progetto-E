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

    public void riempiseatflight(Flight volo) throws SQLException {
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

    public ArrayList<Flight> riempivoli() throws SQLException {
        ArrayList<Flight> flights;
        String query
                = "SELECT V.COD_VOLO, A1.CITTA \"CITTAPARTENZA\", A1.NOME \"AEROPORTOPARTENZA\", A2.CITTA \"CITTAARRIVO\", A2.NOME \"AEROPORTOARRIVO\", V.DATAPARTENZA, V.ORAPARTENZA, V.DATAARRIVO, V.ORAARRIVO, V.PREZZO "
                + "FROM Rotta R, Aeroporto A1, Aeroporto A2, Volo V "
                + "WHERE R.AEROPORTOPARTENZA = A1.COD_AEROPORTO AND R.AEROPORTOARRIVO=A2.COD_AEROPORTO AND R.COD_ROTTA=V.ROTTA";
        ResultSet resultQuery = SQL.queryRead(query);
        flights = ParserSQL.parseFlights(resultQuery);

        for (Flight fli : flights) {
            System.out.println(fli);
            riempiseatflight(fli);
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
            this.getFlightWithSeats(f);
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

    public Reservation searchReservation(int code) throws SQLException {
        Reservation reservation;
        String query
                = "SELECT COD_PRENOTAZIONE, VOLO, EMAIL, NUMERO\n"
                + "FROM Prenotazione\n"
                + "WHERE COD_PRENOTAZIONE = " + code;
        ResultSet resultQuery = SQL.queryRead(query);
        reservation = ParserSQL.parseReservation(resultQuery);
        resultQuery.close();
        return reservation;
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
        for (TicketPassenger tp : reservation.getPassengers()) {
            i++;
            String codeTicket = reservation.getCodeFlight() +""+reservation.getCode()+""+i;
            tp.setCode(codeTicket);
            
            query = "INSERT INTO TicketPasseggero\n"
            //        + "VALUES ('" + codeTicket + "', '" + tp.getID() + "', '" + tp.getName() + "', '" + tp.getSurname() + "', '" + reservation.getCode() + "', '" + reservation.getCodeFlight() + "', '" + tp.getNseat() + "')";
                       + "VALUES ('" + tp.getCode() + "', '" + tp.getID() + "', '" + tp.getName() + "', '" + tp.getSurname() + "', '" + reservation.getCode() + "',0)";
            SQL.queryWrite(query);
            this.setSeatBoolean(reservation.getCodeFlight(), tp.getNseat(), tp.getCode()); //lo metto a 1 cioè occupato
            //aggiunte
            ArrayList<String> meals = tp.getMeals();
            for (String s : meals) {
                this.insertAggiunta(s, codeTicket);
            }
            ArrayList<String> insurances = tp.getInsurances();
            for (String s : insurances) {
                this.insertAggiunta(s, codeTicket);
            }
            ArrayList<String> holdLuggages = tp.getHoldLuggages();
            for (String s : holdLuggages) {
                this.insertAggiunta(s, codeTicket);
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
                        + "VALUES ('" + code + "', '" + codeTicket + "','" + price + "')";
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
                        + "VALUES ('" + code + "', '" + codeTicket + "','" + price + "')";
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
                        + "VALUES ('" + code + "', '" + codeTicket + "','" + price + "')";
                SQL.queryWrite(query);
                break;
            default:
                break;
        }
    }

    public Flight getFlightWithSeats(Flight flight) throws SQLException {
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
}
