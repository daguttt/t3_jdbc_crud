package org.example;

import org.example.entities.User;


public class AppState {

    public enum Status {
        RUNNING, CLOSED
    }

    private User user;
    private Status status;

    public AppState() {
        this.status = Status.RUNNING;
    }

    public User getUser() {
        return user;
    }

    public Status getStatus() {
        return status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
