package com.example.demo.Controller;

import com.example.demo.service.UserService;
import com.example.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // CREATE
    // отображение формы добавления пользователя
    @GetMapping("/new")
    public String newUser() {
        return "createUser";
    }

    // CREATE
    // сохранение (создание) пользователя
    @PostMapping("/new")
     public String createUser(@ModelAttribute User user) {
       userService.createUser(user);
       return "redirect:/users/list";
   }

    // READ
    // показать детали одного пользователя

    @GetMapping("/details/{id}")
    public String getUserDetails(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "readUser";
    }

    // UPDATE
    // отображение формы обновления пользователя

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
       model.addAttribute("user", user);
        return "updateUser";
    }

    // UPDATE
    // обновление пользователя

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute User user) {
       userService.updateUser(user);
        return "redirect:/users/list";
    }

    // DELETE
    // удаление пользователя

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        boolean deleted = userService.deleteUser(id);
        model.addAttribute("deleted", deleted);
        return "redirect:/users/list?deleted=" + deleted;
    }

    // LIST ALL USERS

    @GetMapping({"", "/", "/list"})
    public String findAll(Model model, @RequestParam(value = "deleted", required = false) Boolean deleted) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("deleted", deleted != null && deleted);
        return "listUsers";
    }
}

