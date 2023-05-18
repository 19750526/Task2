package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByLogin(String login) {
        return userRepository.getByLogin(login);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User change(User user) {
        User entity = null;
        Optional<User> maybeUser = userRepository.findById(user.getLogin());
        if (maybeUser.isPresent()) {
            entity = maybeUser.get();
            entity.change(user);
            userRepository.save(entity);
        }
        return entity;
    }

    public User remove(String login) {
        User user = userRepository.getByLogin(login);
        userRepository.delete(user);
        return user;
    }

}
