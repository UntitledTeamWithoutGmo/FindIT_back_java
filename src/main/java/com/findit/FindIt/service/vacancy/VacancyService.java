package com.findit.FindIt.service.vacancy;

import com.findit.FindIt.dto.VacancyDTO;
import com.findit.FindIt.dto.VacancyRegisterDto;
import com.findit.FindIt.entity.Vacancy;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VacancyService {
    VacancyDTO saveVacancy(VacancyRegisterDto registerDto,String recruiterUserName);
    List<VacancyDTO> findAll();
    VacancyDTO callOnVacancy(String username,int id);
    VacancyDTO findById(int id);
    List<VacancyDTO> findByTitle(String title);
}
