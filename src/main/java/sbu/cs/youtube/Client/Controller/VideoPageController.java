package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.codec.digest.DigestUtils;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

public class VideoPageController implements Initializable {

    //region [ - Fields - ]

    private Video video;

    private ArrayList<Video> recommendedVideos;

    private Media media;

    private final ScrollPane recommendedVideosScrollPane = new ScrollPane();
    private final VBox vbxRecommendedVideos = new VBox();

    private final ScrollPane videoScrollPane = new ScrollPane();

    @FXML
    private Button btnLike;

    @FXML
    private Button btnComment;

    @FXML
    private Button btnDislike;

    @FXML
    private Text txtLikes;

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane anchrpnVideoPage;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnSub;

    @FXML
    private Button btnBack;

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
    private TextField txtComment;

    @FXML
    private VBox vbxCommentSection;

    @FXML
    private VBox vbxDescription;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxVideoDetails;

    @FXML
    private SVGPath svgLike;

    @FXML
    private SVGPath svgDislike;

    private Boolean hasLiked = null;

    private MediaPlayer mediaPlayer;

    private MediaView mediaView;

    private Slider volumeSlider;

    private VBox videoStuff;

    private StackPane videoStack;


    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //region [ - Bindings - ]
        vbxCommentSection.prefWidthProperty().bind(videoScrollPane.viewportBoundsProperty().map(Bounds::getWidth));
        vbxCommentSection.prefHeightProperty().bind(videoScrollPane.viewportBoundsProperty().map(Bounds::getHeight));
        vbxLeft.prefWidthProperty().bind(videoScrollPane.viewportBoundsProperty().map(Bounds::getWidth));
        vbxLeft.prefHeightProperty().bind(videoScrollPane.viewportBoundsProperty().map(Bounds::getHeight));


        hbx.prefWidthProperty().bind(anchrpnVideoPage.widthProperty());
        vbxRecommendedVideos.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 7.0 / 20.0).add(30));
        recommendedVideosScrollPane.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 7.0 / 20.0));
        vbxRecommendedVideos.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        recommendedVideosScrollPane.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        vbxRecommendedVideos.setSpacing(20);
        vbxRecommendedVideos.getStyleClass().add("vbx-recommended-videos");
        recommendedVideosScrollPane.getStyleClass().add("scroll-pane");
        recommendedVideosScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        recommendedVideosScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        recommendedVideosScrollPane.setContent(vbxRecommendedVideos);

        //region [ - videoScrollPane - ]
        videoScrollPane.getStyleClass().add("scroll-pane");
        videoScrollPane.setFitToWidth(true);
        videoScrollPane.prefWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0));
        videoScrollPane.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        videoScrollPane.setContent(vbxLeft);
        videoScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        videoScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hbx.getChildren().addAll(videoScrollPane, recommendedVideosScrollPane);
        hbx.prefHeightProperty().bind(anchrpnVideoPage.heightProperty());
        //endregion

        //endregion

        //region [ - Video API - ]
        Gson gson = new Gson();
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
        };
        Response<Video> videoResponse = gson.fromJson(response, responseTypeToken.getType());
        video = videoResponse.getBody();
        //endregion

        //region [ - Check Video View API  - ]
        Request<UserVideo> userVideoRequest = new Request<>(YouTubeApplication.socket, "CheckViewVideoExistence");
        userVideoRequest.send(new UserVideo(YouTubeApplication.user.getId() , video.getId()));

        response = YouTubeApplication.receiveResponse();
        TypeToken<Response<UserVideo>> viewResponseTypeToken = new TypeToken<>() {
        };
        Response<UserVideo> userVideoResponse = gson.fromJson(response, viewResponseTypeToken.getType());
        UserVideo userVideo = userVideoResponse.getBody();
        System.out.println(userVideoResponse.getMessage());

        if (userVideo != null)
            hasLiked = userVideo.getLike();

        //todo set subscribe

        //endregion

        if (video.getChannel().getCreatorId().equals(YouTubeApplication.user.getId())) {
            btnSub.setVisible(false);
        }

        setVideo();
        new Thread(this::displayMedia).start();
//        setPlaybackButtons();
        new Thread(this::displayRecommendedVideos).start();
//        displayRecommendedVideos();
        new Thread(this::displayComments).start();
//        displayComments();
    }
    //endregion

    //region [ - setLikeCount() - ]
    private void setLikeCount() {
        Gson gson = new Gson();
        Request<Video> videoRequest = new Request<>(YouTubeApplication.socket, "GetVideoLikesStatus");
        videoRequest.send(new Video(video.getId()));
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
        };
        Response<Video> videoResponse = gson.fromJson(response, responseTypeToken.getType());
        video = videoResponse.getBody();

        Platform.runLater(() -> {
            txtViews.setText(String.valueOf(video.getViewCount()));
            txtLikes.setText(String.valueOf(video.getLikes()));
        });
    }
    //endregion

    //region [ - setViewCount() - ]
    private void setViewCount() {
        Gson gson = new Gson();
        Request<Video> videoRequest = new Request<>(YouTubeApplication.socket, "GetVideoViewCount");
        videoRequest.send(new Video(video.getId()));
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Video>> responseTypeToken = new TypeToken<>() {
        };
        Response<Video> videoResponse = gson.fromJson(response, responseTypeToken.getType());
        video = videoResponse.getBody();

        Platform.runLater(() -> {
            txtViews.setText(String.valueOf(video.getViewCount()));
        });
    }
    //endregion

    //region [ - displayMedia() - ]
    private void displayMedia() {
//        String videoPath = Paths.get("src/main/resources/Videos/Arcane2.mp4").toUri().toString();
//        media = new Media(videoPath);

        File tempFile;
        try {
            tempFile = File.createTempFile("video", ".mp4");
            tempFile.deleteOnExit();

            // Write the byte array to the temporary file
            try (FileOutputStream fos = new FileOutputStream(tempFile);
                 ByteArrayInputStream bais = new ByteArrayInputStream(video.getVideoBytes())) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = bais.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Create a Media object from the temporary file
        media = new Media(tempFile.toURI().toString());

        //region [ - Without Thread - ]
//         mediaPlayer = new MediaPlayer(media);
//        mediaView = new MediaView(mediaPlayer);
//        mediaView.setPreserveRatio(true);
//        mediaView.setSmooth(true);
//        mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0).subtract(30));
//        vbxLeft.getChildren().addFirst(mediaView);
//        mediaPlayer.play();
        //endregion

        Platform.runLater(() -> {
            mediaPlayer = new MediaPlayer(media);
            mediaView = new MediaView(mediaPlayer);
            mediaView.setPreserveRatio(true);
            mediaView.setSmooth(true);
            mediaView.fitWidthProperty().bind(Bindings.multiply(anchrpnVideoPage.widthProperty(), 13.0 / 20.0).subtract(30));
            videoStack = new StackPane();
            videoStack.getChildren().add(mediaView);
            vbxLeft.getChildren().addFirst(videoStack);
            mediaPlayer.play();

            setPlaybackButtons();
        });
    }
    //endregion

    //region [ - displayRecommendedVideos() - ]
    private void displayRecommendedVideos() {
        Request<ArrayList<Video>> userRequest = new Request<>(YouTubeApplication.socket, "GetRecommendedVideos");
        userRequest.send();

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(response, responseTypeToken.getType());

        recommendedVideos = videoResponse.getBody();
        Platform.runLater(() -> {
            if (recommendedVideos != null) {
                for (var v : recommendedVideos) {
                    if (video.getId().equals(v.getId())) {
                        continue;
                    }
                    FXMLLoader videoRecommendationLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-recommendation.fxml"));
                    HBox videoRecommendation;
                    try {
                        videoRecommendation = videoRecommendationLoader.load();
                        VideoRecommendationController videoRecommendationController = videoRecommendationLoader.getController();
                        if (videoRecommendationController != null) {
                            videoRecommendationController.setVideo(v);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Button button = new Button();
                    button.getStyleClass().add("btn-video");
                    button.setGraphic(videoRecommendation);

                    button.setOnAction(event -> getVideo(event, v));
                    vbxRecommendedVideos.getChildren().add(button);
                    VBox.setVgrow(videoRecommendation, Priority.ALWAYS);
                }
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
        mediaPlayer.stop();
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
        scene = new Scene(root, anchrpnVideoPage.getScene().getWidth(), anchrpnVideoPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - displayComments() - ]
    private void displayComments() {
        Request<Video> videoRequest = new Request<>(YouTubeApplication.socket, "GetVideoComments");
        videoRequest.send(new Video(video.getId()));

        Gson gson = new Gson();
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<ArrayList<Comment>>> viewResponseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Comment>> commentsResponse = gson.fromJson(response, viewResponseTypeToken.getType());
        video.setComments(commentsResponse.getBody());
        System.out.println(commentsResponse.getMessage());

        //region [ - Without Thread - ]
//        vbxCommentSection.getChildren().remove(2, vbxCommentSection.getChildren().size());
//        for (var comment : video.getComments()) {
//            FXMLLoader commentPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/comment-preview.fxml"));
//            Parent commentPreview;
//            try {
//                commentPreview = commentPreviewLoader.load();
//                CommentPreviewController commentPreviewController = commentPreviewLoader.getController();
//                commentPreviewController.setComment(comment);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            vbxCommentSection.getChildren().add(commentPreview);
//        }
        //endregion

//        vbxCommentSection.prefWidthProperty().bind(videoScrollPane.prefViewportWidthProperty());
//        vbxCommentSection.prefHeightProperty().bind(videoScrollPane.prefViewportHeightProperty());

        Platform.runLater(() -> {
            vbxCommentSection.getChildren().remove(2, vbxCommentSection.getChildren().size());
            for (var comment : video.getComments()) {
                FXMLLoader commentPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/comment-preview.fxml"));
                Parent commentPreview;
                try {
                    commentPreview = commentPreviewLoader.load();
                    CommentPreviewController commentPreviewController = commentPreviewLoader.getController();
                    commentPreviewController.setComment(comment);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                vbxCommentSection.getChildren().add(commentPreview);
            }
        });
    }
    //endregion

    //region [ - setPlaybackButtons() - ]
    private void setPlaybackButtons() {
        btnPlayPause.setOnAction(this::pause);
        btnBack.setOnAction(this::restart);
        btnNext.setOnAction(this::next);
        btnVolume.setOnAction(this::volumeOff);

        Slider timeSlider = new Slider(0, 100, 0);

        volumeSlider = new Slider(0, 100, 100);
        volumeSlider.setOrientation(Orientation.HORIZONTAL);
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue()/100==0) {
                volumeOff(new ActionEvent());
            }
            else
                volumeOn(new ActionEvent());
            mediaPlayer.setVolume(newValue.doubleValue()/100);
        });
        volumeSlider.getStyleClass().add("volumeSlider");
        volumeSlider.prefWidthProperty().bind(mediaView.fitWidthProperty().divide(12));



        timeSlider.prefWidthProperty().bind(mediaView.fitWidthProperty());
        timeSlider.prefHeightProperty().bind(mediaView.fitHeightProperty());
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

        Label currentTimeLabel = new Label("00:00");
        Label totalTimeLabel = new Label("00:00");

        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (!timeSlider.isValueChanging()) {
                timeSlider.setValue(newTime.toSeconds() / media.getDuration().toSeconds() * 100);
            }
            currentTimeLabel.setText(formatTime(newTime));
        });

        mediaPlayer.setOnReady(() -> {
            Duration total = media.getDuration();
            totalTimeLabel.setText(formatTime(total));
        });

        Button btnIncreaseSpeed = new Button("1.0x");
        btnIncreaseSpeed.getStyleClass().add("btn-speed");
        btnIncreaseSpeed.setOnAction(event -> {
            double currentRate = mediaPlayer.getRate();
            mediaPlayer.setRate(currentRate + 0.5);
            if (mediaPlayer.getRate() > 2)
                mediaPlayer.setRate(0.5);
            btnIncreaseSpeed.setText(mediaPlayer.getRate() + "x");
        });

        hbxControls.getChildren().add(4, volumeSlider);
        HBox.setMargin(volumeSlider, new Insets(0, 10, 0, 0));
        hbxControls.getChildren().addAll(currentTimeLabel, new Label(" / "), totalTimeLabel, new HBox(), btnIncreaseSpeed);
        HBox.setHgrow(hbxControls.getChildren().get(8), Priority.ALWAYS);
        videoStuff = new VBox(stackPane, hbxControls);
        videoStuff.setAlignment(Pos.BOTTOM_CENTER);
        videoStack.getChildren().add(videoStuff);
        videoStack.setAlignment(Pos.CENTER);
        videoStuff.setVisible(false);

        videoStack.setOnMouseEntered(event -> {
            videoStuff.setVisible(true);
        });

        videoStack.setOnMouseExited(event -> {
            videoStuff.setVisible(false);
        });

        // Add key event handler to the scene
        mediaView.getScene().setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.K) {
                // Pause or play the video based on current status
                if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                    mediaPlayer.pause();
//                    pause(new ActionEvent());
                } else if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED ||
                        mediaPlayer.getStatus() == MediaPlayer.Status.READY ||
                        mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
                    mediaPlayer.play();
                }
            }
        });

        mediaPlayer.play();

    }
    //endregion

    //region [ - volumeOff(ActionEvent event) - ]
    private void volumeOff(ActionEvent event) {
        btnVolume.setOnAction(this::volumeOn);
        mediaPlayer.setVolume(0);
        SVGPath svgPath = (SVGPath) btnVolume.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("m 21.48,17.98 c 0,-1.77 -1.02,-3.29 -2.5,-4.03 v 2.21 l 2.45,2.45 c .03,-0.2 .05,-0.41 .05,-0.63 z m 2.5,0 c 0,.94 -0.2,1.82 -0.54,2.64 l 1.51,1.51 c .66,-1.24 1.03,-2.65 1.03,-4.15 0,-4.28 -2.99,-7.86 -7,-8.76 v 2.05 c 2.89,.86 5,3.54 5,6.71 z M 9.25,8.98 l -1.27,1.26 4.72,4.73 H 7.98 v 6 H 11.98 l 5,5 v -6.73 l 4.25,4.25 c -0.67,.52 -1.42,.93 -2.25,1.18 v 2.06 c 1.38,-0.31 2.63,-0.95 3.69,-1.81 l 2.04,2.05 1.27,-1.27 -9,-9 -7.72,-7.72 z m 7.72,.99 -2.09,2.08 2.09,2.09 V 9.98 z");
    }
    //endregion

    //region [ - volumeOn(ActionEvent event) - ]
    private void volumeOn(ActionEvent event) {
        btnVolume.setOnAction(this::volumeOff);
        if (volumeSlider.getValue() == 0) {
            volumeSlider.setValue(20);
        }
        mediaPlayer.setVolume(volumeSlider.getValue()/100);
        SVGPath svgPath = (SVGPath) btnVolume.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("M8,21 L12,21 L17,26 L17,10 L12,15 L8,15 L8,21 Z M19,14 L19,22 C20.48,21.32 21.5,19.77 21.5,18 C21.5,16.26 20.48,14.74 19,14 ZM19,11.29 C21.89,12.15 24,14.83 24,18 C24,21.17 21.89,23.85 19,24.71 L19,26.77 C23.01,25.86 26,22.28 26,18 C26,13.72 23.01,10.14 19,9.23 L19,11.29 Z");
    }
    //endregion

    //region [ - next(ActionEvent event) - ]
    private void next(ActionEvent event) {
        getVideo(event, recommendedVideos.getFirst());
    }
    //endregion

    //region [ - restart(ActionEvent event) - ]
    private void restart(ActionEvent event) {
        mediaPlayer.stop();
        pause(event);
    }
    //endregion

    //region [ - pause(ActionEvent event) - ]
    private void pause(ActionEvent event) {
        btnPlayPause.setOnAction(this::play);
        mediaPlayer.pause();
        SVGPath svgPath = (SVGPath) btnPlayPause.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z");

    }
    //endregion

    //region [ - play(ActionEvent event) - ]
    private void play(ActionEvent event) {
        btnPlayPause.setOnAction(this::pause);
        mediaPlayer.play();
        SVGPath svgPath = (SVGPath) btnPlayPause.getChildrenUnmodifiable().getFirst();
        svgPath.setContent("M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10 z");
    }
    //endregion

    //region [ - setVideo - ]
    public void setVideo() {
        txtVideoTitle.setText(video.getTitle());
        txtVideoDescription.setText(video.getDescription());
        txtChannelName.setText(video.getChannel().getTitle());
        txtChannelSubscribres.setText(String.valueOf(video.getChannel().getSubscriberCount()));
        LocalDateTime date = LocalDateTime.parse(video.getUploadDate());
        txtDate.setText(date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear());
        txtViews.setText(String.valueOf(video.getViewCount()));
        txtLikes.setText(String.valueOf(video.getLikes()));

//        imgChannelProfile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/ChannelProfile.png"))));
        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(video.getChannel().getCreator().getAvatarBytes());
        Image videoThumbnail = new Image(bis);
        imgChannelProfile.setImage(videoThumbnail);


        if (hasLiked == null) {
        } else if (hasLiked) {
            svgLike.setContent("M3,11h3v10H3V11z M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11v10h10.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z");
        } else if (!hasLiked) {
            svgDislike.setContent("M18,4h3v10h-3V4z M5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21c0.58,0,1.14-0.24,1.52-0.65L17,14V4H6.57 C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14z");
        }

        //region [ - Getting Video Bytes - ]
        //        File tempFile;
//        try {
//            tempFile = File.createTempFile("video", ".mp4");
//            tempFile.deleteOnExit();
//
//            // Write the byte array to the temporary file
//            try (FileOutputStream fos = new FileOutputStream(tempFile);
//                 ByteArrayInputStream bais = new ByteArrayInputStream(video.getVideoBytes())) {
//                byte[] buffer = new byte[1024];
//                int length;
//                while ((length = bais.read(buffer)) != -1) {
//                    fos.write(buffer, 0, length);
//                }
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // Create a Media object from the temporary file
//        Media media = new Media(tempFile.toURI().toString());
//        displayMedia(media);
//        displayMedia();
        //endregion


    }
    //endregion

    //region [ - updateSub(ActionEvent event) - ]
    @FXML
    private void updateSub(ActionEvent event) {
        SVGPath svgPath = (SVGPath) btnSub.getChildrenUnmodifiable().getFirst();

        Request<Subscription> subscriptionRequest = new Request<>(YouTubeApplication.socket, "CheckSubscriptionExistence");
        subscriptionRequest.send(new Subscription(YouTubeApplication.user.getId(), video.getChannelId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<Subscription>> responseTypeToken = new TypeToken<>() {
        };
        Response<Subscription> subscriptionResponse = gson.fromJson(response, responseTypeToken.getType());
        Subscription subscription = subscriptionResponse.getBody();

        if (subscription != null) {
            svgPath.setContent("m3.85 3.15-.7.7 3.48 3.48C6.22 8.21 6 9.22 6 10.32v5.15l-2 1.88V19h14.29l1.85 1.85.71-.71-17-16.99zM5 18v-.23l2-1.88v-5.47c0-.85.15-1.62.41-2.3L17.29 18H5zm5 2h4c0 1.1-.9 2-2 2s-2-.9-2-2zM9.28 5.75l-.7-.7c.43-.29.9-.54 1.42-.7v-.39c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v4.14l-1-1v-3.05c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03-.27.11-.51.24-.75.4z");
            btnSub.setText("Unsubscribed");
            Request<Subscription> unsubRequest = new Request<>(YouTubeApplication.socket, "Unsubscribe");
            unsubRequest.send(new Subscription(YouTubeApplication.user.getId(), video.getChannelId()));

        } else {
            svgPath.setContent("M10 20h4c0 1.1-.9 2-2 2s-2-.9-2-2zm10-2.65V19H4v-1.65l2-1.88v-5.15C6 7.4 7.56 5.1 10 4.34v-.38c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v5.15l2 1.87zm-1 .42-2-1.88v-5.47c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03C8.15 6.11 7 7.99 7 10.42v5.47l-2 1.88V18h14v-.23z");
            btnSub.setText("Subscribed");
            Request<Subscription> subRequest = new Request<>(YouTubeApplication.socket, "Subscribe");
            subRequest.send(new Subscription(YouTubeApplication.user.getId(), video.getChannelId()));
        }

        response = YouTubeApplication.receiveResponse();
        subscriptionResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(subscriptionResponse.getMessage());
    }
    //endregion

    //region [ - updateLike(ActionEvent event) - ]
    @FXML
    private void updateLike(ActionEvent event) {
        String filledLike = "M3,11h3v10H3V11z M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11v10h10.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z";
        String emptiedDislike = "M17,4h-1H6.57C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21 c0.58,0,1.14-0.24,1.52-0.65L17,14h4V4H17z M10.4,19.67C10.21,19.88,9.92,20,9.62,20c-0.26,0-0.5-0.11-0.63-0.3 c-0.07-0.1-0.15-0.26-0.09-0.47l1.52-4.94l0.4-1.29H9.46H5.23c-0.41,0-0.8-0.17-1.03-0.46c-0.12-0.15-0.25-0.4-0.18-0.72l1.34-6 C5.46,5.35,5.97,5,6.57,5H16v8.61L10.4,19.67z M20,13h-3V5h3V13z";
        String emptiedLike = "M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11H3v10h4h1h9.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z M7,20H4v-8h3V20z M19.98,13.17l-1.34,6 C18.54,19.65,18.03,20,17.43,20H8v-8.61l5.6-6.06C13.79,5.12,14.08,5,14.38,5c0.26,0,0.5,0.11,0.63,0.3 c0.07,0.1,0.15,0.26,0.09,0.47l-1.52,4.94L13.18,12h1.35h4.23c0.41,0,0.8,0.17,1.03,0.46C19.92,12.61,20.05,12.86,19.98,13.17z";


        if (hasLiked == null || !hasLiked) {
            svgLike.setContent(filledLike);
            svgDislike.setContent(emptiedDislike);
            hasLiked = true;
            txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) + 1));
        } else if (hasLiked) {
            svgLike.setContent(emptiedLike);
            hasLiked = null;
            txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) - 1));
        }

        Request<UserVideo> userVideoRequest = new Request<>(YouTubeApplication.socket, "LikeVideo");
        userVideoRequest.send(new UserVideo(YouTubeApplication.user.getId(), video.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Response<UserVideo> userVideoResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(userVideoResponse.getMessage());

    }
    //endregion

    //region [ - updateDislike(ActionEvent event) - ]
    @FXML
    private void updateDislike(ActionEvent event) {
        String filledDislike = "M18,4h3v10h-3V4z M5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21c0.58,0,1.14-0.24,1.52-0.65L17,14V4H6.57 C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14z";
        String emptiedDislike = "M17,4h-1H6.57C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21 c0.58,0,1.14-0.24,1.52-0.65L17,14h4V4H17z M10.4,19.67C10.21,19.88,9.92,20,9.62,20c-0.26,0-0.5-0.11-0.63-0.3 c-0.07-0.1-0.15-0.26-0.09-0.47l1.52-4.94l0.4-1.29H9.46H5.23c-0.41,0-0.8-0.17-1.03-0.46c-0.12-0.15-0.25-0.4-0.18-0.72l1.34-6 C5.46,5.35,5.97,5,6.57,5H16v8.61L10.4,19.67z M20,13h-3V5h3V13z";
        String emptiedLike = "M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11H3v10h4h1h9.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z M7,20H4v-8h3V20z M19.98,13.17l-1.34,6 C18.54,19.65,18.03,20,17.43,20H8v-8.61l5.6-6.06C13.79,5.12,14.08,5,14.38,5c0.26,0,0.5,0.11,0.63,0.3 c0.07,0.1,0.15,0.26,0.09,0.47l-1.52,4.94L13.18,12h1.35h4.23c0.41,0,0.8,0.17,1.03,0.46C19.92,12.61,20.05,12.86,19.98,13.17z";

        if (hasLiked == null || hasLiked) {
            svgLike.setContent(emptiedLike);
            svgDislike.setContent(filledDislike);
            if (hasLiked != null && hasLiked)
                txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) - 1));
            hasLiked = false;
        } else if (!hasLiked) {
            svgDislike.setContent(emptiedDislike);
            hasLiked = null;
        }

        Request<UserVideo> userVideoRequest = new Request<>(YouTubeApplication.socket, "DislikeVideo");
        userVideoRequest.send(new UserVideo(YouTubeApplication.user.getId(), video.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Response<UserVideo> userVideoResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(userVideoResponse.getMessage());
    }
    //endregion

    //region [ - updateSave(ActionEvent event) - ]
    @FXML
    private void updateSave(ActionEvent event) {
    }
    //endregion

    //region [ - comment(ActionEvent event) - ]
    @FXML
    private void comment(ActionEvent event) {
        Request<Comment> commentRequest = new Request<>(YouTubeApplication.socket, "Comment");
        Comment comment = new Comment(txtComment.getText(), video.getId(), YouTubeApplication.user.getId(), null);
        commentRequest.send(comment);
        txtComment.clear();

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<UserVideo>> responseTypeToken = new TypeToken<>() {
        };
        Response<Comment> commentResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(commentResponse.getMessage());

        displayComments();
    }
    //endregion

    //region [ - formatTime(Duration time) - ]
    private String formatTime(Duration time) {
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds() % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
    //endregion

    //region [ - setParentController(LayoutController layoutController) - ]
    public void setParentController(LayoutController layoutController) {
        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            anchrpnVideoPage.getStylesheets().clear();
            anchrpnVideoPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/video-page.css")).toExternalForm());
        });
    }
    //endregion

    //region [ - getChannel(ActionEvent event) - ]
    @FXML
    private void getChannel(ActionEvent event) {
        Request<Channel> videoRequest = new Request<>(YouTubeApplication.socket, "GetChannel");
        videoRequest.send(new Channel(video.getChannelId()));

        getChannelPage(event);
    }
    //endregion

    //region [ - getChannelPage(ActionEvent event) - ]
    private void getChannelPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/channel-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxLeft.getScene().getWidth(), vbxLeft.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //endregion

}
