/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Zodraccir
 */
public class Email {

    String destinatario; // questa variabile contiene l'indirizzo del destinatario
    private static final String mittente = "thetablueairline@gmail.com"; // questa variabile contiene l'indirizzo del mittente
    private static final String host = "smtp.gmail.com";                  // questa variabile contiene l'indirizzo dell'host per l'invio email del nostro.. 
    private static final String pass = "ipuffivolanti";
    //..gestore dell'alds , è da cambiare a seconda del gestore che si ha

    String oggetto;          // questa variabile contiene l'oggetto dell'email    
    String testo;     // questa variabile contiene il testo dell'email

    public Email(String sender, String object, String text) {
        this.destinatario = sender;
        this.oggetto = object;
        this.testo = text;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getMittente() {
        return mittente;
    }

    public String getHost() {
        return host;
    }

    public String getOggetto() {
        return oggetto;
    }

    public String getTesto() {
        return testo;
    }
    

    public static void sendMail(Email e) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", Email.host);
            props.put("mail.smtp.user", Email.mittente);
            props.put("mail.smtp.password", Email.pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            
            Session session = Session.getInstance(props, new GMailAuthenticator(Email.mittente, Email.pass));
            MimeMessage message = new MimeMessage(session);
            Address fromAddress = new InternetAddress(Email.mittente);
            Address toAddress = new InternetAddress(e.getDestinatario());
            
            message.setFrom(fromAddress);
            message.setRecipient(Message.RecipientType.TO, toAddress);
            
            message.setSubject(e.getOggetto());
            message.setText(e.getTesto());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, mittente, pass);
            message.saveChanges();
            Transport.send(message);
            transport.close();
        } catch (AddressException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
