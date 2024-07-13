package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import lombok.*;

import java.time.LocalDate;

//This is a pojo class (plain old java object)
//which is a simple java class with filed not related to a framework


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private  Long id;
    private  String name;
    private  String email;
    private  Integer age;
    private LocalDate dateOfJoining;
    private  Boolean active;
}
