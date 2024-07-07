package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import sbu.cs.youtube.Shared.POJO.*;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

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
            hbxChannelDetails.getChildren().remove(1);
        }

        new Thread(this::setChannel).start();
        new Thread(this::displayVideos).start();
        new Thread(this::displayPlaylists).start();
    }
    //endregion

    //region [ - setChannel() - ]
    private void setChannel() {
        txtChannelTitle.setText(channel.getTitle());
        txtChannelDescription.setText(channel.getDescription());
        txtUsername.setText(YouTubeApplication.user.getUsername());
        txtVideosCount.setText(String.valueOf(channel.getVideoCounts()));
        txtSubscribersCount.setText(String.valueOf(channel.getSubscriberCount()));

        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(YouTubeApplication.user.getAvatarBytes());
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
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tilePaneVideo.getChildren().add(videoPreview);

                Button button = new Button();
                button.getStyleClass().add("btn-video");
                button.setGraphic(videoPreview);

                button.setOnAction(event -> getVideo(event, video));
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
        new Request<User>(YouTubeApplication.socket, "GetUserPlaylists").send(YouTubeApplication.user);
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
                VBox playlistPreview;

                try {
                    playlistPreview = playlistPreviewLoader.load();
                    PlaylistPreviewController playlistPreviewController = playlistPreviewLoader.getController();
                    if (playlistPreviewController != null) {
                        playlistPreviewController.setPlaylist(playlist);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tilePanePlaylist.getChildren().add(playlistPreview);

                Button button = new Button();
                button.getStyleClass().add("btn-playlist");
                button.setGraphic(playlistPreview);

                button.setOnAction(event -> getPlaylist(event, playlist));
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

    //region [ - showDialog() - ]
    @FXML
    private void showDialog() {
        // Sample user
        User user = new User("Email", "Username", "Password");

        // Create the custom dialog
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle("Update Your Information");
        dialog.setHeaderText("Update Your Information");

        // Apply CSS to the dialog
        dialog.getDialogPane().getStylesheets().add(Objects.requireNonNull(getClass().getResource("/Styles/Dark/channel-page.css")).toExternalForm());
        dialog.getDialogPane().getStyleClass().add("dialog-pane");

        // Set the button types
        ButtonType updateButtonType = new ButtonType("Update", ButtonType.OK.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

        // Create the labels and fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField fullNameField = new TextField();
        fullNameField.setText(user.getUsername());
        TextField usernameField = new TextField();
        usernameField.setText(user.getUsername());
        TextField emailField = new TextField();
        emailField.setText(user.getEmail());
        TextField passwordField = new TextField();
        passwordField.setText(user.getEmail());
        ImageView imageView =  new ImageView(avatar);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Button uploadButton = new Button("",imageView);
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
                return new User(emailField.getText(), usernameField.getText(), passwordField.getText());
            }
            return null;
        });

        // Show the dialog and update the user if the update button is clicked
        dialog.showAndWait().ifPresent(updatedUser -> {
            YouTubeApplication.user.setFullName(updatedUser.getFullName());
            YouTubeApplication.user.setUsername(updatedUser.getUsername());
            YouTubeApplication.user.setEmail(updatedUser.getEmail());
            YouTubeApplication.user.setPassword(updatedUser.getPassword());
        });
    }
    //endregion

    //endregion
}
