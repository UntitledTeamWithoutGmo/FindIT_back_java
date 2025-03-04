package com.findit.FindIt.service.organization;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.exception.RecruiterNotFoundException;
import com.findit.FindIt.repository.OrganizationRepository;
import com.findit.FindIt.repository.RecruiterRepository;
import com.findit.FindIt.util.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationRepository organizationRepository;
    private RecruiterRepository recruiterRepository;


    @Override
    public Organization findOrgById(int id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.get();
    }

    @Override
    public List<OrganizationDTO> findAll() {
        return organizationRepository.findAll()
                .stream()
                .map(OrganizationMapper::convertToDto)
                .toList();
    }

    @Override
    public OrganizationDTO saveOrg(OrganizationDTO dto) {

        Organization organization = new Organization();
        organization.setName(dto.getName());
        organization.setDescription(dto.getDescription());
        organization.setRating(dto.getRating());

        for(long id : dto.getRecruiters()){
            Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(id);
            if(optionalRecruiter.isEmpty()){
                throw new RecruiterNotFoundException("Recruiter with "+id+" not found");
            } else{
                organization.getRecruiters().add(optionalRecruiter.get());
            }
        }

        return OrganizationMapper.convertToDto(organizationRepository.save(organization));
    }

    @Override
    public Organization updateOrg(int id, OrganizationDTO dto) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        Organization organization= optionalOrganization.get();
        organization.setName(dto.getName());
        organization.setDescription(dto.getDescription());
        organization.setRating(dto.getRating());

        for(long rec_id : dto.getRecruiters()){
            Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(rec_id);
            if(optionalRecruiter.isEmpty()){
                throw new RecruiterNotFoundException("Recruiter with "+rec_id+" not found");
            } else{
                organization.getRecruiters().add(optionalRecruiter.get());
            }
        }

        return organizationRepository.save(organization);
    }

    @Override
    public void deleteOrg(int id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        Organization organizationDel = organization.get();
        organizationRepository.delete(organizationDel);
    }
}
