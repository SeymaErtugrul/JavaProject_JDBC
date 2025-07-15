package main.java.org.example.dao;

import main.java.org.example.user.User;

import java.util.List;

public interface UserDao {
    void createTable();
    void saveUser(User user);
    List<User> findAll();
    void update (User user);
    void delete(int id);
}
