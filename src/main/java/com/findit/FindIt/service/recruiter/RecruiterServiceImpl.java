package com.findit.FindIt.service.recruiter;

import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.dto.RegisterRecruiterDTO;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.entity.Role;
import com.findit.FindIt.exception.OrganizationNotFoundException;
import com.findit.FindIt.exception.RecruiterAlreadyExistsException;
import com.findit.FindIt.exception.RecruiterNotFoundException;
import com.findit.FindIt.repository.OrganizationRepository;
import com.findit.FindIt.repository.RecruiterRepository;
import com.findit.FindIt.service.role.RoleService;
import com.findit.FindIt.util.PasswordValidator;
import com.findit.FindIt.util.RecruiterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService{

    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private OrganizationRepository organizationRepository;

    private PasswordEncoder passwordEncoder;

    private RoleService roleService;


    @Override
    public RecruiterDTO findRecById(int id) {
        return RecruiterMapper.convertToDto(recruiterRepository.findById(id)
                .orElseThrow(() -> new RecruiterNotFoundException("Recruiter with id "+id+" not found")));
    }

    @Override
    public List<RecruiterDTO> findAll() {
        return recruiterRepository.findAll()
                .stream()
                .map(RecruiterMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RecruiterDTO saveRec(RegisterRecruiterDTO dto) {

        Optional<Recruiter> optionalRecruiter = recruiterRepository.findByUsername(dto.getUsername());
        if(optionalRecruiter.isPresent()){
            throw new RecruiterAlreadyExistsException("Recruiter with username "+dto.getUsername()+" already exists");
        }

        Recruiter recruiter = new Recruiter();
        recruiter.setUsername(dto.getUsername());
        recruiter.setSurname(dto.getSurname());
        recruiter.setName(dto.getName());
        recruiter.setPatronymicName(dto.getPatronymicName());
        recruiter.setEmail(dto.getEmail());

        Role role = roleService.getRecruiterRole();
        recruiter.setRoles(Set.of(role));


        recruiter.setPassword(passwordEncoder.encode(
                PasswordValidator.validatePassword(
                        dto.getPassword()
                )
        ));

        recruiter.setOrganization(organizationRepository.findByName(dto.getOrganizationName())
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with name "+dto.getOrganizationName()+" not found")));

        return RecruiterMapper.convertToDto(recruiterRepository.save(recruiter));
    }

    @Override
    public RecruiterDTO updateRec(int id, RecruiterDTO dto) {
        Optional<Recruiter> optionalRecruiter = recruiterRepository.findById(id);
        if(optionalRecruiter.isEmpty()){
            throw new RecruiterNotFoundException("Recruiter with id "+id+" not found");
        }

        Recruiter recruiter = optionalRecruiter.get();
        recruiter.setUsername(dto.getUsername());
        recruiter.setName(dto.getName());
        recruiter.setSurname(dto.getSurname());
        recruiter.setPatronymicName(dto.getPatronymicName());
        recruiter.setEmail(dto.getEmail());
        recruiter.setOrganization(organizationRepository.findByName(dto.getOrganizationName())
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with name "+dto.getOrganizationName()+" not found")));

        return RecruiterMapper.convertToDto(recruiterRepository.save(recruiter));
    }

    @Override
    public void deleteRec(int id) {
        Optional<Recruiter> recruiter = recruiterRepository.findById(id);

        recruiterRepository.delete(recruiter
                .orElseThrow(() -> new RecruiterNotFoundException("Recruiter with id "+id+" not found")));

    }

    @Override
    public Recruiter findByUsername(String username) {
        return recruiterRepository.findByUsername(username).orElseThrow(() -> new RecruiterNotFoundException("Recruiter with username "+username+" not found"));
    }


}
