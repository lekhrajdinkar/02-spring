package com.lekhraj.java.spring.SB_99_RESTful_API.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameCheckAnnotation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Implement custom validation logic
        // return value != null && value.matches("custom-regex-pattern");
        return true;
    }
}
