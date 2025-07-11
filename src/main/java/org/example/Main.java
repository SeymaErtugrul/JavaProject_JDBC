package main.java.org.example;

import org.example.Config.DataBaseConfig;
import org.example.Config.DataBaseConnectorConfig;

import javax.xml.crypto.Data;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DataBaseConnectorConfig.setConnection();
        System.out.println("Bağlantı başarılı!");
        DataBaseConnectorConfig.closeConntection();
    }
}