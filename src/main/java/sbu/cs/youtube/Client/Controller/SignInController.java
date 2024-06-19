package sbu.cs.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private SVGPath inputError;

    @FXML
    private TextField inputField;

    @FXML
    private Text inputLog;

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

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputError.getParent().setVisible(false);
        nextBtn.setOnAction(this::checkEmail);
    }
    //endregion

    //region [ - exitSignInSignUp(ActionEvent event) - ]

    @FXML
    private void exitSignInSignUp(ActionEvent event) {
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
    //endregion

    //region [ - checkEmail(ActionEvent event) - ]

    @FXML
    private void checkEmail(ActionEvent event) {
        String email = inputField.getText();
        boolean emailIsValid = true; // ToDo needs connection to socket
        if (emailIsValid) {
            System.out.println("Email verified");
            nextBtn.setOnAction(this::checkPassword);
            inputField.setPromptText("Password");
        }

    }
    //endregion

    //region [ - checkPassword(ActionEvent event) - ]

    private void checkPassword(ActionEvent event) {
        boolean passwordIsValid = true; // ToDo needs connection to socket
        if (passwordIsValid) {
            System.out.println("Password verified");
            signIn();
        }
    }

    //endregion

    //region [ - signIn() - ]

    private void signIn() {
    }

    //endregion

    //region [ - getSignUpPage(ActionEvent event) - ]

    @FXML
    private void getSignUpPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/sign-up.fxml"));
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
    //endregion

    //endregion

}
