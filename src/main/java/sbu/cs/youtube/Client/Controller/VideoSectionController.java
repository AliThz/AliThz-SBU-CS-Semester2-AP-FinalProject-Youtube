package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoSectionController implements Initializable {

    //region [ - Field - ]
    @FXML
    private AnchorPane mainPane;
    //endregion

    //region [ - Methods - ]

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

        layoutController.hbxContent.getChildren().remove(2);
        layoutController.btnBurger.setDisable(true);
//        layoutController.isExpanded = true;


        FXMLLoader videoPageLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-page.fxml"));
        AnchorPane videoPage;
        try {
            videoPage = videoPageLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.hbxContent.getChildren().add(videoPage);
        VideoPageController videoPageController = videoPageLoader.getController();

//        HBox.setHgrow(layoutController.hbxContent.getChildren().get(2), Priority.ALWAYS);

        // Ensure the AnchorPane resizes with the HBox
        HBox.setHgrow(videoPage, Priority.ALWAYS);

        // Bind the width and height of the AnchorPane to the HBox

//        if (layoutController.isExpanded) {
//            videoPage.prefWidthProperty().bind(layoutController.hbxContent.widthProperty().subtract(layoutController.vbxSideBar.getWidth()+ 100));
//        } else {
//            videoPage.prefWidthProperty().bind(layoutController.hbxContent.widthProperty().subtract(layoutController.vbxSideBar.getWidth() + 79));
            videoPage.prefWidthProperty().bind(layoutController.hbxContent.widthProperty().subtract(90));
//        }
        videoPage.prefHeightProperty().bind(layoutController.hbxContent.heightProperty());


    }
    //endregion

    //endregion


}
