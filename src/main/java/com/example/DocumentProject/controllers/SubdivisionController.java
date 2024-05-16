package com.example.DocumentProject.controllers;

import com.example.DocumentProject.annotations.LoggingAspect;
import com.example.DocumentProject.generators.SubdivisionGenerator;
import com.example.DocumentProject.models.Subdivision;
import com.example.DocumentProject.services.SubdivisionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subdivisions")
public class SubdivisionController {

    @Autowired
    private SubdivisionService subdivisionService;
    @Autowired
    private SubdivisionGenerator subdivisionGenerator;

    @GetMapping()
    @LoggingAspect
    @Operation(summary = "Список всех подразделений")
    public List<Subdivision> findAll(){
        return subdivisionService.findAll();
    }

    @GetMapping("/{id}")
    @LoggingAspect
    @Operation(summary = "Поиск подразделения по ID")
    public Subdivision findById(@PathVariable("id") UUID id){
        return subdivisionService.findById(id);
    }

    @PostMapping()
    @LoggingAspect
    @Operation(summary = "Сохранение нового подразделения")
    public String save(@ModelAttribute("subdivision") Subdivision subdivision){
        subdivisionService.save(subdivision);
        return "redirect:/subdivisions";
    }
    @PostMapping("/generate")
    @LoggingAspect
    @Operation(summary = "Генерация и сохранение нового подразделения")
    public void generateSubdivision(){
        Subdivision subdivision = subdivisionGenerator.generateSubdivision();
        subdivisionService.save(subdivision);
    }
}
