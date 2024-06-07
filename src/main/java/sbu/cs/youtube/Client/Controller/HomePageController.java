package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    //region [ - Fields - ]

    boolean isExpanded = true;

    boolean isSignedIn = false;

    @FXML
    private VBox vbxSideBar;

    @FXML
    private HBox hbxRightNavItem;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setRightNavBarHBox();


    }

    //endregion

    //region [ - openSesame() - ]

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

    //endregion

    //region [ - setRightNavBarHBox() - ]

    private void setRightNavBarHBox() {
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

            signInBtn.setOnAction(this::getSignInPage);

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

            createBtn.setTooltip(new Tooltip("Create"));
            notificationsBtn.setTooltip(new Tooltip("Notifications"));
            accountBtn.setTooltip(new Tooltip("Account Management"));

            createBtn.setOnAction(this::getCreatePage);
            notificationsBtn.setOnAction(this::getNotificationsScene);
            accountBtn.setOnAction(this::getDashboard);

            hbxRightNavItem.getChildren().clear();
            hbxRightNavItem.getChildren().add(createBtn);
            hbxRightNavItem.getChildren().add(notificationsBtn);
            hbxRightNavItem.getChildren().add(accountBtn);
        }
    }
    //endregion

    //region [ - getDashboard(ActionEvent event) - ]

    private void getDashboard(ActionEvent event) {
    }

    //endregion

    //region [ - getNotificationsScene(ActionEvent event) - ]

    private void getNotificationsScene(ActionEvent event) {
    }

    //endregion

    //region [ - getCreatePage(ActionEvent event) - ]

    private void getCreatePage(ActionEvent event) {
    }

    //endregion

    //region [ - getSignInPage(ActionEvent event) - ]

    private void getSignInPage(ActionEvent event) {
    }

    //endregion

    //region [ - sendNotification(String text) - ]

    private void sendNotification(String text) {
        Notifications.create()
                .text(text)
                .hideAfter(Duration.seconds(2))
                .owner(hbxRightNavItem)
                .position(Pos.BASELINE_RIGHT)
                .threshold(3, Notifications. create().title("Notifications"))
                .show();
    }

    //endregion

    //endregion

}
