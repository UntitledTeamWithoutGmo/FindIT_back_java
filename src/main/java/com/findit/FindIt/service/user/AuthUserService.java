package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {
    ResponseEntity<String> createAuth(UserLoginDto dto);
}
