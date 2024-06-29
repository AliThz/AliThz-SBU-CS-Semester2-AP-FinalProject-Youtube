package sbu.cs.youtube.Shared;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sbu.cs.youtube.Shared.POJO.Notification;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Response<T> {

    //region [ - Fields - ]
    private transient final Socket client;
    private final String type;
    private final boolean isDone;
    private final String message;
    private T body;
//    private ArrayList<T> bodyList;
    private Notification notification;
    private transient final BufferedWriter bufferedWriter;
    //endregion

    //region [ - Constructor - ]
    public Response(Socket client, String type, boolean isDone, String message) {
        try {
            this.client = client;
            this.type = type;
            this.isDone = isDone;
            this.message = message;
//            notification = new Notification();
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - send() - ]
    public void send() {
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(this);
        write(jsonResponse);
    }
    //endregion

    //region [ - send(T body) - ]
    public void send(T body) {
        Gson gson = new Gson();
        this.body = body;
        String jsonResponse = gson.toJson(this);
        write(jsonResponse);
    }
    //endregion

//    //region [ - send(ArrayList<T> bodyList) - ]
//    public void send(ArrayList<T> bodyList) {
//        Gson gson = new Gson();
//        this.bodyList = bodyList;
//        String jsonResponse = gson.toJson(this);
//        write(jsonResponse);
//    }
//    //endregion

    //region [ - Getters - ]

    //region [ - getClient() - ]
    public Socket getClient() {
        return client;
    }
    //endregion

    //region [ - getType() - ]
    public String getType() {
        return type;
    }
    //endregion

    //region [ - isDone() - ]
    public boolean isDone() {
        return isDone;
    }
    //endregion

    //region [ - getError() - ]
    public String getMessage() {
        return message;
    }
    //endregion

    //region [ - getBody() - ]
    public T getBody() {
        return body;
    }
    //endregion

//    //region [ - getBodyList() - ]
//    public ArrayList<T> getBodyList() {
//        return bodyList;
//    }
//    //endregion

    //region [ - getNotification() - ]
    public Notification getNotification() {
        return notification;
    }
    //endregion

    //endregion

    //region [ - write(String content) - ]
    private void write(String content) {
        try {
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //endregion
}
