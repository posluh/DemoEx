package com.example.demoex.controller;


import com.example.demoex.model.User;
import com.example.demoex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    String mainPage(){
        return "userList";
    }

    @GetMapping("/all")
    String findAllUser(Model model, String firstname){
        if (firstname == null){
            model.addAttribute("users", userService.findAll());
            return "userList";
        }
        else {
            model.addAttribute("users", userService.findUserByName(firstname));
            return "userList";
        }
    }

    @GetMapping("/form-add")
    String getAddUserForm(){
        return "addUser";
    }

    @PostMapping("/save")
    String saveUser(User user){
        userService.save(user);
        return "redirect:/all";
    }

    @GetMapping("/delete-user/{id}")
    String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/all";
    }

    /** Метод для получения пользователя по его ID
     * После того как получили пользователя по ID, его данные автоматически подставляются в форму
     */

    @GetMapping("/edit-user/{id}")
    String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    /** Сохраняем изменения в БД
     */

    @PostMapping("/update-user/{id}")
    String updateUser(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/all";
    }
}