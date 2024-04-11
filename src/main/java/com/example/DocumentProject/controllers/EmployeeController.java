package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.services.EmployeeService;
import com.example.DocumentProject.services.SubdivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SubdivisionService subdivisionService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("people", employeeService.findAll());
        return "/employees/employeesAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("employee", employeeService.findById(id));
        return "/employees/employeeById";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("subdivisions", subdivisionService.findAll());
        return "/employees/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("employee") Employee employee){
        employeeService.save(employee);
        return "redirect:/employees";
    }
}
