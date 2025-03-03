package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecruiterService {
    Recruiter findRecById(int id);
    List<Recruiter> findAll();
    Recruiter saveRec(RecruiterDTO dto);
    Recruiter updateRec(int id, RecruiterDTO dto);
    void deleteRec(int id);
}
