package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChannelSectionController implements Initializable {

    //region [ - Field - ]
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

        layoutController.scrollPane.getChildrenUnmodifiable().remove(layoutController.flowPane);


        FXMLLoader channelPageLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/channel-page.fxml"));
        VBox channelPage;
        try {
            channelPage = channelPageLoader.load();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.scrollPane.setContent(channelPage);
        ChannelPageController channelPageController = channelPageLoader.getController();
        channelPageController.setParentController(layoutController);
        channelPage.prefWidthProperty().bind(layoutController.scrollPane.viewportBoundsProperty().map(Bounds::getWidth));
        channelPage.prefHeightProperty().bind(layoutController.scrollPane.viewportBoundsProperty().map(Bounds::getHeight));
        //endregion
    }
    //endregion
}
