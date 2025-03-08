package com.findit.FindIt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserRegisterDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymicName")
    private String patronymicName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
