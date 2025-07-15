package main.java.org.example.dao;

import main.java.org.example.user.User;

import java.sql.Connection;
import java.util.List;

public class UserDaoImp implements UserDao{

    private final Connection connection;

    public UserDaoImp(Connection  connection)
    {
        this.connection=connection;
    }
    @Override
    public void createTable() {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }
}
