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
    private transient String request;
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
        this.request = request;
        TypeToken<Request<Object>> responseTypeToken = new TypeToken<>() {
        };
        Request<Object> objectRequest = gson.fromJson(request, responseTypeToken.getType());
        switch (objectRequest.getType()) {
            case "CheckExistingUser":
                CheckExistingUser();
                break;
            case "SignUp":
                signUp();
                break;
            case "SignIn":
                signIn();
        }
    }
    //endregion

    //region [ - signUp() - ]
    private void signUp() {
        Gson gson = new Gson();
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;

        User user = userRequest.getBody();
        databaseManager.insertUser(user);

        response = new Response<>(client, "SignUp", true, "Signed up successfully");
        response.send(user);
    }
    //endregion

    //region [ - signIn() - ]
    private void signIn() {
        Gson gson = new Gson();
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;


        User requestedUser = userRequest.getBody();
        User user;
        if (requestedUser.getEmail().isEmpty()) {
            user = databaseManager.selectUserByUsername(requestedUser.getUsername());
        } else {
            user = databaseManager.selectUserByEmail(requestedUser.getEmail());
        }

        if (user != null) {
            if (requestedUser.getPassword().equals(user.getPassword())) {
                response = new Response<>(client, "SignUp", true, "Signed in successfully");
                response.send(user);
            } else {
                response = new Response<>(client, "SignIn", true, "Password is incorrect");
                response.send();
            }
        } else {
            response = new Response<>(client, "SignIn", true, "User not found");
            response.send();
        }
    }
    //endregion

    //region [ - CheckExistingUser() - ]
    private void CheckExistingUser() {
        Gson gson = new Gson();
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;

        User requestedUser = userRequest.getBody();
        User user = null;
        if (requestedUser.getEmail().isEmpty()) {
            user = databaseManager.selectUserByUsername(requestedUser.getUsername());
        } else {
            user = databaseManager.selectUserByEmail(requestedUser.getEmail());
        }

        if (user != null) {
            response = new Response<>(client, "CheckExistingUser", true, "User found");
        } else {
            response = new Response<>(client, "CheckExistingUser", true, "User not found");
        }

        response.send(user);
    }
    //endregion

    //endregion

}
