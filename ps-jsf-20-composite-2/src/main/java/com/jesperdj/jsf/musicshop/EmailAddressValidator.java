package com.jesperdj.jsf.musicshop;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAddressValidator implements ConstraintValidator<ValidEmailAddress, String> {

    @Override
    public void initialize(ValidEmailAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.equals("") || value.contains("@");
    }
}
