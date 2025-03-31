package com.findit.FindIt.service.user;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.dto.UserLoginDto;
import com.findit.FindIt.dto.UserRegisterDTO;
import com.findit.FindIt.dto.UserUpdateDto;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.exception.UserAlreadyExistException;
import com.findit.FindIt.exception.UserNotFoundException;
import com.findit.FindIt.jwt.JwtToken;
import com.findit.FindIt.jwt.JwtTokenDto;
import com.findit.FindIt.kafka.KafkaConsumer;
import com.findit.FindIt.repository.UserRepository;
//import com.findit.FindIt.service.kafka.KafkaProducer;
import com.findit.FindIt.service.role.RoleService;
import com.findit.FindIt.service.userDetails.UserDetailsServiceImpl;
import com.findit.FindIt.util.PasswordValidator;
import com.findit.FindIt.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private KafkaConsumer kafkaConsumer;

    private String token;

//    private KafkaProducer kafkaProducer;

    @Override
    public UserDTO findUserById(int id) {

        return UserMapper.convertToDto(userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found")));
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO saveUser(UserRegisterDTO dto) {
        if(userRepository.findUserByUsername(dto.getUsername()).isPresent()){
            throw new UserAlreadyExistException("Username "+dto.getUsername()+" already exists");

        }
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setUsername(dto.getUsername());
        user.setPatronymicName(dto.getPatronymicName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(PasswordValidator.validatePassword(dto.getPassword())));
        user.setLevel(0);
        user.setRoles(Set.of(roleService.getUserRole()));
        user.setVacancies(Set.of());
        user.setDescription(dto.getDescription());
        user.setStackTech(dto.getStackTech());

//        kafkaProducer.sendMessage(dto.getUsername());


        return UserMapper.convertToDto(userRepository.save(user));
    }

    @Override
    public UserDTO updateUser(int id, UserUpdateDto dto) {
        Optional<User> userOpt = userRepository.findUserById(id);
        User userNew = userOpt.orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found"));
        userNew.setEmail(dto.getEmail());
        userNew.setName(dto.getName());
        userNew.setSurname(dto.getSurname());
        userNew.setUsername(dto.getUsername());
        userNew.setPatronymicName(dto.getPatronymicName());
        userNew.setLevel(dto.getLevel());
        userNew.setDescription(dto.getDescription());
        userNew.setStackTech(dto.getStackTech());
        return UserMapper.convertToDto(userRepository.save(userNew));

    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findUserById(id);
        userRepository.delete(user
                .orElseThrow(() -> new UserNotFoundException("User with id "+id+" not found")));
    }



    @Override
    public ResponseEntity<JwtTokenDto> createAuth(@RequestBody UserLoginDto dto) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword()));

        }catch (BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        token = jwtToken.generateToken(userDetails);
        JwtTokenDto jwtTokenDto = new JwtTokenDto();
        jwtTokenDto.setToken(token);


        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,"Bearer "+token).body(jwtTokenDto);
    }

    @Override
    public ResponseEntity<UserDTO> profile(String username) {
        UserDTO userDTO = UserMapper.convertToDto(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username "+username+" not found")));

        return ResponseEntity.ok().body(userDTO);
    }

    @Override
    public ResponseEntity<String> listen(String username) {
        String mesKaf = kafkaConsumer.getMessageKafka();
        if(mesKaf.equals("\"pidor\"")){
            Optional<User> userOptional = userRepository.findUserByUsername(username);
            User user = userOptional.orElseThrow(() -> new UserNotFoundException("User with username "+username+" not found"));
            int level = user.getLevel();
            user.setLevel(level+2);
            userRepository.save(user);
            mesKaf = "Jopa";
            return ResponseEntity.status(200).body("Good");


        }
        return ResponseEntity.status(300).body(kafkaConsumer.getMessageKafka());
    }


}
