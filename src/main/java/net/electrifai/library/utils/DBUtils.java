package net.electrifai.library.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    static Connection con;
    static List<String> SQLDataset = new ArrayList<String>();
    static List<Map<String, String>> data = new ArrayList<>();
    static Statement stmt;

    private static void initDBConnection() {
        try {
            Class.forName(PropertiesFile.getProperty("testEnvironment.properties").getString("JDBC_DRIVER"));
            String dbUrl = PropertiesFile.getProperty("testEnvironment.properties").getString("dbUrl");
            String dbUserName = PropertiesFile.getProperty("testEnvironment.properties").getString("dbUserName");
            String dbPassword = PropertiesFile.getProperty("testEnvironment.properties").getString("dbPassword");
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = con.createStatement();
        } catch (Exception e) {
            String log = " DB Connection failed";
            LogManager.printExceptionLog(e, log);
        }

    }

    public static List<String> getGivenColumnValueFromStatement(String queryStatement, String columnName) {
        try {
            SQLDataset.clear();
            //DBUtils.initDBConnection();
            ResultSet resultSet = stmt.executeQuery(queryStatement);
            while (resultSet.next()) {
                SQLDataset.add(resultSet.getString(columnName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SQLDataset;
    }

    public static List<Map<String, String>> getDataFromStatement(String queryStatement, String columnName) {
        try {
            Map<String, String> row;
            data.clear();
            DBUtils.initDBConnection();
            ResultSet resultSet = stmt.executeQuery(queryStatement);
            ResultSetMetaData s1 = resultSet.getMetaData();
            String s2 = s1.getColumnLabel(1);
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
        return data;
    }

    public static void closeConnection() {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            String log = "Connection closed failed";
            LogManager.printExceptionLog(e, log);
        }

    }
}
