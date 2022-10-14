package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private String url;
    private String login;
    private String password;
    private String driver;

    public ConnectionDemo(String path) {
        Config config = new Config(path);
        config.load();
        this.driver = config.value("driver");
        this.url = config.value("url");
        this.login = config.value("login");
        this.password = config.value("password");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionDemo cd = new ConnectionDemo("data/app.properties");
        Class.forName(cd.driver);
        try (Connection connection = DriverManager.getConnection(cd.url, cd.login, cd.password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
