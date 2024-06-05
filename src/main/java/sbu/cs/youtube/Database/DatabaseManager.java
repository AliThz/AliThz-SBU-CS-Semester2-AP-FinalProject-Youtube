package sbu.cs.youtube.Database;

import sbu.cs.youtube.Shared.POJO.Channel;
import sbu.cs.youtube.Shared.POJO.Notification;
import sbu.cs.youtube.Shared.POJO.Subscription;
import sbu.cs.youtube.Shared.POJO.User;

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

    //region [ - ArrayList<User> selectUsers() - ]
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

    //region [ - User selectUser(UUID Id) - ]
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



    //endregion
}
