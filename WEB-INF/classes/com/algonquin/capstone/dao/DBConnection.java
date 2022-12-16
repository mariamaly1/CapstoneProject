package com.algonquin.capstone.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.google.gson.*;


public class DBConnection {

    private static String dbPort = "3306";
    private static String dbUsername = "root";
    private static String dbPassword = "secret";
    private static String dbName = "calendarPin";
    private static String sqlCommandInsertKeys = "";
    private static String sqlCommandInsertValues = "";
    private static String sqlCommandSelectKeys = "";
    private static String sqlCommandSelectValues = "";
    private static String sqlInsertCommand = "INSERT INTO `";
    private static String sqlSelectCommand = "SELECT * FROM `";
    private static String userId = null;
    private static Object row = null;
    
    private static int sizeOfMap = 0;
    
    public static Connection getConnectionToDatabase() {
        Connection connection = null;

        System.out.println("getConnectionToDatabase.");

        try {
            // load the driver class
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("MySQL JDBC Driver Registered!");

            // get hold of the DriverManager
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:"+dbPort+"/"+dbName+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    dbUsername, dbPassword);

        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();

        }

        catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();

        }

        if (connection != null) {
            System.out.println("Connection made to DB!");

        }
        return connection;
    }
    
    @SuppressWarnings("unchecked")
	public static boolean insertIntoDatabase(Connection connection, String dbName, String tableName, HashMap userEntities) {
    	initialize();
    	sqlInsertCommand += dbName+"`.`"+tableName+"`";
    	sizeOfMap = userEntities.size();
    	userEntities.forEach((key, value) -> {
    		if (sizeOfMap == 1) {
    			sqlCommandInsertKeys += "`"+key+"`)";
    			sqlCommandInsertValues += "'"+value+"')";
    		}
    		else {
    			sqlCommandInsertKeys += "`"+key+"`,";
    			sqlCommandInsertValues += "'"+value+"',";
    		}
    		sizeOfMap --;
    	});
    	
		sqlInsertCommand += "("+sqlCommandInsertKeys + " VALUES ("+sqlCommandInsertValues+";";		
        PreparedStatement statement;
        int rs= 0;
		try {
			statement = connection.prepareStatement(sqlInsertCommand);
			rs = statement.executeUpdate(sqlInsertCommand);
			if (rs == 1) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    
    @SuppressWarnings("unchecked")
    public static ResultSet selectFromDatabase(Connection connection, String dbName, String tableName, HashMap userEntities) {
    	initialize();
    	sqlSelectCommand += dbName+"`.`"+tableName+"` WHERE ";
    	sizeOfMap = userEntities.size();
    	userEntities.forEach((key, value) -> {
    		sqlSelectCommand += key+"='"+value+"'";
    
    		if (sizeOfMap > 1) {
    			sqlSelectCommand += " AND ";
    		}
    		sizeOfMap--;
    	});
    	sqlSelectCommand +=";";
    	System.out.println(sqlSelectCommand);
    	PreparedStatement statement;
        ResultSet rs= null;
		try {
			statement = connection.prepareStatement(sqlSelectCommand);
			rs = statement.executeQuery(sqlSelectCommand);
			
			if (rs.next()) {
				
				if (tableName.equals("users")) {
					userId = rs.getString("UserId");
				} else if(tableName.equals("timeframes")) {
					row = rs;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return rs;
    }
    
    public static String getUserId() {
    	return userId;
    }
    
    public static Object getDBRow() {
    	return row;
    }
    
    private static void initialize() {
    	sqlInsertCommand = "INSERT INTO `";
    	sqlCommandInsertKeys = "";
    	sqlCommandInsertValues = "";
    	
    	sqlSelectCommand = "SELECT * FROM `";
    }
    
}
