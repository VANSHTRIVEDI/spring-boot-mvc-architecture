package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    final EmployeeRepository employeeRepository;

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeID}")
   public EmployeeDTO getEmployeeById(@PathVariable(name="employeeID") Long id)
   {

       return  employeeService.getEmployeeById(id);
   }

    @GetMapping
    public List<EmployeeDTO> getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String name)
    {
        return  employeeService.getEmployees();
    }

    @PostMapping
    public EmployeeDTO postEmployee(@RequestBody EmployeeDTO inputEmployee)
    {
        return employeeService.postEmployee(inputEmployee);
    }






}
