package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
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
import javafx.stage.Popup;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

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

    Popup popup = new Popup();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnVideoPreviewOptions.setOnAction(event -> {
            save();
            Bounds bounds = btnVideoPreviewOptions.localToScreen(btnVideoPreviewOptions.getBoundsInLocal());
            popup.setX(bounds.getMinX());
            popup.setY(bounds.getMinY() + bounds.getHeight());

            event.consume();

            if (popup.isShowing()) popup.hide();
            else popup.show((Stage) btnVideoPreviewOptions.getScene().getWindow());
        });

        hbxVideoDetail.prefWidthProperty().bind(vbxDetails.widthProperty());
        txtVideoTitle.wrappingWidthProperty().bind(hbxVideoDetail.widthProperty().subtract(50));
        hbxVideoRecommendation.getStylesheets().clear();
        hbxVideoRecommendation.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/video-recommendation.css")).toExternalForm());
    }

    private void save() {
        Gson gson = new Gson();
        new Request<>(YouTubeApplication.socket, "GetUserPlaylistsBriefly").send(new User(YouTubeApplication.user.getId()));
        Response<ArrayList<Playlist>> playlistsResponse = gson.fromJson(YouTubeApplication.receiveResponse(), new TypeToken<Response<ArrayList<Playlist>>>() {
        }.getType());
        ArrayList<Playlist> playlists = playlistsResponse.getBody();

        VBox vbxPlaylists = new VBox();
        Text text = new Text("Add to Playlist");
        if (YouTubeApplication.theme.equals("Dark")) {
            vbxPlaylists.setStyle("-fx-background-color: rgb(20, 20, 20);-fx-background-radius:20;-fx-padding: 15;-fx-spacing: 10");
            text.setStyle("-fx-fill: rgb(255,255,255); -fx-font-weight: bold; -fx-font-size: 15px;-fx-padding: 10;");
        } else {
            vbxPlaylists.setStyle("-fx-background-color: rgb(240, 240, 240);-fx-background-radius:20;-fx-padding: 15;-fx-spacing: 10");
            text.setStyle("-fx-fill: rgb(0,0,0); -fx-font-weight: bold; -fx-font-size: 15px;-fx-padding: 10;");
        }
        vbxPlaylists.getChildren().add(text);


        popup.getContent().clear();
        popup.getContent().add(vbxPlaylists);

        for (var p : playlists) {
            Button button = new Button(p.getTitle());
            button.setId(p.getId().toString());
            if (YouTubeApplication.theme.equals("Dark")) {
                button.setStyle("-fx-background-color: rgb(70, 70, 70);-fx-background-radius:10;-fx-text-fill: rgb(255, 255, 255);-fx-alignment: center;-fx-text-alignment: center;-fx-tile-alignment: center; -fx-padding: 10; -fx-cursor: HAND;");
            }
            else {
                button.setStyle("-fx-background-color: rgb(200, 200, 200);-fx-background-radius:10;-fx-text-fill: rgb(0, 0, 0);-fx-alignment: center;-fx-text-alignment: center;-fx-tile-alignment: center; -fx-padding: 10; -fx-cursor: HAND;");
            }
            button.setOnAction(event -> {
                new Request<PlaylistDetail>(YouTubeApplication.socket, "AddVideoToPlaylist").send(new PlaylistDetail(UUID.fromString(button.getId()), video.getId()));
                YouTubeApplication.receiveResponse();
            });

            vbxPlaylists.getChildren().add(button);
        }

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
