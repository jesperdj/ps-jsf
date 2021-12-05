package com.jesperdj.jsf.musicshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Named
@RequestScoped
public class SignIn {
    private static final Logger LOG = LoggerFactory.getLogger(SignIn.class);

    @Inject
    private UserManager userManager;

    @Pattern(regexp = "[A-Za-z0-9]{2,20}", message = "Please enter a username consisting of only letters and digits, between 2 and 20 characters long.")
    private String username;

    @Size(min = 8, message = "Your password must consist of at least 8 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        LOG.debug("Set username: [{}]", username);
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        LOG.debug("Set password: ********");
        this.password = password;
    }

    public String submit() {
        LOG.debug("Submit action - signing in user [{}]", username);
        return userManager.signIn(username, password);
    }
}
