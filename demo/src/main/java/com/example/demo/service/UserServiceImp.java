package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    // READ
    //показать детали одного пользователя с проверкой существования по id
    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void createUser(User user) {

        userRepository.save(user);
    }

        // UPDATE

    @Override
    public void updateUser(User user) {
        // Проверяем, существует ли пользователь с данным ID
        if (userRepository.existsById(user.getId())) {
            // Сохраняем обновлённого пользователя
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Пользователь с ID " + user.getId() + " не найден.");
        }

    }

    // DELETE
   @Override
    public boolean deleteUser(long id) {
       Optional<User> userOptional = userRepository.findById(id);
       if (userOptional.isPresent()){
           userRepository.deleteById(id);
           return true;
       }else{
           return false;
       }
   }
    @Override
    public User readUser(long id) {
        return userRepository.findById(id).orElse(null);
    }
}
