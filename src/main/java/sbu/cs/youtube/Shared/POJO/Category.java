package sbu.cs.youtube.Shared.POJO;

import java.util.ArrayList;
import java.util.UUID;

public class Category {
    private UUID Id ;
    private String title ;
    private ArrayList<VideoCategory> videoCategories;

    public Category() {
        Id = UUID.randomUUID();
    }

    public Category(String title) {
        Id = UUID.randomUUID();
        this.title = title;
    }

    public Category(UUID id, String title) {
        this.Id = id;
        this.title = title;
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

    public ArrayList<VideoCategory> getVideoCategories() {
        return videoCategories;
    }

    public void setVideoCategories(ArrayList<VideoCategory> videoCategories) {
        this.videoCategories = videoCategories;
    }
}
