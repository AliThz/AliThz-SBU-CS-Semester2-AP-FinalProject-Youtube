package sbu.cs.youtube.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(2345);
            System.out.println("SERVER HAS STARTED LISTENING ON PORT '2345'");

            while (true) {
                //Waiting for clients to connect
                Socket socket = serverSocket.accept();
                System.out.println("User with Local address :  " + socket.getLocalAddress() + " connected");

                //Giving service to each client
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
}
