package com.findit.FindIt.service.role;

import com.findit.FindIt.entity.Role;
import com.findit.FindIt.exception.RoleNotFoundException;
import com.findit.FindIt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository repository;

    @Override
    public Role getUserRole() {
        Optional<Role> role = repository.findByAuthority("ROLE_USER");
        return role.get();
    }

    public Role getRecruiterRole() {
        Optional<Role> optionalRole = repository.findByAuthority("ROLE_RECRUITER");
        return optionalRole.orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }
}
