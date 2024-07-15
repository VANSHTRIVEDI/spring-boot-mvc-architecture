package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        /*
        one way to do it
    * return s.equals("ADMIN") || s.equals("USER");
    */
       if(s==null)return  false;

       List<String> roles=List.of("ADMIN","USER");
        return roles.contains(s);
    }
}
