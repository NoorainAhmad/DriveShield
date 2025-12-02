package com.driveshield.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "🚗 Welcome to Drive Shield - Vehicle Management System\n\n" +
                "Available Endpoints:\n" +
                "- GET  /api/test/db-connection - Test database connection\n" +
                "- POST /admin/login - User login\n" +
                "- POST /admin/insertUserwriter - Register new user\n" +
                "- GET  /admin/searchUserWriterByID/{id} - Search user by ID\n" +
                "- GET  /admin/deleteUserWriter/{id} - Delete user by ID\n" +
                "- POST /admin/updatePassword - Update user password\n\n" +
                "Application is running successfully! ✅";
    }
}
