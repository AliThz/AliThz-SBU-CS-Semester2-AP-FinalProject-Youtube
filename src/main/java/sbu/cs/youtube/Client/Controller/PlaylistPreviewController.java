package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.POJO.Playlist;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
    private Button btnOpenPlaylist;

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
        btnOpenPlaylist.getStylesheets().clear();
        btnOpenPlaylist.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/playlist-preview.css")).toExternalForm());
        imgThumbnail.fitWidthProperty().bind(vbxPlaylistPreview.widthProperty());
        imgThumbnail.fitHeightProperty().bind(vbxPlaylistPreview.heightProperty());
        imgThumbnail.setPreserveRatio(true);

        hbxPlaylistDetails.prefWidthProperty().bind(vbxPlaylistPreview.widthProperty());

        vbxTextDetails.prefWidthProperty().bind(hbxPlaylistDetails.widthProperty().subtract(100));

        btnPlaylistPreviewOptions.setOnAction(event -> {
            event.consume();
            save(event);
        });



//        ContextMenu contextMenu = new ContextMenu();
//        MenuItem menuItem1 = new MenuItem("Choice 1");
//        MenuItem menuItem2 = new MenuItem("Choice 2");
//        MenuItem menuItem3 = new MenuItem("Choice 3");
//
//        menuItem3.setOnAction((event) -> {
//            System.out.println("Choice 3 clicked!");
//        });
//
//        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3);
//
//        TextArea textArea = new TextArea();
//
//        textArea.setContextMenu(contextMenu);
//
//        vbxPlaylistPreview.getChildren().add(textArea);
//
//        contextMenu.show(btnPlaylistPreviewOptions, btnPlaylistPreviewOptions.localToScreen(0, 0).getX(), btnPlaylistPreviewOptions.localToScreen(0, 0).getY());


        // Create a context menu
        ContextMenu contextMenu = new ContextMenu();

        // Add menu items to the context menu
        MenuItem item1 = new MenuItem("Option 1");
        MenuItem item2 = new MenuItem("Option 2");
        MenuItem item3 = new MenuItem("Option 3");
        contextMenu.getItems().addAll(item1, item2, item3);

//         Set the on-action event handler for the button
        btnPlaylistPreviewOptions.setOnAction(event -> {
            // Show the context menu when the button is clicked
            contextMenu.show(btnPlaylistPreviewOptions, btnPlaylistPreviewOptions.getLayoutX(), btnPlaylistPreviewOptions.getLayoutY());
        });
        btnPlaylistPreviewOptions.setContextMenu(contextMenu);
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

    //region [ - getPlaylist(ActionEvent event) - ]
    @FXML
    private void getPlaylist(ActionEvent event) {
        Request<Playlist> playlistRequest = new Request<>(YouTubeApplication.socket, "GetPlaylist");
        playlistRequest.send(new Playlist(playlist.getId()));
        getPlaylistPage(event);
    }
    //endregion

    //region [ - getPlaylistPage(ActionEvent event) - ]
    private void getPlaylistPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxPlaylistPreview.getScene().getWidth(), vbxPlaylistPreview.getScene().getHeight());
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
            btnOpenPlaylist.getStylesheets().clear();
            btnOpenPlaylist.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/playlist-preview.css")).toExternalForm());
        });
    }
    //endregion

    //region [ - displayPopUpMenu() - ]
    @FXML
    private void displayPopUpMenu() {
//        Popup popup = new Popup();
//        VBox popupContent = new VBox(10);
//        Button btnAddToPlaylist;
//        popupContent.getChildren().addAll(new Label("Menu Item 1"), new Label("Menu Item 2"), new Label("Menu Item 3"));
//        popup.getContent().add(popupContent);
//
//        btnPlaylistPreviewOptions.setOnAction(e -> {
//            if (popup.isShowing()) {
//                popup.hide();
//            } else {
//                popup.show(vbxPlaylistPreview.getScene().getWindow());
//            }
//        });


    }
    //endregion

    //endregion

}
