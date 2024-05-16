package com.example.DocumentProject.controllers;

import com.example.DocumentProject.annotations.LoggingAspect;
import com.example.DocumentProject.generators.OrganizationGenerator;
import com.example.DocumentProject.models.Organization;
import com.example.DocumentProject.services.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private OrganizationGenerator organizationGenerator;

    @GetMapping()
    @Operation(summary = "Получение информации о всех организациях")
    public List<Organization> findAll(){
        return organizationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об организации по Id")
    public Organization findById(@PathVariable("id") UUID id){
        return organizationService.findById(id);
    }


    @PostMapping()
    @Operation(summary = "Создание новой организации")
    public Organization save(@RequestBody @Valid Organization organization, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Ошибка создания новой организации");
        }
         organizationService.save(organization);
        return organization;
    }
    @PostMapping("/generate")
    @LoggingAspect
    @Operation(summary = "Генерация и сохранение новой организации")
    public void generateOrganization(){
        Organization organization = organizationGenerator.generateOrganization();
        organizationService.save(organization);
    }
}
