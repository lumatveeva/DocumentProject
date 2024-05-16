package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Subdivision;
import com.example.DocumentProject.repositories.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SubdivisionService {
    @Autowired
    private SubdivisionRepository subdivisionRepository;

    public Subdivision findById(UUID id){
        return subdivisionRepository.findById(id).orElse(null);
    }
    public List<Subdivision> findAll(){
        return subdivisionRepository.findAll();
    }

    @Transactional
    public void save(Subdivision subdivision){
        subdivisionRepository.save(subdivision);
    }

    @Transactional
    public void update(Subdivision updateSubdivision, UUID id){
        updateSubdivision.setId(id);
        subdivisionRepository.save(updateSubdivision);
    }

    @Transactional
    public void delete(UUID id){
        subdivisionRepository.deleteById(id);
    }
}
