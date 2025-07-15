package main.java.org.example;
import main.java.org.example.Config.DataBaseConnectorConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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
            System.out.println("✅ 'users' tablosu başarıyla oluşturuldu.");
            statement.close();
            DataBaseConnectorConfig.closeConntection();
            System.out.println("Bağlantı kapatıldı.");

        } catch (SQLException e) {
            System.err.println("SQL Hatası: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Hata: " + e.getMessage());
        }

    }
}


