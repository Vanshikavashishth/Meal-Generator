package com.meal;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    // Shared user list (in real apps this would be a database)
    static List<User> users = new ArrayList<>(Arrays.asList(
        new User(1, "Admin",    "admin@meal.com", "admin123", "admin"),
        new User(2, "John Doe", "john@meal.com",  "user123",  "user")
    ));

    static int nextId = 3;

    // POST /api/auth/login
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> body) {
        String email    = body.get("email");
        String password = body.get("password");

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                Map<String, String> res = new HashMap<>();
                res.put("status", "success");
                res.put("role",   u.getRole());
                res.put("name",   u.getName());
                res.put("id",     String.valueOf(u.getId()));
                return res;
            }
        }

        Map<String, String> err = new HashMap<>();
        err.put("status",  "error");
        err.put("message", "Invalid email or password");
        return err;
    }

    // POST /api/auth/register
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> body) {
        String email = body.get("email");

        // Check if email already exists
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                Map<String, String> err = new HashMap<>();
                err.put("status",  "error");
                err.put("message", "Email already registered");
                return err;
            }
        }

        // Create new user (always role = "user")
        User newUser = new User(
            nextId++,
            body.get("name"),
            email,
            body.get("password"),
            "user"
        );
        users.add(newUser);

        Map<String, String> res = new HashMap<>();
        res.put("status", "success");
        res.put("role",   "user");
        res.put("name",   newUser.getName());
        res.put("id",     String.valueOf(newUser.getId()));
        return res;
    }
}