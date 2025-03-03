package com.findit.FindIt.service.organization;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {
    Organization findOrgById(int id);
    List<Organization> findAll();
    Organization saveOrg(OrganizationDTO dto);
    Organization updateOrg(int id, OrganizationDTO dto);
    void deleteOrg(int id);
}
