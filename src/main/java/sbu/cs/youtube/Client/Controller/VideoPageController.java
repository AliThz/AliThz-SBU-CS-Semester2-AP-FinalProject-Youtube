package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private HBox hbxVideoPage;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxRight;

    @FXML
    private VBox vbxVideo;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        String videoPath = "src/main/resources/Videos/Arcane2.mp4";
        String videoPath = "file:///C:/Users/HMHA/OneDrive/Desktop/Arcane2.mp4";

        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaView1.setMediaPlayer(mediaPlayer);

        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);
//
        mediaView.fitWidthProperty().bind(vbxVideo.widthProperty());
        mediaView.fitHeightProperty().bind(vbxVideo.heightProperty());

        VBox.setVgrow(mediaView, Priority.ALWAYS);

        // Playback controls
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> mediaPlayer.pause());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> mediaPlayer.stop());

        Slider timeSlider = new Slider();
        timeSlider.prefWidthProperty().bind(mediaView.fitWidthProperty());
        timeSlider.prefHeightProperty().bind(mediaView.fitHeightProperty());
        timeSlider.setMin(0);
        timeSlider.setMax(100);
        timeSlider.setValue(0);

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newValue.toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100);
            }
        });

        timeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (timeSlider.isValueChanging()) {
                mediaPlayer.seek(Duration.seconds(newValue.doubleValue() / 100 * mediaPlayer.getTotalDuration().toSeconds()));
            }
        });

        vbxVideo.getChildren().add(mediaView);

        HBox hbxControls = new HBox(10, playButton, pauseButton, stopButton);
//        HBox.setHgrow(mediaView, Priority.ALWAYS);

        vbxVideo.getChildren().add(timeSlider);
        vbxVideo.getChildren().add(hbxControls);

        mediaPlayer.play();






        for (int i = 0; i < 4; i++) {
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
            vbxRight.getChildren().add(videoPreview);
        }

    }
    //endregion

    //region [ -  - ]

    //endregion

    //endregion
}
