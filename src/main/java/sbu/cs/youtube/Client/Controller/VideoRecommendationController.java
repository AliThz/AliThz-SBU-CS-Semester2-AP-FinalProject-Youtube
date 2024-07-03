package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VideoRecommendationController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        if (hbxVideoRecommendation.getWidth() < 10) {
//            vbxDetails.setVisible(false);
//        } else {
//            vbxDetails.setVisible(true);
//        }
    }

    //region [ - addThumbnail(String src) - ]
    public void addThumbnail(String src) {
        imgThumbnail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(src))));
    }
    //endregion
}
