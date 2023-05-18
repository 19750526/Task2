package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDefinitionDTO;
import com.example.demo.dto.UserMutationDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setLogin(entity.getLogin());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        return dto;
    }

    public List<UserDTO> toDTOs(List<User> entities) {
        List<UserDTO> dtos = new ArrayList<>();
        for (User entity : entities) {
            dtos.add(toDTO(entity));
        }
        return dtos;
    }

    public User fromDTO(UserDefinitionDTO dto) {
        User entity = new User();
        entity.setLogin(dto.getLogin());
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public User fromDTO(UserMutationDTO dto, String login) {
        User entity = new User();
        entity.setLogin(login);
        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        return entity;
    }
}
