package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.dto.UserRegisterDTO;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.exception.UserNotFoundException;
import com.findit.FindIt.repository.UserRepository;
import com.findit.FindIt.util.PasswordValidator;
import com.findit.FindIt.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findUserById(int id) {

        return UserMapper.convertToDto(userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found")));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO saveUser(UserRegisterDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPatronymicName(dto.getPatronymicName());
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordValidator.validatePassword(dto.getPassword()));
        user.setLevel(0);

        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(int id, UserDTO dto) {
        Optional<User> userOpt = userRepository.findUserById(id);
        User userNew = userOpt.orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found"));
        userNew.setEmail(dto.getEmail());
        userNew.setName(dto.getName());
        userNew.setSurname(dto.getSurname());
        userNew.setPatronymicName(dto.getPatronymicName());
        userNew.setLevel(dto.getLevel());
        return UserMapper.convertToDto(userRepository.save(userNew));

    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findUserById(id);
        userRepository.delete(user
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found")));
    }


}
