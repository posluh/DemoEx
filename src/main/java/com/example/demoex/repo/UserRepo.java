package com.example.demoex.repo;

import com.example.demoex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findByFirstnameContainingIgnoreCase(String firstname); // список пользователь игнорируя регистр
}
