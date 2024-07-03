package sbu.cs.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sbu.cs.youtube.Shared.POJO.Comment;

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
    private ImageView imgProfile;

    @FXML
    private Text txtContent;

    @FXML
    private Label txtLikes;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtUsername;
    //endregion

    //region [ - Methods - ]

    //region [ - initialize(URL location, ResourceBundle resources) - ]
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    //endregion

    //region [ - setComment(Comment comment) - ]
    public void setComment(Comment comment) {
        txtUsername.setText(comment.getSender().getUsername());
        txtContent.setText(comment.getContent());
        txtLikes.setText(String.valueOf(comment.getLikes()));

        //region [ - Time - ]
        LocalDateTime localDateTime = LocalDateTime.parse(comment.getDateCommented());
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        long days = duration.toDays();
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        long time ;
        if (seconds < 60) {
            time = seconds;
        } else if (minutes < 60) {
            time = seconds;
        } else if (hours < 24) {
            time = hours;
        } else if (days < 31) {
            time = days;
        } else {
            time = days % 31;
        }
        txtTime.setText(String.valueOf(time));
        //endregion

        //region [ - Avatar - ]
        ByteArrayInputStream bis;
        bis = new ByteArrayInputStream(comment.getSender().getAvatarBytes());
        Image videoThumbnail = new Image(bis);
        imgProfile.setImage(videoThumbnail);
       //endregion

    }
    //endregion

    //endregion

}
