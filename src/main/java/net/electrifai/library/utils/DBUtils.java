package net.electrifai.library.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection con;
    private static Statement stmt;

    private static void initDBConnection() {
        try {
            Class.forName(PropertiesFile.getProperty("testEnvironment.properties").getString("JDBC_DRIVER"));
            String dbUrl = PropertiesFile.getProperty("testEnvironment.properties").getString("dbUrl");
            String dbUserName = PropertiesFile.getProperty("testEnvironment.properties").getString("dbUserName");
            String dbPassword = PropertiesFile.getProperty("testEnvironment.properties").getString("dbPassword");
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = con.createStatement();
            LogManager.printInfoLog("DB Connection initiated");
        } catch (Exception e) {
            String log = " DB Connection failed";
            LogManager.printExceptionLog(e, log);
        }

    }

    public static List<String> getGivenColumnValueFromStatement(String queryStatement, String columnName) {
        List<String> SQLDataset = new ArrayList<String>();
        try {
            DBUtils.initDBConnection();
            ResultSet resultSet = stmt.executeQuery(queryStatement);
            while (resultSet.next()) {
                SQLDataset.add(resultSet.getString(columnName));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection();
        return SQLDataset;
    }

    public static List<Map<String, String>> getDataFromStatement(String queryStatement) {
        List<Map<String, String>> data = new ArrayList<>();
        try {
            Map<String, String> row;
            DBUtils.initDBConnection();
            ResultSet resultSet = stmt.executeQuery(queryStatement);
            ResultSetMetaData s1 = resultSet.getMetaData();
            int i = s1.getColumnCount();
            while (resultSet.next()) {
                row = new HashMap<>();
                for (int j = 1; j <= i; j++) {
                    row.put(s1.getColumnLabel(j), resultSet.getString(s1.getColumnLabel(j)));
                }
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBUtils.closeConnection();
        return data;
    }

    public static void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
                LogManager.printInfoLog("DB Connection closed");
            }
        } catch (Exception e) {
            String log = "Connection closed failed";
            LogManager.printExceptionLog(e, log);
        }

    }
}
