package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import sbu.cs.youtube.Shared.POJO.User;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpController implements Initializable {

    //region [ - Fields - ]

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
    private Text inputLog;

    @FXML
    private HBox hbxLog;

    private String fullName;
    private LocalDateTime birthDate;
    private String email;
    private String username;
    private String password;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        birthDatePicker.prefWidthProperty().bind(vbxRight.widthProperty());
        nextBtn.setOnAction(this::changeToEmail);
        hbxLog = new HBox();
        inputError = new SVGPath();
        inputError.setContent("M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z");
        inputError.getStyleClass().add("error-svg");
        inputLog = new Text("");
        inputLog.setFill(Color.rgb(255, 122, 96));
        inputLog.setWrappingWidth(204);
        hbxLog.getChildren().addAll(inputError, inputLog);
        hbxLog.setPadding(new Insets(2, 2, 2, 2));
        hbxLog.setSpacing(5);
        hbxLog.setVisible(false);
        vbxRight.getChildren().add(3, hbxLog);

    }
    //endregion

    //region [ - changeToEmail(ActionEvent event) - ]

    private void changeToEmail(ActionEvent event) {
        if (validateName(inputField.getText()) && validateBirthday(birthDatePicker.getValue())) {
            fullName = inputField.getText();
            birthDate = birthDatePicker.getValue().atStartOfDay();

            hbxLog.setVisible(false);
            inputField.clear();
            vbxRight.getChildren().remove(2);
            inputField.setPromptText("Email");
            txtDescription.setText("Enter your email");
            nextBtn.setOnAction(this::changeToPassword);
        } else {
            hbxLog.setVisible(true);
        }
    }
    //endregion

    //region [ - private boolean validateBirthday(LocalDate birthDay) - ]
    private boolean validateBirthday(LocalDate birthDay) {
        if (birthDay == null) {
            inputLog.setText("Invalid entry: please complete the birthday field");
            return false;
        }
        return true;
    }
    //endregion

    //region [ - private boolean validateName(String fullName) - ]
    private boolean validateName(String fullName) {
        String usernameRegex = "^[A-Za-z]+(\\s[A-Za-z]+){0,2}[A-Za-z]{6,}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(fullName);

        if (usernameMatcher.matches()) {
            return true;
        }

        inputLog.setText("Invalid entry: full name should only contain alphabets and up to two spaces");
        return false;
    }
    //endregion

    //region [ - changeToPassword(ActionEvent event) - ]

    private void changeToPassword(ActionEvent event) {
        if (validateEmail(inputField.getText())) {
            email = inputField.getText();
            getUsername(email);

            hbxLog.setVisible(false);
            inputField.clear();
            inputField.setPromptText("Password");
            txtDescription.setText("Enter your password");
            nextBtn.setOnAction(this::signIn);
        }
        else {
            hbxLog.setVisible(true);
        }
    }

    //endregion

    //region [ - private void getUsername(String email) - ]
    private void getUsername(String email) {
        String[] parts = email.split("@");
        username = parts[0];
    }
    //endregion

    //region [ - private boolean validateEmail(String email) - ]
    private boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);

        if (emailMatcher.find()) {
            return true;
        }
        inputLog.setText("Invalid entry: please enter the correct email format");
        return false;
    }
    //endregion

    //region [ - changeToSignIn(ActionEvent event) - ]

    private void signIn(ActionEvent event) {
        if (validatePassword(inputField.getText())) {
            password = inputField.getText();

            Request<User> userRequest = new Request<>(YouTubeApplication.socket, "SignUp");
            userRequest.send(new User(fullName, email, username, password, birthDate.toString()));

            String response = YouTubeApplication.receiveResponse();
            Gson gson = new Gson();
            TypeToken<Response<User>> responseTypeToken = new TypeToken<>() {};
            Response<User> userResponse = gson.fromJson(response, responseTypeToken.getType());

            YouTubeApplication.user = userResponse.getBody(); //todo

            System.out.println("User created : " + YouTubeApplication.user.getUsername());
        }
        else {
            hbxLog.setVisible(true);
        }
    }
    //endregion

    //region [ - validatePassword(String password) - ]

    private boolean validatePassword(String password) {
        String passwordRegex = "^[A-Za-z0-9]+$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);

        if (passwordMatcher.matches()) {
            return true;
        }

        inputLog.setText("Invalid entry: password can only contain alphabets and numbers and have at least 8 characters");
        return false;
    }
    //endregion

    //region [ - exitSignUp(ActionEvent event) - ]

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
    //endregion

    //region [ - getSignInPage(ActionEvent event) - ]
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
        scene = new Scene(root, nextBtn.getScene().getWidth(), nextBtn.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //endregion

}
