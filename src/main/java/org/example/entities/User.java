package org.example.entities;

import org.example.enums.Roles;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Roles role;

    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public User(Long id, String name, String email, String password, Roles role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Roles getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
