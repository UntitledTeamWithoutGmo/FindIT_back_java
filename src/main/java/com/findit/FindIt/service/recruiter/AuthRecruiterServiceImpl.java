package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.RecruiterLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthRecruiterServiceImpl implements AuthRecruiterService{

    @Override
    public ResponseEntity<String> createAuth(RecruiterLoginDTO loginDto) {

        return ResponseEntity.ok("");
    }
}
