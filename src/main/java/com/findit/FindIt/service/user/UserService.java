package com.findit.FindIt.service.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User findUserById(int id);
    List<User> findAll();



}
