package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

import static java.sql.DriverManager.getConnection;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws IOException, SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName, Connection conn) throws Exception {
        String sql = String.format(
                "create table if not exists %s (id serial primary key);",
                tableName
        );
        connectToDB(tableName, sql, conn);
    }

    public void dropTable(String tableName, Connection conn) throws Exception {
        String sql = String.format("drop table %s;", tableName);
        connectToDB(tableName, sql, conn);
        System.out.printf("Table \"%s\" delighted", tableName);
    }

    public void addColumn(String tableName, String columnName, String type,
                                 Connection conn) throws Exception {
        String sql = String.format(
                "ALTER table %s ADD %s %s NULL;", tableName, columnName, type);
        connectToDB(tableName, sql, conn);
    }

    public void dropColumn(String tableName, String columnName, Connection conn)
            throws Exception {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        connectToDB(tableName, sql, conn);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName,
                                    Connection conn) throws Exception {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        connectToDB(tableName, sql, conn);
    }

    private void connectToDB(String tableName, String sql, Connection conn) throws Exception {
        try (Connection connection = conn) {
            try (Statement statement = connection.createStatement()) {
                statement.execute(sql);
                if(!sql.startsWith("drop table")){
                    System.out.println(getTableScheme(conn, tableName));
                }
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
        Properties props = new Properties();
        try(InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("sql.properties")) {
            props.load(in);
        }
        try(TableEditor  tableEditor = new TableEditor(props)){
            tableEditor.createTable("example_table222", tableEditor.connection);
            tableEditor.dropTable("example_table222", tableEditor.connection);
            tableEditor.addColumn("books", "sn", "int", tableEditor.connection);
            tableEditor.dropColumn("books", "sn", tableEditor.connection);
            tableEditor.renameColumn("employees", "name", "fullname", tableEditor.connection);
        }
    }
}
