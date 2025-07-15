package main.java.org.example;
import main.java.org.example.Config.DataBaseConnectorConfig;

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


            String insertSQL = "INSERT INTO users(name, email) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, "Ali");
            preparedStatement.setString(2, "ali@mail.com");
            int rows = preparedStatement.executeUpdate();
            System.out.println( rows + " satır eklendi.");
            preparedStatement.close();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }
            statement.close();
            DataBaseConnectorConfig.closeConntection();
            System.out.println("Bağlantı kapatıldı.");

        } catch (SQLException e) {
            System.err.println("SQL Hatası: " + e.getMessage());
        }
    }
}


