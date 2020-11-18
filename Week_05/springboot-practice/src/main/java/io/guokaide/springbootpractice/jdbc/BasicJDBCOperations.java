package io.guokaide.springbootpractice.jdbc;

import java.sql.*;

public class BasicJDBCOperations {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            String createString = "CREATE table COFFEES " +
                    "(COF_NAME varchar(32) NOT NULL, " +
                    "SUP_ID int NOT NULL, " +
                    "PRICE numeric(10,2) NOT NULL, " +
                    "SALES integer NOT NULL, " +
                    "TOTAL integer NOT NULL, " +
                    "PRIMARY KEY (COF_NAME))";
            statement.executeUpdate(createString);

            System.out.println("===> 增");
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('x', 1, 1.00, 1, 1)");
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('y', 1, 1.00, 1, 2)");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COFFEES");

            showResultSet(resultSet);

            System.out.println("===> 改");
            statement.executeUpdate("UPDATE COFFEES SET PRICE = 2.0 WHERE COF_NAME = 'x'");
            resultSet = statement.executeQuery("SELECT * FROM COFFEES");
            showResultSet(resultSet);

            System.out.println("===> 删");
            statement.executeUpdate("DELETE FROM COFFEES where COF_NAME = 'x'");
            resultSet = statement.executeQuery("SELECT * FROM COFFEES");
            showResultSet(resultSet);

            statement.executeUpdate("DROP TABLE COFFEES");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void showResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.printf("COF_NAME: %s, PRICE: %s\n", resultSet.getString(1), resultSet.getString("PRICE"));
        }
    }

    private static Connection getConnection() throws SQLException {
        System.setProperty("jdbc.drivers", "com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        String password = "rootroot";
        return DriverManager.getConnection(url, username, password);
    }
}
