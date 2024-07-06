package sbu.cs.youtube.Shared.POJO;

import java.util.ArrayList;
import java.util.UUID;

public class Channel {
    private UUID Id;
    private User creator;
    private UUID creatorId;
    private String title;
    private String description;
    private String profilePath;
    private byte[] profileBytes;
    private String dateCreated;
    private ArrayList<Subscription> subscriptions;
    private int subscriberCount;
    private int videoCounts;

    public Channel() {
        subscriptions = new ArrayList<>();
    }
    public Channel(UUID creatorId, String title) {
        Id = UUID.randomUUID();
        this.creatorId = creatorId;
        this.title = title;
        subscriptions = new ArrayList<>();
    }

    public Channel(UUID id) {
        this.Id = id ;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public UUID getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(UUID creatorId) {
        this.creatorId = creatorId;
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

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public byte[] getProfileBytes() {
        return profileBytes;
    }

    public void setProfileBytes(byte[] profileBytes) {
        this.profileBytes = profileBytes;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public int getVideoCounts() {
        return videoCounts;
    }

    public void setVideoCounts(int videoCounts) {
        this.videoCounts = videoCounts;
    }
}
