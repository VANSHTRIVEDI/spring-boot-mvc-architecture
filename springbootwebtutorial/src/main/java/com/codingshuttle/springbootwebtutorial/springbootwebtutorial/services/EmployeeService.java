package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.services;

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

        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);

        //this is work as a way hashmap in java
        //if id is present it set to itself and if not it add a id in employeeEntity so that we can save
        employeeEntity.setId(employeeId);

       EmployeeEntity savedEmployeeEntity= employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {

        boolean idExist=employeeExistById(employeeId);
         if(idExist)
         {
             employeeRepository.deleteById(employeeId);
             return true;
         }

         return  false;




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
        boolean idExist=employeeExistById(employeeId);
        if(!idExist)return  null;

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

        return employeeRepository.existsById(employeeId);
    }
}
