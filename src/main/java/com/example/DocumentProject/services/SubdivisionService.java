package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Organization;
import com.example.DocumentProject.models.Subdivision;
import com.example.DocumentProject.repositories.OrganizationRepository;
import com.example.DocumentProject.repositories.SubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SubdivisionService {
    @Autowired
    private SubdivisionRepository subdivisionRepository;

    public Subdivision findById(int id){
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
    public void update(Subdivision updateSubdivision, int id){
        updateSubdivision.setId_subdivision(id);
        subdivisionRepository.save(updateSubdivision);
    }

    @Transactional
    public void delete(int id){
        subdivisionRepository.deleteById(id);
    }
}
