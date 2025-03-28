package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.dto.RegisterRecruiterDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruiterService {
    RecruiterDTO findRecById(int id);
    List<RecruiterDTO> findAll();
    RecruiterDTO saveRec(RegisterRecruiterDTO dto);
    RecruiterDTO updateRec(int id, RecruiterDTO dto);
    void deleteRec(int id);
    Recruiter findByUsername(String username);
    ResponseEntity<RecruiterDTO> profile(String username);
}
