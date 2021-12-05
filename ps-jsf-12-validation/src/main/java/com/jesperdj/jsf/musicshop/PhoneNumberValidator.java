package com.jesperdj.jsf.musicshop;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("com.jesperdj.jsf.PhoneNumber")
public class PhoneNumberValidator implements Validator {

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String phoneNumber = (String) value;
        if (phoneNumber != null && !phoneNumber.equals("")) {
            checkPattern(phoneNumber);
            checkAreaCode(phoneNumber.substring(0, 3));
            checkOfficeCode(phoneNumber.substring(4, 7));
        }
    }

    private void checkPattern(String phoneNumber) {
        if (!PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches()) {
            throw new ValidatorException(
                    new FacesMessage("Please enter a valid phone number of the form: 800-555-1234."));
        }
    }

    private void checkAreaCode(String areaCode) {
        int firstDigit = Character.digit(areaCode.charAt(0), 10);
        if (firstDigit == 0 || firstDigit == 1) {
            throw new ValidatorException(
                    new FacesMessage("The first digit of the area code of your phone number must not be 0 or 1."));
        }
    }

    private void checkOfficeCode(String officeCode) {
        int firstDigit = Character.digit(officeCode.charAt(0), 10);
        if (firstDigit == 0 || firstDigit == 1) {
            throw new ValidatorException(
                    new FacesMessage("The first digit of the office code of your phone number must not be 0 or 1."));
        }

        int secondDigit = Character.digit(officeCode.charAt(1), 10);
        int thirdDigit = Character.digit(officeCode.charAt(2), 10);
        if (secondDigit == 1 && thirdDigit == 1) {
            throw new ValidatorException(
                    new FacesMessage("The second and third digit of the office code of your phone number must not both be 1."));
        }
    }
}
