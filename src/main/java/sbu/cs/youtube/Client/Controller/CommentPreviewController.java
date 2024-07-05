package sbu.cs.youtube.Client.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sbu.cs.youtube.Shared.POJO.Comment;
import sbu.cs.youtube.Shared.POJO.UserComment;
import sbu.cs.youtube.Shared.POJO.UserVideo;
import sbu.cs.youtube.Shared.Request;
import sbu.cs.youtube.Shared.Response;
import sbu.cs.youtube.YouTubeApplication;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CommentPreviewController implements Initializable {

    //region [ - Fields - ]
    @FXML
    private Button btnDislike;

    @FXML
    private Button btnLike;

    @FXML
    private Button btnReplies;

    @FXML
    private Button btnReply;

    @FXML
    private SVGPath svgLike;

    @FXML
    private SVGPath svgDislike;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Text txtContent;

    @FXML
    private Text txtLikes;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtUsername;

    private Boolean hasLiked = null;
    private Comment comment;

    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        todo setComment
//        hasLiked = ....
//        comment = ...
    }
    //endregion

    //region [ - setComment(Comment comment) - ]
    public void setComment(Comment comment) {
        this.comment = comment;
        txtUsername.setText(comment.getSender().getUsername());
        txtContent.setText(comment.getContent());
        txtLikes.setText(String.valueOf(comment.getLikes()));

        //region [ - Time - ]
        LocalDateTime localDateTime = LocalDateTime.parse(comment.getDateCommented());
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.toSeconds();
        String time ;
        if (seconds < 60) {
            time = seconds + "s";
        } else if (minutes < 60) {
            time = minutes + "m";
        } else if (hours < 24) {
            time = hours + "h";
        } else if (days < 31) {
            time = days + "d";
        } else {
            time = days % 31 + "d";
        }
        txtTime.setText(time);
        //endregion

        //region [ - Avatar - ]
        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(comment.getSender().getAvatarBytes());
        Image videoThumbnail = new Image(bis);
        imgProfile.setImage(videoThumbnail);
        //endregion

    }
    //endregion

    //region [ - updateLike(ActionEvent event) - ]

    @FXML
    private void updateLike(ActionEvent event) {
        String filledLike = "M3,11h3v10H3V11z M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11v10h10.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z";
        String emptiedDislike = "M17,4h-1H6.57C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21 c0.58,0,1.14-0.24,1.52-0.65L17,14h4V4H17z M10.4,19.67C10.21,19.88,9.92,20,9.62,20c-0.26,0-0.5-0.11-0.63-0.3 c-0.07-0.1-0.15-0.26-0.09-0.47l1.52-4.94l0.4-1.29H9.46H5.23c-0.41,0-0.8-0.17-1.03-0.46c-0.12-0.15-0.25-0.4-0.18-0.72l1.34-6 C5.46,5.35,5.97,5,6.57,5H16v8.61L10.4,19.67z M20,13h-3V5h3V13z";
        String emptiedLike = "M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11H3v10h4h1h9.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z M7,20H4v-8h3V20z M19.98,13.17l-1.34,6 C18.54,19.65,18.03,20,17.43,20H8v-8.61l5.6-6.06C13.79,5.12,14.08,5,14.38,5c0.26,0,0.5,0.11,0.63,0.3 c0.07,0.1,0.15,0.26,0.09,0.47l-1.52,4.94L13.18,12h1.35h4.23c0.41,0,0.8,0.17,1.03,0.46C19.92,12.61,20.05,12.86,19.98,13.17z";


        if (hasLiked == null || !hasLiked) {
            svgLike.setContent(filledLike);
            svgDislike.setContent(emptiedDislike);
            txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) + 1));
            hasLiked = true;
        } else if (hasLiked) {
            svgLike.setContent(emptiedLike);
            txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) - 1));
            hasLiked = null;
        }

        Request<UserComment> userCommentRequest = new Request<>(YouTubeApplication.socket, "LikeComment");
        userCommentRequest.send(new UserComment(YouTubeApplication.user.getId(), comment.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<UserComment>> responseTypeToken = new TypeToken<>() {
        };
        Response<UserComment> userCommentResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(userCommentResponse.getMessage());

    }
    //endregion

    //region [ - updateDislike(ActionEvent event) - ]

    @FXML
    private void updateDislike(ActionEvent event) {
        String filledDislike = "M18,4h3v10h-3V4z M5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21c0.58,0,1.14-0.24,1.52-0.65L17,14V4H6.57 C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14z";
        String emptiedDislike = "M17,4h-1H6.57C5.5,4,4.59,4.67,4.38,5.61l-1.34,6C2.77,12.85,3.82,14,5.23,14h4.23l-1.52,4.94C7.62,19.97,8.46,21,9.62,21 c0.58,0,1.14-0.24,1.52-0.65L17,14h4V4H17z M10.4,19.67C10.21,19.88,9.92,20,9.62,20c-0.26,0-0.5-0.11-0.63-0.3 c-0.07-0.1-0.15-0.26-0.09-0.47l1.52-4.94l0.4-1.29H9.46H5.23c-0.41,0-0.8-0.17-1.03-0.46c-0.12-0.15-0.25-0.4-0.18-0.72l1.34-6 C5.46,5.35,5.97,5,6.57,5H16v8.61L10.4,19.67z M20,13h-3V5h3V13z";
        String emptiedLike = "M18.77,11h-4.23l1.52-4.94C16.38,5.03,15.54,4,14.38,4c-0.58,0-1.14,0.24-1.52,0.65L7,11H3v10h4h1h9.43 c1.06,0,1.98-0.67,2.19-1.61l1.34-6C21.23,12.15,20.18,11,18.77,11z M7,20H4v-8h3V20z M19.98,13.17l-1.34,6 C18.54,19.65,18.03,20,17.43,20H8v-8.61l5.6-6.06C13.79,5.12,14.08,5,14.38,5c0.26,0,0.5,0.11,0.63,0.3 c0.07,0.1,0.15,0.26,0.09,0.47l-1.52,4.94L13.18,12h1.35h4.23c0.41,0,0.8,0.17,1.03,0.46C19.92,12.61,20.05,12.86,19.98,13.17z";

        if (hasLiked == null || hasLiked) {
            svgLike.setContent(emptiedLike);
            svgDislike.setContent(filledDislike);
            if (hasLiked != null && hasLiked)
                txtLikes.setText(String.valueOf(Integer.parseInt(txtLikes.getText()) - 1));
            hasLiked = false;
        } else if (!hasLiked) {
            svgDislike.setContent(emptiedDislike);
            hasLiked = null;
        }

        Request<UserComment> userCommentRequest = new Request<>(YouTubeApplication.socket, "DislikeComment");
        userCommentRequest.send(new UserComment(YouTubeApplication.user.getId(), comment.getId()));

        String response = YouTubeApplication.receiveResponse();
        Gson gson = new Gson();
        TypeToken<Response<UserComment>> responseTypeToken = new TypeToken<>() {
        };
        Response<UserComment> userCommentResponse = gson.fromJson(response, responseTypeToken.getType());
        System.out.println(userCommentResponse.getMessage());
    }
    //endregion

    //endregion

}
