package com.findit.FindIt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "recruiter")
public class Recruiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic_name")
    private String patronymicName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
