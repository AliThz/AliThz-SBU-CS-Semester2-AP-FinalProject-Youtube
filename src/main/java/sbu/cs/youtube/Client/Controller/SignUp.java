package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {

    @FXML
    private TextField inputField;

    @FXML
    private Label lblTitle;

    @FXML
    private Button nextBtn;

    @FXML
    private Text txtDescription;

    @FXML
    private VBox vbxContainer;

    @FXML
    private VBox vbxLeft;

    @FXML
    private VBox vbxRight;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private SVGPath inputError;

    @FXML
    private
    Text inputLog;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        birthDatePicker.prefWidthProperty().bind(vbxRight.widthProperty());
        nextBtn.setOnAction(this::changeToEmail);
    }

    private void changeToEmail(ActionEvent event) {
        boolean canChange = true; // ToDo
        if(canChange) {
            vbxRight.getChildren().remove(2);
            inputField.setPromptText("Email");
            HBox hbxLog = new HBox();
            inputError = new SVGPath();
            inputError.setContent("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z");
            inputError.getStyleClass().add("error-svg");
            inputLog = new Text("");
            inputLog.setFill(Color.rgb(255,122,96));
            inputLog.prefWidth(204);
            hbxLog.getChildren().addAll(inputError, inputLog);
            hbxLog.setPadding(new Insets(2, 2, 2, 2));
            hbxLog.setSpacing(5);
            hbxLog.setVisible(false);
            vbxRight.getChildren().add(2, hbxLog);

            txtDescription.setText("Enter your email");
            nextBtn.setOnAction(this::changeToPassword);
        }
    }

    private void changeToPassword(ActionEvent event) {
        boolean checkEmail = true; //Todo
        if (checkEmail) {
            inputField.setPromptText("Password");
            txtDescription.setText("Enter your password");
            nextBtn.setOnAction(this::checkPassword);
        }
    }

    private void checkPassword(ActionEvent event) {
        boolean checkPassword = true; //todo
        if (checkPassword) {
            signIn();
        }
    }

    private void signIn() {
    }

    @FXML
    void exitSignUp(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/home-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void getSignInPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/sign-in.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




}
