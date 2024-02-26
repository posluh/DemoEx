package com.example.demoex.repo;

import com.example.demoex.model.Statement;
import com.example.demoex.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementRepo extends JpaRepository<Statement, Long> {
    List<Statement> findStatementByUser(User user);
}
