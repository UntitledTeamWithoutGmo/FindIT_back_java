package com.findit.FindIt.util;

import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.dto.VacancyDTO;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.entity.Vacancy;
import lombok.experimental.UtilityClass;


import java.util.ArrayList;
import java.util.List;


@UtilityClass
public class VacancyMapper {
    public VacancyDTO convertToDto(Vacancy vacancy){
        VacancyDTO dto = new VacancyDTO();
        dto.setId(vacancy.getId());
        dto.setDescription(vacancy.getDescription());
        dto.setOrganization(vacancy.getOrganization().getName());
        dto.setRecruiter(vacancy.getRecruiter().getUsername());
        dto.setTaskLink(vacancy.getTaskLink());
        dto.setTitle(vacancy.getTitle());
        dto.setPrice(vacancy.getPrice());

        List<Long> usersList = new ArrayList<>();
        for(User user:vacancy.getUsers()){
            usersList.add(user.getId());
        }
        dto.setUsers(usersList);

        return dto;

    }
}
