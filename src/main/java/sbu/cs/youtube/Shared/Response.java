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
    private String type;
    private boolean isDone;
    private String error;
    private T body;
    private ArrayList<T> bodyList;
    private Notification notification;
    private transient final BufferedWriter bufferedWriter;
    //endregion

    //region [ - Constructor - ]
    public Response(Socket client, String type, boolean isDone, String error) {
        try {
            this.client = client;
            this.type = type;
            this.isDone = isDone;
            this.error = error;
            notification = new Notification();
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region [ - Methods - ]

    //region [ - send() - ]
    public void send() {
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(body);

        jsonResponse.addProperty("Type", gson.toJson(type));
        jsonResponse.addProperty("IsDone", gson.toJson(isDone));
        jsonResponse.addProperty("Error", gson.toJson(error));

        write(jsonResponse.toString());
    }
    //endregion

    //region [ - send(T body) - ]
    public void send(T body) {
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        String jsonObject = gson.toJson(body);

        jsonResponse.addProperty("Type", gson.toJson(type));
        jsonResponse.addProperty("Body", jsonObject);
        jsonResponse.addProperty("IsDone", gson.toJson(isDone));
        jsonResponse.addProperty("Error", gson.toJson(error));

        write(jsonResponse.toString());
    }
    //endregion

    //region [ - send(ArrayList<T> bodyList) - ]
    public void send(ArrayList<T> bodyList) {
        JsonObject jsonResponse = new JsonObject();
        Gson gson = new Gson();
        String jsonArray = gson.toJson(bodyList);

        jsonResponse.addProperty("Type", gson.toJson(type));
        jsonResponse.addProperty("BodyList", jsonArray);
        jsonResponse.addProperty("IsDone", gson.toJson(isDone));
        jsonResponse.addProperty("Error", gson.toJson(error));

        write(jsonResponse.toString());
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
