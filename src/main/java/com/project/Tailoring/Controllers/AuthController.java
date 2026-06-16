package com.project.Tailoring.Controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.project.Tailoring.DTO.AuthRequest;
import com.project.Tailoring.DTO.RefreshTokenResponse;
import com.project.Tailoring.Entities.AppUser;
import com.project.Tailoring.Repository.AppUserRepository;
import com.project.Tailoring.Security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(
            @RequestBody AppUser user
    ) {

        user.setPassword(
                passwordEncoder.encode(
                        user.getPassword()
                )
        );

        userRepository.save(user);

        return "User Registered";
    }

    @PostMapping("/login")
    public String login(
            @RequestBody AuthRequest request
    ) {

        AppUser user =
                userRepository.findByUsername(
                        request.getUsername()
                ).orElse(null);

        if(user != null &&
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                )) {

            return jwtUtil.generateToken(
                    user.getUsername()
            );
        }

        return "Invalid Username or Password";
    }

    /**
     * Refresh token endpoint - requires valid JWT token
     * Extracts token from Authorization header and returns a new token with extended expiry
     */
    @PostMapping("/refresh-token")
    public RefreshTokenResponse refreshToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            // Extract token from Authorization header (format: "Bearer <token>")
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new RefreshTokenResponse(
                        null,
                        "Invalid Authorization header format. Expected: Bearer <token>"
                );
            }

            String token = authHeader.substring(7);

            // Validate the token
            if (!jwtUtil.validateToken(token)) {
                return new RefreshTokenResponse(
                        null,
                        "Token is invalid or expired"
                );
            }

            // Generate new token
            String newToken = jwtUtil.refreshToken(token);

            return new RefreshTokenResponse(
                    newToken,
                    "Token refreshed successfully"
            );

        } catch (Exception e) {
            return new RefreshTokenResponse(
                    null,
                    "Error refreshing token: " + e.getMessage()
            );
        }
    }
}