package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SearchSectionController {

    //region [ - Field - ]
    @FXML
    private AnchorPane mainPane;
    //endregion

    //region [ - Method - ]

    //region [ - initialize(String searchText) - ]
    public void initialize(String searchText) {
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

        layoutController.scrollPane.getChildrenUnmodifiable().remove(layoutController.flowPane);


        FXMLLoader searchPageLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/search-page.fxml"));
        VBox searchPage;
        try {
            searchPage = searchPageLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.scrollPane.setContent(searchPage);
        SearchPageController searchPageController = searchPageLoader.getController();
        searchPageController.initialize(searchText);
        searchPage.prefWidthProperty().bind(layoutController.scrollPane.viewportBoundsProperty().map(Bounds::getWidth));
        searchPage.prefHeightProperty().bind(layoutController.scrollPane.viewportBoundsProperty().map(Bounds::getHeight));

//        mainPane.getStylesheets().clear();
//        mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/channel-page.css")).toExternalForm());

        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
//            mainPane.getStylesheets().clear();
//            mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/channel-page.css")).toExternalForm());
        });
    }
    //endregion

    //endregion

}
