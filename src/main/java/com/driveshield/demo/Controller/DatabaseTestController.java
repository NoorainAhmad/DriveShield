package com.driveshield.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/test")
public class DatabaseTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db-connection")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            if (connection != null && !connection.isClosed()) {
                String dbName = connection.getCatalog();
                String dbUrl = connection.getMetaData().getURL();
                String dbUser = connection.getMetaData().getUserName();

                return String.format(
                        "✅ Database Connection Successful!\n\n" +
                                "Database Name: %s\n" +
                                "Database URL: %s\n" +
                                "Database User: %s\n" +
                                "Connection Valid: %s",
                        dbName, dbUrl, dbUser, connection.isValid(5));
            } else {
                return "❌ Connection is closed or null";
            }
        } catch (SQLException e) {
            return "❌ Database Connection Failed!\n\nError: " + e.getMessage();
        }
    }
}
