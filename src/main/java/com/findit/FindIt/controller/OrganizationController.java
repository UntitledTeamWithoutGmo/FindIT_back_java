package com.findit.FindIt.controller;

import com.findit.FindIt.dto.OrganizationDTO;
import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.service.organization.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor
public class OrganizationController {

    @Autowired
    private OrganizationService service;

    @PostMapping("/register")
    private ResponseEntity<OrganizationDTO> saveUser(@RequestBody OrganizationDTO dto){
        return ResponseEntity.status(200).body(service.saveOrg(dto));
    }
    @GetMapping("/all")
    private ResponseEntity<List<OrganizationDTO>> getAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<OrganizationDTO> getById(@PathVariable int id){
        return ResponseEntity.status(200).body(service.findOrgById(id));
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<OrganizationDTO> updateById(@PathVariable int id,@RequestBody OrganizationDTO dto){

        return ResponseEntity.status(200).body(service.updateOrg(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable int id){
        service.deleteOrg(id);
        return ResponseEntity.status(200).body("Deleted");
    }
}
