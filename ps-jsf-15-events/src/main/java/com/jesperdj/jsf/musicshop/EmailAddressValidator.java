package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAddressValidator implements ConstraintValidator<ValidEmailAddress, String> {
    private static final Logger LOG = LoggerFactory.getLogger(EmailAddressValidator.class);

    @Override
    public void initialize(ValidEmailAddress constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        LOG.debug("isValid: [{}]", value);

        return value == null || value.equals("") || value.contains("@");
    }
}
