package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]

    @FXML
    VBox testPane;

    @Override
    public void initialize(URL location, ResourceBundle resoursces) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/home-page.fxml"));
        Parent layout = null;
        try {
            layout = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        testPane.getChildren().add(layout);
        LayoutController controller = loader.getController();

        controller.setRightNavBarHBox();
//        flowPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(15));
//        flowPane.prefHeightProperty().bind(scrollPane.heightProperty());


    }

    private void hello() {



    }

    //endregion

    //endregion

}
