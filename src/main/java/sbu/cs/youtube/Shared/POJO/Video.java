package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Video {
    private UUID Id;
    private String title;
    private String description;
    private Channel channel;
    private UUID channelId;
    private int views;
    private LocalDateTime uploadDate;
    private ArrayList<VideoCategory> categories;
    private ArrayList<VideoLike> videoLikes;
    private ArrayList<Comment> comments;

    public Video() {
        this.categories = new ArrayList<>();
        this.videoLikes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public UUID getChannelId() {
        return channelId;
    }

    public void setChannelId(UUID channelId) {
        this.channelId = channelId;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public ArrayList<VideoCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<VideoCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<VideoLike> getVideoLikes() {
        return videoLikes;
    }

    public void setVideoLikes(ArrayList<VideoLike> videoLikes) {
        this.videoLikes = videoLikes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}