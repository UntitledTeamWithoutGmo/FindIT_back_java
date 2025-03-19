package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.JwtResponse;
import com.findit.FindIt.dto.RecruiterLoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthRecruiterService {
    ResponseEntity<String> createAuth(RecruiterLoginDTO loginDto);
}
