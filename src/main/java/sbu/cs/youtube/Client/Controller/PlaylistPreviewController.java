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
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class PlaylistPreviewController implements Initializable {

    //region [ - Fields - ]
    private Playlist playlist;

    private final int TITLE_MAX_LENGTH = 50;

    @FXML
    private Button btnPlaylistPreviewOptions;

    @FXML
    private Button btnViewPlaylist;

    @FXML
    private HBox hbxPlaylistDetails;

    @FXML
    private HBox hbxViewsAndDate;

    @FXML
    private ImageView imgThumbnail;

    @FXML
    private SVGPath svgpthPlaylistOptions;

    @FXML
    private Text txtChannelName;

    @FXML
    private Text txtPrivatePublic;

    @FXML
    private Text txtVideoCount;

    @FXML
    private Text txtPlaylistTitle;

    @FXML
    private VBox vbxPlaylistPreview;

    @FXML
    private VBox vbxTextDetails;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgThumbnail.fitWidthProperty().bind(vbxPlaylistPreview.widthProperty());
        imgThumbnail.fitHeightProperty().bind(vbxPlaylistPreview.heightProperty());
        imgThumbnail.setPreserveRatio(true);

        hbxPlaylistDetails.prefWidthProperty().bind(vbxPlaylistPreview.widthProperty());


        vbxTextDetails.prefWidthProperty().bind(hbxPlaylistDetails.widthProperty().subtract(100));

        btnPlaylistPreviewOptions.setOnAction(event -> {
            event.consume();
            save(event);
        });
    }

    private void save(ActionEvent event) {
        //todo
    }
    //endregion

    //region [ - setPlaylist(Playlist playlist) - ]
    public void setPlaylist(Playlist playlist) {
        this.playlist= playlist;
        String summarizedTitle = playlist.getTitle();
        if (summarizedTitle.length() > TITLE_MAX_LENGTH) {
            summarizedTitle = summarizedTitle.substring(0, TITLE_MAX_LENGTH);
            summarizedTitle += " ...";
        }
        txtPlaylistTitle.setText(summarizedTitle);
        txtChannelName.setText(YouTubeApplication.user.getUsername());
        txtPrivatePublic.setText(playlist.isPublic() ? "Public" : "Private");
        txtVideoCount.setText(String.valueOf(playlist.getVideos()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(playlist.getThumbnailBytes());
        Image playlistThumbnail = new Image(bis);
        imgThumbnail.setImage(playlistThumbnail);
    }
    //endregion

    //endregion

}
