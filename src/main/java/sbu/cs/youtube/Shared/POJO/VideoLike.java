package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class VideoLike {
    private UUID Id;
    private Video video;
    private UUID videoId;
    private User user;
    private UUID userId;


    public VideoLike() {
        Id = UUID.randomUUID();
    }

    public VideoLike(UUID videoId, UUID userId) {
        Id = UUID.randomUUID();
        this.videoId = videoId;
        this.userId = userId;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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
