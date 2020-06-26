package pl.lodz.p.edu.soap.converter;

import pl.lodz.p.edu.model.Users.User;
import pl.lodz.p.edu.soap.model.UserDTO;

import java.util.UUID;

public class ToDomainModel {

    public static User fromUserDTOtoUser(UserDTO userDTO) {

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setType(userDTO.getType());
        user.setIsActive(userDTO.isActive());
        user.setUserId(UUID.fromString(userDTO.getUserId()));

        return user;
    }
}
