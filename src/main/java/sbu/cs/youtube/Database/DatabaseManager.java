package sbu.cs.youtube.Database;

import sbu.cs.youtube.Shared.POJO.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseManager {

    //region [ - Fields - ]
    private final String URL = "jdbc:postgresql://localhost:5432/Youtube-Development";
    private final String USER = "postgres";
    private final String PASSWORD = "musketeers";
    //endregion

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

            stmt = c.prepareStatement("INSERT INTO UserManagement.User(\"Id\", FullName, \"Email\", \"DateOfBirth\", \"Username\",\"Password\", \"JoinDate\") VALUES (?, ?, ?, ?, ?, ?,?);");
            stmt.setObject(1, user.getId());
            stmt.setString(2, user.getFullName());
            stmt.setString(3, user.getEmail());
            stmt.setObject(4, user.getDateOfBirth());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPassword());
            stmt.setObject(7, user.getJoinDate());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertUser)");
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
                user.setDateOfBirth(LocalDateTime.parse(rs.getString("DateOfBirth")));
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("Password"));
                user.setDateOfBirth(LocalDateTime.parse(rs.getString("JoinDate")));
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
    //endregion

    //region [ - selectUser(UUID Id) - ]
    public User selectUser(UUID Id) {
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

            user.setId(UUID.fromString(rs.getString("Id")));
            user.setFullName(rs.getString("FullName"));
            user.setEmail(rs.getString("Email"));
            user.setDateOfBirth(LocalDateTime.parse(rs.getString("DateOfBirth")));
            user.setSubscriptions(selectSubscriptions(user.getId()));
            user.setNotifications(selectNotifications(user.getId()));
            user.setLikedVideos(selectLikedVideos(user.getId()));
            user.setUsername(rs.getString("Username"));
            user.setPassword(rs.getString("Password"));
            user.setDateOfBirth(LocalDateTime.parse(rs.getString("JoinDate")));

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

    //region [ - updateUser(User user) - ]
    public void updateUser(User user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateUser)");

            stmt = c.prepareStatement("UPDATE UserManagement.User SET \"FullName\" = ?, \"Email\" = ?, \"DateOfBirth\" = ?, \"Username\" = ?, \"Password\" = ? WHERE \"Id\" = ?;");

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

    //region [ - deleteUser(UUID Id) - ]
    public void deleteUser(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
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

    //region [ - insertChannel(Channel user) - ]
    public void insertChannel(Channel user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertChannel)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Channel(\"Id\", CreatedId, \"Title\", \"Description\", \"DateCreated\") VALUES (?, ?, ?, ?, ?);");
            stmt.setObject(1, user.getId());
            stmt.setObject(2, user.getCreatorId());
            stmt.setString(3, user.getTitle());
            stmt.setString(4, user.getDescription());
            stmt.setObject(5, user.getDateCreated());

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

    //region [ - ArrayList<Channel> selectChannels() - ]
    public ArrayList<Channel> selectChannels() {
        Connection c;
        Statement stmt;
        ArrayList<Channel> users = null;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (selectChannels)");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM UserManagement.Channel;");
            users = new ArrayList<>();
            while (rs.next()) {
                Channel user = new Channel();
                user.setId(UUID.fromString(rs.getString("Id")));
                user.setCreatorId(UUID.fromString(rs.getString("Id")));
                user.setCreator(selectUser(user.getCreatorId()));
                user.setTitle(rs.getString("Title"));
                user.setDescription(rs.getString("Description"));
                user.setDateCreated(LocalDateTime.parse(rs.getString("DateOfBirth")));
                users.add(user);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (selectChannels)");
        return users;
    }
    //endregion

    //region [ - Channel selectChannel(UUID Id) - ]
    public Channel selectChannel(UUID Id) {
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

    //region [ - updateChannel(Channel user) - ]
    public void updateChannel(Channel user) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateChannel)");

            stmt = c.prepareStatement("UPDATE UserManagement.Channel SET \"CreatedId\" = ?, \"Title\" = ?, \"Description\" = ?, \"DateCreated\" = ?  WHERE \"Id\" = ?;");

            stmt.setObject(1, user.getCreatorId());
            stmt.setString(2, user.getTitle());
            stmt.setString(3, user.getDescription());
            stmt.setObject(4, user.getDateCreated());
            stmt.setObject(5, user.getId());
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

    //region [ - deleteChannel(UUID Id) - ]
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

    //region [ - insertSubscription(Subscription subscription) - ]
    public void insertSubscription(Subscription subscription) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertSubscription)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Subscription(SubscriberId, channelId, joinDate) VALUES (?, ?, ?);");
            stmt.setObject(1, subscription.getChannel());
            stmt.setObject(2, subscription.getSubscriberId());
            stmt.setObject(3, subscription.getJoinDate());
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

    //region [ - ArrayList<Subscription> selectSubscriptions() - ]
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

    //region [ - ArrayList<Subscription> selectSubscriptions(UUID userId) - ]
    public ArrayList<Subscription> selectSubscriptions(UUID userId) {
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

    //region [ - Subscription selectSubscription(UUID Id) - ]
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

    //region [ - deleteSubscription(UUID SubscriberId, UUID channelId) - ]
    public void deleteSubscription(UUID SubscriberId, UUID channelId) {
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

    //region [ - insertNotification(Notification notification) - ]
    public void insertNotification(Notification notification) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertNotification)");

            stmt = c.prepareStatement("INSERT INTO UserManagement.Notification(\"Id\", UserId, Message, IsRead, dateSent) VALUES (?, ?, ?, ?, ?);");
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

    //region [ - ArrayList<Notification> selectNotifications() - ]
    public ArrayList<Notification> selectNotifications() {
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

    //region [ - ArrayList<Notification> selectNotifications(UUID userId) - ]
    public ArrayList<Notification> selectNotifications(UUID userId) {
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

    //region [ - Notification selectNotification(UUID Id) - ]
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

    //region [ - updateNotification(Notification notification) - ]
    public void updateNotification(Notification notification) {
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

    //region [ - deleteNotification(UUID Id) - ]
    public void deleteNotification(UUID Id) {
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

    //region [ - insertUser(Category category) - ]
    public void insertUser(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertCategory)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Category(\"Id\", \"Title\") VALUES (?, ?);");
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

    //region [ - selectCategories() - ]
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

    //region [ - selectCategory(UUID Id) - ]
    public Category selectCategory(UUID Id) {
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

    //region [ - updateCategory(Category category) - ]
    public void updateCategory(Category category) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateCategory)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Category SET \"Title\" = ? WHERE \"Id\" = ?;");

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

    //region [ - deleteCategory(UUID Id) - ]
    public void deleteCategory(UUID Id) {
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

    //region [ - insertVideo(Video video) - ]
    public void insertVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideo)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Video(\"Id\", \"Title\", \"Description\", \"ChannelId\", \"Views\" , \"UploadDate\") VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setObject(1, video.getId());
            stmt.setString(2, video.getTitle());
            stmt.setString(3, video.getDescription());
            stmt.setObject(4, video.getChannelId());
            stmt.setInt(5, video.getViews());
            stmt.setObject(6, video.getUploadDate());

            stmt.executeUpdate();
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully (insertVideo)");
    }
    //endregion

    //region [ - selectVideos() - ]
    public ArrayList<Video> selectVideos() {
        Connection c;
        Statement stmt;
        ArrayList<Video> videos = null;
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

    //region [ - selectVideo(UUID Id) - ]
    public Video selectVideo(UUID Id) {
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

    //region [ - updateVideo(Video video) - ]
    public void updateVideo(Video video) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateVideo)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Video SET \"Title\" = ?, \"Description\" = ?, \"Views\" = ? WHERE \"Id\" = ?;");

            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getDescription());
            stmt.setInt(3, video.getViews());
            stmt.setObject(4, video.getChannelId());

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

    //region [ - deleteVideo(UUID Id) - ]
    public void deleteVideo(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideo)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.Video WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

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

    //region [ - insertVideoCategory(VideoCategory videoCategory) - ]
    public void insertVideoCategory(VideoCategory videoCategory) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideoCategory)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.VideoCategory(VideoId, CategoryId,) VALUES (?, ?);");
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

    //region [ - ArrayList<VideoCategory> selectVideoCategories() - ]
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

    //region [ - ArrayList<VideoCategory> selectVideoCategories(UUID videoId) - ]
    public ArrayList<VideoCategory> selectVideoCategories(UUID videoId) {
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

    //region [ - ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) - ]
    public ArrayList<VideoCategory> selectCategoryVideos(UUID categoryId) {
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

    //region [ - VideoCategory selectVideoCategory(UUID Id) - ]
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

    //region [ - deleteVideoCategory(UUID videoId, UUID categoryId) - ]
    public void deleteVideoCategory(UUID videoId, UUID categoryId) {
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

    //endregion

    //region [ - VideoLike - ]

    //region [ - insertVideoLike(VideoLike videoLike) - ]
    public void insertVideoLike(VideoLike videoLike) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertVideoLike)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.VideoLike(\"Id\", \"VideoId\", \"UserId\") VALUES (?, ?, ?);");
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
    public ArrayList<VideoLike> selectVideoLikes(UUID videoId) {
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
    public ArrayList<VideoLike> selectLikedVideos(UUID userId) {
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

    //region [ - deleteVideoLike(UUID videoId, UUID likeId) - ]
    public void deleteVideoLike(UUID videoId, UUID likeId) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteVideoLike)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.VideoLike WHERE videoId = ? AND liekId = ?;");
            stmt.setObject(1, videoId);
            stmt.setObject(2, likeId);

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

    //endregion

    //region [ - Playlist - ]

    //region [ - insertPlaylist(Playlist playlist) - ]
    public void insertPlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylist)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Playlist(\"Id\", \"Title\", \"Description\", \"CreatorId\",\"IsPublic\", \"DateCreated\" ) VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setObject(1, playlist.getId());
            stmt.setString(2, playlist.getTitle());
            stmt.setString(3, playlist.getDescription());
            stmt.setObject(4, playlist.getCreatorId());
            stmt.setBoolean(5, playlist.isPublic());
            stmt.setObject(6, playlist.getDateCreated());

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

    //region [ - updatePlaylist(Playlist playlist) - ]
    public void updatePlaylist(Playlist playlist) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updatePlaylist)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Playlist SET \"Title\" = ?, \"Description\" = ?, \"IsPublic\" = ? WHERE \"Id\" = ?;");

            stmt.setObject(1, playlist.getTitle());
            stmt.setString(2, playlist.getDescription());
            stmt.setObject(3, playlist.isPublic());

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

    //region [ - deletePlaylist(UUID Id) - ]
    public void deletePlaylist(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylist)");

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

    //region [ - insertPlaylistDetail(PlaylistDetail playlistDetail) - ]
    public void insertPlaylistDetail(PlaylistDetail playlistDetail) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertPlaylistDetail)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.PlaylistDetail(\"PlaylistId\", \"VideoId\", \"DateAdded\" , \"Number\") VALUES (?, ?, ?, ?);");
            stmt.setObject(1, playlistDetail.getPlaylistId());
            stmt.setObject(2, playlistDetail.getVideoId());
            stmt.setObject(3, playlistDetail.getDateAdded());
            stmt.setInt(4, playlistDetail.getNumber());

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

    //region [ - deletePlaylistDetail(UUID Id) - ]
    public void deletePlaylistDetail(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deletePlaylistDetail)");

            stmt = c.prepareStatement("DELETE FROM ContentManagement.PlaylistDetail WHERE \"Id\" = ?;");
            stmt.setObject(1, Id);

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

    //region [ - insertComment(Comment comment) - ]
    public void insertComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertComment)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.Comment(\"Id\", \"Content\", \"VideoId\", \"SenderId\",\"ParentCommentId\", \"DataCommented\") VALUES (?, ?, ?, ?, ?, ?);");
            stmt.setObject(1, comment.getId());
            stmt.setString(2, comment.getContent());
            stmt.setObject(3, comment.getVideoId());
            stmt.setObject(4, comment.getSenderId());
            stmt.setObject(5, comment.getParentCommentId());
            stmt.setObject(6, comment.getDateCommented());

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
    public ArrayList<Comment> selectComments(UUID videoId) {
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
    public Comment selectComment(UUID Id) {
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

    //region [ - updateComment(Comment comment) - ]
    public void updateComment(Comment comment) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (updateComment)");

            stmt = c.prepareStatement("UPDATE ContentManagement.Comment SET \"Content\" = ? WHERE \"Id\" = ?;");

            stmt.setString(1, comment.getContent());

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

    //region [ - deleteComment(UUID Id) - ]
    public void deleteComment(UUID Id) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (deleteComment)");

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

    //region [ - insertCommentLike(CommentLike commentLike) - ]
    public void insertCommentLike(CommentLike commentLike) {
        Connection c;
        PreparedStatement stmt;
        try {
//            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully (insertCommentLike)");

            stmt = c.prepareStatement("INSERT INTO ContentManagement.CommentLike(\"Id\", \"UserId\", \"CommentId\") VALUES (?, ?, ?);");
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

    //region [ - deleteCommentLike(UUID Id) - ]
    public void deleteCommentLike(UUID Id) {
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

    //endregion

    //endregion


}
