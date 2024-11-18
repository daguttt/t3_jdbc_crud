package org.example.entities;

import java.sql.Date;
import java.time.LocalDate;

public class Project {

    private int id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private int createdById;

    public Project() {}

    public Project(String title, String description, Date startDate, Date endDate, int createdById) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdById = createdById;
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

    public int getCreatedById() {
        return createdById;
    }

    public void setId(int id) {
        this.id = id;
    }
}
