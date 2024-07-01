package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignInController implements Initializable {

    //region [ - Fields - ]

    @FXML
    private SVGPath inputError;

    @FXML
    private TextField inputField;

    @FXML
    private PasswordField passField;

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
        nextBtn.setOnAction(this::verifyCredentials);
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

    private void verifyCredentials(ActionEvent event) {
        String input = inputField.getText();
        String password = DigestUtils.sha256Hex(passField.getText());

        Boolean isEmail = determineInput(input);

        User user;

        if (isEmail == null || password.isEmpty()) {
            inputLog.setText("Invalid entry: please complete all fields correctly");
            inputLog.getParent().setVisible(true);
            return;
        } else if (isEmail) {
            user = new User(input, "", password);
        } else {
            user = new User("", input, password);
        }

        if (signIn(user)) {
            exitSignInSignUp(event);
            System.out.println("Signed In");
        }
        else {
            inputLog.getParent().setVisible(true);
        }
    }

    Boolean determineInput(String input) {
        //email regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(input);

        //username regex
        String usernameRegex = "^[A-Za-z0-9_]+$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(input);

        if (emailMatcher.matches()) {
            return true;
        } else if (usernameMatcher.matches()) {
            return false;
        }

        inputLog.setText("Invalid entry: please enter your username or password");
        return null;
    }

    private void openHomePage() {

    }

    //region [ - checkEmail(ActionEvent event) - ]

    private boolean signIn(User user) {


        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "SignIn");
        userRequest.send(user);

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<User>> responseTypeToken = new TypeToken<>() {
        };
        Response<User> userResponse = gson.fromJson(response, responseTypeToken.getType());

        User responseUser = userResponse.getBody();

        if (responseUser != null) {
            YouTubeApplication.user = responseUser;
            System.out.println(userResponse.getMessage());
            return true;
        } else {
            inputLog.setText(userResponse.getMessage());
            return false;
        }

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
        scene = new Scene(root, nextBtn.getScene().getWidth(), nextBtn.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //endregion

}
