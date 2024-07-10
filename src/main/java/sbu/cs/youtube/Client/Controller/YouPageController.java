package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
import java.time.LocalDateTime;
import java.util.*;

public class YouPageController implements Initializable {

    //region [ - Fields - ]

    private User user;
    private Gson gson;

    @FXML
    private Button btnViewAllHistory;

    @FXML
    private Button btnViewAllLikedVideos;

    @FXML
    private Button btnViewAllPlaylists;

    @FXML
    private Button btnViewAllWatchLater;

    @FXML
    private Button btnViewAllYourClips;

    @FXML
    private Button btnViewChannel;

    @FXML
    private HBox hbxHistoryHeader;

    @FXML
    private HBox hbxHistoryVideos;

    @FXML
    private HBox hbxLikedVideos;

    @FXML
    private HBox hbxLikedVideosHeader;

    @FXML
    private HBox hbxPlaylistsHeader;

    @FXML
    private HBox hbxPlaylistsVideos;

    @FXML
    private HBox hbxUserDetails;

    @FXML
    private HBox hbxWatchLaterHeader;

    @FXML
    private HBox hbxWatchLaterVideos;

    @FXML
    private HBox hbxYourClips;

    @FXML
    private HBox hbxYourClipsHeader;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private ScrollPane scrlbrHistory;

    @FXML
    private ScrollPane scrlbrLikedVideos;

    @FXML
    private ScrollPane scrlbrPlaylists;

    @FXML
    private ScrollPane scrlbrWatchLater;

    @FXML
    private ScrollPane scrlbrYourClips;

    @FXML
    private Text txtFullName;

    @FXML
    private Text txtHistoryTitle;

    @FXML
    private Text txtLikedVideosTitle;

    @FXML
    private Text txtPlaylistsTitle;

    @FXML
    private Text txtWatchLaterTitle;

    @FXML
    private Text txtYourClipsTitle;

    @FXML
    private VBox vbxDashboard;

    @FXML
    private VBox vbxHistory;

    @FXML
    private VBox vbxLikedVideos;

    @FXML
    private VBox vbxPlaylists;

    @FXML
    private VBox vbxUserDetails;

    @FXML
    private VBox vbxWatchLater;

    @FXML
    private VBox vbxYourClips;
    private LayoutController parentController;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbxDashboard.getStylesheets().clear();
        vbxDashboard.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/you-page.css")).toExternalForm());

        gson = new Gson();
        this.user = YouTubeApplication.user;
        new Thread(this::setUser).start();
        new Thread(this::displayHistory).start();
        new Thread(this::displayPlaylists).start();
        new Thread(this::displayWatchLater).start();
        new Thread(this::displayLikedVideos).start();
        new Thread(this::displayYourClips).start();
        new Thread(this::bindItems).start();
    }
    //endregion

    //region [ - setUser() - ]
    public void setUser() {
        txtFullName.setText(user.getFullName());
        btnViewChannel.setText("@" + user.getUsername() + " • " + "View Channel");

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(user.getAvatarBytes());
        Image avatar = new Image(bis);
        imgAvatar.setImage(avatar);
    }
    //endregion

    //region [ - displayHistory() - ]
    private void displayHistory() {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetHistory");
        userRequest.send(new User(user.getId()));

        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> videos = videoResponse.getBody();
        if (videos != null) {
            videos.sort(Comparator.comparing(d -> LocalDateTime.parse(d.getUploadDate())));
            Collections.reverse(videos);
        }
        Platform.runLater(() -> {
            if (videos != null) {
                for (var v : videos) {

                    FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                    VBox videoPreview;
                    try {
                        videoPreview = videoPreviewLoader.load();
                        VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                        if (videoPreviewController != null) {
                            videoPreviewController.setVideo(v);
                        }
                        videoPreviewController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoPreview);

                    button.setOnAction(event -> getVideo(event, v));
                    hbxHistoryVideos.getChildren().add(button);
                    VBox.setVgrow(videoPreview, Priority.ALWAYS);
                }
            }
        });
    }
    //endregion

    //region [ - displayPlaylists() - ]
    private void displayPlaylists() {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetUserPlaylists");
        userRequest.send(new User(user.getId()));

        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Playlist>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Playlist>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Playlist> playlists = videoResponse.getBody();
        Platform.runLater(() -> {
            if (playlists != null) {
                for (var p : playlists) {

                    FXMLLoader playlistPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-preview.fxml"));
                    Button playlistPreview;
                    try {
                        playlistPreview = playlistPreviewLoader.load();
                        PlaylistPreviewController playlistPreviewController = playlistPreviewLoader.getController();
                        if (playlistPreviewController != null) {
                            playlistPreviewController.setPlaylist(p);
                        }
                        playlistPreviewController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    hbxPlaylistsVideos.getChildren().add(playlistPreview);
                    VBox.setVgrow(playlistPreview, Priority.ALWAYS);
                }
            }
        });
    }
    //endregion

    //region [ - displayWatchLater() - ]
    private void displayWatchLater() {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetHistory");
        userRequest.send(new User(user.getId()));

        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> videos = videoResponse.getBody();
        Platform.runLater(() -> {
            if (videos != null) {
                for (var v : videos) {

                    FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                    VBox videoPreview;
                    try {
                        videoPreview = videoPreviewLoader.load();
                        VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                        if (videoPreviewController != null) {
                            videoPreviewController.setVideo(v);
                        }
                        videoPreviewController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoPreview);

                    button.setOnAction(event -> getVideo(event, v));
                    hbxWatchLaterVideos.getChildren().add(button);
                    VBox.setVgrow(videoPreview, Priority.ALWAYS);
                }
            }
        });
    }
    //endregion

    //region [ - displayLikedVideos() - ]
    private void displayLikedVideos() {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetLikedVideos");
        userRequest.send(new User(user.getId()));

        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> videos = videoResponse.getBody();
        Platform.runLater(() -> {
            if (videos != null) {
                for (var v : videos) {

                    FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                    VBox videoPreview;
                    try {
                        videoPreview = videoPreviewLoader.load();
                        VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                        if (videoPreviewController != null) {
                            videoPreviewController.setVideo(v);
                        }
                        videoPreviewController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoPreview);

                    button.setOnAction(event -> getVideo(event, v));
                    hbxLikedVideos.getChildren().add(button);
                    VBox.setVgrow(videoPreview, Priority.ALWAYS);
                }
            }
        });
    }
    //endregion

    //region [ - displayYourClips() - ]
    private void displayYourClips() {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetUserVideos");
        userRequest.send(new User(user.getId()));

        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> videos = videoResponse.getBody();
        Platform.runLater(() -> {
            if (videos != null) {
                for (var v : videos) {

                    FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                    VBox videoPreview;
                    try {
                        videoPreview = videoPreviewLoader.load();
                        VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                        if (videoPreviewController != null) {
                            videoPreviewController.setVideo(v);
                        }
                        videoPreviewController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoPreview);

                    button.setOnAction(event -> getVideo(event, v));
                    hbxYourClips.getChildren().add(button);
                    VBox.setVgrow(videoPreview, Priority.ALWAYS);
                }
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
        scene = new Scene(root, vbxDashboard.getScene().getWidth(), vbxDashboard.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - getChannel(ActionEvent event) - ]
    @FXML
    private void getChannel(ActionEvent event) {
        Request<User> videoRequest = new Request<>(YouTubeApplication.socket, "GetUserChannel");
        videoRequest.send(new User(YouTubeApplication.user.getId()));

        getChannelPage(event);
    }
    //endregion

    //region [ - getChannelPage(ActionEvent event) - ]
    private void getChannelPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/channel-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxDashboard.getScene().getWidth(), vbxDashboard.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - bindItems() - ]
    private void bindItems() {
        hbxHistoryHeader.prefWidthProperty().bind(scrlbrHistory.widthProperty());
        hbxHistoryVideos.prefWidthProperty().bind(scrlbrHistory.widthProperty());

        hbxPlaylistsHeader.prefWidthProperty().bind(scrlbrPlaylists.widthProperty());
        hbxPlaylistsVideos.prefWidthProperty().bind(scrlbrPlaylists.widthProperty());

        hbxWatchLaterHeader.prefWidthProperty().bind(scrlbrWatchLater.widthProperty());
        hbxWatchLaterVideos.prefWidthProperty().bind(scrlbrWatchLater.widthProperty());

        hbxLikedVideosHeader.prefWidthProperty().bind(scrlbrLikedVideos.widthProperty());
        hbxLikedVideos.prefWidthProperty().bind(scrlbrLikedVideos.widthProperty());

        hbxYourClipsHeader.prefWidthProperty().bind(scrlbrYourClips.widthProperty());
        hbxYourClips.prefWidthProperty().bind(scrlbrYourClips.widthProperty());
    }
    //endregion

    //region [ - setParentController(LayoutController layoutController) - ]
    public void setParentController(LayoutController layoutController) {
        this.parentController = layoutController;
        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            vbxDashboard.getStylesheets().clear();
            vbxDashboard.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/you-page.css")).toExternalForm());
        });
    }
    //endregion

    //endregion

}
