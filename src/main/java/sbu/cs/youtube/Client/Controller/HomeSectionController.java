package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeSectionController implements Initializable {
    @FXML
    AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/layout.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mainPane.getChildren().add(layout);
        LayoutController layoutController = loader.getController();

        FlowPane flowPane = new FlowPane();
        flowPane.getStyleClass().add("background");
        layoutController.scrollPane.setContent(flowPane);

        flowPane.prefWidthProperty().bind(layoutController.scrollPane.widthProperty().subtract(15));
        flowPane.prefHeightProperty().bind(layoutController.scrollPane.heightProperty());

        layoutController.vbxLayout.prefWidthProperty().bind(mainPane.widthProperty());
        layoutController.vbxLayout.prefHeightProperty().bind(mainPane.heightProperty());
    }
}
