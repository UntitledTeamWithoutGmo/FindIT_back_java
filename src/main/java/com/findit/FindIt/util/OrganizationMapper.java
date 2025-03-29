package com.findit.FindIt.util;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.Vacancy;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class OrganizationMapper {

    public OrganizationDTO convertToDto(Organization organization){
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(organization.getId());
        dto.setName(organization.getName());
        dto.setDescription(organization.getDescription());
        dto.setRating(organization.getRating());

        List<Long> listRecruiters = new ArrayList<>();
        List<Long> listVacancy = new ArrayList<>();
        for(Recruiter recruiter : organization.getRecruiters()){
            listRecruiters.add(recruiter.getId());
        }
        for(Vacancy vacancy:organization.getVacancies()){
            listVacancy.add(vacancy.getId());
        }

        dto.setRecruiters(listRecruiters);
        dto.setVacancies(listVacancy);

        return dto;
    }
}
