package sbu.cs.youtube.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import sbu.cs.youtube.Server.Database.DatabaseManager;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
                while (client.isConnected()) {
                    receiveRequest();
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {};
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());

        switch (userRequest.getType()) {
            case "SignUp":
                User user = userRequest.getBody();
                databaseManager.insertUser(user);
                Response<User> response = new Response<>(client, "SignUp", true, null);
                response.send();
                break;
            case "SignIn":
//                ArrayList<User> users = databaseManager.selectUserBriefly();
//                Response<User> response = new Response<>(client, "SignIn");
//                response.send(users);
        }
    }
    //endregion

    //endregion

}
