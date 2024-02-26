package com.example.demoex.repo;

import com.example.demoex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
//    List<User> findByNameContainingIgnoreCase(String name); список пользователь игнорируя регистр
}
