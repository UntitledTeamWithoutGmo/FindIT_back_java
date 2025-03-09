package com.findit.FindIt.dto;

import lombok.Data;

@Data
public class RegisterRecruiterDTO {

    private String name;

    private String surname;

    private String patronymicName;

    private String email;

    private String password;

    private String organizationName;
}
