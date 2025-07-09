package com.coffe.platform_api.dto.user;
import com.coffe.platform_api.entity.User;

//Método para mapear entre la entidad User y el DTO UserDTO
//UserDTO dto = UserMapper.toDTO(user);
//Para crear entidad desde request:
//User user = UserMapper.fromCreateRequest(request);
//Para actualizar entidad desde request:
//UserMapper.updateEntity(user, updateRequest);

public class UserMapper {
        public static UserDTO toDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }

    public static User fromCreateRequest(CreatedUserRequest req) {
        if (req == null) return null;
        return new User(
            req.getUsername(),
            req.getEmail(),
            req.getPassword(),
            req.getRole()
        );
    }

    public static void updateEntity(User user, UpdateUserRequest req) {
        if (user == null || req == null) return;
        if (req.getUsername() != null) user.setUsername(req.getUsername());
        if (req.getEmail() != null) user.setEmail(req.getEmail());
        if (req.getPassword() != null) user.setPassword(req.getPassword());
        if (req.getRole() != null) user.setRole(req.getRole());
    }
}
