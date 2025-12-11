package com.driveshield.demo.Bean;

/**
 * Data Transfer Object (DTO) for password update requests.
 * This class encapsulates the data needed to update a user's password.
 */
public class UpdatePasswordRequest {

    private String underwriterId;
    private String oldPassword;
    private String newPassword;
    private String name;

    // Default constructor
    public UpdatePasswordRequest() {
    }

    // Parameterized constructor
    public UpdatePasswordRequest(String underwriterId, String oldPassword, String newPassword) {
        this.underwriterId = underwriterId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UpdatePasswordRequest(String underwriterId, String oldPassword, String newPassword, String name) {
        this.underwriterId = underwriterId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(String underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordRequest{" +
                "underwriterId='" + underwriterId + '\'' +
                ", oldPassword='[PROTECTED]'" +
                ", newPassword='[PROTECTED]'" +
                '}';
    }
}
