package com.findit.FindIt.util;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDTO convertToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPatronymicName(user.getPatronymicName());
        dto.setEmail(user.getEmail());
        dto.setLevel(user.getLevel());

        return dto;
    }
}
