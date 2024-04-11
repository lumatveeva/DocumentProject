package com.example.DocumentProject.repositories;

import com.example.DocumentProject.models.Subdivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdivisionRepository extends JpaRepository<Subdivision, Integer> {
}
