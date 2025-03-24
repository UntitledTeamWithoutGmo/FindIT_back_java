package com.findit.FindIt.controller;

import com.findit.FindIt.dto.*;
import com.findit.FindIt.jwt.JwtTokenDto;
import com.findit.FindIt.service.recruiter.AuthRecruiterService;
import com.findit.FindIt.service.recruiter.RecruiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/recruiter")
@RequiredArgsConstructor
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;
    private final AuthRecruiterService authRecruiterService;

    @PostMapping("/register")
    private ResponseEntity<RecruiterDTO> saveUser(@RequestBody RegisterRecruiterDTO dto){
        return ResponseEntity.status(200).body(recruiterService.saveRec(dto));
    }

    @GetMapping("/all")
    private ResponseEntity<List<RecruiterDTO>> getAll(){
        return ResponseEntity.status(200).body(recruiterService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<RecruiterDTO> getById(@PathVariable int id){
        return ResponseEntity.status(200).body(recruiterService.findRecById(id));
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<RecruiterDTO> updateById(@PathVariable int id,@RequestBody RecruiterDTO dto){
        return ResponseEntity.status(200).body(recruiterService.updateRec(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable int id){
        recruiterService.deleteRec(id);
        return ResponseEntity.status(200).body("Deleted");
    }

    @PostMapping("/jwt")
    private ResponseEntity<JwtTokenDto> createJwtToken(@RequestBody RecruiterLoginDTO recruiterLoginDTO){
        return authRecruiterService.createAuth(recruiterLoginDTO);
    }

    @GetMapping("/porn2")
    public ResponseEntity<String> securedRecruiterField(){
        return ResponseEntity.ok("Mega porn");
    }
}
