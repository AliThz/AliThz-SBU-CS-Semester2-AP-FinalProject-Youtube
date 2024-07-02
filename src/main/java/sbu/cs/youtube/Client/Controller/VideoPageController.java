package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private AnchorPane anchrpnVideoPage;

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
    private HBox hbxViewDate;

    @FXML
    private ImageView imgChannelProfile;

    @FXML
    private Text txtChannelName;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtVideoDescription;

    @FXML
    private Text txtVideoTitle;

    @FXML
    private Text txtViews;

    @FXML
    private Text txtChannelSubscribres;

    @FXML
    private VBox vbxCommentSection;

    @FXML
    private VBox vbxDescription;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxVideoDetails;


    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gson gson = new Gson();
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
        };
        Response<Video> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        Video responseVideo = videoResponse.getBody();
        setVideo(responseVideo);

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
//        mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 5.0 / 7.0));
        mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0));
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
        timeSlider.getStyleClass().add("timeSlider");


        // Create a ProgressBar
        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setMaxWidth(Double.MAX_VALUE);

        // Bind ProgressBar progress to Slider value
        progressBar.progressProperty().bind(timeSlider.valueProperty().divide(timeSlider.maxProperty()));

        // Style the ProgressBar to show the progress in red
        progressBar.getStyleClass().add("progressBar");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(progressBar, timeSlider);


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


        hbxControls.getChildren().add(stackPane);
        mediaPlayer.play();
        //endregion

        //region [ - Recommended Videos - ]
        VBox vbxRecommendedVideos = new VBox();
        for (int i = 0; i < 8; i++) {
//            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-recommendation.fxml"));
            Parent videoPreview;
            try {
                videoPreview = videoPreviewLoader.load();
//                VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                VideoRecommendationController videoPreviewController = videoPreviewLoader.getController();
                if (videoPreviewController != null) {
                    videoPreviewController.addThumbnail("/Images/Thumbnail.jpg");
//                    videoPreviewController.addChannelProfile("/Images/ChannelProfile.png");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbxRecommendedVideos.getChildren().add(videoPreview);
            VBox.setVgrow(videoPreview, Priority.ALWAYS);
        }
//        vbxRecommendedVideos.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 2.0 / 7.0));
        hbx.prefWidthProperty().bind(anchrpnVideoPage.widthProperty());
//        vbxRecommendedVideos.prefWidthProperty().bind(hbx.widthProperty());
        vbxRecommendedVideos.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 7.0 / 20.0));
        vbxRecommendedVideos.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        vbxRecommendedVideos.setSpacing(20);
        vbxRecommendedVideos.getStyleClass().add("vbxRecommendedVideos");
//        anchrpnVideoPage.getChildren().add(vbxRecommendedVideos);
        //endregion

        vbxLeft.getChildren().addFirst(mediaView);
        hbx.getChildren().addAll(vbxRecommendedVideos);
    }
    //endregion

    //region [ - setVideo(Video video) - ]
    public void setVideo(Video video) {
        txtVideoTitle.setText(video.getTitle());
        txtVideoDescription.setText(video.getDescription());
        txtChannelName.setText(video.getChannel().getTitle());
        txtChannelSubscribres.setText(String.valueOf(video.getChannel().getSubscribers()));
        LocalDateTime date = LocalDateTime.parse(video.getUploadDate());
        txtDate.setText(date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear());
        txtViews.setText(String.valueOf(video.getViews()));
    }
    //endregion

    //endregion
}
