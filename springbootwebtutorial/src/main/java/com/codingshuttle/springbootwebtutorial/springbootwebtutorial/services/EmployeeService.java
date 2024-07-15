package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Exception.ResourceNotFoundException;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto.EmployeeDTO;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities.EmployeeEntity;
import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.aspectj.util.Reflection;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id)
    {

        Optional<EmployeeEntity> employeeEntity=  employeeRepository.findById(id);

        return employeeEntity.map(employeeEntity1->modelMapper.map(employeeEntity1,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getEmployees() {
        List<EmployeeEntity> employeeEntityAllList= employeeRepository.findAll();
        return employeeEntityAllList
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO postEmployee(EmployeeDTO inputEmployee) {
      EmployeeEntity employeeEntity=  modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity=employeeRepository.save(employeeEntity);

      return  modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }



    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {

        //one way to it is this second way is you can return exception when no employee found
        //the other way we're as happening below we can make a new instance of employee for that and then add data to it
        employeeExistById(employeeId);

        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);

        //this is work as a way hashmap in java
        //if id is present it set to itself and if not it add a id in employeeEntity so that we can save
        employeeEntity.setId(employeeId);

       EmployeeEntity savedEmployeeEntity= employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
       employeeExistById(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;

        /*

        this given below is one way to delete but its slower compare to deleteById
        EmployeeEntity employeeEntity=  employeeRepository.findById(employeeId).orElse(null);
        if(employeeEntity!=null)
        {
            employeeRepository.delete(employeeEntity);
        }

         */

    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
       employeeExistById(employeeId);
        //we can use get here because we now findById(employeeId) is never going to be null
        EmployeeEntity employeeEntity=employeeRepository.findById(employeeId).get();


        //this will traverse the whole map and for one key value pair if will give key=fild and value
        updates.forEach((field,value) -> {

            //this will get the filed to update from the entity object using RelectionUtils(which type,filed to find)
            Field filedToBeUpdates= ReflectionUtils.findRequiredField(EmployeeEntity.class,field);

            //to update this filed we have setAccesible true
            filedToBeUpdates.setAccessible(true);

            //this is to set the filed (which filed to set,in which object,what is the value)
            ReflectionUtils.setField(filedToBeUpdates,employeeEntity,value);


        });
        EmployeeEntity newEmployeeEntity=employeeRepository.save(employeeEntity);
        return  modelMapper.map(newEmployeeEntity,EmployeeDTO.class);

    }

    public boolean employeeExistById(Long employeeId)
    {
        boolean isExist=employeeRepository.existsById(employeeId);
        if(!isExist)throw new ResourceNotFoundException("Resource Not Found Exception");


        return true;
    }
}
