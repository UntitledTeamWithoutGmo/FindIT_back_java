package com.findit.FindIt.repository;

import com.findit.FindIt.entity.Vacancy;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
    List<Vacancy> findAll();
    Optional<Vacancy> findById(int id);
}
