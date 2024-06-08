package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class UserVideo {
    private Video video;
    private UUID videoId;
    private User user;
    private UUID userId;
    private Boolean like;

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public UserVideo() {
    }

    public UserVideo(UUID videoId, UUID userId) {
        this.videoId = videoId;
        this.userId = userId;

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
}
