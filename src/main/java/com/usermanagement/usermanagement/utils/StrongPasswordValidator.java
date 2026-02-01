package com.usermanagement.usermanagement.utils;

import com.usermanagement.usermanagement.utils.customAnotations.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword,String> {
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) return true;
        return value.length() >= 8 &&
                value.matches(".*[A-Z].*") &&
                value.matches(".*[a-z].*") &&
                value.matches(".*\\d.*") &&
                value.matches(".*[@$!%*?&#].*");
    }

}
