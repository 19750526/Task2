package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDefinitionDTO;
import com.example.demo.dto.UserMutationDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping("/users/")
    public List<UserDTO> users() {
        List<User> users = userService.getAll();
        return userMapper.toDTOs(users);
    }

    @GetMapping("/users/{login}")
    public UserDTO user(@PathVariable(name = "login") String login) {
        User user = userService.getByLogin(login);
        return userMapper.toDTO(user);
    }

    @PostMapping("/users/")
    public UserDTO add(@RequestBody UserDefinitionDTO dto) {
        User user = userMapper.fromDTO(dto);
        User userFromService = userService.create(user);
        return userMapper.toDTO(userFromService);
    }

    @PutMapping("/users/{login}")
    public UserDTO edit(@RequestBody UserMutationDTO dto, @PathVariable(name = "login") String login) {
        User user = userMapper.fromDTO(dto, login);
        User userFromService = userService.change(user);
        return userMapper.toDTO(userFromService);
    }

    @DeleteMapping("/users/{login}")
    public UserDTO delete(@PathVariable(name = "login") String login) {
        User user = userService.remove(login);
        return userMapper.toDTO(user);
    }
}
