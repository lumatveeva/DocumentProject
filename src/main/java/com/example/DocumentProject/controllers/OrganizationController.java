package com.example.DocumentProject.controllers;

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

@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping()
    @Operation(summary = "Получение информации о всех организациях")
    public List<Organization> findAll(){
        return organizationService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об организации по Id")
    public Organization findById(@PathVariable("id") int id){
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
}
