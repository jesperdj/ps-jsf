package com.jesperdj.jsf.musicshop;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserService userService;

    private User currentUser;

    public boolean isSignedIn() {
        return currentUser != null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public String signIn(String username, String password) {
        User user = userService.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            return "sign-in";
        }

        currentUser = user;
        return "index";
    }

    public String signOut() {
        // End the session, removing any session state, including the current user and content of the shopping cart
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        // Redirect is necessary to let the browser make a new GET request
        return "index?faces-redirect=true";
    }

    public String save(User user) {
        userService.saveUser(user);
        currentUser = user;
        return "index";
    }
}
