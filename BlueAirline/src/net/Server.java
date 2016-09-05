/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net;

import controller.FacadeControllerServer;
import controller.InterfaceServer;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * La classe Server è utilizzata per la comunicazione fra client-server.
 * 
 * @author 
 */
public class Server {
    InterfaceServer company;
    int port;
    ServerSocket serverSocket = null;

    /**
     * Inizializza il server.
     *
     * @param company
     * @param port
     */
    public Server(InterfaceServer company, int port) {
        this.company = company;
        this.port = port;
    }

    /**
     * Avvia il server.
     *
     * @throws IOException
     */
    public void startServer() throws IOException {
        serverSocket = new ServerSocket(port);

        Logger.getLogger(Server.class.getName()).log(Level.INFO,
                "Server started");
        while (true) {
            Socket socket = serverSocket.accept();
            RemoteUser u = new RemoteUser((FacadeControllerServer) company, socket);
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
        final InterfaceServer c = FacadeControllerServer.getIstance();
        Server server = new Server(c, PORT);
        try {
            server.startServer();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.INFO, null, ex);
        }
    }
}
