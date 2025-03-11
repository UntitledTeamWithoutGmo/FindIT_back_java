package com.findit.FindIt.service.role;

import com.findit.FindIt.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role getUserRole();
}
