package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Organization;
import com.example.DocumentProject.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organizations")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("organizations", organizationService.findAll());
        return "/organizations/organizationsAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("organization", organizationService.findById(id));
        return "/organizations/organizationById";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("organization", new Organization());
        return "/organizations/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("organization") @Valid Organization organization, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/organizations/new";
        }
         organizationService.save(organization);
        return "redirect:/organizations";
    }
}
