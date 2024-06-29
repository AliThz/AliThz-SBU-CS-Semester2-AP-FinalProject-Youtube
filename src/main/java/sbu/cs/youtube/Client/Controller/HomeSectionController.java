package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    AnchorPane mainPane;

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

        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());




        Request<Video> userRequest = new Request<>(YouTubeApplication.socket, "GetRecommendedVideos");
        userRequest.send();

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        ArrayList<Video> recommendedVideos = videoResponse.getBody();
        for (var video : recommendedVideos) {
            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
            Parent videoPreview;
            try {
                videoPreview = videoPreviewLoader.load();
                VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                if (videoPreviewController != null) {
                    videoPreviewController.setVideo(video);
//                    videoPreviewController.addThumbnail("/Images/Thumbnail.jpg");
//                    videoPreviewController.addChannelProfile("/Images/ChannelProfile.png");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            layoutController.addToFlowPane(videoPreview);
        }
    }
}
