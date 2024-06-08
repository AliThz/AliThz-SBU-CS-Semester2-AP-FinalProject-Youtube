package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Playlist {
    private UUID Id ;
    private String thumbnailPath ;
    private String title ;
    private String description ;

//    private Thumnail thumnail ;
    private User creator ;
    private UUID creatorId ;
    private boolean isPublic ;
    private LocalDateTime dateCreated ;
    private ArrayList<PlaylistDetail> playlistDetails;

    public Playlist() {
        Id = UUID.randomUUID();
        this.playlistDetails = new ArrayList<>();
    }

    public Playlist(String title, String description, UUID creatorId, boolean isPublic) {
        Id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.isPublic = isPublic;
        this.playlistDetails = new ArrayList<>();
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ArrayList<PlaylistDetail> getPlaylistDetails() {
        return playlistDetails;
    }

    public void setPlaylistDetails(ArrayList<PlaylistDetail> playlistDetails) {
        this.playlistDetails = playlistDetails;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }
}
