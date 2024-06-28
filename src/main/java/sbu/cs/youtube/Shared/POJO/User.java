package sbu.cs.youtube.Shared.POJO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID Id;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private String avatarPath;
    private String username;
    private String password;
    private String joinDate;
    private ArrayList<Subscription> subscriptions;
    private ArrayList<Notification> notifications;
    private ArrayList<UserVideo> viewedVideos;
    private ArrayList<UserComment> viewedComments;


    public User() {
        Id = UUID.randomUUID();
        subscriptions = new ArrayList<>();
        notifications = new ArrayList<>();
        viewedVideos = new ArrayList<>();
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String fullName, String email, String username, String password, String dateOfBirth) {
        Id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        subscriptions = new ArrayList<>();
        notifications = new ArrayList<>();
        viewedVideos = new ArrayList<>();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(ArrayList<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<UserVideo> getViewedVideos() {
        return viewedVideos;
    }

    public void setViewedVideos(ArrayList<UserVideo> viewedVideos) {
        this.viewedVideos = viewedVideos;
    }
    public String getAvatarPath() {
        return avatarPath;
    }
    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public ArrayList<UserComment> getViewedComments() {
        return viewedComments;
    }

    public void setViewedComments(ArrayList<UserComment> viewedComments) {
        this.viewedComments = viewedComments;
    }
}
