package com.findit.FindIt.service.vacancy;

import com.findit.FindIt.dto.VacancyDTO;
import com.findit.FindIt.dto.VacancyRegisterDto;
import com.findit.FindIt.entity.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    VacancyDTO saveVacancy(VacancyRegisterDto registerDto,String recruiterUserName);
    List<VacancyDTO> findAll();
}
