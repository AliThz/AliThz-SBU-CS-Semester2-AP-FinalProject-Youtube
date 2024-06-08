package sbu.cs.youtube.Shared;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Request<T> {

    //region [ - Fields - ]
    private final Socket socket;
    private BufferedWriter bufferedWriter;
    private final String type;
    private final T body;
    //endregion

    //region [ - Constructor - ]
    public Request(Socket socket, String type, T body) {
        this.socket = socket;
        this.type = type;
        this.body = body;
        try {
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - send(T body) - ]
    public void send() {
        JsonObject jsonRequest = new JsonObject();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(body);

        jsonRequest.addProperty("Type", gson.toJson(type));
        jsonRequest.addProperty("Body", jsonObject);

        write(jsonRequest.toString());
    }
    //endregion

    //region [ - getType() - ]
    public String getType() {
        return type;
    }
    //endregion

    //region [ - getObject() - ]
    public T getObject() {
        return body;
    }
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
