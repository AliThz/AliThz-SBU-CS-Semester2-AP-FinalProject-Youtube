package sbu.cs.youtube.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sbu.cs.youtube.Database.DatabaseManager;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.Response;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    //region [ - Fields - ]
    private final Socket client;
    private BufferedReader bufferedReader;
    private DatabaseManager databaseManager;
    //endregion

    //region [ - Constructor - ]
    public ClientHandler(Socket client) {
        this.client = client;
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader((client.getInputStream())));
            this.databaseManager = new DatabaseManager();
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - run() - ]
    @Override
    public void run() {
        try {
            try {
                while (true) {
                    receiveRequest();
                }
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException ioe) {
                    System.out.println("!!Exception : " + ioe.getMessage());
                }
            }
        } finally {
            System.out.println("Client with IP :  " + client.getRemoteSocketAddress() + " disconnected");
        }
    }
    //endregion

    //region [ - receiveRequest() - ]
    public void receiveRequest() {
        try {
            String request = bufferedReader.readLine();
            handleRequest(request);
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //region [ - handleRequest(String request) - ]
    public void handleRequest(String request) {
        Gson gson = new Gson();
        JsonObject jsonRequest = gson.fromJson(request, JsonObject.class);

        switch (jsonRequest.get("Type").getAsString()) {
            case "SignUp":
                databaseManager.insertUser(gson.fromJson(jsonRequest.get("Body"), User.class));
                Response<User> response = new Response<>(client, "SignUp");
                response.send();
                break;
            case "SignIn":


        }
    }
    //endregion

    //endregion

}
