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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SubscriptionSectionController implements Initializable {

    //region [ - Field - ]
    @FXML
    AnchorPane mainPane;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.getStylesheets().clear();
        mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/home-section.css")).toExternalForm());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/layout.fxml"));
        Parent layout;
        try {
            layout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().add(layout);
        LayoutController layoutController = loader.getController();

        layoutController.svgSubs.setContent("M20 7H4V6h16v1zm2 2v12H2V9h20zm-7 6-5-3v6l5-3zm2-12H7v1h10V3z");
        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());

        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            mainPane.getStylesheets().clear();
            mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/home-section.css")).toExternalForm());
        });

        if (YouTubeApplication.user == null) {
            return;
        }

        new Thread(() -> displaySubscriptionVideos(layoutController)).start();

    }
    //endregion

    //region [ - displaySubscriptionVideos(LayoutController parentController) - ]
    private void displaySubscriptionVideos(LayoutController parentController) {
        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "GetSubscription");
        userRequest.send(new User(YouTubeApplication.user.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> subscriptionVideos = videoResponse.getBody();
        if (subscriptionVideos == null) {
            return;
        }
        Platform.runLater(() -> {
            for (var video : subscriptionVideos) {
                FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                VBox videoPreview;

                try {
//                layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());
                    videoPreview = videoPreviewLoader.load();
//                videoPreview.prefWidthProperty().bind(mainPane.widthProperty().divide(6));
//                videoPreview.prefHeightProperty().bind(mainPane.heightProperty().divide(6));
                    VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                    videoPreviewController.setParentController(parentController);
                    if (videoPreviewController != null) {
                        videoPreviewController.setVideo(video);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Button button = new Button();
                button.getStyleClass().add("btn-video");
                button.setGraphic(videoPreview);

                button.setOnAction(event -> getVideo(event, video));
                parentController.addToFlowPane(button);
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

    //endregion

}
