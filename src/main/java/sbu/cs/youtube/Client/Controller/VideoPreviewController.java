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
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Channel;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.YouTubeApplication;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoPreviewController implements Initializable {

    //region [ - Fields - ]

    private Video video;
    @FXML
    private Button btnVideoPreviewOptions, btnChannelName, btnChannelProfile;
    @FXML
    private HBox hbxVideoDetails, hbxViewsAndDate;
    @FXML
    private ImageView imgThumbnail, imgChannelProfile;
    @FXML
    private SVGPath svgpthVideoPreviewOptions;
    @FXML
    private Text txtVideoTitle, txtViews, txtDate;
    @FXML
    private VBox vbxVideoPreview, vbxTextDetails;
    private final int TITLE_MAX_LENGTH = 50;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgThumbnail.fitWidthProperty().bind(vbxVideoPreview.widthProperty());
        imgThumbnail.fitHeightProperty().bind(vbxVideoPreview.heightProperty());
        imgThumbnail.setPreserveRatio(true);

        hbxVideoDetails.prefWidthProperty().bind(vbxVideoPreview.widthProperty());


        vbxTextDetails.prefWidthProperty().bind(hbxVideoDetails.widthProperty().subtract(100));

        btnVideoPreviewOptions.setOnAction(event -> {
            event.consume();
            save(event);
        });

        btnChannelProfile.setOnAction(event -> {
            event.consume();
            getChannel(event);
        });
        btnChannelName.setOnAction(event -> {
            event.consume();
            getChannel(event);
        });
    }
    //endregion

    //region [ - getChannel(ActionEvent event) - ]
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
        scene = new Scene(root, vbxVideoPreview.getScene().getWidth(), vbxVideoPreview.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - save(ActionEvent event) - ]
    private void save(ActionEvent event) {
        //todo
    }
    //endregion

    //region [ - setVideo(Video video) - ]
    public void setVideo(Video video) {
        this.video= video;
        String summarizedTitle = video.getTitle();
        if (summarizedTitle.length() > TITLE_MAX_LENGTH) {
            summarizedTitle = summarizedTitle.substring(0, TITLE_MAX_LENGTH);
            summarizedTitle += " ...";
        }
        txtVideoTitle.setText(summarizedTitle);
        btnChannelName.setText(video.getChannel().getTitle());
        LocalDateTime date = LocalDateTime.parse(video.getUploadDate());
        txtDate.setText(date.getDayOfMonth() + " " + date.getMonth());
        txtViews.setText(video.getViewcount() + " views");

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(video.getThumbnailBytes());
        Image videoThumbnail = new Image(bis);
        imgThumbnail.setImage(videoThumbnail);

        bis = new ByteArrayInputStream(video.getChannel().getProfileBytes());
        Image channelProfile = new Image(bis);
        imgChannelProfile.setImage(channelProfile);
    }
    //endregion

    //region [ - setParentController(LayoutController layoutController) - ]
    public void setParentController(LayoutController layoutController) {
        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            vbxVideoPreview.getStylesheets().clear();
            vbxVideoPreview.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/video-preview.css")).toExternalForm());
        });
    }
    //endregion

    //endregion

}
