package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    //region [ - Fields - ]

    boolean isExpanded = true;

    boolean isSignedIn = true;

    @FXML
    private VBox vbxSideBar;

    @FXML
    private HBox hbxRightNavItem;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setRightNavBarHBox();
        flowPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(16));
        flowPane.prefHeightProperty().bind(scrollPane.heightProperty());


    }

    //endregion

    //region [ - openSesame() - ]

    @FXML
    void openSesame() {
        // todo : change font size when collapsed for better looking side bar
        Scene scene = vbxSideBar.getScene();
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
            SVGPath signInSvg = new SVGPath();
            signInSvg.setContent("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm0 1c4.96 0 9 4.04 9 9 0 1.42-.34 2.76-.93 3.96-1.53-1.72-3.98-2.89-7.38-3.03A3.996 3.996 0 0016 9c0-2.21-1.79-4-4-4S8 6.79 8 9c0 1.97 1.43 3.6 3.31 3.93-3.4.14-5.85 1.31-7.38 3.03C3.34 14.76 3 13.42 3 12c0-4.96 4.04-9 9-9zM9 9c0-1.65 1.35-3 3-3s3 1.35 3 3-1.35 3-3 3-3-1.35-3-3zm3 12c-3.16 0-5.94-1.64-7.55-4.12C6.01 14.93 8.61 13.9 12 13.9c3.39 0 5.99 1.03 7.55 2.98C17.94 19.36 15.16 21 12 21z");
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
            SVGPath createSvg = new SVGPath();
            createSvg.setContent("M14 13h-3v3H9v-3H6v-2h3V8h2v3h3v2zm3-7H3v12h14v-6.39l4 1.83V8.56l-4 1.83V6m1-1v3.83L22 7v8l-4-1.83V19H2V5h16z");
            createSvg.getStyleClass().add("create-svg");

            SVGPath notificationsSvg = new SVGPath();
            notificationsSvg.setContent("M10 20h4c0 1.1-.9 2-2 2s-2-.9-2-2zm10-2.65V19H4v-1.65l2-1.88v-5.15C6 7.4 7.56 5.1 10 4.34v-.38c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v5.15l2 1.87zm-1 .42-2-1.88v-5.47c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03C8.15 6.11 7 7.99 7 10.42v5.47l-2 1.88V18h14v-.23z");
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
                .threshold(3, Notifications.create().title("Notifications"))
                .show();
    }

    //endregion

    //endregion

}
