package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.user.*;
import com.coffe.platform_api.entity.User;
import com.coffe.platform_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(UserMapper::toDTO)
                .orElse(null);
    }

    public UserDTO createUser(CreatedUserRequest request) {
        User user = UserMapper.fromCreateRequest(request);
        User saved = userRepository.save(user);
        return UserMapper.toDTO(saved);
    }

    public UserDTO updateUser(UUID id, UpdateUserRequest request) {
        return userRepository.findById(id)
                .map(user -> {
                    UserMapper.updateEntity(user, request);
                    User updated = userRepository.save(user);
                    return UserMapper.toDTO(updated);
                })
                .orElse(null);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}