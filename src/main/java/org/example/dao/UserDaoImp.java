package main.java.org.example.dao;

import main.java.org.example.Config.DataBaseConnectorConfig;
import main.java.org.example.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao{

    private final Connection conn;

    public UserDaoImp(Connection  connection)
    {
        this.conn=connection;
    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("SAVE error: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
                userList.add(user);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("FIND ALL error: " + e.getMessage());
        }

        return userList;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getId());
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Kullanıcı güncellendi (ID: " + user.getId() + ")");
            } else {
                System.out.println("⚠Güncellenecek kullanıcı bulunamadı (ID: " + user.getId() + ")");
            }

        } catch (SQLException e) {
            System.err.println(" UPDATE error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int deletedRows = ps.executeUpdate();

            if (deletedRows > 0) {
                System.out.println(" Kullanıcı silindi (ID: " + id + ")");
            } else {
                System.out.println("Silinecek kullanıcı bulunamadı (ID: " + id + ")");
            }

        } catch (SQLException e) {
            System.err.println("DELETE error: " + e.getMessage());
        }
    }
}
