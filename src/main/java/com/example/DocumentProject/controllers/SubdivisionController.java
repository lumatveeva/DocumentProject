package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Subdivision;
import com.example.DocumentProject.services.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subdivisions")
public class SubdivisionController {

    @Autowired
    private SubdivisionService subdivisionService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("subdivisions", subdivisionService.findAll());
        return "/subdivisions/subdivisionsAll";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("subdivision", subdivisionService.findById(id));
        return "/subdivisions/subdivisionById";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("subdivision", new Subdivision());
        return "/subdivisions/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("subdivision") Subdivision subdivision){
        subdivisionService.save(subdivision);
        return "redirect:/subdivisions";
    }
}
