package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines().filter(e -> {
                        if (!e.contains(";")) {
                            throw new IllegalArgumentException("the string does not contain \";\"");
                        }
                        return true;
                    })
                    .map(e -> e.substring(0, e.length() - 1))
                    .map(e -> e.split(";", 2))
                    .filter(e -> {
                        if (e[0].isEmpty() || e[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    "there is no value before or after the equal sign"
                            );
                        }
                        return true;
                    })
                    .map(e -> new User(e[0], e[1]))
                    .collect(Collectors.toList());
            return users;
        }

    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("url"),
                cfg.getProperty("login"),
                cfg.getProperty("password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into us_test"
                        + "(name, email) values(?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("sql.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "dump.txt");
        db.save(db.load());
    }
}
