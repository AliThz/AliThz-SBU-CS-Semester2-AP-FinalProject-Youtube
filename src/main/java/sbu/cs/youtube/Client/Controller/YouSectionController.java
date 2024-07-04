package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YouSectionController implements Initializable {

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


        FXMLLoader youPageLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/you-page.fxml"));
        VBox youPage;
        try {
            youPage = youPageLoader.load();
//            youPage.prefWidthProperty().bind(mainPane.widthProperty().subtract(60));
//            youPage.prefWidthProperty().bind(mainPane.widthProperty().subtract(layoutController.vbxSideBar.prefWidthProperty().intValue() < 100 ? layoutController.vbxSideBar.prefWidthProperty().add(70) : layoutController.vbxSideBar.prefWidthProperty().add(10)));
//            youPage.prefHeightProperty().bind(mainPane.heightProperty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        layoutController.scrollPane.setContent(youPage);
//        youPage.prefWidthProperty().bind(layoutController.scrollPane.prefWidthProperty());
        youPage.prefWidthProperty().bind(layoutController.scrollPane.viewportBoundsProperty().map(bounds -> bounds.getWidth()));
        YouPageController youPageController = youPageLoader.getController();
        youPageController.setUser(YouTubeApplication.user);
    }
    //endregion

    //endregion

}
