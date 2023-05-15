package net.electrifai.library.utils;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DBUtils {
    private static Connection con;
    private static Statement stmt;
    private static boolean executeDBcmd=true;

    private static void initDBConnection() {
        if(executeDBcmd){
            File dir = new File(System.getProperty("user.dir")+"\\src\\test\\resources\\dbcmd");
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", "Start","dbConnection.bat");
            pb.directory(dir);
            try {
                Process p = pb.start();
                p.waitFor(30, TimeUnit.SECONDS);
            } catch (IOException | InterruptedException e) {
                String log="Data base port forward failed";
                LogManager.printExceptionLog(e,log);
                Thread.currentThread().interrupt();
            }
            executeDBcmd=false;
        }
        try {

            CompositeConfiguration prop=PropertiesFile.getProperty("testEnvironment.properties");
            Class.forName(prop.getString("JDBC_DRIVER"));
            String dbUrl = prop.getString("dbUrl");
            String dbUserName = prop.getString("dbUserName");
            String dbPassword = prop.getString("dbPassword");
            con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            stmt = con.createStatement();
            LogManager.printInfoLog("DB Connection initiated");
        } catch (SQLException | ClassNotFoundException | ConfigurationException | FileNotFoundException e) {
            String log = " DB Connection failed";
            LogManager.printExceptionLog(e, log);
            Assert.fail(log);
        }
    }

    public static List<String> getGivenColumnValueFromStatement(String queryStatement, String columnName) {
        List<String> sqlDataset = new ArrayList<String>();
        try {
            DBUtils.initDBConnection();
            ResultSet resultSet = stmt.executeQuery(queryStatement);
            while (resultSet.next()) {
                sqlDataset.add(resultSet.getString(columnName));
            }
        } catch (Exception e) {
            String log = "Unable get data from DB";
            LogManager.printExceptionLog(e, log);
        }
        DBUtils.closeConnection();
        return sqlDataset;
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
