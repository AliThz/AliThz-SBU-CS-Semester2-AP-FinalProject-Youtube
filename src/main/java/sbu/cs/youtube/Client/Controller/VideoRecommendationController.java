package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sbu.cs.youtube.Shared.POJO.Video;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoRecommendationController implements Initializable {

    private Video video;
    @FXML
    private Button btnVideoPreviewOptions;

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
    private Text txtChannelName;

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
        txtChannelName.setText(video.getChannel().getTitle());
        LocalDateTime date = LocalDateTime.parse(video.getUploadDate());
        txtDate.setText(date.getDayOfMonth() + " " + date.getMonth());
        txtViews.setText(String.valueOf(video.getViews()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(video.getThumbnailBytes());
        Image videoThumbnail = new Image(bis);
        imgThumbnail.setImage(videoThumbnail);
    }
    //endregion
}
