package com.findit.FindIt.dto;

import com.findit.FindIt.entity.Organization;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class RecruiterDTO {

    private long id;

    private String username;

    private String name;

    private String surname;

    private String patronymicName;

    private String email;

    private String organizationName;

    private List<Long> vacancies;

}
