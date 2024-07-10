package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sbu.cs.youtube.Shared.POJO.Video;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
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
        mainPane.getStylesheets().clear();
        mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/create-section.css")).toExternalForm());

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

        EventHandler<ActionEvent> existingHandler = layoutController.btnMode.getOnAction();

        layoutController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            mainPane.getStylesheets().clear();
            mainPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/create-section.css")).toExternalForm());
        });

        layoutController.hbxContent.getChildren().remove(2);
        FlowPane flwPane = new FlowPane();
        flwPane.getStyleClass().add("flwpane");
        flwPane.setAlignment(Pos.CENTER);
        layoutController.hbxContent.getChildren().add(2, flwPane);
        HBox.setHgrow(flwPane, Priority.ALWAYS);

        loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/create-upload.fxml"));
        VBox createUpload;
        try {
            createUpload = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        flwPane.getChildren().add(createUpload);
        CreateUploadController createUploadController = loader.getController();
        createUploadController.setParentController(layoutController);
        createUpload.prefWidthProperty().bind(flwPane.widthProperty().subtract(70));
        createUpload.prefHeightProperty().bind(flwPane.heightProperty().subtract(70));

//        layoutController.hbxContent.getChildren().remove(2);
//
//        loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/create-upload.fxml"));
//        VBox createUpload;
//        try {
//            createUpload = loader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        CreateUploadController createUploadController = loader.getController();
//        createUploadController.setParentController(layoutController);
//        layoutController.hbxContent.getChildren().add(createUpload);
//        HBox.setHgrow(createUpload, Priority.ALWAYS);
    }
    //endregion

    //endregion
}




