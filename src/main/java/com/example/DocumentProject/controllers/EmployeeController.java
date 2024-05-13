package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.services.EmployeeService;
import com.example.DocumentProject.services.SubdivisionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SubdivisionService subdivisionService;

    @GetMapping()
    @Operation(summary = "Получение информации о всех сотрудниках")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Получение информации о сотруднике по его Id")
    public Employee findById(@PathVariable("id") int id, Model model){
        return employeeService.findById(id);
    }

    @GetMapping("/new")
    @Operation(summary = "Создание нового сотрудника")
    @PostMapping()
    public String save(@RequestBody Employee employee){
        employeeService.save(employee);
        return "redirect:/employees";
    }
}
