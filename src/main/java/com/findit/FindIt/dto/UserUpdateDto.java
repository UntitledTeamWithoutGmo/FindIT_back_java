package com.findit.FindIt.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserUpdateDto {
    private long id;

    private String name;

    private String surname;

    private String patronymicName;

    private String email;

    private String username;

    private int level;

    private String description;
    private String stackTech;
}
