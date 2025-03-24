package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.JwtResponse;
import com.findit.FindIt.dto.RecruiterLoginDTO;
import com.findit.FindIt.jwt.JwtTokenDto;
import org.springframework.http.ResponseEntity;

public interface AuthRecruiterService {
    ResponseEntity<JwtTokenDto> createAuth(RecruiterLoginDTO loginDto);
}
