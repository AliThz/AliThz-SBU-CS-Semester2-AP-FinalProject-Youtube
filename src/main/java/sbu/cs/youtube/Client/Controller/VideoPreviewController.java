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
//        addThumbnail("E:\\Ali\\Picutres\\BG\\New folder\\New folder\\01.jpg");
//        addChannelProfile("E:\\Ali\\Picutres\\BG\\Screenshot 2023-11-10 180736.png");
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

    //endregion

}
