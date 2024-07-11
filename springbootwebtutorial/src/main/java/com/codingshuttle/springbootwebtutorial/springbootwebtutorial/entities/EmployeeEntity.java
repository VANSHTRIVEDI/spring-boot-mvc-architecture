package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
//this will tell spring boot that this is the class which you have to convert into table and hibernate will do that

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//using lombok
@Table(name = "employees")
//this will name the table as employees and if not then it will default  take class name as the table name here it will be EmployeeEntity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private  String name;
    private  String email;
    private  Integer age;
    private LocalDate dateOfJoining;
    private  Boolean active;
}
