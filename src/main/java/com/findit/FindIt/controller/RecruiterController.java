package com.findit.FindIt.controller;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.RecruiterDTO;
import com.findit.FindIt.dto.RegisterRecruiterDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import com.findit.FindIt.service.recruiter.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

    @Autowired
    private RecruiterService service;

    @PostMapping("/register")
    private ResponseEntity<RecruiterDTO> saveUser(@RequestBody RegisterRecruiterDTO dto){
        return ResponseEntity.status(200).body(service.saveRec(dto));
    }

    @GetMapping("/all")
    private ResponseEntity<List<RecruiterDTO>> getAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<RecruiterDTO> getById(@PathVariable int id){
        return ResponseEntity.status(200).body(service.findRecById(id));
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<RecruiterDTO> updateById(@PathVariable int id,@RequestBody RecruiterDTO dto){
        return ResponseEntity.status(200).body(service.updateRec(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable int id){
        service.deleteRec(id);
        return ResponseEntity.status(200).body("Deleted");
    }
}
