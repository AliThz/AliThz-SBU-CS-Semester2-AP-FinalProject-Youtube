package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeSectionController implements Initializable {
    @FXML
    AnchorPane mainPane;

    private YouTubeApplication client;

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
        layoutController.setClient(client);

        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());

        for (int i = 0; i < 12; i++) {
            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
            Parent videoPreview;
            try {
                videoPreview = videoPreviewLoader.load();
                VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                if (videoPreviewController != null) {
                    videoPreviewController.addThumbnail("/Images/Thumbnail.jpg");
                    videoPreviewController.addChannelProfile("/Images/ChannelProfile.png");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            layoutController.addToFlowPane(videoPreview);
        }
    }

    public YouTubeApplication getClient() {
        return client;
    }

    public void setClient(YouTubeApplication client) {
        this.client = client;
    }
}
