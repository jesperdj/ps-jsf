package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.jesperdj.jsf.validator.PhoneNumber")
public class PhoneNumberValidator implements Validator {
    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberValidator.class);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        LOG.debug("validate: [{}]", value);

        PhoneNumber phoneNumber = (PhoneNumber) value;
        if (phoneNumber != null) {
            checkAreaCode(phoneNumber.getAreaCode());
            checkOfficeCode(phoneNumber.getOfficeCode());
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
