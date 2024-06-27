package sbu.cs.youtube.Shared;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import sbu.cs.youtube.Shared.POJO.User;

public class Request<T> {

    //region [ - Fields - ]
    private transient final Socket socket;
    private final String type;
    private T body;
    private transient BufferedWriter bufferedWriter;
    //endregion

    //region [ - Constructor - ]
    public Request(Socket socket, String type) {
        this.socket = socket;
        this.type = type;
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - send() - ]
    public void send() {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(this);
        write(jsonRequest);
    }
    //endregion

    //region [ - send(T object) - ]
    public void send(T body) {
        Gson gson = new Gson();
        this.body = body;
        String jsonRequest = gson.toJson(this);
        write(jsonRequest);
    }
    //endregion

    //region [ - Getters - ]

    //region [ - getSocket() - ]
    public Socket getSocket() {
        return socket;
    }
    //endregion

    //region [ - getType() - ]
    public String getType() {
        return type;
    }
    //endregion

    //region [ - getObject() - ]
    public T getBody() {
        return body;
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