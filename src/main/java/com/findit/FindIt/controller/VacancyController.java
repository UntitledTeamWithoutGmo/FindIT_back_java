package com.findit.FindIt.controller;

import com.findit.FindIt.dto.VacancyDTO;
import com.findit.FindIt.dto.VacancyRegisterDto;
import com.findit.FindIt.entity.Vacancy;
import com.findit.FindIt.service.vacancy.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancy")
public class VacancyController {
    @Autowired
    private VacancyService vacancyService;

    @PostMapping("/create")
    ResponseEntity<VacancyDTO> create( @RequestBody VacancyRegisterDto registerDto,@AuthenticationPrincipal String username){
        return ResponseEntity.status(200).body(vacancyService.saveVacancy(registerDto,username));
    }
    @GetMapping("/all")
    ResponseEntity<List<VacancyDTO>> findAll(){
        return ResponseEntity.status(200).body(vacancyService.findAll());
    }
}
