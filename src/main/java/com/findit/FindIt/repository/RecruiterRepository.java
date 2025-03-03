package com.findit.FindIt.repository;

import com.findit.FindIt.entity.Organization;
import com.findit.FindIt.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter,Long> {
    Optional<Recruiter> findById(int id);
    List<Recruiter> findAll();
}
