package com.findit.FindIt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vacancy")
@Data
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name="price")
    private String price;
    @Column(name="task_link")
    private String taskLink;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @ManyToMany(mappedBy = "vacancies")
    private Set<User> users;
    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

}
