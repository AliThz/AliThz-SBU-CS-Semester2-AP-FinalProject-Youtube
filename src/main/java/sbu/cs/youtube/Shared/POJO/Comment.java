package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Comment {
    private UUID Id;
    private String content;
    private Video video;
    private UUID videoId;
    private User sender;
    private UUID senderId;
    private Comment parentComment;
    private UUID parentCommentId;
    private String dateCommented;
    private ArrayList<UserComment> viewers;
    private int likes = 0 ;
    private int dislikes = 0 ;

    public Comment() {
        Id = UUID.randomUUID();
        viewers = new ArrayList<>();
        parentComment = null;
    }


    public Comment(String content, UUID videoId, UUID senderId, UUID parentCommentId) {
        Id = UUID.randomUUID();
        this.content = content;
        this.videoId = videoId;
        this.senderId = senderId;
        this.parentCommentId = parentCommentId;
        viewers = new ArrayList<>();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public UUID getVideoId() {
        return videoId;
    }

    public void setVideoId(UUID videoId) {
        this.videoId = videoId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public UUID getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(UUID parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getDateCommented() {
        return dateCommented;
    }

    public void setDateCommented(String dateCommented) {
        this.dateCommented = dateCommented;
    }
    public ArrayList<UserComment> getViewers() {
        return viewers;
    }

    public void setViewers(ArrayList<UserComment> viewers) {
        this.viewers = viewers;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
