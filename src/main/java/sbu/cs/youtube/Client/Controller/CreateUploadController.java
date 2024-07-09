package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import sbu.cs.youtube.YouTubeApplication;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateUploadController implements Initializable {
    //region [ - Fields - ]
    private LayoutController parentController;

    @FXML
    public VBox vbxUpload;

    @FXML
    private Hyperlink linkTerms, linkGuidelines, linkLearnMore;
    //endregion

    //region [ - Methods - ]
    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        vbxUpload.getStylesheets().clear();
        vbxUpload.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/create-section.css")).toExternalForm());

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
    //endregion

    //region [ - selectFiles(ActionEvent event) - ]
    @FXML
    private void selectFiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Mp4 file");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("mp4 files (*.mp4)", "*.mp4");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File selectedFile = fileChooser.showOpenDialog(vbxUpload.getScene().getWindow());

        if (selectedFile != null) {
            getDetails(selectedFile);
        }
    }
    //endregion

    //region [ - getDetails(File selectedFile) - ]
    private void getDetails(File selectedFile) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/create-details.fxml"));
        VBox createDetails;
        try {
            createDetails = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CreateDetailsController controller = loader.getController();
        controller.initialize(selectedFile, parentController);

        FlowPane flwPane = (FlowPane) parentController.hbxContent.getChildren().get(2);
        flwPane.getChildren().removeFirst();
        flwPane.getChildren().add(createDetails);

        createDetails.prefWidthProperty().bind(flwPane.widthProperty().subtract(70));
        createDetails.prefHeightProperty().bind(flwPane.heightProperty().subtract(70));
    }
    //endregion

    //region [ - setParentController(LayoutController parentController) - ]
    public void setParentController(LayoutController parentController) {
        this.parentController = parentController;
        EventHandler<ActionEvent> existingHandler = parentController.btnMode.getOnAction();

        parentController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            vbxUpload.getStylesheets().clear();
            vbxUpload.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/create-section.css")).toExternalForm());
        });
    }
    //endregion
    //endregion

}
