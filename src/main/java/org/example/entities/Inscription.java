package org.example.entities;

import java.sql.Date;

public class Inscription {
    private Integer id;
    private User user;
    private Project project;
    private Date date;

    public Inscription() {}

    public Inscription(User user, Project project, Date date) {
        this(null, user, project, date);
    }

    public Inscription(Integer id, User user, Project project, Date date) {
        this.id = id;
        this.user = user;
        this.project = project;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Project getProject() {
        return project;
    }

    public Date getDate() {
        return date;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
