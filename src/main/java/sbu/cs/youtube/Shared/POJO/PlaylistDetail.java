package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class PlaylistDetail {
    private Playlist playlist;
    private UUID playlistId;
    private Video video;
    private UUID videoId;
    private String dateAdded;
    private int number;

    public PlaylistDetail() {
    }

    public PlaylistDetail(UUID playlistId, UUID videoId) {
        this.playlistId = playlistId;
        this.videoId = videoId;
    }

    public PlaylistDetail(UUID playlistId, UUID videoId, Video video) {
        this.playlistId = playlistId;
        this.videoId = videoId;
        this.video = video;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public UUID getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(UUID playlistId) {
        this.playlistId = playlistId;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
