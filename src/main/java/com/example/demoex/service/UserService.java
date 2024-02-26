package com.example.demoex.service;

import com.example.demoex.model.Role;
import com.example.demoex.model.User;
import com.example.demoex.repo.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void registration(User user) {
        user.setFirstname(user.getFirstname());
        user.setSurname(user.getSurname());
        user.setLastname(user.getLastname());
        user.setUsername(user.getUsername());
        user.setNumber(user.getNumber());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

    }
}
