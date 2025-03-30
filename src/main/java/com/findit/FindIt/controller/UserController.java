package com.findit.FindIt.controller;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.dto.UserLoginDto;
import com.findit.FindIt.dto.UserRegisterDTO;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.jwt.JwtTokenDto;
import com.findit.FindIt.kafka.KafkaConsumer;
import com.findit.FindIt.service.user.AuthUserService;
import com.findit.FindIt.service.user.UserService;
import com.findit.FindIt.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private AuthUserService authUserService;

    @PostMapping("/register")
    private ResponseEntity<UserDTO> saveUser(@RequestBody UserRegisterDTO dto){
        return ResponseEntity.status(200).body(service.saveUser(dto));
    }

    @PostMapping("/login")
    private ResponseEntity<JwtTokenDto> login(@RequestBody UserLoginDto dto){
        return service.createAuth(dto);

    }
    @GetMapping("/profile")
    private ResponseEntity<UserDTO> profile(@AuthenticationPrincipal String username){
        return service.profile(username);
    }

    @GetMapping("/porn")
    private ResponseEntity<String> porn(){
        return ResponseEntity.status(200).body("Shut up fuck up,keep on suck my dick");
    }

    @GetMapping("/all")
    private ResponseEntity<List<UserDTO>> getAll(){
        return ResponseEntity.status(200).body(service.findAll());
    }



    @GetMapping("/{id}")
    private ResponseEntity<UserDTO> getById(@PathVariable int id){
        return ResponseEntity.status(200).body(service.findUserById(id));
    }
    @PutMapping("/listen")
    private ResponseEntity<String> listen(@AuthenticationPrincipal String username){
        return service.listen(username);
    }
    @PutMapping("/update/{id}")
    private ResponseEntity<UserDTO> updateById(@PathVariable int id,@RequestBody UserDTO user){

        return ResponseEntity.status(200).body(service.updateUser(id,user));
    }
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable int id){
        service.deleteUser(id);
        return ResponseEntity.status(200).body("Deleted");
    }






}
