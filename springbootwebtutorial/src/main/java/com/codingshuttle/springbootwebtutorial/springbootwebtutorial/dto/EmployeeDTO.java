package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.dto;

import com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.*;
import org.apache.logging.log4j.message.Message;

import java.time.LocalDate;

//This is a pojo class (plain old java object)
//which is a simple java class with filed not related to a framework


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private  Long id;

    @NotBlank(message="Name of the employee cannot be empty")
    @Size(min = 2,max = 10,message = "Number of character in should range {3,10}")
    private  String name;


    @Email
    private  String email;


    @Max(value = 90,message = "Age of employee cannot be more than 90 ")
    @Min(value = 18,message = "Age of employee cannot be less than 18")
    private  Integer age;

    @NotNull(message="Salary cannot be run")
    @Positive(message="Salary cannot be negative")
    @Digits(integer = 6,fraction = 2,message = "The salary can be in form XXXX.YY")
    @DecimalMin(value = "100.00")
    @DecimalMax(value="100000.99")
    private  double salary;


    @PastOrPresent(message = "dateOfJoining cannot be in future")
    private LocalDate dateOfJoining;


    @AssertTrue(message = "Employee should be active")
    private  Boolean active;

    @NotNull(message = "Name of the employee cannot be null")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Role of Employee can either be USER or ADMIN")
    @EmployeeRoleValidation
    private  String role;
}
