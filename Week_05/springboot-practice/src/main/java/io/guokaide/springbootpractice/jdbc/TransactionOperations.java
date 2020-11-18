package io.guokaide.springbootpractice.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class TransactionOperations {
    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.println("===> init data");
        createTableAndInsertData();
        HashMap<String, Integer> salesForWeek = new HashMap<>();
        salesForWeek.put("x", 1);
        salesForWeek.put("y", 3);
        salesForWeek.put("w", 10);
        System.out.println("===> update data");
        updateCoffeeSales(salesForWeek);
        showDataAndDropTable();
    }

    private static void updateCoffeeSales(HashMap<String, Integer> salesForWeek) {
        String updateSales = "UPDATE COFFEES SET SALES = ? WHERE COF_NAME = ?";
        String updateTotal = "UPDATE COFFEES SET TOTAL = TOTAL + ? WHERE COF_NAME = ?";
        Connection connection;
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        try (PreparedStatement updateSalesStat = connection.prepareStatement(updateSales);
             PreparedStatement updateTotalStat = connection.prepareStatement(updateTotal);
             Statement rollBackStatement = connection.createStatement()) {
            connection.setAutoCommit(false);
            for (Map.Entry<String, Integer> e : salesForWeek.entrySet()) {
                updateSalesStat.setInt(1, e.getValue());
                updateSalesStat.setString(2, e.getKey());
                updateSalesStat.executeUpdate();
                // rollback
//                rollBackStatement.executeUpdate("INSERT INTO COFFEES VALUES ('x', 1, 1.00, 1, 1)");

                updateTotalStat.setInt(1, e.getValue());
                updateTotalStat.setString(2, e.getKey());
                updateTotalStat.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void createTableAndInsertData() {
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
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('x', 1, 1.00, 1, 1)");
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('y', 2, 2.00, 2, 2)");
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('z', 3, 3.00, 3, 3)");
            statement.executeUpdate("INSERT INTO COFFEES VALUES ('w', 4, 4.00, 4, 4)");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM COFFEES");
            showResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showDataAndDropTable() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM COFFEES");
            showResultSet(resultSet);
            statement.executeUpdate("DROP TABLE COFFEES");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void showResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.printf("COF_NAME: %s, SALES: %d, TOTAL: %d\n",
                    resultSet.getString(1), resultSet.getInt("SALES"), resultSet.getInt("TOTAL"));
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
