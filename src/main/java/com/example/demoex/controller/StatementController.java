package com.example.demoex.controller;

import com.example.demoex.model.Statement;
import com.example.demoex.service.StatementService;
import lombok.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statement")
public class StatementController {
    private final StatementService statementService;

    @GetMapping("/user")
    public String userStatement(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("statements", statementService.getUserStatement(user));
        return "userStatement";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminStatement(Model model) {
        model.addAttribute("statements", statementService.getAllStatement());
        return "adminStatement";
    }

    @GetMapping("/createForm")
    public String createStatementForm() {
        return "createStatement";
    }

    @PostMapping("/create")
    public String createStatement(@AuthenticationPrincipal User user, Statement statement) {
        statementService.create(statement, user);
        return "redirect:/statement/user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirmation")
    public String confirmationStatement(Statement statement){
        statementService.confirmation(statement);
        return "redirect:/statement/admin";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/rejected")
    public String rejectedStatement( Statement statement) {
        statementService.rejected(statement);
        return "redirect:/statement/admin";
    }
}
