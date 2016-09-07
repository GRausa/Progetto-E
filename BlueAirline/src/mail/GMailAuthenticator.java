package mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Provides the authentication for sending emails.
 * 
 * @author riccardoz
 */
public class GMailAuthenticator extends Authenticator {

        String user;
        String pw;
        /**
         * Istanzia un nuovo authenticator per l'invio della mail.
         * Constructs a new authenticator for sending emails.
         * 
         * @param username Username.
         * @param password Password.
         */
        public GMailAuthenticator(String username, String password) {
            super();
            this.user = username;
            this.pw = password;
        }

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pw);
        }
    }