package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeSectionController implements Initializable {

    //region [ - Field - ]
    @FXML
    AnchorPane mainPane;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/layout.fxml"));
        Parent layout;
        try {
            layout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().add(layout);
        LayoutController layoutController = loader.getController();

        layoutController.svgHome.setContent("M4 21V10.08l8-6.96 8 6.96V21h-6v-6h-4v6H4z");
        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());


        if (YouTubeApplication.user == null) {
            return;
        }

        Request<ArrayList<Video>> userRequest = new Request<>(YouTubeApplication.socket, "GetRecommendedVideos");
        userRequest.send();

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> recommendedVideos = videoResponse.getBody();
        if (recommendedVideos == null) {
            return;
        }
        for (var video : recommendedVideos) {
            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
            VBox videoPreview;

            try {
//                layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());
                videoPreview = videoPreviewLoader.load();
//                videoPreview.prefWidthProperty().bind(mainPane.widthProperty().divide(6));
//                videoPreview.prefHeightProperty().bind(mainPane.heightProperty().divide(6));
                VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
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
            layoutController.addToFlowPane(button);
        }
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
        scene = new Scene(root, mainPane.getScene().getWidth(), mainPane.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //endregion

}
