package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //we are making this EmployeeEntity just for now to learn it's not a good practice in between
    //entity and controller there will be a service layer
    @GetMapping(path = "/{employeeID}")
   public EmployeeEntity getEmployeeById(@PathVariable(name="employeeID") Long id)
   {
       /*
           employeeRepository.findById(id)
           this will return a optional data
           optional is class inside java.util
           which wraps the null value so that you can handle it accordingly
           employeeRepository.findById(id)  if its output is a null than we handle it

        */
       return  employeeRepository.findById(id).orElse(null);
   }

    @GetMapping
    public List<EmployeeEntity> getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String name)
    {
        return  employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity postEmployee(@RequestBody EmployeeEntity inputEmployee)
    {
        return employeeRepository.save(inputEmployee);
    }


//    @PutMapping
//    public  String postEmployeed()
//    {
//        return "HI POSTED AN EMPLOYEE";
//    }





}
