package com.jesperdj.jsf.musicshop;

import javax.el.ELContext;
import javax.el.MethodExpression;
import javax.faces.component.FacesComponent;
import javax.faces.component.UINamingContainer;

@FacesComponent("com.jesperdj.jsf.component.SignIn")
public class SignInComponent extends UINamingContainer {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object submit() {
        MethodExpression expression = (MethodExpression) getAttributes().get("action");

        ELContext elContext = getFacesContext().getELContext();
        Object[] params = {username, password};

        return expression.invoke(elContext, params);
    }
}
