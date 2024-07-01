package sbu.cs.youtube.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import sbu.cs.youtube.Server.Database.DatabaseManager;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
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
    private String request;
    private final Gson gson;
    //endregion

    //region [ - Constructor - ]
    public ClientHandler(Socket client) {
        this.gson = new Gson();
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
    public void receiveRequest() throws IOException {
        try {
            String request = bufferedReader.readLine();
            handleRequest(request);
        } catch (IOException ioe) {
            try {
                if (client != null) {
                    client.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("!!Exception : " + e.getMessage());
            }
        }
    }
    //endregion

    //region [ - handleRequest(String request) - ]
    public void handleRequest(String request) {
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
                break;
            case "GetRecommendedVideos":
                GetRecommendedVideos();
                break;
        }
    }
    //endregion

    //region [ - signUp() - ]
    private void signUp() {
        TypeToken<Request<User>> responseTypeToken = new TypeToken<>() {
        };
        Request<User> userRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<User> response;

        User user = userRequest.getBody();
        databaseManager.insertUser(user);

        response = new Response<>(client, userRequest.getType(), true, "Signed up successfully");
        response.send(user);
    }
    //endregion

    //region [ - signIn() - ]
    private void signIn() {
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
                response = new Response<>(client, userRequest.getType(), true, "Signed in successfully");
            } else {
                response = new Response<>(client, userRequest.getType(), true, "Password is incorrect");
                user = null;
            }
        } else {
            response = new Response<>(client, userRequest.getType(), true, "User not found");
        }
        response.send(user);
    }
    //endregion

    //region [ - CheckExistingUser() - ]
    private void CheckExistingUser() {
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
            response = new Response<>(client, userRequest.getType(), true, "There is already a user with this email");
        } else {
            response = new Response<>(client, userRequest.getType(), true, "User not found");
        }

        response.send(user);
    }
    //endregion

    //region [ - GetRecommendedVideos() - ]
    private void GetRecommendedVideos() {
        TypeToken<Request<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Request<ArrayList<Video>> videosRequest = gson.fromJson(request, responseTypeToken.getType());
        Response<ArrayList<Video>> response;

        ArrayList<Video> videos = databaseManager.selectVideosBriefly();

        response = new Response<>(client, videosRequest.getType(), true, "Signed up successfully");
        response.send(videos);
    }
    //endregion

    //endregion

}
