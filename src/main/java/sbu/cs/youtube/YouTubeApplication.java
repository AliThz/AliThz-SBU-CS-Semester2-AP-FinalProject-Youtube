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
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Response;

import javax.crypto.Cipher;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        FXMLLoader fxmlLoader = new FXMLLoader(YouTubeApplication.class.getResource("home-section.fxml"));
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
//        Socket socket = new Socket("localhost", 2345);
//        Request<Video> userRequest = new Request<>(socket, "GetVideo");
//        Video video = new Video("avengers" , "marvel video" , UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf") , 65 , LocalDateTime.now().toString());
//        video.setId(UUID.fromString("dece64c2-acd4-427a-a5d5-920905b1041b"));
//        userRequest.send(video);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- get Playlist test ----------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Playlist> userRequest = new Request<>(socket, "GetPlaylist");
//        Playlist playlist = new Playlist();
//        playlist.setId(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b"));
//        userRequest.send(playlist);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- CheckSubscriptionExistence Test ----------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Subscription> userRequest = new Request<>(socket, "CheckSubscriptionExistence");
//        Subscription subscription = new Subscription();
//        subscription.setChannelId(UUID.fromString("e0ce92d5-f8ff-40eb-8d46-31c7a19ecc1a"));
//        subscription.setSubscriberId(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        userRequest.send(subscription);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- Subscribe Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Subscription> userRequest = new Request<>(socket, "Subscribe");
//        Subscription subscription = new Subscription();
//        subscription.setChannelId(UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf"));
//        subscription.setSubscriberId(UUID.fromString("5479f070-5f9b-47f3-b762-629d22c1dffc"));
//        userRequest.send(subscription);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- UnSubscribe Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Subscription> userRequest = new Request<>(socket, "Unsubscribe");
//        Subscription subscription = new Subscription();
//        subscription.setChannelId(UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf"));
//        subscription.setSubscriberId(UUID.fromString("5479f070-5f9b-47f3-b762-629d22c1dffc"));
//        userRequest.send(subscription);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- checkViewVideoExistence Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<UserVideo> userRequest = new Request<>(socket, "CheckViewVideoExistence");
//        UserVideo userVideo = new UserVideo();
//        userVideo.setUserId(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        userVideo.setVideoId(UUID.fromString("be7d7d84-c089-4c71-8492-572627494874"));
//        userRequest.send(userVideo);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- likeVideo and dislikeVideo Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<UserVideo> userRequest = new Request<>(socket, "DislikeVideo");
//        UserVideo userVideo = new UserVideo();
//        userVideo.setUserId(UUID.fromString("13d11d94-e385-4e29-9c68-38d8c97a0429"));
//        userVideo.setVideoId(UUID.fromString("be7d7d84-c089-4c71-8492-572627494875")    );
//        userRequest.send(userVideo);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
//        ------------------------- LikeComment and DislikeComment Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<UserComment> userRequest = new Request<>(socket, "GetCommentLikesStatus");
//        UserComment userComment = new UserComment();
//        userComment.setUserId(UUID.fromString("5479f070-5f9b-47f3-b762-629d22c1dffc"));
//        userComment.setCommentId(UUID.fromString("e667f49f-ffb8-4f28-a8e2-caeaa33e59c6")    );
//        userRequest.send(userComment);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- LikeComment and DislikeComment Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Comment> userRequest = new Request<>(socket, "GetCommentLikesStatus");
//        Comment comment = new Comment();
//        comment.setId(UUID.fromString("5b16a1da-1d90-4e0d-a901-37a50ba0fe22"));
//        userRequest.send(comment);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select channelVideos Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Channel> userRequest = new Request<>(socket, "GetChannelVideos");
//        Channel channel = new Channel();
//        channel.setId(UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf"));
//        userRequest.send(channel);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select channel Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Channel> userRequest = new Request<>(socket, "GetChannel");
//        Channel channel = new Channel();
//        channel.setId(UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf"));
//        userRequest.send(channel);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select channel Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Channel> userRequest = new Request<>(socket, "GetChannel");
//        Channel channel = new Channel();
//        channel.setId(UUID.fromString("a03df34b-5370-461c-8206-1c4ac95c94cf"));
//        userRequest.send(channel);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select UserPlaylists Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<User> userRequest = new Request<>(socket, "GetUserPlaylists");
//        User user = new User();
//        user.setId(UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f"));
//        userRequest.send(user);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- AddVideoToPlaylist Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<PlaylistDetail> userRequest = new Request<>(socket, "AddVideoToPlaylist");
//        PlaylistDetail playlistDetail = new PlaylistDetail();
//        playlistDetail.setVideoId(UUID.fromString("230f32f1-824e-4a51-b040-e2e5dde39dc5"));
//        playlistDetail.setPlaylistId(UUID.fromString("80790d65-faca-4084-89b1-bafa6b7520bd"));
//        userRequest.send(playlistDetail);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();

        //        ------------------------- SelectLikedVideos Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<User> userRequest = new Request<>(socket, "GetLikedVideos");
//        User user = new User();
//        user.setId(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        userRequest.send(user);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();

        //        ------------------------- selectVideoViewCount Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Video> userRequest = new Request<>(socket, "GetVideoViewCount");
//        Video video = new Video();
//        video.setId(UUID.fromString("ee7f655a-a0ca-48c5-b796-29009608aa2c"));
//        userRequest.send(video);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- getCategories Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<ArrayList<Category>> userRequest = new Request<>(socket, "GetCategories");
//        ArrayList<Category> categories = new ArrayList<Category>();
//        userRequest.send(categories);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select categoryVideos Test ---------------------------------------------
//        Socket socket = new Socket("localhost", 2345);
//        Request<Category> userRequest = new Request<>(socket, "GetCategoryVideos");
//        Category category = new Category();
//        category.setId(UUID.fromString("3d967083-bdd6-4016-b412-b7a2d9576d28"));
//        userRequest.send(category);
//        YouTubeApplication client = new YouTubeApplication(socket);
//        String response = receiveResponse();
//        System.out.println(response);
//        launch();
        //        ------------------------- Select VidoeCategories Test ---------------------------------------------
        Socket socket = new Socket("localhost", 2345);
        Request<Category> userRequest = new Request<>(socket, "GetVideoCategories");
        Category category = new Category();
        category.setId(UUID.fromString("a6592c17-0cef-4fa3-a0ae-9fc1844d1971"));
        userRequest.send(category);
        YouTubeApplication client = new YouTubeApplication(socket);
        String response = receiveResponse();
        System.out.println(response);
        launch();


    }
}