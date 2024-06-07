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
    private LocalDateTime dateSent ;

    public Notification() {
        Id = UUID.randomUUID();
    }

    public Notification(String message) {
        Id = UUID.randomUUID();
        this.message = message;
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

    public LocalDateTime getDateSent() {
        return dateSent;
    }

    public void setDateSent(LocalDateTime dateSent) {
        this.dateSent = dateSent;
    }
}
