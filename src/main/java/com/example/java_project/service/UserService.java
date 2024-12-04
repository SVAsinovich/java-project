package com.example.java_project.service;

import com.example.java_project.domain.model.Role;
import com.example.java_project.domain.model.User;
import com.example.java_project.exception.EntityAlreadyExistsException;
import com.example.java_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new EntityAlreadyExistsException("User with username already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException("User with email already exists");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username not found"));

    }

    //For Spring Security
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    //Get current user from context
    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Deprecated
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }
}
