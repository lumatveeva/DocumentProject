package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(UUID id){
        return employeeRepository.findById(id).orElse(null);
    }
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Transactional
    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    @Transactional
    public void update(Employee updateEmployee, UUID id){
        updateEmployee.setId(id);
        employeeRepository.save(updateEmployee);
    }

    @Transactional
    public void delete(UUID id){
        employeeRepository.deleteById(id);
    }
}
