package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserLoginDto;
import com.findit.FindIt.jwt.JwtToken;
import com.findit.FindIt.service.userDetails.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseEntity<String> createAuth(@RequestBody UserLoginDto dto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        String token = jwtToken.generateToken(userDetails);


        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,"Bearer "+token).body("Nice");
    }
}
