package main.java.org.example;
import main.java.org.example.Config.DataBaseConnectorConfig;
import main.java.org.example.dao.UserDaoImp;
import main.java.org.example.user.User;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataBaseConnectorConfig.setConnection();
            System.out.println("Bağlantı başarılı!");
            Connection connection = DataBaseConnectorConfig.getConnection();
          Statement statement = connection.createStatement();
            String createTableSQL = """
                        CREATE TABLE IF NOT EXISTS users (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255),
                            email VARCHAR(255)
                        );
                    """;
            statement.execute(createTableSQL);
            System.out.println("'users' tablosu başarıyla oluşturuldu.");

//
//            String insertSQL = "INSERT INTO users(name, email) VALUES (?, ?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
//            preparedStatement.setString(1, "Ali");
//            preparedStatement.setString(2, "ali@mail.com");
//            int rows = preparedStatement.executeUpdate();
//            System.out.println( rows + " satır eklendi.");
//            preparedStatement.close();
//
//            String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
//            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
//            updateStatement.setString(1, "Can");
//            updateStatement.setInt(2, 1);
//
//            int updatedRows = updateStatement.executeUpdate();
//            System.out.println(" Güncellenen satır sayısı: " + updatedRows);
//            updateStatement.close();
//
//            String deleteSQL = "DELETE FROM users WHERE id = ?";
//            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
//            deleteStatement.setInt(1, 4);
//
//            int deletedRows = deleteStatement.executeUpdate();
//            System.out.println("Silinen satır sayısı: " + deletedRows);
//            deleteStatement.close();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String email = resultSet.getString("email");
//
//                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
//            }
//
//            statement.close();

            User user=new User(0,"seyma","seyma@gmail.com");
            UserDaoImp userDaoImp=new UserDaoImp(connection);
            userDaoImp.saveUser(user);

            DataBaseConnectorConfig.closeConntection();
            System.out.println("Bağlantı kapatıldı.");


        }catch (SQLException e) {
            System.err.println("SQL Hatası: " + e.getMessage());
        }
    }
}


