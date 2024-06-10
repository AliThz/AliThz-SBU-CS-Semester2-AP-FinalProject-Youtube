package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    boolean isExpanded = true;
    boolean isSignedIn = false;

    @FXML
    private VBox vbxSideBar;

    @FXML
    private HBox hbxRightNavItem;

    @FXML
    void openSesame() {
        if (isExpanded) {
            vbxSideBar.setPrefSize(80, 466);
            vbxSideBar.setSpacing(30);
            for (var c : vbxSideBar.getChildren()) {
                if (c instanceof Button) {
                    ((Button) c).getChildrenUnmodifiable().getFirst().getStyleClass().remove("expand-flow-pane");
                    ((Button) c).getChildrenUnmodifiable().getFirst().getStyleClass().add("collapse-flow-pane");
                    if (((Button) c).getChildrenUnmodifiable().getFirst() instanceof FlowPane) {
                        if (((FlowPane) ((Button) c).getChildrenUnmodifiable().getFirst()).getChildren().get(1) instanceof Label) {
                            ((FlowPane) ((Button) c).getChildrenUnmodifiable().getFirst()).getChildren().get(1).setStyle("-fx-font-size: 15px;");
                        }
                    }
                }
            }
            isExpanded = false;
        } else {
            vbxSideBar.setPrefSize(220, 466);
            vbxSideBar.setSpacing(0);
            for (var c : vbxSideBar.getChildren()) {
                if (c instanceof Button) {
                    ((Button) c).getChildrenUnmodifiable().getFirst().getStyleClass().remove("collapse-flow-pane");
                    ((Button) c).getChildrenUnmodifiable().getFirst().getStyleClass().add("expand-flow-pane");
                    if (((Button) c).getChildrenUnmodifiable().getFirst() instanceof FlowPane) {
                        if (((FlowPane) ((Button) c).getChildrenUnmodifiable().getFirst()).getChildren().get(1) instanceof Label) {
                            ((FlowPane) ((Button) c).getChildrenUnmodifiable().getFirst()).getChildren().get(1).setStyle("-fx-font-size: 15px;");
                        }
                    }
                }
            }
            isExpanded = true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!isSignedIn) {


            Button signInBtn = new Button();
            Label signInSvg = new Label();
            Label signInLbl = new Label();
            HBox signInHbx = new HBox();

            signInBtn.getStyleClass().add("signIn-btn");
            signInSvg.getStyleClass().add("signIn-svg");
            signInLbl.getStyleClass().add("signIn-lbl");
            signInLbl.setText("Sign In");

            signInHbx.getChildren().add(signInSvg);
            signInHbx.getChildren().add(signInLbl);
            signInHbx.setSpacing(7);
            signInBtn.setGraphic(signInHbx);
            signInBtn.setTooltip(new Tooltip("Sign In"));

            // ToDo set onAction for signInBtn
            hbxRightNavItem.getChildren().clear();
            hbxRightNavItem.getChildren().add(signInBtn);
        } else {
            // create buttons
            Button createBtn = new Button();
            createBtn.getStyleClass().add("loggedIn-btn");

            Button notificationsBtn = new Button();
            notificationsBtn.getStyleClass().add("loggedIn-btn");

            Button accountBtn = new Button();
            accountBtn.getStyleClass().add("loggedIn-btn");

            // create graphics for buttons
            Label createSvg = new Label();
            createSvg.getStyleClass().add("create-svg");

            Label notificationsSvg = new Label();
            notificationsSvg.getStyleClass().add("notifications-svg");

            Label accountImg = new Label();
            accountImg.getStyleClass().add("account-img");

            createBtn.setGraphic(createSvg);
            notificationsBtn.setGraphic(notificationsSvg);
            accountBtn.setGraphic(accountImg);

            // ToDo set onAction for buttons

            hbxRightNavItem.getChildren().clear();
            hbxRightNavItem.getChildren().add(createBtn);
            hbxRightNavItem.getChildren().add(notificationsBtn);
            hbxRightNavItem.getChildren().add(accountBtn);
        }

    }
}