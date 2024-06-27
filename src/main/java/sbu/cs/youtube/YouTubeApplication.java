package sbu.cs.youtube;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import sbu.cs.youtube.Client.Controller.HomeSectionController;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.Request;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class YouTubeApplication extends Application {

    private Socket socket;
    private User user;
    private static BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;


    public YouTubeApplication() {
    }

    public YouTubeApplication(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            close(socket, bufferedReader, bufferedWriter);
        }
    }

    public String receiveResponse() {
        String response = null;
        try {
            response = bufferedReader.readLine();
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }

        return response;
    }

    private void close(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YouTubeApplication.class.getResource("home-section.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HomeSectionController controller = fxmlLoader.getController();
        controller.setClient(this);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/YoutubeIcon.png"))));
        stage.setTitle("Youtube");
        stage.setScene(scene);
        stage.show();
    }

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


    public Socket getSocket() {
        return socket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) throws IOException {
//        Socket socket = new Socket("localhost", 2345);
////        Request<User> userRequest = new Request<>(socket, "SignUp");
////        userRequest.send(new User("Ali Taherzadeh", "Ali.Thz@gmail.com", "AliThz", "Ali123456", LocalDateTime.now().toString()));
//        YouTubeApplication client = new YouTubeApplication(socket);
//        receiveResponse();

        launch();
    }
}