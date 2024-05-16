package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Organization;
import com.example.DocumentProject.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public Organization findById(UUID id){
        return organizationRepository.findById(id).orElse(null);
    }
    public List<Organization> findAll(){
        return organizationRepository.findAll();
    }

    @Transactional
    public void save(Organization organization){
        organizationRepository.save(organization);
    }

    @Transactional
    public void update(Organization updateOrganization, UUID id){
        updateOrganization.setId(id);
        organizationRepository.save(updateOrganization);
    }

    @Transactional
    public void delete(UUID id){
        organizationRepository.deleteById(id);
    }
}
