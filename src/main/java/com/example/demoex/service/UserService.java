package com.example.demoex.service;

import com.example.demoex.model.Role;
import com.example.demoex.model.User;
import com.example.demoex.repo.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public void save(User user){
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

    public void deleteUser(Long id){
        this.userRepo.deleteById(id);
    }

    /** Метод для получения пользователя по ID
     * Используем лямба-выражение чтобы создать исключение в случае если пользователя в БД не окажется
     */

    public User getUserById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    }

    public List<User> findUserByName(String firstname){
        return userRepo.findByFirstnameContainingIgnoreCase(firstname);
    }

    public User getAuthorizedUser(org.springframework.security.core.userdetails.User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public void createAdmin(){
        User user = new User();
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);
    }
}
