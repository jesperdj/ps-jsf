package com.jesperdj.jsf.musicshop;

import java.io.Serializable;

public class PhoneNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    private String areaCode;
    private String officeCode;
    private String subscriberNumber;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    public void setSubscriberNumber(String subscriberNumber) {
        this.subscriberNumber = subscriberNumber;
    }

    @Override
    public String toString() {
        return areaCode + "-" + officeCode + "-" + subscriberNumber;
    }
}
