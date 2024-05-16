package com.example.DocumentProject.repositories;

import com.example.DocumentProject.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findById(UUID id);
    void deleteById(UUID id);
}
