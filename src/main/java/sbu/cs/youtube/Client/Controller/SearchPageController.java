package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class SearchPageController {

    //region [ - Fields - ]
    Gson gson = new Gson();
    private String searchText;
    @FXML
    private FlowPane flowPanePlaylists;

    @FXML
    private FlowPane flowPaneVideos;

    @FXML
    private FlowPane flowPaneChannels;

    @FXML
    private VBox vbxSearchPage;

    @FXML
    private ScrollPane scrollPaneChannels;

    @FXML
    private ScrollPane scrollPanePlaylists;

    @FXML
    private ScrollPane scrollPaneVideos;

    private LayoutController parentController;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(String searchText) - ]
    public void initialize(String searchText){
        vbxSearchPage.getStylesheets().clear();
        vbxSearchPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/search-page.css")).toExternalForm());
        this.searchText = searchText;
        new Thread(this::displayVideos).start();
        new Thread(this::displayPlaylists).start();
        new Thread(this::displayChannels).start();
    }
    //endregion

    //region [ - displayVideos() - ]
    private void displayVideos() {
        new Request<>(YouTubeApplication.socket, "SearchVideo").send(searchText);
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());

        ArrayList<Video> videos = videoResponse.getBody();
        if (videos == null) {
            return;
        }
        Platform.runLater(() -> {
//            for (var video : videos) {
//                FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
//                VBox videoPreview;
//
//                try {
//                    videoPreview = videoPreviewLoader.load();
//                    VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
//                    if (videoPreviewController != null) {
//                        videoPreviewController.setVideo(video);
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                Button button = new Button();
//                button.getStyleClass().add("btn-video");
//                button.setGraphic(videoPreview);
//
//                button.setOnAction(event -> getVideo(event, video));
//
//                flowPaneVideos.getChildren().add(button);
//            }




            for (var video : videos) {
                FXMLLoader videoRecommendationLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-recommendation.fxml"));
                HBox videoRecommendation;

                try {
                    videoRecommendation = videoRecommendationLoader.load();
                    VideoRecommendationController videoRecommendationController = videoRecommendationLoader.getController();
                    if (videoRecommendationController != null) {
                        videoRecommendationController.setVideo(video);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Button button = new Button();
                button.getStyleClass().add("btn-video");
                button.setGraphic(videoRecommendation);


//                button.prefWidthProperty().bind(scrollPaneVideos.prefViewportWidthProperty().subtract(100));
//                scrollPaneVideos.prefViewportWidthProperty().bind(hbxPlaylistPage.widthProperty().subtract(100));
                button.prefWidthProperty().bind(scrollPaneVideos.viewportBoundsProperty().map(bounds -> bounds.getWidth() - 10));
                button.prefHeightProperty().bind(scrollPaneVideos.prefViewportHeightProperty());
                flowPaneVideos.prefWidthProperty().bind(scrollPaneVideos.prefViewportWidthProperty());
                flowPaneVideos.prefHeightProperty().bind(scrollPaneVideos.prefViewportHeightProperty());

                videoRecommendation.prefWidthProperty().bind(button.widthProperty().subtract(20));

                button.setOnAction(event -> getVideo(event, video));

                flowPaneVideos.getChildren().add(button);
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
        scene = new Scene(root, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
    //endregion


    //region [ - displayPlaylists() - ]
    private void displayPlaylists() {
        new Request<>(YouTubeApplication.socket, "SearchPlaylist").send(searchText);
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
                Button playlistPreview;

                try {
                    playlistPreview = playlistPreviewLoader.load();
                    PlaylistPreviewController playlistPreviewController = playlistPreviewLoader.getController();
                    playlistPreviewController.setParentController(parentController);
                    if (playlistPreviewController != null) {
                        playlistPreviewController.setPlaylist(playlist);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                flowPanePlaylists.getChildren().add(playlistPreview);
            }
        });
    }
    //endregion

    //region [ - displayChannels() - ]
    private void displayChannels() {
//        new Request<>(YouTubeApplication.socket, "searchChannel").send(searchText);
//        TypeToken<Response<ArrayList<Channel>>> responseTypeToken = new TypeToken<>() {
//        };
//        Response<ArrayList<Channel>> channelResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());
//
//        ArrayList<Channel> channels = channelResponse.getBody();
//        if (channels == null) {
//            return;
//        }
    }
    //endregion

    //region [ - setParentController(LayoutController layoutController) - ]
    public void setParentController(LayoutController layoutController) {
        this.parentController = layoutController;
        EventHandler<ActionEvent> existingHandler = parentController.btnMode.getOnAction();

        parentController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            vbxSearchPage.getStylesheets().clear();
            vbxSearchPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/search-page.css")).toExternalForm());
        });
    }
    //endregion

    //endregion

}
