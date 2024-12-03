package org.wemeet.sbjt.core;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> getUsersByStatus(String status) {
        return userRepository.findUsersByStatus(status);
    }

    @Transactional
    public void createUsersWithRollback() {
        // Insert the first user
        User user1 = new User();
        user1.setName("John Doe");
        user1.setEmail("john.doe@example.com");
        userRepository.save(user1);
        System.out.println("First user inserted: " + user1.getName());

        // Simulate an error for the second user to trigger rollback
        User user2 = new User();
        user2.setName("Jane Doe");
        user2.setEmail(null); // This violates a NOT NULL constraint on 'email'

        userRepository.save(user2); // This will throw a ConstraintViolationException
        System.out.println("Second user inserted: " + user2.getName());
    }
}
