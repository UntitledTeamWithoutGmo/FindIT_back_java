package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.repository.RecruiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService{

    @Autowired
    private RecruiterRepository repository;

    @Override
    public Recruiter findRecById(int id) {
        Optional<Recruiter> recruiter = repository.findById(id);
        return recruiter.get();
    }

    @Override
    public List<Recruiter> findAll() {
        return repository.findAll();
    }

    @Override
    public Recruiter saveRec(RecruiterDTO dto) {
        Recruiter recruiter = new Recruiter();
        recruiter.setSurname(dto.getSurname());
        recruiter.setName(dto.getName());
        recruiter.setPatronymicName(dto.getPatronymicName());
        recruiter.setEmail(dto.getEmail());
        recruiter.setPassword(dto.getPassword());
        recruiter.setOrganization(dto.getOrganization());
        return repository.save(recruiter);
    }

    @Override
    public Recruiter updateRec(int id, RecruiterDTO dto) {
        Optional<Recruiter> recruiter = repository.findById(id);

        Recruiter recruiterNew= recruiter.get();
        recruiterNew.setName(dto.getName());
        recruiterNew.setSurname(dto.getSurname());
        recruiterNew.setPatronymicName(dto.getPatronymicName());
        recruiterNew.setEmail(dto.getEmail());
        recruiterNew.setPassword(dto.getPassword());
        recruiterNew.setOrganization(dto.getOrganization());

        return repository.save(recruiterNew);
    }

    @Override
    public void deleteRec(int id) {
        Optional<Recruiter> recruiter = repository.findById(id);

        Recruiter deleteRec= recruiter.get();
        repository.delete(deleteRec);

    }
}
