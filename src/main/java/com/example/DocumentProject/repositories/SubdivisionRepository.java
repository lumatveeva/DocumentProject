package com.example.DocumentProject.repositories;

import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.models.Subdivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubdivisionRepository extends JpaRepository<Subdivision, Integer> {
    Optional<Subdivision> findById(UUID id);
    void deleteById(UUID id);
}
