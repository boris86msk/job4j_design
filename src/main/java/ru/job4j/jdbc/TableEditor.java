package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws IOException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    public static void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s (%s);",
                tableName,
                "id serial primary key"
        );
        connectToDB(tableName, sql);
    }

    public static void dropTable(String tableName) throws Exception {
        String sql = String.format("drop table %s;", tableName);
        try (Connection connection = getCon()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.printf("Table \"%s\" delighted", tableName);
            }
        }
    }

    public static void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER table %s ADD %s %s NULL;", tableName, columnName, type);
        connectToDB(tableName, sql);
    }

    public static void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        connectToDB(tableName, sql);
    }

    public static void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        connectToDB(tableName, sql);
    }

    private static void connectToDB(String tableName, String sql) throws Exception {
        try (Connection connection = getCon()) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                System.out.println(getTableScheme(connection, tableName));
            }
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        createTable("example_table");
        dropTable("example_table");
        addColumn("books", "SN", "int");
        dropColumn("books", "SN");
        renameColumn("employees", "name", "fullname");
    }

    private static Connection getCon() throws Exception {
       /** try(InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("sql.properties")) {
            Config config = new Config(in);
            config.load(in);
        }*/
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/product_db";
        String login = "postgres";
        String password = "password";
        return DriverManager.getConnection(url, login, password);
    }

}
