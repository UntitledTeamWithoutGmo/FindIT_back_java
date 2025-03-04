package com.findit.FindIt.repository;

import com.findit.FindIt.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Optional<Organization> findById(int id);
    List<Organization> findAll();
    Optional<Organization> findByName(String name);
}
