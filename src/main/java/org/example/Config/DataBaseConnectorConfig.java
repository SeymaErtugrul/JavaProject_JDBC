package main.java.org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnectorConfig {
    public static Connection connection;

    public static void setConnection()
    {
        try
        {
            connection= DriverManager.getConnection(DataBaseConfig.DataBase_URL, DataBaseConfig.DataBase_UserName, DataBaseConfig.DataBase_Password);

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
public static Connection getConnection(){
        return connection;

}
    public static void closeConntection()
    {
        try
        {
            connection.close();
        }

        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
