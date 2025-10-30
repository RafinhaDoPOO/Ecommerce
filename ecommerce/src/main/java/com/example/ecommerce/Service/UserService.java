package com.example.ecommerce.Service;

import com.example.ecommerce.Entity.Role;
import com.example.ecommerce.Entity.User;
import com.example.ecommerce.Repository.RoleRepository;
import com.example.ecommerce.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, String email, String roleName) {
        Role role = roleRepository.findByName(roleName)
            .orElseThrow(() -> new RuntimeException("Role não encontrada"));

        User user = new User(username, passwordEncoder.encode(password), email);
        user.addRole(role);

        return userRepository.save(user);
    }
}
