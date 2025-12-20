package com.driveshield.demo.Controller;

import com.driveshield.demo.Bean.LoginRequest;
import com.driveshield.demo.Bean.LoginResponse;
import com.driveshield.demo.Bean.UserWriter;
import com.driveshield.demo.Service.Service;
import com.driveshield.demo.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private Service service = new Service();

    /**
     * JWT Login endpoint
     * Returns a JWT token on successful authentication
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate user using existing service
        UserWriter user = service.login(username, password);

        if (user != null) {
            // Determine role
            String role = user.isAdmin() ? "admin" : "user";

            // Generate JWT token
            String token = jwtUtil.generateToken(user.getName(), role, user.getUnderwriterId());

            // Create response
            LoginResponse response = new LoginResponse(
                    token,
                    user.getName(),
                    role,
                    user.getUnderwriterId());

            return ResponseEntity.ok(response);
        } else {
            // Invalid credentials
            return ResponseEntity.status(401).body(LoginResponse.error("Invalid username or password"));
        }
    }

    /**
     * Validate token endpoint - useful for checking if token is still valid
     */
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                String role = jwtUtil.extractRole(token);
                return ResponseEntity.ok("Token valid for user: " + username + " with role: " + role);
            }
        }
        return ResponseEntity.status(401).body("Invalid or expired token");
    }
}
