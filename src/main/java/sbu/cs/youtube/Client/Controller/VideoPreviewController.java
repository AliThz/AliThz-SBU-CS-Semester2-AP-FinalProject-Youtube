package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.POJO.Video;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoPreviewController implements Initializable {

    //region [ - Fields - ]
    @FXML
    private Button btnVideoPreviewOptions;
    @FXML
    private HBox hbxVideoDetails, hbxViewsAndDate;
    @FXML
    private ImageView imgThumbnail, imgChannelProfile;
    @FXML
    private SVGPath svgpthVideoPreviewOptions;
    @FXML
    private Text txtVideoTitle, txtChannelName, txtViews, txtDate;
    @FXML
    private VBox vbxVideoPreview, vbxTextDetails;
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
    }
    //endregion

    //region [ - addThumbnail(String src) - ]
    public void addThumbnail(String src) {
        imgThumbnail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(src))));
    }
    //endregion

    //region [ - addChannelProfile(String src) - ]
    public void addChannelProfile(String src) {
        imgChannelProfile.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(src))));
    }
    //endregion

    //region [ - setAttributes(Video video) - ]
    public void setVideo(Video video) {
        txtVideoTitle.setText(video.getTitle());
        txtChannelName.setText(video.getChannel().getTitle());
        txtDate.setText(video.getUploadDate());
        txtViews.setText(String.valueOf(video.getViews()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(video.getThumbnailBytes());
        Image videoThumbnail = new Image(bis);
        imgThumbnail.setImage(videoThumbnail);

        bis = new ByteArrayInputStream(video.getChannel().getProfileBytes());
        Image channelProfile = new Image(bis);
        imgChannelProfile.setImage(channelProfile);
    }
    //endregion

    //endregion

}
