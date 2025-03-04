package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository repository;

    @Override
    public User findUserById(int id) {
        Optional<User> user = repository.findUserById(id);
        return user.get();
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User saveUser(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPatronymicName(dto.getPatronymicName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setLevel(dto.getLevel());
        return repository.save(user);
    }

    @Override
    public User updateUser(int id, UserDTO dto) {
        Optional<User> userOpt = repository.findUserById(id);
        User userNew = userOpt.get();
        userNew.setEmail(dto.getEmail());
        userNew.setName(dto.getName());
        userNew.setSurname(dto.getSurname());
        userNew.setPatronymicName(dto.getPatronymicName());
        userNew.setPassword(dto.getPassword());
        userNew.setLevel(dto.getLevel());
        return repository.save(userNew);

    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = repository.findUserById(id);
        User userDel = user.get();
        repository.delete(userDel);
    }


}
