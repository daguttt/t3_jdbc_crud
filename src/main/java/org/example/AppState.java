package org.example;

import org.example.entities.User;


public class AppState {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
