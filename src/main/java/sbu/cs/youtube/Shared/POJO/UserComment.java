package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class UserComment {
    private User user;
    private UUID userId;
    private Comment comment;
    private UUID commentId;
    private Boolean like;

    public UserComment() {
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public UserComment(UUID userId,UUID commentId ) {
        this.commentId = commentId;
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public UUID getCommentId() {
        return commentId;
    }

    public void setCommentId(UUID commentId) {
        this.commentId = commentId;
    }
}
