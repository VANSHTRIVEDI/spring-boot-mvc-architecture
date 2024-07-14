package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.controllers;


import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services.EmployeeService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    final EmployeeRepository employeeRepository;

    final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //solve error here
    @GetMapping(path = "/{employeeID}")
   public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeID") Long id)
   {
        Optional<EmployeeDTO> employeeDTO=  employeeService.getEmployeeById(id);

       return  employeeDTO.map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1)).orElse(ResponseEntity.notFound().build());
   }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(required = false) Integer age, @RequestParam(required = false) String name)
    {
        return  ResponseEntity.ok(employeeService.getEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> postEmployee(@RequestBody @Valid EmployeeDTO inputEmployee)
    {
        EmployeeDTO savedEmployeeDTO=employeeService.postEmployee(inputEmployee);
        return new ResponseEntity<>(savedEmployeeDTO, HttpStatus.CREATED);
    }

    //put is used when we want to edit the whole post on a  database
    //patch is used want we want to edit some-things on a the databse

    //there are two ways one we can throw and error if the id user want to exist is not present
    //second is we can create new id in the database and all our data in the database
    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid EmployeeDTO employeeDTO,@PathVariable Long employeeId)
    {

       return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId,employeeDTO));


    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId)
    {
       boolean deleted= employeeService.deleteEmployeeById(employeeId);
       if(deleted)
       {
          return ResponseEntity.ok(deleted);
       }
       else
       {
            return  ResponseEntity.notFound().build();
       }
    }

    //we have to validate it but because it has map of field we have to check every field in some other method
    @PatchMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable Long employeeId,@RequestBody Map<String, Object>updates)
    {
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }

}
