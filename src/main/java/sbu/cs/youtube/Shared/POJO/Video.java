package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Video {
    private UUID Id;
    private String title;
    private transient String thumbnailPath ;
    private byte[] thumbnailBytes;
    private String description;
    private Channel channel;
    private UUID channelId;
    private int views;
    private String uploadDate;
    private ArrayList<VideoCategory> categories;
    private ArrayList<UserVideo> viewers;
    private ArrayList<Comment> comments;
    private int likes = 0 ;
    private int dislikes = 0 ;

    public Video() {
        Id = UUID.randomUUID();
        this.categories = new ArrayList<>();
        this.viewers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Video(String title, String description) {
        Id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.categories = new ArrayList<>();
        this.viewers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Video(String title, String description, UUID channelId, int views, String uploadDate) {
        Id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.channelId = channelId;
        this.views = views;
        this.uploadDate = uploadDate;
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

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public ArrayList<VideoCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<VideoCategory> categories) {
        this.categories = categories;
    }

    public ArrayList<UserVideo> getViewers() {
        return viewers;
    }

    public void setViewers(ArrayList<UserVideo> viewers) {
        this.viewers = viewers;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public byte[] getThumbnailBytes() {
        return thumbnailBytes;
    }

    public void setThumbnailBytes(byte[] thumbnailBytes) {
        this.thumbnailBytes = thumbnailBytes;
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