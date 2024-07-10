package sbu.cs.youtube.Shared.POJO;

import java.util.UUID;

public class VideoCategory {
    private Video video;
    private UUID videoId;
    private Category category;
    private UUID categoryId;

    public VideoCategory() {
    }

    public VideoCategory(UUID videoId, UUID categoryId) {
        this.videoId = videoId;
        this.categoryId = categoryId;
    }

    public VideoCategory(UUID categoryId) {
        this.categoryId = categoryId;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
