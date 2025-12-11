package com.driveshield.demo.Controller;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.driveshield.demo.Bean.*;
import com.driveshield.demo.Service.*;

// Enable Cross-Origin Resource Sharing (CORS) for all origins
@CrossOrigin(origins = "*")
// Mark this class as a REST controller to handle HTTP requests
@RestController
// Base URL for this controller is "/admin"
@RequestMapping("/admin")
public class adminController {

    // Service layer instance for handling business logic
    private Service service = new Service();

    // Gson instance for converting Java objects to JSON
    private Gson gson = new Gson();

    /**
     * Endpoint to search for a UserWriter by ID.
     * 
     * @param UserwriterId The ID of the UserWriter to search for.
     * @return The UserWriter object in JSON format.
     */
    @RequestMapping(value = "/searchUserWriterByID/{UserwriterId}", method = RequestMethod.GET)
    public String searchAdminById(@PathVariable String UserwriterId) {
        UserWriter user = service.searchByID(UserwriterId); // Fetch the user by ID
        String response = gson.toJson(user); // Convert the user to JSON
        return response;
    }

    /**
     * Endpoint to insert a new UserWriter.
     * 
     * @param userwriter The UserWriter object to insert.
     * @return A success or failure message in JSON format.
     */
    @RequestMapping(value = "/insertUserwriter", method = RequestMethod.POST)
    public String insertUserwriter(@RequestBody UserWriter userwriter) {
        // Set the role as Admin if the role is "true"
        boolean isRequested = userwriter.getRole().equalsIgnoreCase("true");
        userwriter.setAdmin(isRequested);

        // Add the UserWriter to the system
        boolean isSuccess = service.addUserWriter(userwriter);
        if (isSuccess) {
            return gson.toJson(userwriter); // Return the user object if insertion is successful
        } else {
            return gson.toJson("Userwriter registration failed");
        }
    }

    /**
     * Endpoint to delete a UserWriter by ID.
     * 
     * @param UserwriterId The ID of the UserWriter to delete.
     * @return A JSON response indicating success or failure.
     */
    @RequestMapping(value = "/deleteUserWriter/{UserwriterId}", method = RequestMethod.GET)
    public String deleteUserwriter(@PathVariable String UserwriterId) {
        boolean tt = service.deleteByUID(UserwriterId); // Delete the user by ID
        String response = gson.toJson(tt); // Convert the result to JSON
        return response;
    }

    /**
     * Endpoint for user login.
     * 
     * @param userWriter The UserWriter object containing login credentials.
     * @return A JSON response with the login result and user details.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserWriter userWriter) {
        // Check if the requested role is Admin
        // boolean isRequestedAdmin = userWriter.getRole().equalsIgnoreCase("admin");
        boolean isRequestedAdmin = userWriter.isAdmin();

        // Fetch the user from the database using name and password
        UserWriter loggedIn = service.login(userWriter.getName(), userWriter.getPassword());

        // Response map to hold the result and additional data
        Map<String, String> responseMap = new HashMap<>();

        if (loggedIn != null) {
            // Check if the default password is being used
            // if (loggedIn.getPassword().equals("Employee@12")) {
            // responseMap.put("result", "Update your password (default password
            // detected)");
            // responseMap.put("underwriterName", loggedIn.getName());
            // } else {
            // Validate the role and permissions
            if (loggedIn.getRole().equalsIgnoreCase("admin")) {
                isRequestedAdmin = true;

                if (isRequestedAdmin && loggedIn.isAdmin()) {
                    System.out.println("DEBUG Controller: Admin login successful");
                    responseMap.put("result", "Login successful: Admin");
                    responseMap.put("underwriterName", loggedIn.getName());
                } else if (!isRequestedAdmin && !loggedIn.isAdmin()) {
                    responseMap.put("result", "Login successful: User");
                    responseMap.put("underwriterName", loggedIn.getName());
                } else {
                    responseMap.put("result", "Invalid role or permissions");
                    responseMap.put("underwriterName", null); // Optional, for explicitness
                }
            }
        } else

        {
            responseMap.put("result", "Invalid username or password");
            responseMap.put("underwriterName", null);
        }

        // Convert the response map to JSON format
        return gson.toJson(responseMap);
    }

    /**
     * Endpoint to update the password for a UserWriter.
     * 
     * @param userwriter The UserWriter object containing the new password and
     *                   credentials.
     * @return A success or failure message in JSON format.
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String updatePassword(@RequestBody UserWriter userwriter) {
        String answer = "";
        String newPassword = userwriter.getPassword();
        String userId = userwriter.getUnderwriterId();
        String currentPassword = userwriter.getPassword(); // Current password provided by user

        // Verify the current password
        if (service.checkCurrentPassword(userId, currentPassword)) {
            // Update the password if the current password is correct
            Boolean result = service.updateUnderWriterPassword(userId, newPassword);
            if (result == true) {
                answer = "Password Updated Successfully";
            } else {
                answer = "Failed to update Password";
            }
        } else {
            answer = "Current Password You Entered is Incorrect";
        }

        return gson.toJson(answer);
    }

}