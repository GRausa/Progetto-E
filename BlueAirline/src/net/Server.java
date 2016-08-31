/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import controller.FacadeController;
import controller.InterfaceClient;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    InterfaceClient company;
    int port;
    ServerSocket serverSocket = null;

    /**
     * Server initialization.
     *
     * @param company
     * @param port
     */
    public Server(InterfaceClient company, int port) {
        this.company = company;
        this.port = port;
    }

    /**
     * Start the server.
     *
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);

        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                "Server started");
        while (true) {
            Socket socket = serverSocket.accept();
            RemoteUser u = new RemoteUser(company, socket);
            Logger.getLogger(Server.class.getName()).log(Level.INFO, "Accepting a new user. Ip guest : " + socket.getInetAddress() + "port guest:" + socket.getPort());
            u.start();
        }
    }

    /**
     * Start the application.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        final int PORT = 8888;
        final InterfaceClient c = new FacadeController();
        Server server = new Server(c, PORT);
        try {
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, null, ex);
        }
    }
}
