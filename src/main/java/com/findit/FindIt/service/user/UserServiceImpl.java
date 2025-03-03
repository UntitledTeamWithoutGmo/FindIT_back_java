package com.findit.FindIt.service.user;

import com.findit.FindIt.entity.User;
import com.findit.FindIt.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{



    private UserRepository repository;


    @Override
    public User findUserById(int id) {
        Optional<User> user = repository.findUserById(id);
        return user.get();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
