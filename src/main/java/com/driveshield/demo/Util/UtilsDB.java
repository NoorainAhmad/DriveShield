package com.driveshield.demo.Util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // Import Statement class for creating tables

public class UtilsDB {

    // --- MySQL Connection Details ---
    private static final String DB_URL = "jdbc:mysql://localhost:3306/VehicleDb?serverTimezone=UTC";
    private static final String USER = "root"; // Replace with your MySQL username (often 'root')
    private static final String PASS = "root"; // Replace with your MySQL password
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() {
        Connection cn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName(DRIVER);
            System.out.println("Connecting to database...");
            // Establish the connection
            cn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Failed to connect to MySQL database: " + e.getMessage());
        }
        return cn;
    }

    public static void closeAllConnection(Connection cn, PreparedStatement ps, ResultSet rs) {
        // ... (This method remains the same as your original code)
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // The insert methods remain largely the same, just ensure table names match
    // MySQL
    public static void insertDataIntoRegister(Connection cn) throws SQLException {
        // Note: Your original code used a table name "Register" in the main method
        // but used "Register" and "Insurance" in these methods.
        // Make sure table names match what you created in MySQL workbench.
        String query = "INSERT INTO user_writers(name, date_of_birth, gender, address, date_of_join, underwriterId, password, isAdmin, isdeleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            for (int i = 1; i <= 1; i++) {
                ps.setString(1, "User" + i);
                ps.setDate(2, Date.valueOf("1990-01-" + (10 + i)));
                ps.setString(3, i % 2 == 0 ? "M" : "F");
                ps.setString(4, "Address" + i);
                ps.setDate(5, Date.valueOf("2023-01-" + (10 + i)));
                ps.setString(6, "U" + i);
                ps.setString(7, "Password" + i);
                ps.setBoolean(8, i == 1); // First user is admin
                ps.setBoolean(9, false); // Default value
                ps.executeUpdate();
            }
        }
    }

    public static void insertDataIntoInsurance(Connection cn) throws SQLException {
        String query = "INSERT INTO insurances(vehicleNo, make, model, startDate, endDate, premiumAmount, insuranceId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            for (int i = 1; i <= 2; i++) {
                ps.setString(1, "VNO" + i);
                ps.setString(2, "Make" + i);
                ps.setString(3, "Model" + i);
                ps.setDate(4, Date.valueOf("2023-02-" + (10 + i)));
                ps.setDate(5, Date.valueOf("2024-02-" + (10 + i)));
                ps.setDouble(6, 5000.0 + i * 100);
                ps.setString(7, "INS" + i);
                ps.executeUpdate();
            }
        }
    }

    public static void main(String[] args) {
        Connection cn = null;
        // Since you already created the tables in MySQL Workbench, we remove
        // the "CREATE TABLE" commands from the main method to avoid errors.
        try {
            cn = UtilsDB.getConnection();

            // Insert data into tables you created manually in MySQL Workbench
            insertDataIntoRegister(cn);
            System.out.println("Data inserted into user_writers table.");

            insertDataIntoInsurance(cn);
            System.out.println("Data inserted into insurances table.");

            // Display data from Register table (renamed user_writers in MySQL)
            PreparedStatement ps = cn.prepareStatement("SELECT * FROM user_writers");
            ResultSet rs = ps.executeQuery();
            System.out.println("User Writers Table:");
            while (rs.next()) {
                // Adjust index/names based on your column order/names
                System.out.println(
                        rs.getString("name") + ":" + rs.getDate("date_of_birth") + ":" + rs.getString("gender"));
            }
            rs.close();
            ps.close();

            // Display data from Insurance table (renamed insurances in MySQL)
            ps = cn.prepareStatement("SELECT * FROM insurances");
            rs = ps.executeQuery();
            System.out.println("Insurance Table:");
            while (rs.next()) {
                System.out.println(
                        rs.getString("vehicleNo") + ":" + rs.getString("make") + ":" + rs.getString("insuranceId"));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            // MySQL error codes will now appear here
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            closeAllConnection(cn, null, null);
        }
    }
}
