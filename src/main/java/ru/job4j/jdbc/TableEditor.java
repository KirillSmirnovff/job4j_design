package ru.job4j.jdbc;

import org.postgresql.util.PSQLException;

import java.io.*;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    private Statement statement;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        this.connection = DriverManager.getConnection(url, login, password);
        statement = connection.createStatement();
    }

    public void createTable(String tableName) throws SQLException {
        String sql = String.format(
                "create table if not exists %s();", tableName
        );
        statement.execute(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "drop table if exists %s;", tableName
        );
        statement.execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "alter table if exists %s add column if not exists %s %s;", tableName, columnName, type
        );
        statement.execute(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s drop column if exists %s;", tableName, columnName
        );
        statement.execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        String sql = String.format(
                "alter table if exists %s rename %s to %s;", tableName, columnName, newColumnName
        );
        statement.execute(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws SQLException {
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

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        InputStream in = new FileInputStream("./data/app.properties");
        Properties config = new Properties();
        config.load(in);
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("cpu");
        System.out.println(getTableScheme(tableEditor.connection, "cpu"));
        tableEditor.addColumn("cpu", "amd", "varchar(255)");
        System.out.println(getTableScheme(tableEditor.connection, "cpu"));
        tableEditor.renameColumn("cpu", "amd", "intel");
        System.out.println(getTableScheme(tableEditor.connection, "cpu"));
        tableEditor.dropColumn("cpu", "intel");
        System.out.println(getTableScheme(tableEditor.connection, "cpu"));
        tableEditor.dropTable("cpu");
        try {
            String schema = getTableScheme(tableEditor.connection, "cpu");
            System.out.println(schema);
        } catch (PSQLException e) {
            System.out.println("Table is not exists");
        }
    }
}