package com.findit.FindIt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "rating")
    private int rating;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Recruiter> recruiters;

}
