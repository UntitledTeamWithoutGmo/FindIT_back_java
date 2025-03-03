package com.findit.FindIt.service.organization;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationRepository repository;


    @Override
    public Organization findOrgById(int id) {
        Optional<Organization> organization = repository.findById(id);
        return organization.get();
    }

    @Override
    public List<Organization> findAll() {
        return repository.findAll();
    }

    @Override
    public Organization saveOrg(OrganizationDTO dto) {
        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setDescription(dto.getDescription());
        organization.setRating(dto.getRating());
        organization.setRecruiters(dto.getRecruiters());
        return repository.save(organization);
    }

    @Override
    public Organization updateOrg(int id, OrganizationDTO dto) {
        Optional<Organization> organization = repository.findById(id);
        Organization organizationNew= organization.get();
        organizationNew.setRecruiters(dto.getRecruiters());
        organizationNew.setName(dto.getName());
        organizationNew.setDescription(dto.getDescription());
        organizationNew.setRating(dto.getRating());

        return repository.save(organizationNew);
    }

    @Override
    public void deleteOrg(int id) {
        Optional<Organization> organization = repository.findById(id);
        Organization organizationDel = organization.get();
        repository.delete(organizationDel);
    }
}
