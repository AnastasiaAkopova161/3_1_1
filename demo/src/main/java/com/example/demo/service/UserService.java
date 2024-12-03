package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    //абстракция, методы для управления пользователями

    void createUser(User user);

    User readUser(long id);

    void updateUser(User user);

   boolean deleteUser(long id);

    List<User> findAll();

    User findById(Long id);
}
