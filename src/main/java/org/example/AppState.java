package org.example;

import org.example.entities.User;

import java.util.Optional;

public class AppState {

    private User user;
    private AppStatus status;

    public AppState() {
        this.status = AppStatus.RUNNING;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }
}
