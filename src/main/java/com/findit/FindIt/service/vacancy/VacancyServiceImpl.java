package com.findit.FindIt.service.vacancy;

import com.findit.FindIt.dto.VacancyDTO;
import com.findit.FindIt.dto.VacancyRegisterDto;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.Vacancy;
import com.findit.FindIt.exception.OrganizationNotFoundException;
import com.findit.FindIt.exception.RecruiterNotFoundException;
import com.findit.FindIt.repository.OrganizationRepository;
import com.findit.FindIt.repository.RecruiterRepository;
import com.findit.FindIt.repository.VacancyRepository;
import com.findit.FindIt.service.organization.OrganizationServiceImpl;
import com.findit.FindIt.util.VacancyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService{
    @Autowired
    private VacancyRepository repository;
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public VacancyDTO saveVacancy(VacancyRegisterDto registerDto,String recruiterUserName) {
        Recruiter recruiter = recruiterRepository.findByUsername(recruiterUserName)
                .orElseThrow(() -> new RecruiterNotFoundException("Recruiter with username "+recruiterUserName+" not found"));
        Organization organization = recruiter.getOrganization();
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle(registerDto.getTitle());
        vacancy.setDescription(registerDto.getDescription());
        vacancy.setTaskLink(registerDto.getTaskLink());
        vacancy.setOrganization(organization);
        vacancy.setRecruiter(recruiter);
        if(registerDto.getPrice()==null){
            vacancy.setPrice("nullable");
        }
        if(registerDto.getTaskLink()==null){
            vacancy.setTaskLink("nullable");
        }
        if(registerDto.getPrice()!=null){
            vacancy.setPrice(registerDto.getPrice());
        }
        if(registerDto.getTaskLink()!=null){
            vacancy.setTaskLink(registerDto.getTaskLink());
        }


        return VacancyMapper.convertToDto(repository.save(vacancy));
    }

    @Override
    public List<VacancyDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(VacancyMapper::convertToDto)
                .collect(Collectors.toList());
    }
}
