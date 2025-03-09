package com.findit.FindIt.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationDTO {

    private long id;

    private String name;

    private String description;

    private int rating;

    private List<Long> recruiters;

}
