package com.findit.FindIt.service.userDetails;

import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.repository.RecruiterRepository;
import com.findit.FindIt.repository.UserRepository;
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
    private RecruiterRepository recruiterService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findUserByUsername(username);
        if(userOptional.isEmpty()){
            Optional<Recruiter> rec = recruiterService.findByUsername(username);
            Recruiter recNew = rec.get();
            return new org.springframework.security.core.userdetails.User(
                    recNew.getUsername(),
                    recNew.getPassword(),
                    recNew.getRoles()
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
