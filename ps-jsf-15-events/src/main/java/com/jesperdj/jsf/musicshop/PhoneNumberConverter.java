package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.Objects;
import java.util.regex.Pattern;

@FacesConverter(forClass = PhoneNumber.class)
public class PhoneNumberConverter implements Converter {
    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberConverter.class);

    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("[0-9]{3}-[0-9]{3}-[0-9]{4}");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOG.debug("getAsObject: [{}]", value);

        if (value == null || value.equals("")) {
            return null;
        }

        if (!PHONE_NUMBER_PATTERN.matcher(value).matches()) {
            throw new ConverterException(
                    new FacesMessage("Please enter a valid phone number of the form: 800-555-1234."));
        }

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setAreaCode(value.substring(0, 3));
        phoneNumber.setOfficeCode(value.substring(4, 7));
        phoneNumber.setSubscriberNumber(value.substring(8));

        return phoneNumber;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOG.debug("getAsString: [{}]", value);

        return Objects.toString(value);
    }
}
