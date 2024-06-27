package sbu.cs.youtube.Client.Controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import static java.nio.file.Paths.get;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private HBox hbxVideoPage, hbxControls;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxRight;

    @FXML
    private VBox vbxVideo;

    @FXML
    private Button btnVolume, btnPause, btnNext, btnPlay;

    @FXML
    private StackPane stckpnVideo;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        hbxVideoPage.prefWidthProperty().bind(scrlpnMain.prefWidthProperty());
//        hbxVideoPage.prefHeightProperty().bind(scrlpnMain.prefHeightProperty());

//        hbxVideoPage.minWidthProperty().bind(scrlpnMain.viewportBoundsProperty().get().widthProperty());
//        hbxVideoPage.prefWidthProperty().bind(scrlpnMain.viewportBoundsProperty().get().widthProperty());
//        hbxVideoPage.maxWidthProperty().bind(scrlpnMain.viewportBoundsProperty().get().widthProperty());

        // Bind the HBox width to the ScrollPane viewport width
//        hbxVideoPage.minWidthProperty().bind(Bindings.createDoubleBinding(
//                () -> scrlpnMain.getViewportBounds().getWidth(),
//                scrlpnMain.viewportBoundsProperty()));
//        hbxVideoPage.prefWidthProperty().bind(Bindings.createDoubleBinding(
//                () -> scrlpnMain.getViewportBounds().getWidth(),
//                scrlpnMain.viewportBoundsProperty()));
//        hbxVideoPage.maxWidthProperty().bind(Bindings.createDoubleBinding(
//                () -> scrlpnMain.getViewportBounds().getWidth(),
//                scrlpnMain.viewportBoundsProperty()));

        String videoPath = Paths.get("src/main/resources/Videos/Arcane2.mp4").toUri().toString();

        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
//        mediaView1.setMediaPlayer(mediaPlayer);


//        mediaView.fitWidthProperty().bind(vbxVideo.widthProperty());
//        mediaView.fitHeightProperty().bind(vbxVideo.heightProperty());

        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);

//        VBox.setVgrow(mediaView, Priority.ALWAYS);

        // Playback controls
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> mediaPlayer.play());
        btnPlay.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> mediaPlayer.pause());
        btnPause.setOnAction(e -> mediaPlayer.pause());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> mediaPlayer.stop());

        Slider timeSlider = new Slider();
        timeSlider.prefWidthProperty().bind(stckpnVideo.prefWidthProperty());
        timeSlider.prefHeightProperty().bind(stckpnVideo.prefHeightProperty());
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

//        StackPane stckpnVideo = new StackPane();
//        stckpnVideo.setVisible(true);
//        vbxVideo.prefWidthProperty().bind(vbxLeft.widthProperty());
//        vbxVideo.prefHeightProperty().bind(vbxLeft.heightProperty());
//        stckpnVideo.prefWidthProperty().bind(vbxVideo.widthProperty());
//        stckpnVideo.prefHeightProperty().bind(vbxVideo.heightProperty());

        VBox.setVgrow(stckpnVideo, Priority.ALWAYS);
        stckpnVideo.getChildren().add(mediaView);

        mediaView.fitWidthProperty().bind(stckpnVideo.widthProperty());
        mediaView.fitHeightProperty().bind(stckpnVideo.heightProperty());

//        vbxVideo.getChildren().addFirst(stckpnVideo);

//        HBox hbxControls = new HBox(10, playButton, pauseButton, stopButton);
//        HBox.setHgrow(mediaView, Priority.ALWAYS);

        hbxControls.getChildren().add(timeSlider);
//        vbxVideo.getChildren().add(hbxControls);

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
