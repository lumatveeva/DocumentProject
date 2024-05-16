package com.example.DocumentProject.repositories;

import com.example.DocumentProject.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
     Optional <Organization> findById(UUID id);
     void  deleteById(UUID id);

}
