package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Channel;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoRecommendationController implements Initializable {

    private Video video;
    @FXML
    private Button btnVideoPreviewOptions;

    @FXML
    private HBox hbxVideoDetail;

    @FXML
    private HBox hbxViewsAndDate;

    @FXML
    private HBox hbxVideoRecommendation;

    @FXML
    private VBox vbxDetails;

    @FXML
    private ImageView imgThumbnail;

    @FXML
    private SVGPath svgpthVideoPreviewOptions;

    @FXML
    private Button btnChannelName;

    @FXML
    private Text txtDate;

    @FXML
    private Text txtVideoTitle;

    @FXML
    private Text txtViews;

    private final int TITLE_MAX_LENGTH = 50;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVideoPreviewOptions.setOnAction(event -> {
            event.consume();
            save(event);
        });

        hbxVideoDetail.prefWidthProperty().bind(vbxDetails.prefWidthProperty());
        hbxVideoRecommendation.getStylesheets().clear();
        hbxVideoRecommendation.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/video-recommendation.css")).toExternalForm());
    }

    private void save(ActionEvent event) {
        //todo
    }

    //region [ - addThumbnail(String src) - ]
    public void addThumbnail(String src) {
        imgThumbnail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(src))));
    }
    //endregion

    //region [ - setVideo(Video video) - ]
    public void setVideo(Video video) {
        this.video = video;
        String summarizedTitle = video.getTitle();
        if (summarizedTitle.length() > TITLE_MAX_LENGTH) {
            summarizedTitle = summarizedTitle.substring(0, TITLE_MAX_LENGTH);
            summarizedTitle += " ...";
        }
        txtVideoTitle.setText(summarizedTitle);
        btnChannelName.setText(video.getChannel().getTitle());
        LocalDateTime date = LocalDateTime.parse(video.getUploadDate());
        txtDate.setText(date.getDayOfMonth() + " " + date.getMonth());
        txtViews.setText(String.valueOf(video.getViewCount()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(video.getThumbnailBytes());
        Image videoThumbnail = new Image(bis);
        imgThumbnail.setImage(videoThumbnail);
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
        scene = new Scene(root, hbxVideoRecommendation.getScene().getWidth(), hbxVideoRecommendation.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - setParentController(LayoutController layoutController) - ]
    public void setParentController(LayoutController layoutController) {
        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            hbxVideoRecommendation.getStylesheets().clear();
            hbxVideoRecommendation.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/video-recommendation.css")).toExternalForm());
        });
    }
}
