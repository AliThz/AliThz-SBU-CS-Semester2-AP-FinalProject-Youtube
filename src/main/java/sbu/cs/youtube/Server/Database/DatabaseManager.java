package sbu.cs.youtube.Server.Database;

import sbu.cs.youtube.Shared.POJO.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class DatabaseManager {

    //region [ - Fields - ]
    private static final String URL = "jdbc:postgresql://localhost:5432/Youtube-Development";
    private static final String USER = "postgres";
    private static final String PASSWORD = "musketeers";

    //endregion
    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID());
//        Playlist playlist = selectPlaylist(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b"));
//        System.out.println(playlist.getDateCreated());
//        ------------------ Select Notification Test ------------------------
//        Notification notification = selectNotification(UUID.fromString("7b024880-3f65-418e-aace-995735f9d827"));
//        System.out.println(notification.getMessage());
//        ------------------- Channel Select Test --------------------------------------------------------------------------------------------------------------------
//        Channel channel = selectChannel(UUID.fromString("e0ce92d5-f8ff-40eb-8d46-31c7a19ecc1a"));
//        System.out.println(channel.getDateCreated());
//        -------------------- User select Test -----------------------------------------------------------------------------------------------------------------------
//        Subscription subscription = new Subscription(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"),UUID.fromString("e0ce92d5-f8ff-40eb-8d46-31c7a19ecc1a"));
//        insertSubscription(subscription);
//        Subscription subscription1 = new Subscription(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"),UUID.fromString("9c824884-139c-4deb-a0cb-c298dfe36efb"));
//        insertSubscription(subscription1);
//
//        Notification notification = new Notification(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"),"salam" , false , LocalDateTime.now());
//        insertNotification(notification);
//        Notification notification1 = new Notification(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"),"mahan" , false , LocalDateTime.now());
//        insertNotification(notification1);
//
//        UserVideo userVideo = new UserVideo(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"));
//        insertUserVideo(userVideo);
//        UserVideo userVideo1 = new UserVideo(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
//        insertUserVideo(userVideo1);
//
//        VideoCategory videoCategory = new VideoCategory(UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"),UUID.fromString("45a91717-6e6c-4c7a-a7d9-54b1d69ad1bd"));
//        insertVideoCategory(videoCategory);
//        VideoCategory videoCategory1 = new VideoCategory(UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"),UUID.fromString("45a91717-6e6c-4c7a-a7d9-54b1d69ad1bd"));
//        insertVideoCategory(videoCategory1);
//
//        UserComment userComment = new UserComment(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));
//        insertUserComment(userComment);
//        UserComment userComment1 = new UserComment(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("5a7ea6ba-3999-4fe3-8774-89600bebfe0e"));
//        insertUserComment(userComment1);
//
//        User user = selectUser(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        System.out.println(user.getId());
//        for (Subscription subscription2 : user.getSubscriptions()){
//            System.out.println(subscription2.getJoinDate());
//        }
//        System.out.println(user.getViewedVideos().size());
//        System.out.println(user.getNotifications().size());
//        System.out.println(user.getViewedComments().size());

//        ------------ Category --------------------

//        Category category = new Category("fun");
//        insertCategory(category);
//        Category category1 = new Category("game");
//        category1.setId(category.getId());
//        updateCategory(category1);
//        deleteCategory(category.getId());

//        ------------ Video --------------------
//
//        Video video = new Video("1", "1", channel.getId(), 4, LocalDateTime.now());
//
//        ArrayList<VideoCategory> videoCategories= new ArrayList<>() ;
//        VideoCategory videoCategory = new VideoCategory(video.getId() , UUID.fromString("3d967083-bdd6-4016-b412-b7a2d9576d28"));
//        videoCategories.add(videoCategory);
//        VideoCategory videoCategory1 = new VideoCategory(video.getId() , UUID.fromString("46e7d055-33c9-4a2f-b802-a5b0bb3332ec"));
//        videoCategories.add(videoCategory1);
//        video.setCategories(videoCategories);
//
//        insertVideo(video);
//
//        PlaylistDetail playlistDetail = new PlaylistDetail(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b") , video.getId());
//        insertPlaylistDetail(playlistDetail);
//        PlaylistDetail playlistDetail1 = new PlaylistDetail(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b") , video.getId());
//        insertPlaylistDetail(playlistDetail1);
//
//        UserVideo userVideo = new UserVideo( UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") , video.getId());
//        insertUserVideo(userVideo);
//        UserVideo userVideo1 = new UserVideo( UUID.fromString("13d11d94-e385-4e29-9c68-38d8c97a0429") , video.getId());
//        insertUserVideo(userVideo1);

//        deleteVideo(video.getId());

//        ------------ UserVideo ---------------

//        UserVideo userVideo = new UserVideo( UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") , UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"));
//        insertUserVideo(userVideo);
//        UserVideo userVideo1 = new UserVideo( UUID.fromString("13d11d94-e385-4e29-9c68-38d8c97a0429") , UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"));
//        insertUserVideo(userVideo1);
//        deleteUserVideo(UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"));
//        deleteUserVideo( UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") , UUID.fromString("be7d7d84-c089-4c71-8492-572627494875"));

//        ------------ Plaulist ---------------

//        Playlist playlist = new Playlist("1" , "1" , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , false);
//        insertPlaylist(playlist);
//        Playlist playlist1 = new Playlist("2" , "2" , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , false);
//        playlist1.setId(playlist.getId());
//
//        PlaylistDetail playlistDetail = new PlaylistDetail(playlist.getId() , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
//        insertPlaylistDetail(playlistDetail);
//        PlaylistDetail playlistDetail1 = new PlaylistDetail(playlist.getId(), UUID.fromString("015f254e-ca95-4bae-a8cd-1e3d6b4b69ee"));
//        insertPlaylistDetail(playlistDetail1);
//
//        deletePlaylist(playlist.getId());

//        ------------ PlaulistDatinl ---------------

//        PlaylistDetail playlistDetail = new PlaylistDetail(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b") , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
//        insertPlaylistDetail(playlistDetail);
//        PlaylistDetail playlistDetail1 = new PlaylistDetail(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b") , UUID.fromString("015f254e-ca95-4bae-a8cd-1e3d6b4b69ee"));
//        insertPlaylistDetail(playlistDetail1);
//        PlaylistDetail playlistDetail2 = new PlaylistDetail(UUID.fromString("2a88cea5-ee25-48f4-aad4-afe49a25739f") , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
//        insertPlaylistDetail(playlistDetail2);
//        PlaylistDetail playlistDetail3 = new PlaylistDetail(UUID.fromString("2a88cea5-ee25-48f4-aad4-afe49a25739f") , UUID.fromString("015f254e-ca95-4bae-a8cd-1e3d6b4b69ee"));
//        insertPlaylistDetail(playlistDetail3);
//        deletePlaylistDetails(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b"));
//        deletePlaylistDetail(UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
//        deletePlaylistDetail(UUID.fromString("05b6fd7d-279c-4cd2-8374-b4a8fdd63e1b") , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));

//        -------------------- Comment --------------------

//        Comment comment = new Comment("5" , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7") , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , null);
//        insertComment(comment);
//        Comment comment1 = new Comment("lalala" , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7") , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , null);
//        comment1.setId(comment.getId());
//        updateComment(comment1);
//        deleteComment(comment1.getId());

//        ------------------- UserComment -----------------

//    UserComment userComment = new UserComment(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));
//    insertUserComment(userComment);
//    UserComment userComment1 = new UserComment(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));
//    insertUserComment(userComment1);
//    deleteUserComment(UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));
//    deleteUserComment(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb") ,UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));
    }

    //region [ - Methods - ]

    //region [ - User - ]

    //region [ - insertUser(User user) - ]
    public void insertUser(User user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertUser)");

            stmt = c.prepareStatement("""
                    INSERT INTO UserManagement.User(\"Id\", FullName, Email, DateOfBirth, Username, \"Password\")
                    VALUES (?, ?, ?, ?, ?,?);
                    """);

            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setObject(4, LocalDateTime.parse(user.getDateOfBirth()));
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());

            stmt.executeUpdate();
            c.commit();


            insertChannel(new Channel(user.getId(), user.getUsername()));


            stmt.close();
            c.close();
            System.out.println("Operation done successfully (insertUser)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectUsersBriefly() - ]
    public ArrayList<User> selectUsersBriefly() {
        Connection c;
        Statement stmt;
        ArrayList<User> users = null;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserBriefly)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT "Id","Username", "Email", "Password" FROM UserManagement.User;
                    """);

            users = new ArrayList<>();
            while (rs.next()) {
                user = new User();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setEmail(rs.getString("Email"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                users.add(user);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserBriefly)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return users;
    }
    //endregion

    //region [ - selectUsers() - ]
    public ArrayList<User> selectUsers() {
        Connection c;
        Statement stmt;
        ArrayList<User> users = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUsers)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM UserManagement.User;
                    """);
            users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setSubscriptions(selectSubscriptions(user.getId()));
                user.setNotifications(selectNotifications(user.getId()));
                user.setViewedVideos(selectUserVideos(user.getId()));
                Timestamp timestamp = rs.getTimestamp("DateOfBirth");
                user.setDateOfBirth(timestamp.toLocalDateTime().toString().toString());
                timestamp = rs.getTimestamp("JoinDate");
                user.setDateOfBirth(timestamp.toLocalDateTime().toString().toString());
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                users.add(user);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUsers)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return users;
    }
    //endregion No

    //region [ - selectUser(UUID Id) - ] Tested
    public User selectUser(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUser)");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.User 
                    WHERE \"Id\" = ?
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            user = new User();
            if (rs.next()) {
                user.setId(Id);
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                Timestamp timestamp = rs.getTimestamp("DateOfBirth");
                user.setDateOfBirth(timestamp.toLocalDateTime().toString().toString());
                timestamp = rs.getTimestamp("JoinDate");
                user.setDateOfBirth(timestamp.toLocalDateTime().toString().toString());
                user.setSubscriptions(selectSubscriptions(user.getId()));
                user.setNotifications(selectNotifications(user.getId()));
                user.setViewedVideos(selectUserVideos(user.getId()));
                user.setViewedComments(selectUserComments(user.getId()));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectUser)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return user;
    }
    //endregion

    //region [ - selectUserBriefly(UUID Id) - ] Tested
    public User selectUserBriefly(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUser)");

            stmt = c.prepareStatement("""
                    SELECT "Id", "username", "fullname", "AvatarPath" FROM UserManagement.User 
                    WHERE "Id" = ?
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            user = new User();
            if (rs.next()) {
                user.setId(Id);
                user.setUsername(rs.getString("Username"));
                user.setFullName(rs.getString("FullName"));
                user.setAvatarPath(rs.getString("AvatarPath"));
                user.setAvatarBytes(convertImageToByteArray(user.getAvatarPath(), "jpg"));
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectUser)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return user;
    }
    //endregion

    //region [ - selectUserByUsername(String Username) - ]
    public User selectUserByUsername(String username) {
        Connection c;
        PreparedStatement stmt;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUser)");

            stmt = c.prepareStatement("""
                    SELECT "Id", "fullname", "username", "email", "Password" , "AvatarPath"
                    FROM UserManagement.User 
                    WHERE username = ?
                    """);
            stmt.setObject(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setAvatarPath(rs.getString("AvatarPath"));
                user.setAvatarBytes(convertImageToByteArray(user.getAvatarPath(), "jpg"));
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectUser)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return user;
    }
    //endregion

    //region [ - selectUserByEmail(String email) - ]
    public User selectUserByEmail(String email) {
        Connection c;
        PreparedStatement stmt;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUser)");

            stmt = c.prepareStatement("""
                    SELECT "Id", "fullname", "username", "email", "Password","AvatarPath" 
                    FROM UserManagement.User 
                    WHERE email = ?
                    """);
            stmt.setObject(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("Password"));
                user.setAvatarPath(rs.getString("AvatarPath"));
                user.setAvatarBytes(convertImageToByteArray(user.getAvatarPath(), "jpg"));
            }
            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectUser)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return user;
    }
    //endregion

    //region [ - updateUser(User user) - ] Tested
    public void updateUser(User user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUser)");

            stmt = c.prepareStatement("""
                    UPDATE UserManagement.User
                    SET fullname = ?, email = ?, DateOfBirth = ?, Username = ?, \"Password\" = ?, AvatarPath = ?
                    WHERE \"Id\" = ?;
                    """);

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setObject(3, LocalDateTime.parse(user.getDateOfBirth()));
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setObject(6, user.getAvatarPath());
            stmt.setObject(7, user.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updateUser)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - deleteUser(UUID Id) - ] Not Exist
    public void deleteUser(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUser)");

            stmt = c.prepareStatement("""
                    DELETE FROM UserManagement.User WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteUser)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Channel - ]

    //region [ - insertChannel(Channel channel) - ] Tested
    public void insertChannel(Channel channel) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertChannel)");
            stmt = c.prepareStatement("""
                    INSERT INTO UserManagement.Channel(\"Id\", CreatorId, Title, Description) 
                    VALUES (?, ?, ?, ?);
                    """);
            stmt.setObject(1, channel.getId());
            stmt.setObject(2, channel.getCreatorId());
            stmt.setString(3, channel.getTitle());
            stmt.setString(4, channel.getDescription());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertChannel)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - ArrayList<Channel> selectChannels() - ] Not Tested
    public ArrayList<Channel> selectChannels() {
        Connection c;
        Statement stmt;
        ArrayList<Channel> channels = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectChannels(ALL))");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM UserManagement.Channel;
                    """);
            channels = new ArrayList<>();
            while (rs.next()) {
                Channel channel = new Channel();
                channel.setId(UUID.fromString(rs.getString("Id")));
                channel.setCreatorId(UUID.fromString(rs.getString("Id")));
                channel.setCreator(selectUser(channel.getCreatorId()));
                channel.setTitle(rs.getString("Title"));
                channel.setDescription(rs.getString("Description"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateCreated"));
                channel.setDateCreated(timestamp.toLocalDateTime().toString().toString());
                channels.add(channel);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectChannels(ALL))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return channels;
    }
    //endregion

    //region [ - Channel selectChannelBriefly(UUID Id) - ] UnUsed
    public Channel selectChannelBriefly(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Channel channel = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectChannelBriefly)");

//                    SELECT "Title", "Description" , "CreatorId" FROM UserManagement.Channel
            stmt = c.prepareStatement("""
                    SELECT "creatorid", "title", "description", "ProfilePath"
                    FROM UserManagement.Channel 
                    WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            channel = new Channel();

            if (rs.next()) {
                channel.setId(Id);
                channel.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
                channel.setCreator(selectUserBriefly(channel.getCreatorId()));
                channel.setTitle(rs.getString("Title"));
                channel.setDescription(rs.getString("Description"));
                channel.setProfilePath(rs.getString("ProfilePath"));
                channel.setProfileBytes(convertImageToByteArray(channel.getCreator().getAvatarPath(), "jpg"));
            }

//            stmt = c.prepareStatement("""
//                    SELECT COUNT(UserId) AS Subscribers
//                    FROM UserManagement.Subscription
//                    WHERE ChannelId = ?;
//                    """);
//
//            stmt.setObject(1, Id);
//            rs = stmt.executeQuery();
//            channel.setSubscribers(rs.getInt("Subscribers"));

            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectChannelBriefly)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return channel;
    }
    //endregion

    //region [ - selectChannel(UUID Id) - ] Tested
    public Channel selectChannel(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Channel channel = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectChannel)");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Channel 
                    WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            channel = new Channel();

            if (rs.next()) {
                channel.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
                channel.setTitle(rs.getString("Title"));
                channel.setDescription(rs.getString("Description"));
                channel.setId(UUID.fromString(rs.getString("Id")));
                if (rs.getString("DateCreated") != null) {
                    Timestamp timestamp = Timestamp.valueOf(rs.getString("DateCreated"));
                    channel.setDateCreated(timestamp.toLocalDateTime().toString());
                }
            }

            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectChannel)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return channel;
    }
    //endregion

    //region [ - updateChannel(Channel channel) - ] Tested
    public void updateChannel(Channel channel) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateChannel)");

            stmt = c.prepareStatement("""
                    UPDATE UserManagement.Channel SET CreatorId = ?, Title = ?, Description = ?, \"DateCreated\" = ?  
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, channel.getCreatorId());
            stmt.setString(2, channel.getTitle());
            stmt.setString(3, channel.getDescription());
            stmt.setObject(4, channel.getDateCreated());
            stmt.setObject(5, channel.getId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updateChannel)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - deleteChannel(UUID Id) - ] Not Exist
    public void deleteChannel(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteChannel)");

            stmt = c.prepareStatement("""
                    DELETE FROM UserManagement.Channel 
                    WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteChannel)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Subscription - ]

    //region [ - insertSubscription(Subscription subscription) - ] Tested
    public void insertSubscription(Subscription subscription) {

        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertSubscription)");

            stmt = c.prepareStatement("""
                    INSERT INTO UserManagement.Subscription(SubscriberId, channelId) 
                    VALUES (?, ?);
                    """);
            stmt.setObject(1, subscription.getSubscriberId());
            stmt.setObject(2, subscription.getChannelId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertSubscription)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectSubscriptions() - ] Not Test
    public ArrayList<Subscription> selectSubscriptions() {
        Connection c;
        Statement stmt;
        ArrayList<Subscription> subscriptions = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscriptions(ALL))");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM UserManagement.Subscription;
                    """);
            subscriptions = new ArrayList<>();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriberId(UUID.fromString(rs.getString("Id")));
                subscription.setChannelId(UUID.fromString(rs.getString("Id")));
                subscription.setSubscriber(selectUser(subscription.getSubscriberId()));
                subscription.setChannel(selectChannel(subscription.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("JoinDate"));
                subscription.setJoinDate(timestamp.toLocalDateTime().toString());
                subscriptions.add(subscription);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectSubscriptions(ALL))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return subscriptions;
    }
    //endregion

    //region [ - selectSubscriptions(UUID userId) - ] Test
    public ArrayList<Subscription> selectSubscriptions(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Subscription> subscriptions = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscriptions(base on UserId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Subscription WHERE SubscriberId = ?;
                    """);
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            subscriptions = new ArrayList<>();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriberId(UUID.fromString(rs.getString("SubscriberId")));
                subscription.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                subscription.setChannel(selectChannel(subscription.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("JoinDate"));
                subscription.setJoinDate(timestamp.toLocalDateTime().toString());
                subscriptions.add(subscription);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectSubscriptions(base on UserId))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return subscriptions;
    }
    //endregion

    //region [ - selectSubscription(UUID Id) - ] Not Exist
    public Subscription selectSubscription(UUID subscriberId, UUID channelId) {
        Connection c;
        PreparedStatement stmt;
        Subscription subscription = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscription)");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Subscription WHERE "subscriberid" = ? AND "channelid" = ? 
                    """);
            stmt.setObject(1, subscriberId);
            stmt.setObject(2, channelId);
            ResultSet rs = stmt.executeQuery();
            subscription = new Subscription();

            if (rs.next()) {
                subscription.setSubscriberId(UUID.fromString(rs.getString("subscriberid")));
                subscription.setChannelId(UUID.fromString(rs.getString("channelid")));
//                subscription.setSubscriber(selectUser(subscription.getSubscriberId()));
                subscription.setChannel(selectChannel(subscription.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("JoinDate"));
                subscription.setJoinDate(timestamp.toLocalDateTime().toString());
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectSubscription)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return subscription;
    }
    //endregion

    //region [ - subscriptionExistence - ]

    public Subscription subscriptionExistence(UUID subscriberId, UUID channelId) {
        Connection c;
        PreparedStatement stmt;
        Subscription subscription = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscription)");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Subscription WHERE "subscriberid" = ? AND "channelid" = ? 
                    """);
            stmt.setObject(1, subscriberId);
            stmt.setObject(2, channelId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                subscription = new Subscription();
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectSubscription)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return subscription;
    }

    //endregion

    //region [ - deleteSubscription(UUID SubscriberId, UUID channelId) - ] Tested
    public void deleteSubscription(UUID SubscriberId, UUID channelId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteSubscription)");

            stmt = c.prepareStatement("""
                    DELETE FROM UserManagement.Subscription WHERE SubscriberId = ? AND channelId = ?;
                    """);
            stmt.setObject(1, SubscriberId);
            stmt.setObject(2, channelId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteSubscription)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Notification - ]

    //region [ - insertNotification(Notification notification) - ] Tested
    public void insertNotification(Notification notification) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertNotification)");

            stmt = c.prepareStatement("""                 
                    INSERT INTO UserManagement.Notification(\"Id\", UserId, \"Message\", IsRead, dateSent) VALUES (?, ?, ?, ?, ?);
                    """);

            stmt.setObject(1, notification.getId());
            stmt.setObject(2, notification.getUserId());
            stmt.setString(3, notification.getMessage());
            stmt.setBoolean(4, notification.isRead());
            stmt.setObject(5, notification.getDateSent());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertNotification)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - ArrayList<Notification> selectNotifications() - ] Not test
    public ArrayList<Notification> selectNotifications() { //
        Connection c;
        Statement stmt;
        ArrayList<Notification> notifications = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectNotifications(ALL))");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserManagement.Notification;");
            notifications = new ArrayList<>();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(UUID.fromString(rs.getString("Id")));
                notification.setUserId(UUID.fromString(rs.getString("UserId")));
                notification.setUser(selectUser(notification.getUserId()));
                notification.setMessage(rs.getString("Message"));
                notification.setRead(Boolean.getBoolean(rs.getString("Description")));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateSent"));
                notification.setDateSent(timestamp.toLocalDateTime().toString().toString());
                notifications.add(notification);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectNotifications)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return notifications;
    }
    //endregion

    //region [ - ArrayList<Notification> selectNotifications(UUID userId) - ] test
    public ArrayList<Notification> selectNotifications(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Notification> notifications = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectNotifications(base on userId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Notification WHERE UserId = ?;
                    """);

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            notifications = new ArrayList<>();
            while (rs.next()) {
                Notification notification = new Notification();

                notification.setId(UUID.fromString(rs.getString("Id")));
                notification.setUserId(UUID.fromString(rs.getString("UserId")));
//                notification.setUser(selectUser(notification.getUserId()));
                notification.setMessage(rs.getString("Message"));
                notification.setRead(Boolean.getBoolean(rs.getString("isRead")));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateSent"));
                notification.setDateSent(timestamp.toLocalDateTime().toString().toString());

                notifications.add(notification);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (base on userId)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return notifications;
    }
    //endregion

    //region [ - Notification selectNotification(UUID Id) - ] Test
    public Notification selectNotification(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Notification notification = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectNotification)");

            stmt = c.prepareStatement("""
                    SELECT * FROM UserManagement.Notification WHERE \"Id\" = ?
                    """);

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            notification = new Notification();
            if (rs.next()) {
                notification.setId(UUID.fromString(rs.getString("Id")));
                notification.setUserId(UUID.fromString(rs.getString("UserId")));
//                notification.setUser(selectUser(notification.getUserId()));
                notification.setMessage(rs.getString("Message"));
                notification.setRead(Boolean.getBoolean(rs.getString("isRead")));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateSent"));
                notification.setDateSent(timestamp.toLocalDateTime().toString().toString());
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectNotification)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return notification;
    }
    //endregion

    //region [ - updateNotification(Notification notification) - ] Tested
    public void updateNotification(Notification notification) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateNotification)");

            stmt = c.prepareStatement("""
                    UPDATE UserManagement.Notification SET IsRead = ?  
                    WHERE \"Id\" = ?;
                    """);

            stmt.setBoolean(1, notification.isRead());
            stmt.setObject(2, notification.getId());

            stmt.executeUpdate();

            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updateNotification)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - deleteNotification(UUID Id) - ] Tested
    public void deleteNotification(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteNotification)");

            stmt = c.prepareStatement("""
                    DELETE FROM UserManagement.Notification 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

            System.out.println("Operation done successfully (deleteChannel)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Category - ]

    //region [ - insertCategory(Category category) - ] Tested
    public void insertCategory(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertCategory)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.Category(\"Id\", Title) 
                    VALUES (?, ?);
                    """);

            stmt.setObject(1, category.getId());
            stmt.setString(2, category.getTitle());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (insertCategory)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectCategories() - ] Not Tested
    public ArrayList<Category> selectCategories() {
        Connection c;
        Statement stmt;
        ArrayList<Category> categories = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCategories)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM ContentManagement.Category;
                    """);
            categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();
                category.setId(UUID.fromString(rs.getString("Id")));
                category.setTitle(rs.getString("Title"));
                category.setVideoCategories(selectCategoryVideos(category.getId()));
                categories.add(category);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectCategories)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return categories;
    }
    //endregion

    //region [ - selectCategory(UUID Id) - ] Tested
    public Category selectCategory(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Category category = null;
        VideoCategory videoCategory = null;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCategory)");

            stmt = c.prepareStatement("""
                    SELECT * 
                    FROM ContentManagement.Category 
                    WHERE \"Id\" = ?
                    """);

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            category = new Category();

            if (rs.next()) {
                category.setId(UUID.fromString(rs.getString("Id")));
                category.setTitle(rs.getString("Title"));
                category.setVideoCategories(selectCategoryVideos(category.getId()));
            }

            rs.close();
            stmt.close();

            stmt = c.prepareStatement("""
                    SELECT vc.CategoryId,pd."videoId, v.Title, v.Description, v.ChannelId , v."UploadDate" , v.ThumbnailPath 
                    FROM ContentManagement.VideoCategory vc
                    INNER JOIN ContentManagment.Video v ON vc.VideoId = v."Id"
                    WHERE pd.CategoryId = ? ;   
                    """);
            stmt.setObject(1, Id);
            rs = stmt.executeQuery();

            videoCategories = new ArrayList<>();
            while (rs.next()) {
                videoCategory = new VideoCategory();
                videoCategory.setCategory(category);
                videoCategory.setCategoryId(Id);
                videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("VideoId")));
                video.setTitle(rs.getString("Title"));
                video.setViewers(selectUserVideos(Id));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                videoCategory.setVideo(video);
                videoCategories.add(videoCategory);
            }
            category.setVideoCategories(videoCategories);

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectCategory)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return category;
    }
    //endregion

    //region [ - updateCategory(Category category) - ] Tested
    public void updateCategory(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateCategory)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.Category SET Title = ? 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setString(1, category.getTitle());
            stmt.setObject(2, category.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updateCategory)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - deleteCategory(UUID Id) - ] Not Exist
    public void deleteCategory(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteCategory)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.Category WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteCategory)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Video - ] To think

    //region [ - insertVideo(Video video) - ] Tested
    public void insertVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideo)");


            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.Video(\"Id\", Title, Description, ChannelId , \"UploadDate\" ) 
                    VALUES (?, ?, ?, ?, ?, ?);
                    """);

            stmt.setObject(1, video.getId());
            stmt.setString(2, video.getTitle());
            stmt.setString(3, video.getDescription());
            stmt.setObject(4, video.getChannelId());
            stmt.setObject(5, video.getUploadDate());

            stmt.executeUpdate();
            c.commit();

            if (video.getCategories() != null) {
                for (var videoCategory : video.getCategories()) {
                    insertVideoCategory(videoCategory);
                }
            }

            stmt.close();
            System.out.println("Operation done successfully (insertVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectVideos() - ] Not Tested
    public ArrayList<Video> selectVideos() {
        Connection c;
        Statement stmt;
        PreparedStatement stmt1;
        ArrayList<Video> videos = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideos)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(
                    """
                            SELECT * FROM ContentManagement.Video;
                            """);

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
                video.setCategories(selectVideoCategories(video.getId()));
                video.setViewers(selectUserVideos(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannel(video.getChannelId()));
                video.setComments(selectComments(video.getId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDateTime"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
                //TODO
//                ---------------------------- what should i do for handle likes and dislike in this method -------------------
//                stmt = c.prepareStatement("""
//                    SELECT COUNT(UserId) AS VideoLikes
//                    FROM ContentManagement.PlaylistDetail
//                    WHERE VideoId = video.getId() AND Like = true;
//                    """);
//                stmt.setObject(1, video.getId());
//                rs = stmt.executeQuery();
//                video.setLikes(rs.getInt("VideosLikes"));
//
//                stmt = c.prepareStatement("""
//                    SELECT COUNT(UserId) AS VideoDisLikes
//                    FROM ContentManagement.PlaylistDetail
//                    WHERE VideoId = ? AND Like = false;
//                    """);
//                stmt.setObject(1, Id);
//                rs = stmt.executeQuery();
//                video.setDislikes(rs.getInt("VideosDislikes"));


            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideos)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion

    //region [ - selectVideosBriefly() - ] Not Tested
    public ArrayList<Video> selectVideosBriefly() {
        Connection c;
        Statement stmt;
        PreparedStatement stmt1;
        ArrayList<Video> videos = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideosBriefly)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT *  FROM ContentManagement.Video;
                    """);

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideosBriefly)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion

    //region [ - selectVideosByChannel(UUID channelId) - ] Not test
    public ArrayList<Video> selectVideosByChannel(UUID channelId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Video> videos = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoBriefly)");

            stmt = c.prepareStatement("""
                    SELECT "title" , "Id" , "UploadDate" , "thumbnailpath" , "description"   
                    FROM ContentManagement.Video 
                    WHERE \"channelid\" = ?;
                    """)
            ;

            stmt.setObject(1, channelId);
            ResultSet rs = stmt.executeQuery();

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
                video.setChannelId(channelId);
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoBriefly)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion

    //region [ - selectVideosByCreator(UUID creatorId) - ] Not test
    public ArrayList<Video> selectVideosByCreator(UUID creatorId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Video> videos = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideosByCreator)");

            stmt = c.prepareStatement("""
                    SELECT v."title" , v."Id" , v."UploadDate" , v."ThumbnailPath" , v."description" , v."channelid"\s
                    FROM ContentManagement.Video v INNER JOIN UserManagement.Channel c
                    ON v."channelid" = c."Id"
                    WHERE "creatorid" = ?;
                    """);

            stmt.setObject(1, creatorId);
            ResultSet rs = stmt.executeQuery();

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("channelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideosByCreator)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion


    //region [ - selectLikedVideos (UUID userId) - ]
    public ArrayList<Video> selectLikedVideos(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Video> videos = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectLikedVideos)");

            stmt = c.prepareStatement("""
                    SELECT v."title" , v."Id" , v."UploadDate" , v."ThumbnailPath" , v."description" ,v."channelid" ,uv."videoid"
                    FROM ContentManagement.Video v JOIN ContentManagement.UserVideo uv
                    ON v."Id" = uv."videoid"\s
                    WHERE uv.userid = ? AND uv."Like" = true;
                    """);

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("channelid")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectLikedVideos)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion

    //region [ - selectHistory (UUID userId) - ]
    public ArrayList<Video> selectHistory(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Video> videos = null;
        try {
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (select history)");

            stmt = c.prepareStatement("""
                    SELECT v."title" , v."Id" , v."UploadDate" , v."ThumbnailPath" , v."description" ,v."channelid" ,uv."videoid"
                    FROM ContentManagement.Video v JOIN ContentManagement.UserVideo uv
                    ON v."Id" = uv."videoid"
                    WHERE uv.userid = ?;
                    """);

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();

            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("channelid")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                videos.add(video);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (select history)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videos;
    }
    //endregion

    //region [ - selectVideoBriefly(UUID Id) - ] Not test
    public Video selectVideoBriefly(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Video video = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoBriefly)");

            stmt = c.prepareStatement("""
                    SELECT "Title" , "ChannelId" , "UploadDate" , "ThumbnailPath" , "Description" 
                    FROM ContentManagement.Video 
                    WHERE \"Id\" = ?;
                    """)
            ;

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            video = new Video();
            if (rs.next()) {
                video.setId(Id);
                video.setTitle(rs.getString("Title"));
                video.setViewers(selectUserVideos(Id));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoBriefly)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return video;
    }
    //endregion

    //    region [ - selectVideo(UUID Id) - ] test
    public Video selectVideo(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Video video = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideo)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.Video 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            video = new Video();
            if (rs.next()) {
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
//                video.setCategories(selectVideoCategories(video.getId()));
//                video.setViewers(selectUserVideos(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                video.setPath(rs.getString("Path"));
                video.setVideoBytes(convertVideoToByteArray(video.getPath()));
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return video;
    }
    //endregion

    //region [ - updateVideo(Video video) - ] Tested
    public void updateVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateVideo)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.Video SET Title = ?, Description = ? 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getDescription());
            stmt.setObject(3, video.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updateVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //    region [ - deleteVideo(UUID videoid) - ] YES
    public void deleteVideo(UUID videoid) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideo)");

//            for (var comment : selectComments(videoid)) {
//                deleteComment(comment.getId());
//            }
            deletePlaylistDetail(videoid);
            deleteVideoCategory(videoid);
            deleteUserVideo(videoid);

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.Video 
                    WHERE \"Id\" = ?;
                    """);
            stmt.setObject(1, videoid);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

            System.out.println("Operation done successfully (deleteVideo)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion /////////////////////

    //region [ - VideoCategory - ]

    //region [ - insertVideoCategory(VideoCategory videoCategory) - ] Tested
    public void insertVideoCategory(VideoCategory videoCategory) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideoCategory)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.VideoCategory(VideoId, CategoryId) 
                    VALUES (?, ?);
                    """);
            stmt.setObject(1, videoCategory.getVideoId());
            stmt.setObject(2, videoCategory.getCategoryId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertVideoCategory)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - ArrayList<VideoCategory> selectVideoCategories() - ] Not Tested
    public ArrayList<VideoCategory> selectVideoCategories() {
        Connection c;
        Statement stmt;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.VideoCategory;");
            videoCategories = new ArrayList<>();
            while (rs.next()) {
                VideoCategory videoCategory = new VideoCategory();
                videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
                videoCategory.setCategoryId(UUID.fromString(rs.getString("CategoryId")));
                videoCategory.setVideo(selectVideo(videoCategory.getVideoId()));
                videoCategory.setCategory(selectCategory(videoCategory.getCategoryId()));
                videoCategories.add(videoCategory);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoCategories(ALL))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videoCategories;
    }
    //endregion

    //region [ - ArrayList<VideoCategory> selectVideoCategories(UUID videoId) - ] Tested
    public ArrayList<VideoCategory> selectVideoCategories(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories (based on video))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.VideoCategory 
                    WHERE VideoId = ?;
                    """);

            stmt.setObject(1, videoId);
            ResultSet rs = stmt.executeQuery();

            videoCategories = new ArrayList<>();
            while (rs.next()) {
                VideoCategory videoCategory = new VideoCategory();
                videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
                videoCategory.setCategoryId(UUID.fromString(rs.getString("CategoryId")));
//                videoCategory.setVideo(selectVideo(videoCategory.getVideoId()));
//                videoCategory.setCategory(selectCategory(videoCategory.getCategoryId()));
                videoCategories.add(videoCategory);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoCategories (based on video))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videoCategories;
    }
    //endregion

    //region [ - ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) - ] Not Tested
    public ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories (selectVideoLikes(based on category))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.VideoCategory 
                    WHERE CategoryId = ?;
                    """);
            stmt.setObject(1, categoryId);
            ResultSet rs = stmt.executeQuery();
            videoCategories = new ArrayList<>();
            while (rs.next()) {
                VideoCategory videoCategory = new VideoCategory();
                videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
                videoCategory.setCategoryId(UUID.fromString(rs.getString("CategoryId")));
                videoCategory.setVideo(selectVideo(videoCategory.getVideoId()));
                videoCategory.setCategory(selectCategory(videoCategory.getCategoryId()));
                videoCategories.add(videoCategory);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoCategories (selectVideoLikes(based on category))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videoCategories;
    }
    //endregion

    //region [ - VideoCategory selectVideoCategory(UUID videoId ,UUID categoryId ) - ] Not Tested
    public VideoCategory selectVideoCategory(UUID videoId, UUID categoryId) {
        Connection c;
        PreparedStatement stmt;
        VideoCategory videoCategory = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategory)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.VideoCategory WHERE \"videoId\" = ? AND \"categoryId\" = ?;
                    """);

            stmt.setObject(1, videoId);
            stmt.setObject(2, categoryId);
            ResultSet rs = stmt.executeQuery();
            videoCategory = new VideoCategory();
            videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
            videoCategory.setCategoryId(UUID.fromString(rs.getString("CategoryId")));
            videoCategory.setVideo(selectVideo(videoCategory.getVideoId()));
            videoCategory.setCategory(selectCategory(videoCategory.getCategoryId()));

            rs.close();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (selectVideoCategory)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videoCategory;
    }
    //endregion

    //region [ - deleteVideoCategory(UUID videoId, UUID categoryId) - ] Tested
    public void deleteVideoCategory(UUID videoId, UUID categoryId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoCategory)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.VideoCategory 
                    WHERE VideoId = ? AND CategoryId = ?;""");

            stmt.setObject(1, videoId);
            stmt.setObject(2, categoryId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteVideoCategory)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deleteVideoCategory(UUID videoId) - ] Tested
    public void deleteVideoCategory(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoCategory (based on videoId))");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.VideoCategory 
                    WHERE VideoId = ?;
                    """);
            stmt.setObject(1, videoId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteVideoCategory (based on videoId))");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - UserVideo - ]

    //region [ - insertUserVideo (UserVideo UserVideo) - ] YES
    public void insertUserVideo(UserVideo userVideo) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertUserVideo)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.UserVideo(VideoId, UserId , \"Like\") 
                    VALUES (?, ?, ?);
                    """);
            stmt.setObject(1, userVideo.getVideoId());
            stmt.setObject(2, userVideo.getUserId());
            stmt.setObject(3, userVideo.getLike());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertUserVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectUserVideos() - ] Not Tested
    public ArrayList<UserVideo> selectUserVideos() {
        Connection c;
        Statement stmt;
        ArrayList<UserVideo> userVideos = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserVideo(ALL))");
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.UserVideo;");
            userVideos = new ArrayList<>();
            while (rs.next()) {
                UserVideo userVideo = new UserVideo();
                userVideo.setLike(rs.getBoolean("Like"));
                if (rs.wasNull()) {
                    userVideo.setLike(null);
                }
                userVideo.setVideoId(UUID.fromString(rs.getString("VideoId")));
                userVideo.setUserId(UUID.fromString(rs.getString("UserId")));
                userVideo.setVideo(selectVideo(userVideo.getVideoId()));
                userVideo.setUser(selectUser(userVideo.getUserId()));
                userVideos.add(userVideo);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserVideo(ALL))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userVideos;
    }
    //endregion

    //region [ - selectUserVideos(UUID userId) - ] Tested
    public ArrayList<UserVideo> selectUserVideos(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<UserVideo> userVideos = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserVideos(based on User))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserVideo 
                    WHERE UserId = ?;
                    """);

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            userVideos = new ArrayList<>();
            while (rs.next()) {
                UserVideo userVideo = new UserVideo();
                userVideo.setLike(rs.getBoolean("Like"));
                if (rs.wasNull()) {
                    userVideo.setLike(null);
                }
                userVideo.setVideoId(UUID.fromString(rs.getString("VideoId")));
                userVideo.setUserId(UUID.fromString(rs.getString("UserId")));
                userVideo.setVideo(selectVideo(userVideo.getVideoId()));
//                userVideo.setUser(selectUser(userVideo.getUserId()));
                userVideos.add(userVideo);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserVideos(based on User))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userVideos;
    }
    //endregion

    //region [ - selectVideoUsers(UUID userId) - ] Not Tested
    public ArrayList<UserVideo> selectVideoUsers(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<UserVideo> videoUsers = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserVideos(based on userId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserVideo 
                    WHERE VideoId = ?;
                    """);

            stmt.setObject(1, videoId);
            ResultSet rs = stmt.executeQuery();
            videoUsers = new ArrayList<>();
            while (rs.next()) {
                UserVideo userVideo = new UserVideo();
                userVideo.setLike(rs.getBoolean("Like"));
                if (rs.wasNull()) {
                    userVideo.setLike(null);
                }
                userVideo.setVideoId(UUID.fromString(rs.getString("VideoId")));
                userVideo.setUserId(UUID.fromString(rs.getString("UserId")));
                userVideo.setVideo(selectVideo(userVideo.getVideoId()));
//                userVideo.setUser(selectUser(userVideo.getUserId()));
                videoUsers.add(userVideo);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserVideos(based on userId))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return videoUsers;
    }
    //endregion

    //region [ - selectUserVideo(UUID userID , UUID videoId) - ] Not Test
    public UserVideo selectUserVideo(UUID userID, UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        UserVideo userVideo = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserVideo)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserVideo 
                    WHERE UserId = ? AND VideoId = ?;
                    """);

            stmt.setObject(1, userID);
            stmt.setObject(2, videoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userVideo = new UserVideo();
                userVideo.setLike(rs.getBoolean("Like"));
                if (rs.wasNull()) {
                    userVideo.setLike(null);
                }
                userVideo.setVideoId(videoId);
                userVideo.setUserId(userID);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userVideo;
    }
    //endregion

    //    region [ - selectVideoLikesStatus(UUID Id) - ] test
    public Video selectVideoLikesStatus(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Video video = new Video();
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (getVideoLikes)");

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS VideoLikes
                    FROM ContentManagement.UserVideo
                    WHERE videoid = ? AND \"Like\" = true;
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                video.setLikes(rs.getInt("VideoLikes"));
            }

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS VideoDislikes
                    FROM ContentManagement.UserVideo
                    WHERE videoid = ? AND \"Like\" = false;
                    """);

            stmt.setObject(1, Id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                video.setDislikes(rs.getInt("VideoDislikes"));
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return video;
    }
    //endregion

    //region [ - userVideoExistence(UUID userID , UUID videoId) - ] Not Test
    public UserVideo userVideoExistence(UUID userID, UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        UserVideo userVideo = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserVideo)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserVideo 
                    WHERE UserId = ? AND VideoId = ?;
                    """);

            stmt.setObject(1, userID);
            stmt.setObject(2, videoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userVideo.setLike(rs.getBoolean("Like"));
                if (rs.wasNull()) {
                    userVideo.setLike(null);
                }
                userVideo.setVideoId(videoId);
                userVideo.setUserId(userID);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserVideo)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userVideo;
    }
    //endregion

    //region [ - updateUserVideo(UserVideo userVideo) - ] YES
    public void updateUserVideo(UserVideo userVideo) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUserVideo)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.UserVideo
                    SET "Like" = ?
                    WHERE UserId = ? AND VideoId = ?;
                    """);
            stmt.setObject(1, userVideo.getLike());
            stmt.setObject(2, userVideo.getUserId());
            stmt.setObject(3, userVideo.getVideoId());
            stmt.executeUpdate();

            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (updateUserVideo)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deleteUserVideo(UUID userId , UUID videoId) - ] YES
    public void deleteUserVideo(UUID userId, UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUserVideo)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.UserVideo 
                    WHERE UserId = ? AND VideoId = ?;
                    """);

            stmt.setObject(1, userId);
            stmt.setObject(2, videoId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteUserVideo)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deleteUserVideo(UUID videoId) - ] Yes
    public void deleteUserVideo(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUserVideo (based on videoId))");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.UserVideo 
                    WHERE VideoId = ?;
                    """);

            stmt.setObject(1, videoId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteUserVideo (based on videoId))");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Playlist - ]

    //region [ - insertPlaylist(Playlist playlist) - ] Tested
    public void insertPlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylist)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.Playlist(\"Id\", Title, Description, CreatorId,IsPublic) 
                    VALUES (?, ?, ?, ?, ?);
                    """);

            stmt.setObject(1, playlist.getId());
            stmt.setString(2, playlist.getTitle());
            stmt.setString(3, playlist.getDescription());
            stmt.setObject(4, playlist.getCreatorId());
            stmt.setBoolean(5, playlist.isPublic());
//            stmt.setObject(6, playlist.getDateCreated());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertPlaylist)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectPlaylists() - ] Not test
    public ArrayList<Playlist> selectPlaylists() {
        Connection c;
        Statement stmt;
        ArrayList<Playlist> playlists = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylists)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.Playlist;");
            playlists = new ArrayList<>();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(UUID.fromString(rs.getString("Id")));
                playlist.setTitle(rs.getString("Title"));
                playlist.setDescription(rs.getString("Description"));
                playlist.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
                playlist.setCreator(selectUser(playlist.getCreatorId()));
                playlist.setPlaylistDetails(selectPlaylistDetails(playlist.getId()));
                playlist.setPublic(rs.getBoolean("IsPublic"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateCreated"));
                playlist.setThumbnailPath(rs.getString("ThumbnailPath"));
                playlist.setThumbnailBytes(convertImageToByteArray(playlist.getThumbnailPath(), "jpg"));
                playlist.setDateCreated(timestamp.toLocalDateTime().toString());

                playlists.add(playlist);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylists)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlists;
    }

    //endregion

    //region [ - selectPlaylist(UUID Id) - ]
    public Playlist selectPlaylist(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Playlist playlist = null;
        PlaylistDetail playlistDetail = null;
        ArrayList<PlaylistDetail> playlistDetails = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylist)");

            stmt = c.prepareStatement("""
                    SELECT *
                    FROM ContentManagement.Playlist 
                    WHERE \"Id\" = ? ;
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                playlist = new Playlist();
                playlist.setId(UUID.fromString(rs.getString("Id")));
                playlist.setTitle(rs.getString("Title"));
                playlist.setDescription(rs.getString("Description"));
                playlist.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
//                playlist.setCreator(selectUser(playlist.getCreatorId()));
                playlist.setPlaylistDetails(selectPlaylistDetails(playlist.getId()));
                playlist.setPublic(rs.getBoolean("IsPublic"));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateCreated"));
                playlist.setThumbnailPath(rs.getString("ThumbnailPath"));
                playlist.setThumbnailBytes(convertImageToByteArray(playlist.getThumbnailPath(), "jpg"));
                playlist.setDateCreated(timestamp.toLocalDateTime().toString());
            }

            rs.close();
            stmt.close();

            stmt = c.prepareStatement("""
                    SELECT pd.PlaylistId, pd."videoid", v.Title, v.Description, v.ChannelId , v."UploadDate" , v.ThumbnailPath 
                    FROM ContentManagement.PlaylistDetail pd
                    INNER JOIN ContentManagement.Video v ON pd.videoid = v."Id"
                    WHERE pd.PlaylistId = ? ;
                    """);
            stmt.setObject(1, Id);
            rs = stmt.executeQuery();

            playlistDetails = new ArrayList<>();
            while (rs.next()) {
                playlistDetail = new PlaylistDetail();
                playlistDetail.setPlaylist(playlist);
                playlistDetail.setPlaylistId(Id);
                playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("VideoId")));
                video.setTitle(rs.getString("Title"));
                video.setViewers(selectUserVideos(Id));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannelBriefly(video.getChannelId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("UploadDate"));
                video.setUploadDate(timestamp.toLocalDateTime().toString());
                video.setThumbnailPath(rs.getString("ThumbnailPath"));
                video.setThumbnailBytes(convertImageToByteArray(video.getThumbnailPath(), "jpg"));
                playlistDetail.setVideo(video);
                playlistDetails.add(playlistDetail);
            }
            playlist.setPlaylistDetails(playlistDetails);

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylist)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlist;
    }
    //endregion

    //region [ - selectPlaylistBriefly(UUID Id) - ] Not Test
    public Playlist selectPlaylistBriefly(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Playlist playlist = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylistBriefly)");

            stmt = c.prepareStatement("""
                    SELECT "Title" , "Description" , "CreatorId" , "thumbnailPath"
                    FROM ContentManagement.Playlist 
                    WHERE \"Id\" = ? ;
                    """);

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            playlist = new Playlist();

            if (rs.next()) {
                playlist.setId(Id);
                playlist.setTitle(rs.getString("Title"));
                playlist.setDescription(rs.getString("Description")); // WHY ???
                playlist.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
//                playlist.setCreator(selectUser(playlist.getCreatorId()));
                playlist.setThumbnailPath(rs.getString("ThumbnailPath"));
                playlist.setThumbnailBytes(convertImageToByteArray(playlist.getThumbnailPath(), "jpg"));
            }

            stmt = c.prepareStatement("""
                    SELECT COUNT(VidoeId) AS Videos
                    FROM ContentManagement.PlaylistDetail
                    WHERE PlaylistId = ?;
                    """);

            stmt.setObject(1, Id);
            rs = stmt.executeQuery();
            playlist.setVideos(rs.getInt("Videos"));

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylistBriefly)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlist;
    }
    //endregion

    //region [ - selectPlaylistsBrieflyByUser(UUID Id) - ] Not Test
    public ArrayList<Playlist> selectPlaylistsBrieflyByUser(UUID creatorId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Playlist> playlists = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylistsBrieflyByUser)");

            stmt = c.prepareStatement("""
                    SELECT "title", "description", "Id", "thumbnailpath", 
                           (SELECT COUNT(videoid) FROM ContentManagement.PlaylistDetail WHERE playlistid = p."Id") AS Videos
                    FROM ContentManagement.Playlist p
                    WHERE "creatorid" = ?;
                    """);

            stmt.setObject(1, creatorId);
            ResultSet rs = stmt.executeQuery();

            playlists = new ArrayList<>();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setId(UUID.fromString(rs.getString("Id")));
                playlist.setTitle(rs.getString("Title"));
                playlist.setDescription(rs.getString("Description"));
                playlist.setCreatorId(creatorId);
                playlist.setThumbnailPath(rs.getString("ThumbnailPath"));
                playlist.setThumbnailBytes(convertImageToByteArray(playlist.getThumbnailPath(), "jpg"));
                playlist.setVideos(rs.getInt("Videos"));
                playlists.add(playlist);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylistsBrieflyByUser)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlists;
    }
    //endregion

    //region [ - updatePlaylist(Playlist playlist) - ] Tested
    public void updatePlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updatePlaylist)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.Playlist SET Title = ?, Description = ?, IsPublic = ? 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, playlist.getTitle());
            stmt.setString(2, playlist.getDescription());
            stmt.setObject(3, playlist.isPublic());
            stmt.setObject(4, playlist.getId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (updatePlaylist)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //    region [ - deletePlaylist(UUID Id) - ] Tested
    public void deletePlaylist(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylist)");

            deletePlaylistDetails(Id);
            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.Playlist 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deletePlaylist)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - PlaylistDetail - ]

    //region [ - insertPlaylistDetail(PlaylistDetail playlistDetail) - ] Tested
    public void insertPlaylistDetail(PlaylistDetail playlistDetail) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylistDetail)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.PlaylistDetail(PlaylistId, VideoId , SequenceNumber) 
                    VALUES (?, ?, ?);
                    """);

            stmt.setObject(1, playlistDetail.getPlaylistId());
            stmt.setObject(2, playlistDetail.getVideoId());
            stmt.setInt(3, playlistDetail.getNumber());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertPlaylistDetail)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectPlaylistDetails() - ]
    public ArrayList<PlaylistDetail> selectPlaylistDetails() {
        Connection c;
        Statement stmt;
        ArrayList<PlaylistDetail> playlistDetails = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylistDetails)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM ContentManagement.PlaylistDetail;
                    """);

            playlistDetails = new ArrayList<>();
            while (rs.next()) {
                PlaylistDetail playlistDetail = new PlaylistDetail();

                playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
                playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
                playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
                playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateAdded"));
                playlistDetail.setDateAdded(timestamp.toLocalDateTime().toString());
                playlistDetail.setNumber(rs.getInt("SequenceNumber"));

                playlistDetails.add(playlistDetail);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylistDetails)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlistDetails;
    }
    //endregion

    //region [ - selectPlaylistDetails(UUID playlistId) - ]
    public ArrayList<PlaylistDetail> selectPlaylistDetails(UUID playlistId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<PlaylistDetail> playlistDetails = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylistDetails)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.PlaylistDetail 
                    WHERE PlayListId = ?;
                    """);

            stmt.setObject(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            playlistDetails = new ArrayList<>();
            while (rs.next()) {
                PlaylistDetail playlistDetail = new PlaylistDetail();

                playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
                playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
                playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
                playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
                Timestamp timestamp = Timestamp.valueOf(rs.getString("DateAdded"));
                playlistDetail.setDateAdded(timestamp.toLocalDateTime().toString());
                playlistDetail.setNumber(rs.getInt("SequenceNumber"));

                playlistDetails.add(playlistDetail);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylistDetails)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlistDetails;
    }
    //endregion

    //region [ - selectPlaylistDetail(UUID Id) - ]
    public PlaylistDetail selectPlaylistDetail(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        PlaylistDetail playlistDetail = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylistDetail)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.PlaylistDetail 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();

            playlistDetail = new PlaylistDetail();
            playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
            playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
            playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
            playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
            Timestamp timestamp = Timestamp.valueOf(rs.getString("DateAdded"));
            playlistDetail.setDateAdded(timestamp.toLocalDateTime().toString());
            playlistDetail.setNumber(rs.getInt("SequenceNumber"));

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectPlaylistDetail)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return playlistDetail;
    }
    //endregion

    //region [ - deletePlaylistDetail(UUID playlistId, UUID videoId) - ] Tested
    public void deletePlaylistDetail(UUID playlistId, UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.PlaylistDetail 
                    WHERE PlaylistId = ? AND VideoId = ?;
                    """);

            stmt.setObject(1, playlistId);
            stmt.setObject(2, videoId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deletePlaylistDetail)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deletePlaylistDetail(UUID videoId) - ] Tested
    public void deletePlaylistDetail(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.PlaylistDetail WHERE VideoId = ?;
                    """);

            stmt.setObject(1, videoId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deletePlaylistDetail)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deletePlaylistDetails(UUID playlistId) - ] Tested
    public void deletePlaylistDetails(UUID playlistId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.PlaylistDetail 
                    WHERE PlaylistId = ?;
                    """);

            stmt.setObject(1, playlistId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deletePlaylistDetail)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Comment - ]

    //region [ - insertComment(Comment comment) - ] Tested
    public void insertComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertComment)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.Comment(\"Id\", \"Message\", VideoId, SenderId,ParentCommentId) 
                    VALUES (?, ?, ?, ?, ?);
                    """);

            stmt.setObject(1, comment.getId());
            stmt.setString(2, comment.getContent());
            stmt.setObject(3, comment.getVideoId());
            stmt.setObject(4, comment.getSenderId());
            stmt.setObject(5, comment.getParentCommentId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            System.out.println("Operation done successfully (insertComment)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectComments() - ]
    public ArrayList<Comment> selectComments() {
        Connection c;
        Statement stmt;
        ArrayList<Comment> comments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectComments(ALL))");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM ContentManagement.Comment;
                    """);

            comments = new ArrayList<>();
            while (rs.next()) {
                Comment comment = new Comment();

                comment.setId(UUID.fromString(rs.getString("Id")));
                comment.setVideoId(UUID.fromString(rs.getString("VideoId")));
                comment.setVideo(selectVideo(comment.getVideoId()));
                comment.setSenderId(UUID.fromString(rs.getString("SenderId")));
                comment.setSender(selectUser(comment.getSenderId()));
                comment.setParentCommentId(UUID.fromString(rs.getString("ParentCommentId")));
                if (comment.getParentCommentId() != null) {
                    comment.setParentComment(selectComment(comment.getParentCommentId()));
                }
                Timestamp timestamp = Timestamp.valueOf(rs.getString("CommentDate"));
                comment.setDateCommented(timestamp.toLocalDateTime().toString().toString());

                comments.add(comment);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectComments(ALL))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return comments;
    }
    //endregion

    //region [ - selectComments(UUID videoId) - ] Tested
    public ArrayList<Comment> selectComments(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Comment> comments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectComments(base on videoId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.Comment 
                    WHERE videoId = ?;
                    """);

            stmt.setObject(1, videoId);
            ResultSet rs = stmt.executeQuery();
            comments = new ArrayList<>();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(UUID.fromString(rs.getString("Id")));
                comment.setVideoId(UUID.fromString(rs.getString("VideoId")));
                comment.setContent(rs.getString("Message"));
//                comment.setVideo(selectVideo(comment.getVideoId()));
                comment.setSenderId(UUID.fromString(rs.getString("SenderId")));
                comment.setSender(selectUserBriefly(comment.getSenderId()));

                if (rs.getString("ParentCommentId") != null) {
                    comment.setParentCommentId(UUID.fromString(rs.getString("ParentCommentId")));
                    comment.setParentComment(selectComment(comment.getParentCommentId()));
                }
                Timestamp timestamp = Timestamp.valueOf(rs.getString("CommentDate"));
                comment.setDateCommented(timestamp.toLocalDateTime().toString());
                comments.add(comment);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectComments(base on videoId))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return comments;
    }
    //endregion

    //region [ - selectComment(UUID Id) - ] Tested
    public Comment selectComment(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Comment comment = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectComment)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.Comment 
                    WHERE \"Id\" = ?
                    """);

            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            comment = new Comment();

            if (rs.next()) {
                comment.setId(UUID.fromString(rs.getString("Id")));
                comment.setVideoId(UUID.fromString(rs.getString("VideoId")));
                comment.setVideo(selectVideo(comment.getVideoId()));
                comment.setSenderId(UUID.fromString(rs.getString("SenderId")));
//            comment.setSender(selectUser(comment.getSenderId()));
                if (rs.getString("ParentCommentId") != null) {
                    comment.setParentCommentId(UUID.fromString(rs.getString("ParentCommentId")));
                    comment.setParentComment(selectComment(comment.getParentCommentId()));
                }
                Timestamp timestamp = Timestamp.valueOf(rs.getString("CommentDate"));
                comment.setDateCommented(timestamp.toLocalDateTime().toString().toString());
            }

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS CommentLikes
                    FROM ContentManagement.UserComment
                    WHERE CommentId = ? AND Like = true;
                    """);
            stmt.setObject(1, Id);
            rs = stmt.executeQuery();
            comment.setLikes(rs.getInt("VideoLikes"));

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS CommentdisLikes
                    FROM ContentManagement.UserComment
                    WHERE CommentId = ? AND Like = false;
                    """);
            stmt.setObject(1, Id);
            rs = stmt.executeQuery();
            comment.setDislikes(rs.getInt("VideoDislikes"));

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectComment)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return comment;
    }
    //endregion

    //region [ - updateComment(Comment comment) - ] Tested
    public void updateComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {

//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateComment)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.Comment SET \"Message\" = ? 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setString(1, comment.getContent());
            stmt.setObject(2, comment.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (updateComment)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - deleteComment(UUID Id) - ] Not Tested
    public void deleteComment(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteComment)");

            deleteUserComment(Id);
            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.Comment 
                    WHERE \"Id\" = ?;
                    """);

            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteComment)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - UserComment - ]

    //region [ - insertUserComment (UserComment UserComment) - ] Yes
    public void insertUserComment(UserComment userVideo) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");

            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertUserComment)");

            stmt = c.prepareStatement("""
                    INSERT INTO ContentManagement.UserComment(UserId, CommentId , \"Like\") 
                    VALUES (?, ?, ?);
                    """);

            stmt.setObject(1, userVideo.getUserId());
            stmt.setObject(2, userVideo.getCommentId());
            stmt.setObject(3, userVideo.getLike());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (insertUserComment)");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    //endregion

    //region [ - selectUserComments() - ] Not Tested
    public ArrayList<UserComment> selectUserComments() {
        Connection c;
        Statement stmt;
        ArrayList<UserComment> userComments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("""
                    SELECT * FROM ContentManagement.UserComment;
                    """);

            userComments = new ArrayList<>();
            while (rs.next()) {
                UserComment userComment = new UserComment();
                userComment.setLike(rs.getBoolean("Like"));
                userComment.setUserId(UUID.fromString(rs.getString("CommentId")));
                userComment.setCommentId(UUID.fromString(rs.getString("UserId")));
                userComment.setUser(selectUser(userComment.getUserId()));
                userComment.setComment(selectComment(userComment.getCommentId()));
                userComments.add(userComment);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectVideoCategories)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userComments;
    }
    //endregion

    //region [ - selectUserComments(UUID userID) - ] Tested
    public ArrayList<UserComment> selectUserComments(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<UserComment> userComments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserComments(based on userId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserComment 
                    WHERE UserId = ?;
                    """);

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();

            userComments = new ArrayList<>();
            while (rs.next()) {
                UserComment userComment = new UserComment();

                userComment.setLike(rs.getBoolean("Like"));
                userComment.setUserId(UUID.fromString(rs.getString("UserId")));
                userComment.setCommentId(UUID.fromString(rs.getString("CommentId")));
                userComment.setComment(selectComment(userComment.getCommentId()));
//                userComment.setUser(selectUser(userComment.getUserId()));

                userComments.add(userComment);
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserComments(based on userId))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userComments;
    }
    //endregion

    //region [ - selectCommentUsers(UUID commentId) - ] Not Tested
    public ArrayList<UserComment> selectCommentUsers(UUID commentId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<UserComment> userComments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCommentUsers(based on commentId))");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserComment 
                    WHERE CommentId = ?;
                    """);

            stmt.setObject(1, commentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserComment userComment = new UserComment();

                userComment.setLike(rs.getBoolean("Like"));
                userComment.setUserId(UUID.fromString(rs.getString("UserId")));
                userComment.setCommentId(UUID.fromString(rs.getString("CommentId")));
                userComment.setComment(selectComment(userComment.getCommentId()));
//                userComment.setUser(selectUser(userComment.getUserId()));

                userComments.add(userComment);
            }
            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectCommentUsers(based on commentId))");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userComments;
    }
    //endregion

    //    region [ - selectCommentLikesStatus(UUID Id) - ] test
    public Comment selectCommentLikesStatus(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Comment comment = new Comment();
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (getCommentLikes)");

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS CommentLikes
                    FROM ContentManagement.UserComment
                    WHERE commentid = ? AND \"Like\" = true;
                    """);
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comment.setLikes(rs.getInt("CommentLikes"));
            }

            stmt = c.prepareStatement("""
                    SELECT COUNT(UserId) AS CommentDislikes
                    FROM ContentManagement.UserComment
                    WHERE commentid = ? AND \"Like\" = false;
                    """);

            stmt.setObject(1, Id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                comment.setDislikes(rs.getInt("CommentDislikes"));
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectComment)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return comment;
    }
    //endregion

    //region [ - UserComment selectUserComment(UUID Id) - ] Not Tested
    public UserComment selectUserComment(UUID userID, UUID commentID) {
        Connection c;
        PreparedStatement stmt;
        UserComment userComment = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUserComment)");

            stmt = c.prepareStatement("""
                    SELECT * FROM ContentManagement.UserComment 
                    WHERE UserId = ? AND CommentId = ?;
                    """);

            stmt.setObject(1, userID);
            stmt.setObject(2, commentID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                userComment = new UserComment();
                userComment.setLike(rs.getBoolean("Like"));
                userComment.setUserId(UUID.fromString(rs.getString("UserId")));
                userComment.setCommentId(UUID.fromString(rs.getString("CommentId")));
            }

            rs.close();
            stmt.close();
            System.out.println("Operation done successfully (selectUserComment)");
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return userComment;
    }
    //endregion

    //region [ - updateUserComment(UserCommnet userVideo) - ] YES
    public void updateUserComment(UserComment userComment) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUserComment)");

            stmt = c.prepareStatement("""
                    UPDATE ContentManagement.UserComment
                    SET "Like" = ?
                    WHERE UserId = ? AND CommentId = ?;
                    """);
            stmt.setObject(1, userComment.getLike());
            stmt.setObject(2, userComment.getUserId());
            stmt.setObject(3, userComment.getCommentId());
            stmt.executeUpdate();

            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (updateUserComment)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion


    //region [ - deleteUserComment(UUID userId , UUID commentID) - ] Yes (this method don't want to exist)
    public void deleteUserComment(UUID userId, UUID commentID) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUserComment)");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.UserComment 
                    WHERE UserId = ? AND CommentId = ?;
                    """);

            stmt.setObject(1, userId);
            stmt.setObject(2, commentID);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteUserComment)");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //region [ - deleteUserComment(UUID commentID) - ] Yes
    public void deleteUserComment(UUID commentId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUserComment (based on commentId))");

            stmt = c.prepareStatement("""
                    DELETE FROM ContentManagement.UserComment 
                    WHERE CommentId = ?;
                    """);

            stmt.setObject(1, commentId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
            System.out.println("Operation done successfully (deleteUserComment (based on commentID))");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
    //endregion

    //endregion

    //region [ - Tools - ]

    //region [ - convertImageToByteArray(String imagePath, String type) - ]
    private byte[] convertImageToByteArray(String imagePath, String type) {
        String path;
        if (imagePath == null) {
            path = "src/main/resources/Images/Arcane2.jpg";
        } else {
            path = "src/main/resources" + imagePath;
        }
        System.out.println("In ConvertImage Method");
        byte[] imageBytes = null;
        try {
            // Load the image
            File file = new File(path);
            BufferedImage bufferedImage = ImageIO.read(file);

            // Convert BufferedImage to byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            imageBytes = baos.toByteArray();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("End of ConvertImage Method");
        return imageBytes;
    }
    //endregion

    //region [ - convertVideoToByteArray - ]

    public byte[] convertVideoToByteArray(String videoPath) {
        String path;
        if (videoPath == null) {
            path = "src/main/resources/Videos/AvengersInfinityWar.mp4";
        } else {
            path = "src/main/resources" + videoPath;
        }
        videoPath = path;
        System.out.println("In ConvertImage Method");
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(videoPath))) {
            int videoSize = bis.available();
            byte[] videoBytes = new byte[videoSize];
            int byteRead;
            int i = 0;
            while ((byteRead = bis.read()) != -1) {
                videoBytes[i++] = (byte) byteRead;
            }
            System.out.println("End of ConvertImage Method");
            return videoBytes;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //endregion

    //endregion

    //endregion

}
