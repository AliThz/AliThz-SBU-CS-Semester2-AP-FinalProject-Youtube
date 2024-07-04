package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateController implements Initializable {


    @FXML
    private AnchorPane mainPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
