package com.driveshield.demo.Bean;

public class LoginResponse {
    private String token;
    private String username;
    private String role;
    private String underwriterId;
    private String message;
    private boolean success;

    public LoginResponse() {
    }

    public LoginResponse(String token, String username, String role, String underwriterId) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.underwriterId = underwriterId;
        this.success = true;
        this.message = "Login successful";
    }

    public static LoginResponse error(String message) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUnderwriterId() {
        return underwriterId;
    }

    public void setUnderwriterId(String underwriterId) {
        this.underwriterId = underwriterId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
