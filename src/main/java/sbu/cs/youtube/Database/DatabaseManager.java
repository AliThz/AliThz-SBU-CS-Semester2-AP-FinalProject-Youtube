package sbu.cs.youtube.Database;

import javafx.util.converter.LocalDateTimeStringConverter;
import sbu.cs.youtube.Shared.POJO.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseManager {

    //region [ - Fields - ]
    private static final String URL = "jdbc:postgresql://localhost:5432/Youtube-development";
    private static final String USER = "postgres";
    private static final String PASSWORD = "musketeers";
    //endregion

    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        User user = new User("1", "1", "1", "1", LocalDateTime.now());
        insertUser(user);
        User user1 = new User("2", "2", "2", "2", LocalDateTime.now());
//        updateUser(user1);
        Channel channel = new Channel(user.getId(), "lalala");
        channel.setId(UUID.fromString("e01c7ca2-6f82-43ed-b95f-843e316a7cd5"));
        user1.setId(UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
        Subscription subscription = new Subscription(user1.getId(), channel.getId());

//        ------------ Category --------------------

//        Category category = new Category("fun");
//        insertCategory(category);
//        Category category1 = new Category("game");
//        category1.setId(category.getId());
//        updateCategory(category1);
//        deleteCategory(category.getId());

//        ------------ Video --------------------

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
//        VideoLike videoLike = new VideoLike(video.getId(), UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        insertVideoLike(videoLike);
//        VideoLike videoLike1 = new VideoLike(video.getId(), UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f"));
//        insertVideoLike(videoLike1);
//
//        deleteVideo(video.getId());

//        ------------ VideoLike ---------------

//        VideoLike videoLike = new VideoLike(UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"), UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        insertVideoLike(videoLike);
//        VideoLike videoLike1 = new VideoLike(UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"), UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f"));
//        insertVideoLike(videoLike1);
//        deleteVideoLike(UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"),UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));

//        ------------ Plaulist ---------------

        Playlist playlist = new Playlist("1" , "1" , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , false);
        insertPlaylist(playlist);
//        Playlist playlist1 = new Playlist("2" , "2" , UUID.fromString("bc44ef5f-4f4a-4300-af39-c6972a9ac73f") , false);
//        playlist1.setId(playlist.getId());

        PlaylistDetail playlistDetail = new PlaylistDetail(playlist.getId() , UUID.fromString("3d416e3a-629c-4559-83f5-5aa41fe8ece7"));
        insertPlaylistDetail(playlistDetail);
        PlaylistDetail playlistDetail1 = new PlaylistDetail(playlist.getId(), UUID.fromString("015f254e-ca95-4bae-a8cd-1e3d6b4b69ee"));
        insertPlaylistDetail(playlistDetail1);

        deletePlaylist(playlist.getId());

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

//        ------------------- CommentLike -----------------

//        CommentLike commentLike = new CommentLike(UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68") , UUID.fromString("62cb0ff4-4501-4eff-9637-3fab17fbd1bb"));
//        insertCommentLike(commentLike);
//        CommentLike commentLike1 = new CommentLike(UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68") , UUID.fromString("7e15195d-7696-4bef-b268-440f240e6719"));
//        insertCommentLike(commentLike1);
//        deleteCommentLike(commentLike1.getId());
//        deleteACommentLikes(UUID.fromString("5c30e06f-3e74-4465-9059-c808e5c75a68"));






    }

    //region [ - Methods - ]
    //region [ - User - ]

    //region [ - insertUser(User user) - ] Tested
    public static void insertUser(User user) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertUser)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.User(\"Id\", FullName, email, DateOfBirth, Username, \"Password\") VALUES (?, ?, ?, ?, ?,?);");
            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setObject(4, user.getDateOfBirth());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());

            stmt.executeUpdate();
            c.commit();


            insertChannel(new Channel(user.getId(), user.getUsername()));


            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertUser)");
    }
    //endregion

    //region [ - selectUsers() - ] Not Tested
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserManagement.User;");
            users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setSubscriptions(selectSubscriptions(user.getId()));
                user.setNotifications(selectNotifications(user.getId()));
                user.setLikedVideos(selectLikedVideos(user.getId()));
                Timestamp timestamp =rs.getTimestamp("DateOfBirth");
                user.setDateOfBirth(timestamp.toLocalDateTime());
                timestamp =rs.getTimestamp("JoinDate");
                user.setDateOfBirth(timestamp.toLocalDateTime());
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                users.add(user);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectUsers)");
        return users;
    }
    //endregion No    kl;asdf

    //region [ - selectUser(UUID Id) - ] Not Tested
    public static User selectUser(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        User user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectUser)");

                stmt = c.prepareStatement("SELECT * FROM UserManagement.User WHERE \"Id\" = ?");
                 stmt.setObject(1, Id);
                ResultSet rs = stmt.executeQuery();

            user = new User();
            if (rs.next()) {
                user.setId(Id);
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                Timestamp timestamp =rs.getTimestamp("DateOfBirth");
                user.setDateOfBirth(timestamp.toLocalDateTime());
                timestamp =rs.getTimestamp("JoinDate");
                user.setDateOfBirth(timestamp.toLocalDateTime());
                user.setSubscriptions(selectSubscriptions(user.getId()));
                user.setNotifications(selectNotifications(user.getId()));
                user.setLikedVideos(selectLikedVideos(user.getId()));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectUser)");
        return user;
    }
    //endregion

    //region [ - updateUser(User user) - ] Tested
    public static void updateUser(User user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUser)");

            stmt = c.prepareStatement("UPDATE UserManagement.User SET fullname = ?, email = ?, DateOfBirth = ?, Username = ?, \"Password\" = ? WHERE \"Id\" = ?;");

            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setObject(3, user.getDateOfBirth());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setObject(6, user.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateUser)");
    }
    //endregion

    //region [ - deleteUser(UUID Id) - ] Not Exist
    public static void deleteUser(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteUser)");

            stmt = c.prepareStatement("DELETE FROM UserManagement.User WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteUser)");
    }
    //endregion

    //endregion

    //region [ - Channel - ]

    //region [ - insertChannel(Channel channel) - ] Tested
    public static void insertChannel(Channel channel) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertChannel)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Channel(\"Id\", CreatorId, Title, Description) VALUES (?, ?, ?, ?);");
            stmt.setObject(1, channel.getId());
            stmt.setObject(2, channel.getCreatorId());
            stmt.setString(3, channel.getTitle());
            stmt.setString(4, channel.getDescription());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertChannel)");
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
            System.out.println("Opened database successfully (selectChannels)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserManagement.Channel;");
            channels = new ArrayList<>();
            while (rs.next()) {
                Channel channel = new Channel();
                channel.setId(UUID.fromString(rs.getString("Id")));
                channel.setCreatorId(UUID.fromString(rs.getString("Id")));
                channel.setCreator(selectUser(channel.getCreatorId()));
                channel.setTitle(rs.getString("Title"));
                channel.setDescription(rs.getString("Description"));
                channel.setDateCreated(LocalDateTime.parse(rs.getString("DateOfBirth")));
                channels.add(channel);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectChannels)");
        return channels;
    }
    //endregion

    //region [ - Channel selectChannel(UUID Id) - ] Not Tested
    public static Channel selectChannel(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Channel user = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectChannel)");

            stmt = c.prepareStatement("SELECT * FROM UserManagement.Channel WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            user = new Channel();

            user.setId(UUID.fromString(rs.getString("Id")));
            user.setCreatorId(UUID.fromString(rs.getString("Id")));
            user.setCreator(selectUser(user.getCreatorId()));
            user.setTitle(rs.getString("Title"));
            user.setDescription(rs.getString("Description"));
            user.setDateCreated(LocalDateTime.parse(rs.getString("DateOfBirth")));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectChannel)");
        return user;
    }
    //endregion

    //region [ - updateChannel(Channel user) - ] Tested
    public static void updateChannel(Channel channel) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateChannel)");

            stmt = c.prepareStatement("UPDATE UserManagement.Channel SET CreatorId = ?, Title = ?, Description = ?, \"DateCreated\" = ?  WHERE \"Id\" = ?;");

            stmt.setObject(1, channel.getCreatorId());
            stmt.setString(2, channel.getTitle());
            stmt.setString(3, channel.getDescription());
            stmt.setObject(4, channel.getDateCreated());
            stmt.setObject(5, channel.getId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateChannel)");
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

            stmt = c.prepareStatement("DELETE FROM UserManagement.Channel WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteChannel)");
    }
    //endregion

    //endregion

    //region [ - Subscription - ]

    //region [ - insertSubscription(Subscription subscription) - ] Tested
    public static void insertSubscription(Subscription subscription) {
        System.out.println(subscription.getSubscriberId());
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertSubscription)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Subscription(SubscriberId, channelId) VALUES (?, ?);");
            stmt.setObject(1, subscription.getSubscriberId());
            stmt.setObject(2, subscription.getChannelId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertSubscription)");
    }
    //endregion

    //region [ - ArrayList<Subscription> selectSubscriptions() - ] Not Test
    public ArrayList<Subscription> selectSubscriptions() {
        Connection c;
        Statement stmt;
        ArrayList<Subscription> subscriptions = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscriptions)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserManagement.Subscription;");
            subscriptions = new ArrayList<>();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriberId(UUID.fromString(rs.getString("Id")));
                subscription.setChannelId(UUID.fromString(rs.getString("Id")));
                subscription.setSubscriber(selectUser(subscription.getSubscriberId()));
                subscription.setChannel(selectChannel(subscription.getChannelId()));
                subscription.setJoinDate(LocalDateTime.parse(rs.getString("JoinDate")));
                subscriptions.add(subscription);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectSubscriptions)");
        return subscriptions;
    }
    //endregion

    //region [ - ArrayList<Subscription> selectSubscriptions(UUID userId) - ] Not Test
    public static ArrayList<Subscription> selectSubscriptions(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Subscription> subscriptions = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscriptions)");

            stmt = c.prepareStatement("SELECT * FROM UserManagement.Subscription WHERE UserId = ?;");
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            subscriptions = new ArrayList<>();
            while (rs.next()) {
                Subscription subscription = new Subscription();
                subscription.setSubscriberId(UUID.fromString(rs.getString("Id")));
                subscription.setChannelId(UUID.fromString(rs.getString("Id")));
                subscription.setChannel(selectChannel(subscription.getChannelId()));
                subscription.setJoinDate(LocalDateTime.parse(rs.getString("JoinDate")));
                subscriptions.add(subscription);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectSubscriptions)");
        return subscriptions;
    }
    //endregion

    //region [ - Subscription selectSubscription(UUID Id) - ] Not Exist
    public Subscription selectSubscription(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Subscription subscription = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectSubscription)");

            stmt = c.prepareStatement("SELECT * FROM UserManagement.Subscription WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            subscription = new Subscription();

            subscription.setSubscriberId(UUID.fromString(rs.getString("Id")));
            subscription.setChannelId(UUID.fromString(rs.getString("Id")));
            subscription.setSubscriber(selectUser(subscription.getSubscriberId()));
            subscription.setChannel(selectChannel(subscription.getChannelId()));
            subscription.setJoinDate(LocalDateTime.parse(rs.getString("JoinDate")));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectSubscription)");
        return subscription;
    }
    //endregion

    //region [ - deleteSubscription(UUID SubscriberId, UUID channelId) - ] Tested
    public static void deleteSubscription(UUID SubscriberId, UUID channelId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteSubscription)");

            stmt = c.prepareStatement("DELETE FROM UserManagement.Subscription WHERE SubscriberId = ? AND channelId = ?;");
            stmt.setObject(1, SubscriberId);
            stmt.setObject(2, channelId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteSubscription)");
    }
    //endregion

    //endregion

    //region [ - Notification - ]

    //region [ - insertNotification(Notification notification) - ] Tested
    public static void insertNotification(Notification notification) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertNotification)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Notification(\"Id\", UserId, \"Message\", IsRead, dateSent) VALUES (?, ?, ?, ?, ?);");
            stmt.setObject(1, notification.getId());
            stmt.setObject(2, notification.getUserId());
            stmt.setString(3, notification.getMessage());
            stmt.setBoolean(4, notification.isRead());
            stmt.setObject(5, notification.getDateSent());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertNotification)");
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
            System.out.println("Opened database successfully (selectNotifications)");

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
                notification.setDateSent(LocalDateTime.parse(rs.getString("DateSent")));
                notifications.add(notification);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectNotifications)");
        return notifications;
    }
    //endregion

    //region [ - ArrayList<Notification> selectNotifications(UUID userId) - ] Not test
    public static ArrayList<Notification> selectNotifications(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Notification> notifications = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectNotifications)");

            stmt = c.prepareStatement("SELECT * FROM UserManagement.Notification WHERE UserId = ?;");
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            notifications = new ArrayList<>();
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setId(UUID.fromString(rs.getString("Id")));
                notification.setUserId(UUID.fromString(rs.getString("UserId")));
                notification.setUser(selectUser(notification.getUserId()));
                notification.setMessage(rs.getString("Message"));
                notification.setRead(Boolean.getBoolean(rs.getString("Description")));
                notification.setDateSent(LocalDateTime.parse(rs.getString("DateSent")));
                notifications.add(notification);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectNotifications)");
        return notifications;
    }
    //endregion

    //region [ - Notification selectNotification(UUID Id) - ] Not Test
    public Notification selectNotification(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Notification notification = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectNotification)");

            stmt = c.prepareStatement("SELECT * FROM UserManagement.Notification WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            notification = new Notification();

            notification.setId(UUID.fromString(rs.getString("Id")));
            notification.setUserId(UUID.fromString(rs.getString("UserId")));
            notification.setUser(selectUser(notification.getUserId()));
            notification.setMessage(rs.getString("Message"));
            notification.setRead(Boolean.getBoolean(rs.getString("Description")));
            notification.setDateSent(LocalDateTime.parse(rs.getString("DateSent")));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectNotification)");
        return notification;
    }
    //endregion

    //region [ - updateNotification(Notification notification) - ] Tested
    public static void updateNotification(Notification notification) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateNotification)");

            stmt = c.prepareStatement("UPDATE UserManagement.Notification SET IsRead = ?  WHERE \"Id\" = ?;");

            stmt.setBoolean(1, notification.isRead());
            stmt.setObject(2, notification.getId());

            stmt.executeUpdate();

            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateNotification)");
    }
    //endregion

    //region [ - deleteNotification(UUID Id) - ] Tested
    public static void deleteNotification(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteNotification)");

            stmt = c.prepareStatement("DELETE FROM UserManagement.Notification WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteChannel)");
    }
    //endregion

    //endregion

    //region [ - Category - ]

    //region [ - insertCategory(Category category) - ] Tested
    public static void insertCategory(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertCategory)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Category(\"Id\", Title) VALUES (?, ?);");
            stmt.setObject(1, category.getId());
            stmt.setString(2, category.getTitle());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertCategory)");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.Category;");
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectCategories)");
        return categories;
    }
    //endregion

    //region [ - selectCategory(UUID Id) - ] Not Tested
    public static Category selectCategory(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Category category = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCategory)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.Category WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            category = new Category();

            category.setId(UUID.fromString(rs.getString("Id")));
            category.setTitle(rs.getString("Title"));
            category.setVideoCategories(selectCategoryVideos(category.getId()));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectCategory)");
        return category;
    }
    //endregion

    //region [ - updateCategory(Category category) - ] Tested
    public static void updateCategory(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateCategory)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Category SET Title = ? WHERE \"Id\" = ?;");

            stmt.setString(1, category.getTitle());
            stmt.setObject(2, category.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateCategory)");
    }
    //endregion

    //region [ - deleteCategory(UUID Id) - ] Not Exist
    public static void deleteCategory(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteCategory)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.Category WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteCategory)");
    }
    //endregion

    //endregion

    //region [ - Video - ]

    //region [ - insertVideo(Video video) - ] Tested
    public static void insertVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideo)");


            stmt = c.prepareStatement("INSERT INTO ContentManagement.Video(\"Id\", Title, Description, ChannelId, \"Views\" , \"UploadDate\" ) VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setObject(1, video.getId());
            stmt.setString(2, video.getTitle());
            stmt.setString(3, video.getDescription());
            stmt.setObject(4, video.getChannelId());
            stmt.setInt(5, video.getViews());
            stmt.setObject(6, video.getUploadDate());

            stmt.executeUpdate();
            c.commit();

            if (video.getCategories() != null) {
                for (var videoCategory : video.getCategories()) {
                    insertVideoCategory(videoCategory);
                }
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertVideo)");
    }
    //endregion

    //region [ - selectVideos() - ] Not Tested
    public ArrayList<Video> selectVideos() {
        Connection c;
        Statement stmt;
        ArrayList<Video> videos = null ;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideos)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.Video;");
            videos = new ArrayList<>();
            while (rs.next()) {
                Video video = new Video();
                video.setId(UUID.fromString(rs.getString("Id")));
                video.setTitle(rs.getString("Title"));
                video.setDescription(rs.getString("Description"));
                video.setCategories(selectVideoCategories(video.getId()));
                video.setVideoLikes(selectVideoLikes(video.getId()));
                video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
                video.setChannel(selectChannel(video.getChannelId()));
                video.setComments(selectComments(video.getId()));
                video.setViews(Integer.parseInt(rs.getString("Views")));
                video.setUploadDate(LocalDateTime.parse(rs.getString("UploadDateTime")));
                videos.add(video);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideos)");
        return videos;
    }
    //endregion

    //region [ - selectVideo(UUID Id) - ] Not Tested
    public static Video selectVideo(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Video video = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideo)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.Video WHERE \"Id\" = ?");
            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();
            video = new Video();

            video.setId(UUID.fromString(rs.getString("Id")));
            video.setTitle(rs.getString("Title"));
            video.setDescription(rs.getString("Description"));
            video.setCategories(selectVideoCategories(video.getId()));
            video.setVideoLikes(selectVideoLikes(video.getId()));
            video.setChannelId(UUID.fromString(rs.getString("ChannelId")));
            video.setComments(selectComments(video.getId()));
            video.setChannel(selectChannel(video.getChannelId()));
            video.setViews(Integer.valueOf(rs.getString("Views")));
            video.setUploadDate(LocalDateTime.parse(rs.getString("UploadDateTime")));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideo)");
        return video;
    }
    //endregion

    //region [ - updateVideo(Video video) - ] Tested
    public static void updateVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateVideo)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Video SET Title = ?, Description = ?, \"Views\" = ? WHERE \"Id\" = ?;");

            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getDescription());
            stmt.setInt(3, video.getViews());
            stmt.setObject(4, video.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateVideo)");
    }
    //endregion

    //    region [ - deleteVideo(UUID videoid) - ] Tested
    public static void deleteVideo(UUID videoid) {
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
            deleteVideoLike(videoid);
            stmt = c.prepareStatement("DELETE FROM ContentManagement.Video WHERE \"Id\" = ?;");
            stmt.setObject(1, videoid);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteVideo)");
    }
    //endregion

    //endregion

    //region [ - VideoCategory - ]

    //region [ - insertVideoCategory(VideoCategory videoCategory) - ] Tested
    public static void insertVideoCategory(VideoCategory videoCategory) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideoCategory)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.VideoCategory(VideoId, CategoryId) VALUES (?, ?);");
            stmt.setObject(1, videoCategory.getVideoId());
            stmt.setObject(2, videoCategory.getCategoryId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertVideoCategory)");
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoCategories)");
        return videoCategories;
    }
    //endregion

    //region [ - ArrayList<VideoCategory> selectVideoCategories(UUID videoId) - ]  Not Tested
    public static ArrayList<VideoCategory> selectVideoCategories(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories (selectVideoLikes(based on video))");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoCategory WHERE VideoId = ?;");
            stmt.setObject(1, videoId);
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoCategories (selectVideoLikes(based on video))");
        return videoCategories;
    }
    //endregion

    //region [ - ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) - ] Not Tested
    public static ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoCategory> videoCategories = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategories (selectVideoLikes(based on category))");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoCategory WHERE CategoryId = ?;");
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
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoCategories (selectVideoLikes(based on category))");
        return videoCategories;
    }
    //endregion

    //region [ - VideoCategory selectVideoCategory(UUID Id) - ] Not Tested
    public VideoCategory selectVideoCategory(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        VideoCategory videoCategory = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoCategory)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoCategory WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            videoCategory = new VideoCategory();
            videoCategory.setVideoId(UUID.fromString(rs.getString("VideoId")));
            videoCategory.setCategoryId(UUID.fromString(rs.getString("CategoryId")));
            videoCategory.setVideo(selectVideo(videoCategory.getVideoId()));
            videoCategory.setCategory(selectCategory(videoCategory.getCategoryId()));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoCategory)");
        return videoCategory;
    }
    //endregion

    //region [ - deleteVideoCategory(UUID videoId, UUID categoryId) - ] Tested
    public static void deleteVideoCategory(UUID videoId, UUID categoryId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoCategory)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.VideoCategory WHERE VideoId = ? AND CategoryId = ?;");
            stmt.setObject(1, videoId);
            stmt.setObject(2, categoryId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteVideoCategory)");
    }
    //endregion

    //region [ - deleteVideoCategory(UUID videoId) - ] Tested
    public static void deleteVideoCategory(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoCategory (based on videoId))");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.VideoCategory WHERE VideoId = ?;");
            stmt.setObject(1, videoId);
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteVideoCategory (based on videoId))");
    }
    //endregion

    //endregion

    //region [ - VideoLike - ]

    //region [ - insertVideoLike(VideoLike videoLike) - ] Tested
    public static void insertVideoLike(VideoLike videoLike) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideoLike)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.VideoLike(\"Id\", VideoId, UserId) VALUES (?, ?, ?);");
            stmt.setObject(1, videoLike.getId());
            stmt.setObject(2, videoLike.getVideoId());
            stmt.setObject(3, videoLike.getUserId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertVideoLike)");
    }
    //endregion

    //region [ - ArrayList<VideoLike> selectVideoLikes() - ]
    public ArrayList<VideoLike> selectVideoLikes() {
        Connection c;
        Statement stmt;
        ArrayList<VideoLike> videoLikes = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoLikes)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.VideoLike;");
            videoLikes = new ArrayList<>();
            while (rs.next()) {
                VideoLike videoLike = new VideoLike();

                videoLike.setId(UUID.fromString(rs.getString("Id")));
                videoLike.setVideoId(UUID.fromString(rs.getString("VideoId")));
                videoLike.setVideo(selectVideo(videoLike.getVideoId()));
                videoLike.setUserId(UUID.fromString(rs.getString("UserId")));
                videoLike.setUser(selectUser(videoLike.getUserId()));
                videoLikes.add(videoLike);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoLikes)");
        return videoLikes;
    }
    //endregion

    //region [ - selectVideoLikes(UUID videoId) - ]
    public static ArrayList<VideoLike> selectVideoLikes(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoLike> videoLikes = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoLikes(based on video))");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoLike WHERE videoId = ?;");
            stmt.setObject(1, videoId);
            ResultSet rs = stmt.executeQuery();
            videoLikes = new ArrayList<>();
            while (rs.next()) {
                VideoLike videoLike = new VideoLike();

                videoLike.setId(UUID.fromString(rs.getString("Id")));
                videoLike.setUserId(UUID.fromString(rs.getString("UserId")));
                videoLike.setUser(selectUser(videoLike.getUserId()));
                videoLikes.add(videoLike);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoLikes(based on video))");
        return videoLikes;
    }
    //endregion

    //region [ - selectLikedVideos(UUID userId) - ]
    public static ArrayList<VideoLike> selectLikedVideos(UUID userId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<VideoLike> videoLikes = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoLikes(based on video))");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoLike WHERE userId = ?;");
            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();
            videoLikes = new ArrayList<>();
            while (rs.next()) {
                VideoLike videoLike = new VideoLike();

                videoLike.setId(UUID.fromString(rs.getString("Id")));
                videoLike.setUserId(UUID.fromString(rs.getString("UserId")));
                videoLike.setVideo(selectVideo(videoLike.getVideoId()));
                videoLikes.add(videoLike);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoLikes(based on video))");
        return videoLikes;
    }
    //endregion

    //region [ - selectVideoLike(UUID Id) - ]
    public VideoLike selectVideoLike(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        VideoLike videoLike = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectVideoLike)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.VideoLike WHERE \"Id\" = ?");
            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();
            videoLike = new VideoLike();

            videoLike.setId(UUID.fromString(rs.getString("Id")));
            videoLike.setVideoId(UUID.fromString(rs.getString("VideoId")));
            videoLike.setVideo(selectVideo(videoLike.getVideoId()));
            videoLike.setUserId(UUID.fromString(rs.getString("UserId")));
            videoLike.setUser(selectUser(videoLike.getUserId()));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectVideoLike)");
        return videoLike;
    }
    //endregion

    //region [ - deleteVideoLike(UUID videoId, UUID likeId) - ] Tested
    public static void deleteVideoLike(UUID videoId, UUID userId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoLike)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.VideoLike WHERE videoId = ? AND userId = ?;");
            stmt.setObject(1, videoId);
            stmt.setObject(2, userId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteVideoLike)");
    }
    //endregion

    //region [ - deleteVideoLike(UUID videoId) - ] Tested
    public static void deleteVideoLike(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoLike (based on videoId))");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.VideoLike WHERE videoId = ?;");
            stmt.setObject(1, videoId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteVideoLike (based on videoId))");
    }
    //endregion

    //endregion

    //region [ - Playlist - ]

    //region [ - insertPlaylist(Playlist playlist) - ] Tested
    public static void insertPlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylist)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Playlist(\"Id\", Title, Description, CreatorId,IsPublic) VALUES (?, ?, ?, ?, ?);");
            stmt.setObject(1, playlist.getId());
            stmt.setString(2, playlist.getTitle());
            stmt.setString(3, playlist.getDescription());
            stmt.setObject(4, playlist.getCreatorId());
            stmt.setBoolean(5, playlist.isPublic());
//            stmt.setObject(6, playlist.getDateCreated());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertPlaylist)");
    }
    //endregion

    //region [ - selectPlaylists() - ]
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
                playlist.setDateCreated(LocalDateTime.parse(rs.getString("DateCreated")));

                playlists.add(playlist);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectPlaylists)");
        return playlists;
    }
    //endregion

    //region [ - selectPlaylist(UUID Id) - ]
    public Playlist selectPlaylist(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Playlist playlist = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectPlaylist)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.Playlist WHERE \"Id\" = ?");
            stmt.setObject(1, Id);
            ResultSet rs = stmt.executeQuery();
            playlist = new Playlist();

            playlist.setId(UUID.fromString(rs.getString("Id")));
            playlist.setTitle(rs.getString("Title"));
            playlist.setDescription(rs.getString("Description"));
            playlist.setCreatorId(UUID.fromString(rs.getString("CreatorId")));
            playlist.setCreator(selectUser(playlist.getCreatorId()));
            playlist.setPlaylistDetails(selectPlaylistDetails(playlist.getId()));
            playlist.setPublic(rs.getBoolean("IsPublic"));
            playlist.setDateCreated(LocalDateTime.parse(rs.getString("DateCreated")));


            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectPlaylist)");
        return playlist;
    }
    //endregion

    //region [ - updatePlaylist(Playlist playlist) - ] Tested
    public static void updatePlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updatePlaylist)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Playlist SET Title = ?, Description = ?, IsPublic = ? WHERE \"Id\" = ?;");

            stmt.setObject(1, playlist.getTitle());
            stmt.setString(2, playlist.getDescription());
            stmt.setObject(3, playlist.isPublic());
            stmt.setObject(4 , playlist.getId());
            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updatePlaylist)");
    }
    //endregion

//    region [ - deletePlaylist(UUID Id) - ] Tested
    public static void deletePlaylist(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylist)");

            deletePlaylistDetails(Id);
            stmt = c.prepareStatement("DELETE FROM ContentManagement.Playlist WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deletePlaylist)");
    }
    //endregion

    //endregion

    //region [ - PlaylistDetail - ]

    //region [ - insertPlaylistDetail(PlaylistDetail playlistDetail) - ] Tested
    public static void insertPlaylistDetail(PlaylistDetail playlistDetail) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylistDetail)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.PlaylistDetail(PlaylistId, VideoId , SequenceNumber) VALUES (?, ?, ?);");
            stmt.setObject(1, playlistDetail.getPlaylistId());
            stmt.setObject(2, playlistDetail.getVideoId());
            stmt.setInt(3, playlistDetail.getNumber());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertPlaylistDetail)");
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
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.PlaylistDetail;");
            playlistDetails = new ArrayList<>();
            while (rs.next()) {
                PlaylistDetail playlistDetail = new PlaylistDetail();

                playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
                playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
                playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
                playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
                playlistDetail.setDateAdded(LocalDateTime.parse(rs.getString("DateAdded")));
                playlistDetail.setNumber(rs.getInt("Number"));

                playlistDetails.add(playlistDetail);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectPlaylistDetails)");
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

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.PlaylistDetail WHERE PlayListId = ?;");
            stmt.setObject(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            playlistDetails = new ArrayList<>();
            while (rs.next()) {
                PlaylistDetail playlistDetail = new PlaylistDetail();

                playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
                playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
                playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
                playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
                playlistDetail.setDateAdded(LocalDateTime.parse(rs.getString("DateAdded")));
                playlistDetail.setNumber(rs.getInt("Number"));

                playlistDetails.add(playlistDetail);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectPlaylistDetails)");
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

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.PlaylistDetail WHERE \"Id\" = ?");
            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();
            playlistDetail = new PlaylistDetail();

            playlistDetail.setPlaylistId(UUID.fromString(rs.getString("PlaylistId")));
            playlistDetail.setPlaylist(selectPlaylist(playlistDetail.getVideoId()));
            playlistDetail.setVideoId(UUID.fromString(rs.getString("VideoId")));
            playlistDetail.setVideo(selectVideo(playlistDetail.getVideoId()));
            playlistDetail.setDateAdded(LocalDateTime.parse(rs.getString("DateAdded")));
            playlistDetail.setNumber(rs.getInt("Number"));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectPlaylistDetail)");
        return playlistDetail;
    }
    //endregion

    //region [ - deletePlaylistDetail(UUID playlistId, UUID videoId) - ] Tested
    public static void deletePlaylistDetail(UUID playlistId, UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.PlaylistDetail WHERE PlaylistId = ? AND VideoId = ?;");
            stmt.setObject(1, playlistId);
            stmt.setObject(2, videoId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deletePlaylistDetail)");
    }
    //endregion

    //region [ - deletePlaylistDetail(UUID videoId) - ] Tested
    public static void deletePlaylistDetail(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.PlaylistDetail WHERE VideoId = ?;");
            stmt.setObject(1, videoId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deletePlaylistDetail)");
    }
    //endregion

    //region [ - deletePlaylistDetails(UUID playlistId) - ] Tested
    public static void deletePlaylistDetails(UUID playlistId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.PlaylistDetail WHERE PlaylistId = ?;");
            stmt.setObject(1, playlistId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deletePlaylistDetail)");
    }
    //endregion

    //endregion

    //region [ - Comment - ]

    //region [ - insertComment(Comment comment) - ] Tested
    public static void insertComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertComment)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Comment(\"Id\", \"Message\", VideoId, SenderId,ParentCommentId) VALUES (?, ?, ?, ?, ?);");
            stmt.setObject(1, comment.getId());
            stmt.setString(2, comment.getContent());
            stmt.setObject(3, comment.getVideoId());
            stmt.setObject(4, comment.getSenderId());
            stmt.setObject(5, comment.getParentCommentId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertComment)");
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
            System.out.println("Opened database successfully (selectComments)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.Comment;");
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
                comment.setDateCommented(LocalDateTime.parse(rs.getString("DataCommented")));

                comments.add(comment);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectComments)");
        return comments;
    }
    //endregion

    //region [ - selectComments(UUID videoId) - ]
    public static ArrayList<Comment> selectComments(UUID videoId) {
        Connection c;
        PreparedStatement stmt;
        ArrayList<Comment> comments = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectComments)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.Comment WHERE videoId = ?;");
            stmt.setObject(1, videoId);
            ResultSet rs = stmt.executeQuery();
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
                comment.setDateCommented(LocalDateTime.parse(rs.getString("DataCommented")));

                comments.add(comment);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectComments)");
        return comments;
    }
    //endregion

    //region [ - selectComment(UUID Id) - ]
    public static Comment selectComment(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        Comment comment = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectComment)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.Comment WHERE \"Id\" = ?");
            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();
            comment = new Comment();

            comment.setId(UUID.fromString(rs.getString("Id")));
            comment.setVideoId(UUID.fromString(rs.getString("VideoId")));
            comment.setVideo(selectVideo(comment.getVideoId()));
            comment.setSenderId(UUID.fromString(rs.getString("SenderId")));
            comment.setSender(selectUser(comment.getSenderId()));
            comment.setParentCommentId(UUID.fromString(rs.getString("ParentCommentId")));
            if (comment.getParentCommentId() != null) {
                comment.setParentComment(selectComment(comment.getParentCommentId()));
            }
            comment.setDateCommented(LocalDateTime.parse(rs.getString("DataCommented")));

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectComment)");
        return comment;
    }
    //endregion

    //region [ - updateComment(Comment comment) - ] Tested
    public static void updateComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {

//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateComment)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Comment SET \"Message\" = ? WHERE \"Id\" = ?;");

            stmt.setString(1, comment.getContent());
            stmt.setObject(2 , comment.getId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (updateComment)");
    }
    //endregion

    //region [ - deleteComment(UUID Id) - ] Not Tested
    public static void deleteComment(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteComment)");

//            deleteACommentLikes(Id);
            stmt = c.prepareStatement("DELETE FROM ContentManagement.Comment WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteComment)");
    }
    //endregion

    //endregion

    //region [ - CommentLike - ]

    //region [ - insertCommentLike(CommentLike commentLike) - ] Tested
    public static void insertCommentLike(CommentLike commentLike) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertCommentLike)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.CommentLike(\"Id\", UserId, CommentId) VALUES (?, ?, ?);");
            stmt.setObject(1, commentLike.getId());
            stmt.setObject(2, commentLike.getUserId());
            stmt.setObject(3, commentLike.getCommentId());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertCommentLike)");
    }
    //endregion

    //region [ - selectCommentLikes() - ]
    public ArrayList<CommentLike> selectCommentLikes() {
        Connection c;
        Statement stmt;
        ArrayList<CommentLike> commentLikes = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCommentLikes)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.CommentLike;");
            commentLikes = new ArrayList<>();
            while (rs.next()) {
                CommentLike commentLike = new CommentLike();

                commentLike.setId(UUID.fromString(rs.getString("Id")));
                commentLike.setCommentId(UUID.fromString(rs.getString("CommentId")));
                commentLike.setComment(selectComment(commentLike.getCommentId()));
                commentLike.setUserId(UUID.fromString(rs.getString("UserId")));
                commentLike.setUser(selectUser(commentLike.getUserId()));

                commentLikes.add(commentLike);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectCommentLikes)");
        return commentLikes;
    }
    //endregion

    //region [ - selectCommentLikes(UUID commentId) - ]
    public ArrayList<CommentLike> selectCommentLikes(UUID commentId) {
        Connection c;
        Statement stmt;
        ArrayList<CommentLike> commentLikes = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCommentLikes)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ContentManagement.CommentLike;");
            commentLikes = new ArrayList<>();
            while (rs.next()) {
                CommentLike commentLike = new CommentLike();

                commentLike.setId(UUID.fromString(rs.getString("Id")));
                commentLike.setCommentId(UUID.fromString(rs.getString("CommentId")));
                commentLike.setComment(selectComment(commentLike.getCommentId()));
                commentLike.setUserId(UUID.fromString(rs.getString("UserId")));
                commentLike.setUser(selectUser(commentLike.getUserId()));

                commentLikes.add(commentLike);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectCommentLikes)");
        return commentLikes;
    }
    //endregion

    //region [ - selectCommentLike(UUID Id) - ]
    public CommentLike selectCommentLike(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        CommentLike commentLike = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectCommentLike)");

            stmt = c.prepareStatement("SELECT * FROM ContentManagement.CommentLike WHERE \"Id\" = ?");
            stmt.setObject(1, Id); // what is this
            ResultSet rs = stmt.executeQuery();
            commentLike = new CommentLike();

            commentLike.setId(UUID.fromString(rs.getString("Id")));
            commentLike.setCommentId(UUID.fromString(rs.getString("CommentId")));
            commentLike.setComment(selectComment(commentLike.getCommentId()));
            commentLike.setUserId(UUID.fromString(rs.getString("UserId")));
            commentLike.setUser(selectUser(commentLike.getUserId())); // why not commentLike.User

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectCommentLike)");
        return commentLike;
    }
    //endregion

    //region [ - deleteCommentLike(UUID Id) - ] Tested
    public static void deleteCommentLike(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteCommentLike)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.CommentLike WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteCommentLike)");
    }
    //endregion

    //region [ - deleteACommentLikes(UUID commentId) - ] Tested
    public static void deleteACommentLikes(UUID commentId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteCommentLike)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.CommentLike WHERE CommentId = ?;");
            stmt.setObject(1, commentId);

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully (deleteCommentLike)");
    }
    //endregion

    //endregion
    //endregion
}
