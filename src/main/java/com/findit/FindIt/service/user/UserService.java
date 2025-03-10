package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.dto.UserLoginDto;
import com.findit.FindIt.dto.UserRegisterDTO;
import com.findit.FindIt.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserDTO findUserById(int id);
    List<UserDTO> findAll();
    UserDTO saveUser(UserRegisterDTO dto);
    UserDTO updateUser(int id, UserDTO dto);
    void deleteUser(int id);
    ResponseEntity<String> createAuth(UserLoginDto dto);


}
