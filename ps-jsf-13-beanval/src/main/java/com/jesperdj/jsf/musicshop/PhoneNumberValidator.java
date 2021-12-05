package com.jesperdj.jsf.musicshop;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.equals("")) {
            return checkPattern(value, context) &&
                    checkAreaCode(value.substring(0, 3), context) &&
                    checkOfficeCode(value.substring(4, 7), context);
        }

        return true;
    }

    private boolean checkPattern(String phoneNumber, ConstraintValidatorContext context) {
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please enter a valid phone number of the form: 800-555-1234.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean checkAreaCode(String areaCode, ConstraintValidatorContext context) {
        int firstDigit = Character.digit(areaCode.charAt(0), 10);
        if (firstDigit == 0 || firstDigit == 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The first digit of the area code of your phone number must not be 0 or 1.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    private boolean checkOfficeCode(String officeCode, ConstraintValidatorContext context) {
        int firstDigit = Character.digit(officeCode.charAt(0), 10);
        if (firstDigit == 0 || firstDigit == 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The first digit of the office code of your phone number must not be 0 or 1.")
                    .addConstraintViolation();
            return false;
        }

        int secondDigit = Character.digit(officeCode.charAt(1), 10);
        int thirdDigit = Character.digit(officeCode.charAt(2), 10);
        if (secondDigit == 1 && thirdDigit == 1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The second and third digit of the office code of your phone number must not both be 1.")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
