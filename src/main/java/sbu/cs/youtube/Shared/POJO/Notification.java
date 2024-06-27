package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Notification {
    private UUID Id ;
    private User user ;
    private UUID userId ;
    private String message ;
    private boolean isRead ;
    private String dateSent ;

    public Notification() {
        Id = UUID.randomUUID();
    }

    public Notification(UUID userId, String message, boolean isRead, String dateSent) {
        Id = UUID.randomUUID();
        this.userId = userId;
        this.message = message;
        this.isRead = isRead;
        this.dateSent = dateSent;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getDateSent() {
        return dateSent;
    }

    public void setDateSent(String dateSent) {
        this.dateSent = dateSent;
    }
}
