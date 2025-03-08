package com.findit.FindIt.service.organization;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.exception.OrganizationAlreadyExistsException;
import com.findit.FindIt.exception.OrganizationNotFoundException;
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
    @Autowired
    private RecruiterRepository recruiterRepository;


    @Override
    public OrganizationDTO findOrgById(int id) {
        return OrganizationMapper.convertToDto(organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with id "+id+" not found")));
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
        Optional<Organization> optionalOrganization = organizationRepository.findByName(dto.getName());
        if(optionalOrganization.isPresent()){
            throw new OrganizationAlreadyExistsException("Organization with name "+dto.getName()+" already exists");
        }
        organization.setName(dto.getName());
        organization.setDescription(dto.getDescription());
        organization.setRating(0);

        organization.setRecruiters(List.of());

//        for(long id : dto.getRecruiters()){
//            Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(id);
//            if(optionalRecruiter.isEmpty()){
//                throw new RecruiterNotFoundException("Recruiter with "+id+" not found");
//            } else{
//                organization.getRecruiters().add(optionalRecruiter.get());
//            }
//        }

        return OrganizationMapper.convertToDto(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDTO updateOrg(int id, OrganizationDTO dto) {
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

        return OrganizationMapper.convertToDto(organizationRepository.save(organization));
    }

    @Override
    public void deleteOrg(int id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if(organization.isEmpty()){
            throw new OrganizationNotFoundException("Organization with id "+id+" not found");
        } else{
            organizationRepository.delete(organization.get());
        }
    }
}
