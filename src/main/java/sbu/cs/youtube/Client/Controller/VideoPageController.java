package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import sbu.cs.youtube.Shared.POJO.Subscription;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    private Video video;

    @FXML
    private AnchorPane anchrpnVideoPage;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSub;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnPause;

    @FXML
    private Button btnPlayPause;

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

    MediaPlayer mediaPlayer;
    MediaView mediaView;


    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Gson gson = new Gson();
//        String response = YouTubeApplication.receiveResponse();
//        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
//        };
//        Response<Video> videoResponse = gson.fromJson(response, responseTypeToken.getType());
//
//        Video responseVideo = videoResponse.getBody();
//        setVideo(responseVideo);

        imgChannelProfile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/ChannelProfile.png"))));

        //region [ - Media View - ]
        String videoPath = Paths.get("src/main/resources/Videos/Arcane2.mp4").toUri().toString();

        Media media = new Media(videoPath);
        mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.setSmooth(true);
        //endregion

        mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0));

        setPlaybackButtons();

        //region [ - Recommended Videos - ]
        VBox vbxRecommendedVideos = new VBox();
        for (int i = 0; i < 8; i++) {
            FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-recommendation.fxml"));
            Parent videoPreview;
            try {
                videoPreview = videoPreviewLoader.load();
                VideoRecommendationController videoPreviewController = videoPreviewLoader.getController();
                if (videoPreviewController != null) {
                    videoPreviewController.addThumbnail("/Images/Thumbnail.jpg");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbxRecommendedVideos.getChildren().add(videoPreview);
            VBox.setVgrow(videoPreview, Priority.ALWAYS);
        }

        ScrollPane recommendedVideosScrollPane = new ScrollPane();
        hbx.prefWidthProperty().bind(anchrpnVideoPage.widthProperty());
        vbxRecommendedVideos.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 7.0 / 20.0));
        recommendedVideosScrollPane.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 7.0 / 20.0));
        vbxRecommendedVideos.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        recommendedVideosScrollPane.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        vbxRecommendedVideos.setSpacing(20);
        vbxRecommendedVideos.getStyleClass().add("vbx-recommended-videos");
        //endregion


        recommendedVideosScrollPane.getStyleClass().add("scroll-pane");
        recommendedVideosScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        recommendedVideosScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        recommendedVideosScrollPane.setContent(vbxRecommendedVideos);
        vbxLeft.getChildren().addFirst(mediaView);
        ScrollPane videoScrollPane = new ScrollPane();
        videoScrollPane.getStyleClass().add("scroll-pane");
        videoScrollPane.setFitToWidth(true);
        videoScrollPane.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0));
        videoScrollPane.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        videoScrollPane.setContent(vbxLeft);
        videoScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        videoScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hbx.getChildren().addAll(videoScrollPane, recommendedVideosScrollPane);
        hbx.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());



        //region [ - Comments - ]
//        for (var comment : video.getComments()) {
        for (int i = 0; i < 8; i++) {
            FXMLLoader commentPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/comment-preview.fxml"));
            Parent commentPreview;
            try {
                commentPreview = commentPreviewLoader.load();
//                VideoPreviewController commentPreviewController = commentPreviewLoader.getController();
//                VideoRecommendationController commentPreviewController = commentPreviewLoader.getController();
//                if (commentPreviewController != null) {
//                    commentPreviewController.addThumbnail("/Images/Thumbnail.jpg");
//                    commentPreviewController.addChannelProfile("/Images/ChannelProfile.png");
//                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vbxCommentSection.getChildren().add(commentPreview);
        }
        //endregion


    }

    private void setPlaybackButtons() {
        btnPlayPause.setOnAction(this::pause);
        btnBack.setOnAction(this::restart);
        btnNext.setOnAction(this::next);

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

    }

    private void next(ActionEvent event) {
        //todo
    }
    //endregion

    private void restart(ActionEvent event) {
        mediaPlayer.stop();
        mediaPlayer.play();
    }

    private void pause(ActionEvent event) {
        btnPlayPause.setOnAction(this::play);
        mediaPlayer.pause();
        SVGPath svgPath = (SVGPath) btnPlayPause.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z");

    }

    private void play(ActionEvent event) {
        btnPlayPause.setOnAction(this::pause);
        mediaPlayer.play();
        SVGPath svgPath = (SVGPath) btnPlayPause.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10 z");
    }


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

    @FXML
    private void updateSub(ActionEvent event) {
        Request<Subscription> subscriptionRequest = new Request<>(YouTubeApplication.socket, "SignUp");
        subscriptionRequest.send(new Subscription(YouTubeApplication.user.getId(), video.getChannelId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<Subscription>> responseTypeToken = new TypeToken<>() {};
        Response<Subscription> subscriptionResponse = gson.fromJson(response, responseTypeToken.getType());
        Subscription subscription = subscriptionResponse.getBody();

        if (subscription != null) {
            SVGPath svgPath = (SVGPath) btnSub.getChildrenUnmodifiable().getFirst();
            svgPath.setContent("m3.85 3.15-.7.7 3.48 3.48C6.22 8.21 6 9.22 6 10.32v5.15l-2 1.88V19h14.29l1.85 1.85.71-.71-17-16.99zM5 18v-.23l2-1.88v-5.47c0-.85.15-1.62.41-2.3L17.29 18H5zm5 2h4c0 1.1-.9 2-2 2s-2-.9-2-2zM9.28 5.75l-.7-.7c.43-.29.9-.54 1.42-.7v-.39c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v4.14l-1-1v-3.05c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03-.27.11-.51.24-.75.4z");
            btnSub.setText("Unsubscribed");
            //todo update sub
        }
        else {
            SVGPath svgPath = (SVGPath) btnSub.getChildrenUnmodifiable().getFirst();
            svgPath.setContent("M10 20h4c0 1.1-.9 2-2 2s-2-.9-2-2zm10-2.65V19H4v-1.65l2-1.88v-5.15C6 7.4 7.56 5.1 10 4.34v-.38c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v5.15l2 1.87zm-1 .42-2-1.88v-5.47c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03C8.15 6.11 7 7.99 7 10.42v5.47l-2 1.88V18h14v-.23z");
            btnSub.setText("Subscribed");
            //todo update sub
        }

    }

    //endregion
}
