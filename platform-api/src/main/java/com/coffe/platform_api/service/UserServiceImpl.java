package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.user.*;
import com.coffe.platform_api.entity.User;
import com.coffe.platform_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

@Override
public UserDTO getCurrentUser() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByUsername(username);
    return UserMapper.toDTO(user);
}

@Override
public UserDTO updateCurrentUser(UpdateUserRequest request) {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByUsername(username);
    if (request.getUsername() != null) user.setUsername(request.getUsername());
    if (request.getEmail() != null) user.setEmail(request.getEmail());
    userRepository.save(user);
    return UserMapper.toDTO(user);
}
}