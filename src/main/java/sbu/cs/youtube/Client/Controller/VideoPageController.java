package sbu.cs.youtube.Client.Controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private AnchorPane anchrpnVideoPage;

    @FXML
    private ScrollPane scrllpnVideo;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnVolume;

    @FXML
    private HBox hbx;

    @FXML
    private HBox hbxChannel;

    @FXML
    private HBox hbxControls;

    @FXML
    private HBox hbxVideoDetails;

    @FXML
    private ImageView imgChannelProfile;

    @FXML
    private Text txtVideoDetail;

    @FXML
    private Text txtVideoTitle;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxVideoDetails;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        imgChannelProfile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/ChannelProfile.png"))));

        //region [ - Media View - ]
        String videoPath = Paths.get("src/main/resources/Videos/Arcane2.mp4").toUri().toString();

        Media media = new Media(videoPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);
        //endregion

//        anchrpnVideoPage.getChildren().add(mediaView);
//        mediaView.fitWidthProperty().bind(Bindings.divide(anchrpnVideoPage.widthProperty(), 1.4));
        mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 5.0 / 7.0));
//        scrllpnVideo.prefWidthProperty().bind(Bindings.multiply(vbxLeft.widthProperty(), 5.0 / 7.0));
//        scrllpnVideo.prefHeightProperty().bind(vbxLeft.heightProperty());

        //region [ - Playback Controls - ]
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> mediaPlayer.play());
        btnPlay.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> mediaPlayer.pause());
        btnPause.setOnAction(e -> mediaPlayer.pause());

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


        hbxControls.getChildren().add(timeSlider);
        mediaPlayer.play();
        //endregion

        //region [ - Recommended Videos - ]
        VBox vbxRecommendedVideos = new VBox();
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
            vbxRecommendedVideos.getChildren().add(videoPreview);
            VBox.setVgrow(videoPreview, Priority.ALWAYS);
        }
        vbxRecommendedVideos.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 2.0 / 7.0));
        vbxRecommendedVideos.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        vbxRecommendedVideos.setAlignment(Pos.BASELINE_CENTER);
        vbxRecommendedVideos.getStyleClass().add("vbxRecommendedVideos");
//        anchrpnVideoPage.getChildren().add(vbxRecommendedVideos);
        //endregion

        vbxLeft.getChildren().addFirst(mediaView);
        hbx.getChildren().addAll(vbxRecommendedVideos);
    }
    //endregion

    //region [ -  - ]

    //endregion

    //endregion
}
