package com.jesperdj.jsf.musicshop.checkout;

import com.jesperdj.jsf.musicshop.ShoppingCart;
import com.jesperdj.jsf.musicshop.User;
import com.jesperdj.jsf.musicshop.UserManager;

import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@FlowScoped("checkout")
public class Checkout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 30, message = "Please enter a name.")
    private String shippingName;

    @Size(min = 1, max = 30, message = "Please enter an address.")
    private String shippingAddress;

    @Size(min = 1, max = 30, message = "Please enter a city.")
    private String shippingCity;

    @Size(min = 1, max = 30, message = "Please enter a name.")
    private String paymentName;

    @NotNull(message = "Please select a credit card type.")
    private CreditCardType creditCardType;

    @Pattern(regexp = "[0-9]{16}", message = "Please enter a valid credit card number.")
    private String creditCardNumber;

    @Inject
    private UserManager userManager;

    @Inject
    private ShoppingCart shoppingCart;

    private boolean paymentSuccess;

    @PostConstruct
    public void initialize() {
        if (userManager.isSignedIn()) {
            User user = userManager.getCurrentUser();
            shippingName = user.getFirstName() + " " + user.getLastName();
            paymentName = shippingName;
        }
    }

    public void fulfillOrder() {
        paymentSuccess = !creditCardNumber.endsWith("00");
    }

    public String finishOrder() {
        if (paymentSuccess) {
            shoppingCart.empty();
        }

        return "finished";
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public boolean isPaymentSuccess() {
        return paymentSuccess;
    }

    public void setPaymentSuccess(boolean paymentSuccess) {
        this.paymentSuccess = paymentSuccess;
    }
}
