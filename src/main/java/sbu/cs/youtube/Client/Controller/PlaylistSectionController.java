package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import sbu.cs.youtube.Shared.POJO.Playlist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistSectionController implements Initializable {

    //region [ - Field - ]
    private Playlist playlist;
    @FXML
    private AnchorPane mainPane;
    //endregion

    //region [ - Method - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader layoutLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/layout.fxml"));
        Parent layout;
        try {
            layout = layoutLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().add(layout);
        LayoutController layoutController = layoutLoader.getController();

        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());

        // removing scroll pane
        layoutController.hbxContent.getChildren().remove(2);
//        layoutController.hbxContent.getChildren().remove(layoutController.scrollPane);


        FXMLLoader PlaylistPageLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-page.fxml"));
        HBox playlistPage;
        try {
            playlistPage = PlaylistPageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.hbxContent.getChildren().add(playlistPage);
        HBox.setHgrow(playlistPage, Priority.ALWAYS);
//        playlistPage.prefWidthProperty().bind(layoutController.hbxContent.prefWidthProperty());
//        playlistPage.prefHeightProperty().bind(layoutController.hbxContent.prefHeightProperty());
        playlistPage.prefWidthProperty().bind(layoutController.hbxContent.widthProperty().subtract(layoutController.vbxSideBar.widthProperty().add(10)));
        playlistPage.prefHeightProperty().bind(layoutController.hbxContent.heightProperty());
    }
    //endregion

    //endregion
}
