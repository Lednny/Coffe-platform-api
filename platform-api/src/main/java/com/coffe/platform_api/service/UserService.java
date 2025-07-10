package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.user.*;

public interface UserService {
    UserDTO getCurrentUser();
    UserDTO updateCurrentUser(UpdateUserRequest request);
}