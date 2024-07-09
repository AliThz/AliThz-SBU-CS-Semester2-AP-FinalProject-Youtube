package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Channel;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlaylistPageController implements Initializable {

    //region [ - Fields - ]

    private Playlist playlist;
    @FXML
    private Button btnChannelTitle;

    @FXML
    private Button btnPlayAll;

    @FXML
    private HBox hbxButtons;

    @FXML
    private HBox hbxPlaylistPage;

    @FXML
    private ImageView imgPlaylistThumbnail;

    @FXML
    private ScrollPane scrollPaneVideos;

    @FXML
    private Text txtPlaylistDescription;

    @FXML
    private Text txtPlaylistTitle;

    @FXML
    private VBox vbxPlaylistInfo;

    @FXML
    private VBox vbxVideos;
    private LayoutController parentController;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gson gson = new Gson();
        TypeToken<Response<Playlist>> responseTypeToken = new TypeToken<>() {
        };
        Response<Playlist> playlistResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());
        playlist = playlistResponse.getBody();

        hbxPlaylistPage.getStylesheets().clear();
        hbxPlaylistPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/playlist-page.css")).toExternalForm());

        setPlaylist();
        displayVideos();
    }
    //endregion

    //region [ - setPlaylist() - ]
    public void setPlaylist() {
        if (playlist.getThumbnailBytes() != null) {
            ByteArrayInputStream bis;
            bis = new ByteArrayInputStream(playlist.getThumbnailBytes());
            Image thumbnail = new Image(bis);
            imgPlaylistThumbnail.setImage(thumbnail);
        } else {
            vbxPlaylistInfo.getChildren().removeFirst();
        }

        txtPlaylistTitle.setText(playlist.getTitle());
        btnChannelTitle.setText(playlist.getCreator()!= null?playlist.getCreator().getUsername(): "");
        txtPlaylistDescription.setText(playlist.getDescription());
    }
    //endregion

    //region [ - displayVideos() - ]
    private void displayVideos() {
        ArrayList<Video> videos = new ArrayList<>();
        playlist.getPlaylistDetails().forEach(pd -> videos.add(pd.getVideo()));
        if (videos == null) {
            return;
        }
        Platform.runLater(() -> {
                for (var video : videos) {
                    FXMLLoader videoRecommendationLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-recommendation.fxml"));
                    HBox videoRecommendation;

                    try {
                        videoRecommendation = videoRecommendationLoader.load();
                        VideoRecommendationController videoRecommendationController = videoRecommendationLoader.getController();
                        if (videoRecommendationController != null) {
                            videoRecommendationController.setVideo(video);
                        }
                        videoRecommendationController.setParentController(parentController);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoRecommendation);


//                button.prefWidthProperty().bind(scrollPaneVideos.prefViewportWidthProperty().subtract(100));
                    scrollPaneVideos.prefViewportWidthProperty().bind(hbxPlaylistPage.widthProperty().subtract(100));
                    button.prefWidthProperty().bind(scrollPaneVideos.viewportBoundsProperty().map(bounds -> bounds.getWidth() - 10));
                    button.prefHeightProperty().bind(scrollPaneVideos.prefViewportHeightProperty());
                    vbxVideos.prefWidthProperty().bind(scrollPaneVideos.prefViewportWidthProperty());
                    vbxVideos.prefHeightProperty().bind(scrollPaneVideos.prefViewportHeightProperty());

                    videoRecommendation.prefWidthProperty().bind(button.widthProperty().subtract(20));

                    button.setOnAction(event -> getVideo(event, video));

                    vbxVideos.getChildren().add(button);
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
        scene = new Scene(root, hbxPlaylistPage.getScene().getWidth(), hbxPlaylistPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - setParentController(LayoutController parentController) - ]
    public void setParentController(LayoutController controller) {
        this.parentController = controller;
        EventHandler<ActionEvent> existingHandler = parentController.btnMode.getOnAction();

        parentController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            hbxPlaylistPage.getStylesheets().clear();
            hbxPlaylistPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/playlist-page.css")).toExternalForm());
        });
    }
    //endregion

    //endregion
}
