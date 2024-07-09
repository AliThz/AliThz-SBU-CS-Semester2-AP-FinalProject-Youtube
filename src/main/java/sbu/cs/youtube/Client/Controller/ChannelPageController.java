package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.codec.digest.DigestUtils;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChannelPageController implements Initializable {

    //region [ - Fields - ]

    private Channel channel;
    private final Gson gson = new Gson();

    @FXML
    private HBox hbxChannelDetails;

    @FXML
    private HBox hbxButtons;

    @FXML
    private ImageView imgAvatar;

    @FXML
    private ScrollPane scrlpnPlaylist;

    @FXML
    private ScrollPane scrlpnVideo;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPlaylist;

    @FXML
    private Tab tabVideo;

    @FXML
    private TilePane tilePanePlaylist;

    @FXML
    private TilePane tilePaneVideo;

    @FXML
    private Text txtChannelDescription;

    @FXML
    private Text txtChannelTitle;

    @FXML
    private Text txtSubscribersCount;

    @FXML
    private Text txtUsername;

    @FXML
    private Text txtVideosCount;

    @FXML
    private VBox vbxChannelDetails;

    @FXML
    private VBox vbxChannelPage;

    @FXML
    private Button btnSub;

    @FXML
    private Button btnEditCredentials;

    private Image avatar;

    private LayoutController parentController;

    File newImage;

    boolean updateable;

    boolean avatarChanged;

    boolean isUpdating;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String response = YouTubeApplication.receiveResponse();
        TypeToken<Response<Channel>> responseTypeToken = new TypeToken<>() {
        };
        Response<Channel> videoResponse = gson.fromJson(response, responseTypeToken.getType());
        channel = videoResponse.getBody();

        if (channel.getCreatorId().equals(YouTubeApplication.user.getId())) {
            hbxButtons.getChildren().removeFirst();
        } else {
            hbxButtons.getChildren().remove(1);
            hbxButtons.getChildren().remove(2);
        }

        new Thread(this::setChannel).start();
        new Thread(this::displayVideos).start();
        new Thread(this::displayPlaylists).start();

        vbxChannelPage.getStylesheets().clear();
        vbxChannelPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/channel-page.css")).toExternalForm());

    }
    //endregion

    //region [ - logOut(ActionEvent event) - ]

    @FXML
    private void logOut(ActionEvent event) {
        YouTubeApplication.user = null;
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

    //region [ - setChannel() - ]
    private void setChannel() {
        txtChannelTitle.setText(channel.getTitle());
        System.out.println(channel.getTitle());
        txtChannelDescription.setText(channel.getDescription());
        txtUsername.setText(channel.getCreator().getUsername());
        txtVideosCount.setText(channel.getVideoCounts() + " Videos");
        txtSubscribersCount.setText(channel.getSubscriberCount() + " Subscribers");

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(channel.getCreator().getAvatarBytes());
        avatar = new Image(bis);
        imgAvatar.setImage(avatar);
    }
    //endregion

    //region [ - displayVideos() - ]
    private void displayVideos() {
        new Request<Channel>(YouTubeApplication.socket, "GetChannelVideos").send(channel);
        TypeToken<Response<ArrayList<Video>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Video>> videoResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());

        ArrayList<Video> recommendedVideos = videoResponse.getBody();
        if (recommendedVideos == null) {
            return;
        }
        Platform.runLater(() -> {
            for (var video : recommendedVideos) {
                FXMLLoader videoPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-preview.fxml"));
                VBox videoPreview;

                try {
                    videoPreview = videoPreviewLoader.load();
                    VideoPreviewController videoPreviewController = videoPreviewLoader.getController();
                    if (videoPreviewController != null) {
                        videoPreviewController.setVideo(video);
                    }
                    videoPreviewController.setParentController(parentController);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Button button = new Button();
                button.getStyleClass().add("btn-video");
                button.setGraphic(videoPreview);

                button.setOnAction(event -> getVideo(event, video));

                tilePaneVideo.getChildren().add(button);
            }
        });
    }
    //endregion

    //region [ - getVideo(ActionEvent event, Video video) - ]
    private void getVideo(ActionEvent event, Video video) {
        Request<Video> videoRequest = new Request<>(YouTubeApplication.socket, "GetVideo");
        videoRequest.send(new Video(video.getId()));

        getVideoPage(event);
    }
    //endregion

    //region [ - getVideoPage(ActionEvent event) - ]
    private void getVideoPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/video-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxChannelPage.getScene().getWidth(), vbxChannelPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - displayPlaylists() - ]
    private void displayPlaylists() {
        new Request<User>(YouTubeApplication.socket, "GetUserPlaylists").send(new User(channel.getCreatorId()));
        TypeToken<Response<ArrayList<Playlist>>> responseTypeToken = new TypeToken<>() {
        };
        Response<ArrayList<Playlist>> playlistResponse = gson.fromJson(YouTubeApplication.receiveResponse(), responseTypeToken.getType());

        ArrayList<Playlist> playlists = playlistResponse.getBody();
        if (playlists == null) {
            return;
        }
        Platform.runLater(() -> {
            for (var playlist : playlists) {
                FXMLLoader playlistPreviewLoader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-preview.fxml"));
                Button playlistPreview;

                try {
                    playlistPreview = playlistPreviewLoader.load();
                    PlaylistPreviewController playlistPreviewController = playlistPreviewLoader.getController();
                    playlistPreviewController.setParentController(parentController);
                    if (playlistPreviewController != null) {
                        playlistPreviewController.setPlaylist(playlist);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tilePanePlaylist.getChildren().add(playlistPreview);
            }
        });
    }
    //endregion

    //region [ - getPlaylist(ActionEvent event, Playlist playlist) - ]
    private void getPlaylist(ActionEvent event, Playlist playlist) {
        Request<Playlist> playlistRequest = new Request<>(YouTubeApplication.socket, "GetPlaylist");
        playlistRequest.send(new Playlist(playlist.getId()));

        getPlaylistPage(event);
    }
    //endregion

    //region [ - getPlaylistPage(ActionEvent event) - ]
    private void getPlaylistPage(ActionEvent event) {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sbu/cs/youtube/playlist-section.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, vbxChannelPage.getScene().getWidth(), vbxChannelPage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }
    //endregion

    //region [ - updateSub(ActionEvent event) - ]
    @FXML
    private void updateSub() {
        SVGPath svgPath = (SVGPath) btnSub.getChildrenUnmodifiable().getFirst();

        Request<Subscription> subscriptionRequest = new Request<>(YouTubeApplication.socket, "CheckSubscriptionExistence");
        subscriptionRequest.send(new Subscription(YouTubeApplication.user.getId(), channel.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<Subscription>> responseTypeToken = new TypeToken<>() {
        };
        Response<Subscription> subscriptionResponse = gson.fromJson(response, responseTypeToken.getType());
        Subscription subscription = subscriptionResponse.getBody();

        if (subscription != null) {
            svgPath.setContent("m3.85 3.15-.7.7 3.48 3.48C6.22 8.21 6 9.22 6 10.32v5.15l-2 1.88V19h14.29l1.85 1.85.71-.71-17-16.99zM5 18v-.23l2-1.88v-5.47c0-.85.15-1.62.41-2.3L17.29 18H5zm5 2h4c0 1.1-.9 2-2 2s-2-.9-2-2zM9.28 5.75l-.7-.7c.43-.29.9-.54 1.42-.7v-.39c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v4.14l-1-1v-3.05c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03-.27.11-.51.24-.75.4z");
            btnSub.setText("Unsubscribed");
            Request<Subscription> unsubRequest = new Request<>(YouTubeApplication.socket, "Unsubscribe");
            unsubRequest.send(new Subscription(YouTubeApplication.user.getId(), channel.getId()));

        } else {
            svgPath.setContent("M10 20h4c0 1.1-.9 2-2 2s-2-.9-2-2zm10-2.65V19H4v-1.65l2-1.88v-5.15C6 7.4 7.56 5.1 10 4.34v-.38c0-1.42 1.49-2.5 2.99-1.76.65.32 1.01 1.03 1.01 1.76v.39c2.44.75 4 3.06 4 5.98v5.15l2 1.87zm-1 .42-2-1.88v-5.47c0-2.47-1.19-4.36-3.13-5.1-1.26-.53-2.64-.5-3.84.03C8.15 6.11 7 7.99 7 10.42v5.47l-2 1.88V18h14v-.23z");
            btnSub.setText("Subscribed");
            Request<Subscription> subRequest = new Request<>(YouTubeApplication.socket, "Subscribe");
            subRequest.send(new Subscription(YouTubeApplication.user.getId(), channel.getId()));
        }

        response = YouTubeApplication.receiveResponse();
        subscriptionResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(subscriptionResponse.getMessage());
    }
    //endregion

    //region [ - setParentController(LayoutController parentController) - ]
    public void setParentController(LayoutController parentController) {
        this.parentController = parentController;
        EventHandler<ActionEvent> existingHandler = parentController.btnMode.getOnAction();

        parentController.btnMode.setOnAction(event -> {
            if (existingHandler != null) {
                existingHandler.handle(event);
            }
            vbxChannelPage.getStylesheets().clear();
            vbxChannelPage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/channel-page.css")).toExternalForm());
        });
    }
    //endregion

    //region [ - showDialog() - ]
    @FXML
    private void showDialog() {
        isUpdating = false;
        // Sample user
        User user = new User("Email", "Username", "Password");

        // Create the custom dialog
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Update Your Information");
        dialog.setHeaderText("Update Your Information");

        // Apply CSS to the dialog
        dialog.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/" + YouTubeApplication.theme + "/channel-page.css")).toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");
//        dialog.setGraphic(new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/info.jpg")))));

        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonType.OK.getButtonData());
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, cancelButtonType);

        // Create the labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField fullNameField = new TextField();
        fullNameField.setPromptText("New Full Name");
        fullNameField.setText(YouTubeApplication.user.getFullName());
        TextField usernameField = new TextField();
        usernameField.setPromptText("New Username");
        usernameField.setText(YouTubeApplication.user.getUsername());
        TextField emailField = new TextField();
        emailField.setPromptText("New Email");
        emailField.setText(YouTubeApplication.user.getEmail());
        TextField passwordField = new TextField();
        passwordField.setPromptText("New Password");
        ImageView imageView = new ImageView(avatar);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Button uploadButton = new Button("", imageView);

        avatarChanged = false;
        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select new Avatar");
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
            fileChooser.getExtensionFilters().add(extensionFilter);
            newImage = fileChooser.showOpenDialog(vbxChannelPage.getScene().getWindow());
            if (newImage != null) {
                avatarChanged = true;
                imageView.setImage(new Image(newImage.toURI().toString()));
            }

        });
        uploadButton.getStyleClass().add("btn-upload");

        grid.add(new Label("Full Name:"), 0, 0);
        grid.add(fullNameField, 1, 0);
        grid.add(new Label("Username:"), 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(new Label("Email:"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(new Label("Avatar:"), 0, 4);
        grid.add(uploadButton, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // Convert the result to a user object when the update button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                return new User(fullNameField.getText(), emailField.getText(), avatarChanged ? convertImageToByteArray(newImage.getAbsolutePath()) : null, usernameField.getText(), passwordField.getText().isEmpty() ? YouTubeApplication.user.getPassword() : passwordField.getText());
            }
            return null;
        });

        updateable = true;
        // Show the dialog and update the user if the update button is clicked
        dialog.showAndWait().ifPresent(updatedUser -> {
            isUpdating = true;
            if (verifyFullName(updatedUser.getFullName())) {
                YouTubeApplication.user.setFullName(updatedUser.getFullName());
            } else {
                updateable = false;
                return;
            }
            if (verifyUsername(updatedUser.getUsername())) {
                YouTubeApplication.user.setUsername(updatedUser.getUsername());
            } else {
                updateable = false;
                return;
            }
            if (verifyEmail(updatedUser.getEmail())) {
                YouTubeApplication.user.setEmail(updatedUser.getEmail());
            } else {
                updateable = false;
                return;
            }
            if (verifyPassword(updatedUser.getPassword())) {
                YouTubeApplication.user.setPassword(updatedUser.getPassword());
            } else {
                updateable = false;
                return;
            }
            if (avatarChanged)
                YouTubeApplication.user.setAvatarBytes(convertImageToByteArray(newImage.getAbsolutePath()));
            else {
                YouTubeApplication.user.setAvatarBytes(null);
            }
        });


        if (!updateable) {
            return;
        }

        if (isUpdating) {
            Request<User> userRequest = new Request<>(YouTubeApplication.socket, "ChangeUserInfo");
            userRequest.send(YouTubeApplication.user);

            String response = YouTubeApplication.receiveResponse();
            Gson gson = new Gson();
            TypeToken<Response<User>> responseTypeToken = new TypeToken<>() {
            };
            Response<User> userResponse = gson.fromJson(response, responseTypeToken.getType());

            YouTubeApplication.user = userResponse.getBody();

            parentController.sendNotification(userResponse.getMessage());
        }
    }
    //endregion

    //region [ - verifyPassword(String newPassword) - ]

    private boolean verifyPassword(String newPassword) {
        if (newPassword.isEmpty()) {
            return true;
        }
        String passwordRegex = "^[A-Za-z0-9]+$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(newPassword);

        if (passwordMatcher.matches()) {
            return true;
        }

        parentController.sendNotification("Password can only contain alphabets and numbers and have at least 8 characters");
        return false;
    }
    //endregion

    //region [ - verifyEmail(String newEmail) - ]

    private boolean verifyEmail(String newEmail) {
        if (newEmail.equals(YouTubeApplication.user.getEmail())) return true;

        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "CheckExistingUser");
        userRequest.send(new User(newEmail, "", ""));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<User>> responseTypeToken = new TypeToken<>() {
        };
        Response<User> userResponse = gson.fromJson(response, responseTypeToken.getType());

        User responseUser = userResponse.getBody();

        if (responseUser != null) {
            parentController.sendNotification(userResponse.getMessage());
            return false;
        } else {
            parentController.sendNotification(userResponse.getMessage());
            return true;
        }
    }
    //endregion

    //region [ - verifyUsername(String newUsername) - ]

    private boolean verifyUsername(String newUsername) {
        if (newUsername.equals(YouTubeApplication.user.getUsername())) return true;

        Request<User> userRequest = new Request<>(YouTubeApplication.socket, "CheckExistingUser");
        userRequest.send(new User("", newUsername, ""));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<User>> responseTypeToken = new TypeToken<>() {
        };
        Response<User> userResponse = gson.fromJson(response, responseTypeToken.getType());

        User responseUser = userResponse.getBody();

        if (responseUser != null) {
            parentController.sendNotification(userResponse.getMessage());
            return false;
        } else {
            parentController.sendNotification(userResponse.getMessage());
            return true;
        }
    }
    //endregion

    //region [ - verifyFullName(String newFullName) - ]

    private boolean verifyFullName(String newFullName) {
        String usernameRegex = "^(?!\\s)(?!.*\\s{2})[a-zA-Z ]{3,}$";
        Pattern usernamePattern = Pattern.compile(usernameRegex);
        Matcher usernameMatcher = usernamePattern.matcher(newFullName);

        if (usernameMatcher.matches()) {
            return true;
        }

        parentController.sendNotification("Full name should only contain alphabets and no consecutive spaces");
        return false;
    }
    //endregion

    //region [ - convertImageToByteArray(String imagePath, String type) - ]
    private byte[] convertImageToByteArray(String imagePath) {
        System.out.println("In ConvertImage Method");
        byte[] imageBytes = null;
        try {
            // Load the image
            File file = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(file);

            // Convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("End of ConvertImage Method");
        return imageBytes;
    }
    //endregion

    //endregion
}
