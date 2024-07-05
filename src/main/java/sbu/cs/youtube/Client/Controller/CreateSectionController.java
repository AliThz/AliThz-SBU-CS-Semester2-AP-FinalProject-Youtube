package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateSectionController implements Initializable {


    //region [ - Field - ]
    @FXML
    AnchorPane mainPane;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/layout.fxml"));
        Parent layout;
        try {
            layout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().add(layout);
        LayoutController layoutController = loader.getController();

        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());

        layoutController.hbxContent.getChildren().remove(2);


        loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/create-page.fxml"));
        AnchorPane createPage;
        try {
            createPage = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.hbxContent.getChildren().add(2, createPage);
        HBox.setHgrow(createPage, Priority.ALWAYS);

        createPage.prefWidthProperty().bind(layoutController.hbxContent.widthProperty().subtract(90));
        createPage.prefHeightProperty().bind(layoutController.hbxContent.heightProperty());

        //todo responsivity has problems

    }
    //endregion

    //endregion
}




