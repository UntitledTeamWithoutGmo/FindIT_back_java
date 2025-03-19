package com.findit.FindIt.util;

import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.entity.Recruiter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RecruiterMapper {

    public RecruiterDTO convertToDto(Recruiter recruiter){
        RecruiterDTO dto = new RecruiterDTO();
        dto.setId(recruiter.getId());
        dto.setUsername(recruiter.getUsername());
        dto.setName(recruiter.getName());
        dto.setSurname(recruiter.getSurname());
        dto.setPatronymicName(recruiter.getPatronymicName());
        dto.setEmail(recruiter.getEmail());
        dto.setOrganizationName(recruiter.getOrganization().getName());

        return dto;
    }
}
