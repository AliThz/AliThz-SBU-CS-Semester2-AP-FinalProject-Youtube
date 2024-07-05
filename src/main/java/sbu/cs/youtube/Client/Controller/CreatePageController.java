package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreatePageController implements Initializable {


    @FXML
    private AnchorPane mainPane;

    @FXML
    private VBox vbxUpload;

    @FXML
    private VBox vbxDetails;

    @FXML
    private Hyperlink linkTerms, linkGuidelines, linkLearnMore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vbxUpload.prefWidthProperty().bind(mainPane.widthProperty());
        vbxUpload.prefHeightProperty().bind(mainPane.heightProperty());

        vbxDetails.prefWidthProperty().bind(mainPane.widthProperty());
        vbxDetails.prefHeightProperty().bind(mainPane.heightProperty());

        //region [ - Links - ]
        linkTerms.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/t/terms"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        linkGuidelines.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/yt/about/policies/"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        linkLearnMore.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.youtube.com/yt/copyright"));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });
        //endregion
    }

    @FXML
    private void selectFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Mp4 file");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("mp4 files (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        //todo connect to DB
    }
}
