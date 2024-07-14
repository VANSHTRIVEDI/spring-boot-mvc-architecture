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
    private  double salary;
    private LocalDate dateOfJoining;


    //if ever there is an error like active=null and you are not getting expected value
    //if may be because of jackson was unable to serial and deserialize the value properly
    //in that case we use @JsonProperty("active")  instead of active there will name of filed which was giving null
    //it tell json to use whole active as property
    //this should be done in both layers cause serial and deserialization happen in entity and dto both
    private  Boolean active;
    private  String role;
}
