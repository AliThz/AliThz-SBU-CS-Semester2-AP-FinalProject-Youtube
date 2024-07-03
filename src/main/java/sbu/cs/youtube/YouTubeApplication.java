package sbu.cs.youtube;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import sbu.cs.youtube.Client.Controller.HomeSectionController;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class YouTubeApplication extends Application {

    public static Socket socket;
    public static User user;
    private static BufferedReader bufferedReader;
    private static BufferedWriter bufferedWriter;


    public YouTubeApplication() {
    }

    public YouTubeApplication(Socket socket){
        try {
            YouTubeApplication.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            close(socket, bufferedReader, bufferedWriter);
        }
    }

    public static String receiveResponse() {
        String response = null;
        try {
            response = bufferedReader.readLine();
        } catch (IOException ioe) {
            System.out.println("!!Exception : " + ioe.getMessage());
        }

        return response;
    }

    private static void close(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
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
//        FXMLLoader fxmlLoader = new FXMLLoader(YouTubeApplication.class.getResource("home-section.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(YouTubeApplication.class.getResource("video-page.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(YouTubeApplication.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
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

    public static void main(String[] args) throws IOException {
//        ------------------------- Sign up Test ----------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<User> userRequest = new Request<>(socket, "SignUp");
//        userRequest.send(new User("Ali Taherzadeh", "Ali.Thz@gmail.com", "AliThz", "Ali123456", LocalDateTime.now().toString()));
//        YouTubeApplication client = new YouTubeApplication(socket);
//        receiveResponse();
//        ------------------------- Sign in Test ----------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<User> userRequest = new Request<>(socket, "SignIn");
//        userRequest.send(new User("Ali Taherzadeh", "Ali.Thz@gmail.com", "", "Ali123456", LocalDateTime.now().toString()));
//        YouTubeApplication client = new YouTubeApplication(socket);
//        receiveResponse();
//        ------------------------- get Video test --------------------------------
        Socket socket = new Socket("localhost", 2345);
        Request<Video> userRequest = new Request<>(socket, "GetVideo");
        Video video = new Video("avengers" , "marvel video" , UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf") , 65 , LocalDateTime.now().toString());
        video.setId(UUID.fromString("dece64c2-acd4-427a-a5d5-920905b1041b"));
        userRequest.send(video);
        YouTubeApplication client = new YouTubeApplication(socket);
        String response = receiveResponse();
        System.out.println(response);
        launch();
    }
}