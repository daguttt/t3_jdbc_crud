package org.example.entities;

import java.sql.Date;

public class Project {

    private Integer id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private User createdBy;

    public Project() {}

    public Project(String title, String description, Date startDate, Date endDate, User createdBy) {
        this(null, title, description, startDate, endDate, createdBy);
    }

    public Project(Integer id, String title, String description, Date startDate, Date endDate, User createdBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Título: %s
                Descripción: %s
                Fecha de inicio: %s
                Fecha de fin: %s
                Creado por: %s
                """,this.id, this.title, this.description, this.startDate, this.endDate, this.createdBy.getName());
    }
}
