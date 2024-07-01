package sbu.cs.youtube.Client.Controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class TestController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane vbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        anchorPane.prefWidthProperty().bind(vbox.widthProperty());
//        anchorPane.prefHeightProperty().bind(vbox.heightProperty());


        String videoPath = Paths.get("src/main/resources/Videos/Arcane2.mp4").toUri().toString();

        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

        anchorPane.getChildren().add(mediaView);
//        mediaView.fitWidthProperty().bind(anchorPane.widthProperty().divide(1));
//        mediaView.fitHeightProperty().bind(anchorPane.heightProperty().divide(1));
        mediaView.fitWidthProperty().bind(Bindings.divide(anchorPane.widthProperty(), 1.4));
//        mediaView.fitHeightProperty().bind(anchorPane.heightProperty().divide(2));

        mediaPlayer.play();
    }
}
