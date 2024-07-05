package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class Video {
    private UUID Id;
    private String title;
    private transient String thumbnailPath ;
    private byte[] thumbnailBytes;
    private transient String path;
    private byte[] videoBytes ;
    private String description;
    private Channel channel;
    private UUID channelId;
    private String uploadDate;
    private ArrayList<VideoCategory> categories;
    private ArrayList<UserVideo> viewers;
    private ArrayList<Comment> comments;
    private int viewcount;
    private int likes;
    private int dislikes;
    private String fileName ;

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

    public Video(String title, String description, UUID channelId, int viewcount, String uploadDate) {
        Id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.channelId = channelId;
        this.viewcount = viewcount;
        this.uploadDate = uploadDate;
    }

    public Video(UUID id) {
        Id = id;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public byte[] getVideoBytes() {
        return videoBytes;
    }

    public void setVideoBytes(byte[] videoBytes) {
        this.videoBytes = videoBytes;
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

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}