package net.electrifai.library.utils;

import org.testng.Assert;

import java.sql.*;
import java.util.*;

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
            Assert.fail(log);
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
            String log = "Unable get data from DB";
            LogManager.printExceptionLog(e, log);
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
            String log = "Unable get data from DB";
            LogManager.printExceptionLog(e, log);
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

    public static List<String> getRowDataAsList(String sqlQuery, Map<Integer, String> queryParams) {
        PreparedStatement query = null;
        List<String> rowData = new ArrayList<>();
        DBUtils.initDBConnection();
        try {
            query = con.prepareStatement(sqlQuery);
            for (int i = 1; i <= queryParams.size(); i++) {
                query.setString(i, queryParams.get(i));
            }
            ResultSet resultSet = query.executeQuery();

            resultSet.next();
            for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                rowData.add(resultSet.getString(j));
            }

        } catch (Exception exception) {
            String log = "Unable get data from DB";
            LogManager.printExceptionLog(exception, log);
        } finally {

            if (query != null) {
                try {
                    query.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBUtils.closeConnection();
            }
        }
        return rowData;
    }
}
