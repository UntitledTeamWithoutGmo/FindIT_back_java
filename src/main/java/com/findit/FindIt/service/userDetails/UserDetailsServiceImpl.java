package com.findit.FindIt.service.userDetails;

import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.repository.UserRepository;
import com.findit.FindIt.service.recruiter.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private RecruiterService recruiterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findUserByUsername(username);
        if(userOptional.isEmpty()){
            Recruiter rec = recruiterService.findByUsername(username);
            return new org.springframework.security.core.userdetails.User(
                    rec.getUsername(),
                    rec.getPassword(),
                    rec.getRoles()
            );
        } else{
            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoles()
            );
        }
    }
}
