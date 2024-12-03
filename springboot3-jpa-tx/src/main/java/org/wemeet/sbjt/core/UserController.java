package org.wemeet.sbjt.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/status/{status}")
    public List<User> getUsersByStatus(@PathVariable String status) {
        return userService.getUsersByStatus(status);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUsers() {
        try {
            userService.createUsersWithRollback();
            return ResponseEntity.ok("Users created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred: " + e.getMessage());
        }
    }
}
