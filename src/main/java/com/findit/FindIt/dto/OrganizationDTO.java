package com.findit.FindIt.dto;

import com.findit.FindIt.entity.Recruiter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class OrganizationDTO {

    private long id;

    private String name;

    private String description;

    private int rating;

    private List<Recruiter> recruiters;

}
