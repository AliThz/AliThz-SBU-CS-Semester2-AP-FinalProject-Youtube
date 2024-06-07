package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class CommentLike {
    private UUID Id;
    private User user;
    private UUID userId;
    private Comment comment;
    private UUID commentId;

    public CommentLike() {
        Id = UUID.randomUUID();
    }

    public CommentLike(UUID commentId, UUID userId) {
        Id = UUID.randomUUID();
        this.commentId = commentId;
        this.userId = userId;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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
