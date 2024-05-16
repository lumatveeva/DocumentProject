package com.example.DocumentProject.repositories;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findById(UUID id);
    void deleteById(UUID id);
}
