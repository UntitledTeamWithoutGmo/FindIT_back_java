package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.repository.OrganizationRepository;
import com.findit.FindIt.repository.RecruiterRepository;
import com.findit.FindIt.util.RecruiterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService{

    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private OrganizationRepository organizationRepository;


    @Override
    public Recruiter findRecById(int id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);
        return recruiter.get();
    }

    @Override
    public List<Recruiter> findAll() {
        return recruiterRepository.findAll();
    }

    @Override
    public RecruiterDTO saveRec(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter();
        recruiter.setSurname(dto.getSurname());
        recruiter.setName(dto.getName());
        recruiter.setPatronymicName(dto.getPatronymicName());
        recruiter.setEmail(dto.getEmail());
        recruiter.setPassword(dto.getPassword());
        recruiter.setOrganization(organizationRepository.findByName(dto.getOrganizationName()).get());
        return RecruiterMapper.convertToDto(recruiterRepository.save(recruiter));
    }

    @Override
    public Recruiter updateRec(int id, RecruiterDTO dto) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);

        Recruiter recruiterNew= recruiter.get();
        recruiterNew.setName(dto.getName());
        recruiterNew.setSurname(dto.getSurname());
        recruiterNew.setPatronymicName(dto.getPatronymicName());
        recruiterNew.setEmail(dto.getEmail());
        recruiterNew.setPassword(dto.getPassword());
        recruiterNew.setOrganization(organizationRepository.findByName(dto.getOrganizationName()).get());

        return recruiterRepository.save(recruiterNew);
    }

    @Override
    public void deleteRec(int id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);

        Recruiter deleteRec= recruiter.get();
        recruiterRepository.delete(deleteRec);

    }
}
