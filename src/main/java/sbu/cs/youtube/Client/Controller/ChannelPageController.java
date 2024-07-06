package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Channel;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChannelPageController implements Initializable {

    //region [ - Fields - ]

    private Channel channel;
    private final Gson gson = new Gson();
    @FXML
    private HBox hbxChannelDetails;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private ScrollPane scrlpnPlaylist;

    @FXML
    private ScrollPane scrlpnVideo;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPlaylist;

    @FXML
    private Tab tabVideo;

    @FXML
    private TilePane tilePanePlaylist;

    @FXML
    private TilePane tilePaneVideo;

    @FXML
    private Text txtChannelDescription;

    @FXML
    private Text txtChannelTitle;

    @FXML
    private Text txtSubscribersCount;

    @FXML
    private Text txtUsername;

    @FXML
    private Text txtVideosCount;

    @FXML
    private VBox vbxChannelDetails;

    @FXML
    private VBox vbxChannelPage;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Channel>> responseTypeToken = new TypeToken<>() {
        };
        Response<Channel> videoResponse = gson.fromJson(response, responseTypeToken.getType());
        channel = videoResponse.getBody();


        new Thread(this::setChannel).start();
        new Thread(this::displayVideos).start();
        new Thread(this::displayPlaylists).start();
    }
    //endregion

    //region [ - setChannel() - ]
    private void setChannel() {
        txtChannelTitle.setText(channel.getTitle());
        txtChannelDescription.setText(channel.getDescription());
        txtUsername.setText(YouTubeApplication.user.getUsername());
//        txtVideosCount.setText(channel);
        txtSubscribersCount.setText(String.valueOf(channel.getSubscribers()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(YouTubeApplication.user.getAvatarBytes());
        Image avatar = new Image(bis);
        imgAvatar.setImage(avatar);
    }
    //endregion

    //region [ - displayVideos() - ]
    private void displayVideos() {
        new Request<Channel>(YouTubeApplication.socket, "GetChannelVideos").send(channel);
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());

        ArrayList<Video> recommendedVideos = videoResponse.getBody();
        if (recommendedVideos == null) {
            return;
        }
        Platform.runLater(() -> {
            for (var video : recommendedVideos) {
                FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                VBox videoPreview;

                try {
                    videoPreview = videoPreviewLoader.load();
                    VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                    if (videoPreviewController != null) {
                        videoPreviewController.setVideo(video);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tilePaneVideo.getChildren().add(videoPreview);

                Button button = new Button();
                button.getStyleClass().add("btn-video");
                button.setGraphic(videoPreview);

                button.setOnAction(event -> getVideo(event, video));
            }
        });
    }
    //endregion

    //region [ - getVideo(ActionEvent event, Video video) - ]
    private void getVideo(ActionEvent event, Video video) {
        Request<Video> videoRequest = new Request<>(YouTubeApplication.socket, "GetVideo");
        videoRequest.send(new Video(video.getId()));

        getVideoPage(event);
    }
    //endregion

    //region [ - getVideoPage(ActionEvent event) - ]
    private void getVideoPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxChannelPage.getScene().getWidth(), vbxChannelPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion


    //region [ - displayPlaylists() - ]
    private void displayPlaylists() {
        new Request<User>(YouTubeApplication.socket, "GetUserPlaylists").send(YouTubeApplication.user);
        TypeToken<Response<ArrayList<Playlist>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Playlist>> playlistResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());

        ArrayList<Playlist> playlists = playlistResponse.getBody();
        if (playlists == null) {
            return;
        }
        Platform.runLater(() -> {
            for (var playlist : playlists) {
                FXMLLoader playlistPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-preview.fxml"));
                VBox playlistPreview;

                try {
                    playlistPreview = playlistPreviewLoader.load();
                    PlaylistPreviewController playlistPreviewController = playlistPreviewLoader.getController();
                    if (playlistPreviewController != null) {
                        playlistPreviewController.setPlaylist(playlist);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tilePanePlaylist.getChildren().add(playlistPreview);

                Button button = new Button();
                button.getStyleClass().add("btn-playlist");
                button.setGraphic(playlistPreview);

                button.setOnAction(event -> getPlaylist(event, playlist));
            }
        });
    }
    //endregion

    //region [ - getPlaylist(ActionEvent event, Playlist playlist) - ]
    private void getPlaylist(ActionEvent event, Playlist playlist) {
        Request<Playlist> playlistRequest = new Request<>(YouTubeApplication.socket, "GetPlaylist");
        playlistRequest.send(new Playlist(playlist.getId()));

        getPlaylistPage(event);
    }
    //endregion

    //region [ - getPlaylistPage(ActionEvent event) - ]
    private void getPlaylistPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxChannelPage.getScene().getWidth(), vbxChannelPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //endregion
}
