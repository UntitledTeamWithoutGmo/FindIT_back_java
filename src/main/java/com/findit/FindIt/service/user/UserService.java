package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    User findUserById(int id);
    List<User> findAll();
    User saveUser(UserDTO dto);
    User updateUser(int id, UserDTO dto);
    void deleteUser(int id);


}
