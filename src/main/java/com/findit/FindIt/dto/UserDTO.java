package com.findit.FindIt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class UserDTO {

    private long id;

    private String name;

    private String surname;

    private String patronymicName;

    private String email;

    private String username;

    private int level;
    private List<Long> vacancies;
    private List<String> stackTech;
    private String description;

}