package com.findit.FindIt.util;

import com.findit.FindIt.dto.UserDTO;
import com.findit.FindIt.entity.User;
import com.findit.FindIt.entity.Vacancy;
import lombok.experimental.UtilityClass;
import org.apache.kafka.common.protocol.types.Field;

import java.util.*;

@UtilityClass
public class UserMapper {

    public UserDTO convertToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setSurname(user.getSurname());
        dto.setPatronymicName(user.getPatronymicName());
        dto.setEmail(user.getEmail());
        dto.setLevel(user.getLevel());
        List<Long> vacancyList = new ArrayList<>();


        for(Vacancy vacancy:user.getVacancies()){
            vacancyList.add(vacancy.getId());
        }
        if(user.getDescription()!=null){
            dto.setDescription(user.getDescription());
        }
        if(user.getStackTech()!=null){
            String[] stackArr = user.getStackTech().split(",");
            List<String> stackArrList = new ArrayList<>(Arrays.asList(stackArr));
            dto.setStackTech(stackArrList);
        }
        dto.setVacancies(vacancyList);

        return dto;
    }
}
